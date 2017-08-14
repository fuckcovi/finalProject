package com.kh.mixmatch.pointshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCartCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCommand;

public interface PointShopMapper {

	
	//상품목록정보
	public List<PointShopCommand> list(Map<String,Object> map);
	public int getRowCount(Map<String,Object> map);
	@Insert("INSERT INTO g_point_product(p_seq,p_grade,p_name,p_context,p_price,upload_file,p_file_name,id) "
			+ "VALUES(g_point_product_seq.nextval,#{p_grade},#{p_name},#{p_context},#{p_price},#{upload_file},#{p_file_name},#{id}) ")
	public void insert(PointShopCommand point);
	@Select("SELECT * FROM g_point_product WHERE p_seq=#{p_seq}")
	public PointShopCommand selectBoard(Integer p_seq);
	@Update("UPDATE g_point_product SET p_name=#{p_name}, p_grade=#{p_grade}, p_price=#{p_price}, p_context=#{p_context} where p_seq=#{p_seq}")
	public void update(PointShopCommand point);
	@Delete("delete from g_point_product where p_seq=#{p_seq}")
	public void delete(Integer p_seq);
	
	//구매내역
	@Select("SELECT * FROM g_pointshop_cart ORDER BY p_date desc")
	public List<PointShopCartCommand> cart();
	//구매등록
	@Insert("INSERT INTO g_pointshop_cart (p_seq,id,p_name,amount,p_price,p_date) VALUES(g_pointshop_cart_seq.nextval,#{id},#{p_name},#{amount},#{p_price},sysdate)")
	public void cart_insert(PointShopCartCommand pointcart);
	//구매 시 포인트 차감
	@Update("UPDATE g_member SET point = point-${p_price} WHERE id=#{id}")
	public void point_update(Map<String,Object> map);
	
	
	//회원등급 검색
	@Select("SELECT * FROM g_member WHERE id=#{id}")
	public MemberCommand selectMemberInfo(String id);
	
}
