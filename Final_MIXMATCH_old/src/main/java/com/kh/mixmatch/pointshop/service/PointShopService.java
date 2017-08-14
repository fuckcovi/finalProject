package com.kh.mixmatch.pointshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCartCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCommand;

@Transactional
public interface PointShopService {

	@Transactional(readOnly=true)
	public List<PointShopCommand> list(Map<String,Object> map);
	
	@Transactional(readOnly=true)
	public int getRowCount(Map<String,Object> map);
	
	public void insert(PointShopCommand board);
	@Transactional(readOnly=true)
	public PointShopCommand selectBoard(Integer p_seq);
	public void update(PointShopCommand point);
	public void delete(Integer p_seq);
	
	@Transactional(readOnly=true)
	public List<PointShopCartCommand> cart();
	public void cart_insert(PointShopCartCommand pointcart);
	public void point_update(Map<String,Object> map);
	
	//회원등급 검색
	@Transactional(readOnly=true)
	public MemberCommand selectMemberInfo(String id);
}
