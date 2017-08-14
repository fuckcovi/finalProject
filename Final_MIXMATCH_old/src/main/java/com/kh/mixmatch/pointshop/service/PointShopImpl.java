package com.kh.mixmatch.pointshop.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.pointshop.dao.PointShopMapper;
import com.kh.mixmatch.pointshop.domain.PointShopCartCommand;
import com.kh.mixmatch.pointshop.domain.PointShopCommand;

@Service("pointShopService")
public class PointShopImpl implements PointShopService {

	@Resource
	private PointShopMapper pointShopMapper;
	
	@Override
	public List<PointShopCommand> list(Map<String, Object> map) {
		return pointShopMapper.list(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return pointShopMapper.getRowCount(map);
	}

	@Override
	public void insert(PointShopCommand point) {
		pointShopMapper.insert(point);
	}

	@Override
	public PointShopCommand selectBoard(Integer p_seq) {
		return pointShopMapper.selectBoard(p_seq);
	}

	@Override
	public void update(PointShopCommand point) {
		pointShopMapper.update(point);
		
	}

	@Override
	public void delete(Integer p_seq) {
		pointShopMapper.delete(p_seq);
	}

	@Override
	public void cart_insert(PointShopCartCommand pointcart) {
		pointShopMapper.cart_insert(pointcart);
		
	}

	@Override
	public MemberCommand selectMemberInfo(String id) {
		return pointShopMapper.selectMemberInfo(id);
	}

	@Override
	public void point_update(Map<String, Object> map) {
		pointShopMapper.point_update(map);
	}

	@Override
	public List<PointShopCartCommand> cart() {
		
		return pointShopMapper.cart();
	}

}
