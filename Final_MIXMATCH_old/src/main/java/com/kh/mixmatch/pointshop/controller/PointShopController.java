package com.kh.mixmatch.pointshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCartCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCommand;
import com.kh.mixmatch.pointshop.service.PointShopService;
import com.kh.mixmatch.util.PagingUtil;


@Controller
public class PointShopController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 5;
	private int pageCount = 5;
	
	@Resource
	private PointShopService pointShopService;
	
	//�ڹٺ��ʱ�ȭ
	@ModelAttribute("pointShopCommand")
	public PointShopCommand initCommand(){
		return new PointShopCommand();
	}
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("pointShopCartCommand")
	public PointShopCartCommand initcommand(){
		return new PointShopCartCommand();
	}	
	
	//����Ʈ�� ����������
	@RequestMapping("/point/pointHome.do")
	public ModelAndView pointHome(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword){
			
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword.toUpperCase());
		
		int count = pointShopService.getRowCount(map);
		
		if(log.isDebugEnabled()){
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,rowCount,pageCount,"pointHome.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<PointShopCommand> list =null;
		if(count > 0){
			list = pointShopService.list(map);
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("PointHome");
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	
	}
	
		////��ǰ����
	@RequestMapping("/point/delete.do")
	public String pointDelete(@RequestParam("p_seq") int p_seq){
		pointShopService.delete(p_seq);
		return "redirect:/point/pointHome.do";
	}
	
//////////////////////////////////////////////////////////////
	
	//���ų���
	@RequestMapping("point/pointShopCart.do")
	public String pointshopcart(Model model){
		
		
		List<PointShopCartCommand> cart;
		
		cart = pointShopService.cart();
		
		model.addAttribute("cart", cart);
		
		return "PointCart";
	}
	//��ǰ������////////////////////////////////////////////////////////
	
	
	@RequestMapping(value="/point/pointShopDetail.do",method=RequestMethod.GET)
	public String pointshopDetail(@RequestParam("p_seq") int p_seq,
			HttpSession session,Model model){
		if(log.isDebugEnabled()){
			log.debug("<<p_seq>> : " + p_seq);
		}
		
		PointShopCommand product = pointShopService.selectBoard(p_seq);
		model.addAttribute("product", product);
		
		return "PointDetail";
	}
	//��ǰ����
	@RequestMapping(value="/point/purchase.do")
	@ResponseBody
	public Map<String,Object> purchaseAjaxAction(HttpSession session,@RequestParam("p_name")String p_name,@RequestParam("p_price")int p_price,
			@RequestParam("p_grade")String p_grade, @RequestParam("amount")int amount){
		
		if(log.isDebugEnabled()){
			log.debug("p_name : " + p_name);
			log.debug("p_price : " + p_price);
			log.debug("p_grade : " + p_grade);
			log.debug("amount : " + amount);
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		MemberCommand memberCommand = pointShopService.selectMemberInfo(user_id);
		
		if(memberCommand.getAuth().toUpperCase().equals("BRONZE")){
			if(!p_grade.toUpperCase().equals("BRONZE")){
				mapJson.put("grade", "등급 부족.");
			}
		}else if(memberCommand.getAuth().toUpperCase().equals("SILVER")){
			if(!p_grade.toUpperCase().equals("BRONZE") || !p_grade.toUpperCase().equals("SILVER")){
				mapJson.put("grade", "등급 부족.");
			}
		}else if(memberCommand.getAuth().toUpperCase().equals("GOLD")){
			if(p_grade.toUpperCase().equals("DIAMOND") || p_grade.toUpperCase().equals("PLATINUM")){
				mapJson.put("grade", "등급 부족.");
			}
		}else if(memberCommand.getAuth().toUpperCase().equals("PLATINUM")){
			if(p_grade.toUpperCase().equals("DIAMOND")){
				mapJson.put("grade", "등급 부족.");
			}
		}
		
		int total_price = p_price * amount;
		
		if(total_price > memberCommand.getPoint()){
			mapJson.put("point", "포인트 부족.");
		}else{
			Map<String,Object> map = new HashMap<String, Object>();
			
			PointShopCartCommand pointShopCartCommand = new PointShopCartCommand();
			
			pointShopCartCommand.setId(user_id);
			pointShopCartCommand.setP_name(p_name);
			pointShopCartCommand.setAmount(amount);
			pointShopCartCommand.setP_price(total_price);
			
			pointShopService.cart_insert(pointShopCartCommand);
			
			map.put("p_price", total_price);
			map.put("id", user_id);
			pointShopService.point_update(map);
			
		}
		
		System.out.println(mapJson);
		
		return mapJson;
	}
	
	
	//��ǰ����///////////////////////////////
	@RequestMapping(value="/point/PointUpdate.do",method=RequestMethod.GET)
	public String pointFormUpdate(@RequestParam("p_seq") int p_seq,
	Model model){
	
		PointShopCommand product = pointShopService.selectBoard(p_seq);
		model.addAttribute("product", product);
	
		return "PointUpdate";
	}
	@RequestMapping(value="/point/PointUpdate.do",method=RequestMethod.POST)
	public String pointActionUpdate(@ModelAttribute("pointShopCommand")
	@Valid PointShopCommand pointShopCommand, 
	BindingResult result, HttpSession session
			){
		
		pointShopService.update(pointShopCommand);
		
		return "redirect:/point/pointHome.do";
	}
	
	
	
	//��ǰ���///////////////////////////////////////////////////////////
	@RequestMapping(value="/point/pointWrite.do",method=RequestMethod.GET)
	public String pointWrite(){
		return "PointWrite";
	}
	
	@RequestMapping(value="/point/pointWrite.do",method=RequestMethod.POST)
	public String pointWriteAction(@ModelAttribute("pointShopCommand")
	@Valid PointShopCommand pointShopCommand, 
	BindingResult result, HttpSession session){
		
		
		if(log.isDebugEnabled()){
			log.debug("<<pointShopCommand>> : " + pointShopCommand);
		}
	
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "PointWrite";
		}
		
		pointShopCommand.setId((String)session.getAttribute("user_id"));
		
		pointShopService.insert(pointShopCommand);
		
		return "redirect:/point/pointHome.do";
	}
	
	//�̹��� ���
		@RequestMapping("/point/imageView.do")
		public ModelAndView viewImage(@RequestParam("p_seq") int p_seq){
			
			PointShopCommand point = pointShopService.selectBoard(p_seq);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			mav.addObject("imageFile", point.getUpload_file());
			mav.addObject("filename", point.getP_file_name());
			
			return mav;
		}
	
	
}
