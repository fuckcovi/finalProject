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
	
	public BaseCommand updateBase(int b_seq);
	public BasketCommand updateBasket(int b_seq);
	public FootCommand updateFoot(int f_seq);
	
	@Transactional(readOnly=true)
	public BaseCommand selectBase(int b_seq);
	@Transactional(readOnly=true)
	public BasketCommand selectBasket(int b_seq);
	@Transactional(readOnly=true)
	public FootCommand selectFoot(int f_seq);
	@Transactional(readOnly=true)
	public List<BaseCommand> listBase();
	@Transactional(readOnly=true)
	public List<BasketCommand> listBasket();
	@Transactional(readOnly=true)
	public List<FootCommand> listFoot();
}