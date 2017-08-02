package com.kh.mixmatch.team.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
@Transactional
public interface TotalTypeService {
	public void insertBase(BaseCommand base);
	public void insertBasket(BasketCommand basket);
	public void insertFoot(FootCommand foot);
	
	public void deleteBase(int b_seq);
	public void deleteBasket(int b_seq);
	public void deleteFoot(int f_seq);
	
	public void updateBase(BaseCommand base);
	public void updateBasket(BasketCommand basket);
	public void updateFoot(FootCommand foot);
	
	@Transactional(readOnly=true)
	public BaseCommand selectBase(int b_seq);
	@Transactional(readOnly=true)
	public BasketCommand selectBasket(int b_seq);
	@Transactional(readOnly=true)
	public FootCommand selectFoot(int f_seq);
	@Transactional(readOnly=true)
	public List<BaseCommand> listBase(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<BasketCommand> listBasket(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<FootCommand> listFoot(Map<String, Object> map);
}
