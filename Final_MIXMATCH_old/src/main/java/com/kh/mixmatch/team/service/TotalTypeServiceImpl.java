package com.kh.mixmatch.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.team.dao.TotalTypeMapper;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;

@Service("totalTypeService")
public class TotalTypeServiceImpl implements TotalTypeService{

	@Resource
	private TotalTypeMapper totalTypeMapper;
	
	@Override
	public void insertBase(BaseCommand base) {
		totalTypeMapper.insertBase(base);
	}

	@Override
	public void insertBasket(BasketCommand basket) {
		totalTypeMapper.insertBasket(basket);
	}

	@Override
	public void insertFoot(FootCommand foot) {
		totalTypeMapper.insertFoot(foot);
	}

	@Override
	public void deleteBase(int b_seq) {
		totalTypeMapper.deleteBase(b_seq);
	}

	@Override
	public void deleteBasket(int b_seq) {
		totalTypeMapper.deleteBasket(b_seq);
	}

	@Override
	public void deleteFoot(int f_seq) {
		totalTypeMapper.deleteFoot(f_seq);
	}

	@Override
	public void updateBase(BaseCommand base) {
		totalTypeMapper.updateBase(base);
	}

	@Override
	public void updateBasket(BasketCommand basket) {
		totalTypeMapper.updateBasket(basket);
	}

	@Override
	public void updateFoot(FootCommand foot) {
		totalTypeMapper.updateFoot(foot);
	}

	@Override
	public BaseCommand selectBase(int b_seq) {
		return totalTypeMapper.selectBase(b_seq);
	}

	@Override
	public BasketCommand selectBasket(int b_seq) {
		return totalTypeMapper.selectBasket(b_seq);
	}

	@Override
	public FootCommand selectFoot(int f_seq) {
		return totalTypeMapper.selectFoot(f_seq);
	}

	@Override
	public List<BaseCommand> listBase(Map<String, Object> map) {
		return totalTypeMapper.listBase(map);
	}

	@Override
	public List<BasketCommand> listBasket(Map<String, Object> map) {
		return totalTypeMapper.listBasket(map);
	}

	@Override
	public List<FootCommand> listFoot(Map<String, Object> map) {
		return totalTypeMapper.listFoot(map);
	}
	
}
