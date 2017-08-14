package com.kh.mixmatch.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;

public interface TotalTypeMapper {
	@Insert("INSERT INTO g_baseball(b_seq,id,t_name,m_seq,b_bat,b_hit,b_rbi,b_score,b_avg,b_win,b_lose,b_strike,b_ip,b_er,b_era) VALUES(f_baseball_seq.nextval,#{id},#{t_name},#{m_seq},#{b_bat},#{b_hit},#{b_rbi},#{b_score},NVL(#{b_hit}/DECODE(#{b_bat},0,NULL,#{b_bat}),0),#{b_win},#{b_lose},#{b_strike},#{b_ip},#{b_er},NVL((#{b_er}*9)/DECODE(#{b_ip},0,NULL,#{b_ip}),0))")
	public void insertBase(BaseCommand base);
	@Insert("INSERT INTO g_basketball(b_seq,id,t_name,m_seq,b_score,b_assist,b_rebound,b_steel,b_block,b_3point) VALUES(f_basketball_seq.nextval,#{id},#{t_name},#{m_seq},#{b_score},#{b_assist},#{b_rebound},#{b_steel},#{b_block},#{b_3point})")
	public void insertBasket(BasketCommand basket);
	@Insert("INSERT INTO g_football(f_seq,id,t_name,m_seq,f_shoot,f_assist,f_goal,f_attack) VALUES(f_football_seq.nextval,#{id},#{t_name},#{m_seq},#{f_shoot},#{f_assist},#{f_goal},#{f_attack})")
	public void insertFoot(FootCommand foot);
	
	public void deleteBase(Integer b_seq);
	public void deleteBasket(Integer b_seq);
	public void deleteFoot(Integer f_seq);
	
	@Update("UPDATE g_baseball SET b_bat=#{b_bat},b_hit=#{b_hit},b_rbi=#{b_rbi},b_score=#{b_score},b_avg=NVL(#{b_hit}/DECODE(#{b_bat},0,NULL,#{b_bat}),0),b_win=#{b_win},b_lose=#{b_lose},b_strike=#{b_strike},b_ip=#{b_ip},b_er=#{b_er},b_era=NVL((#{b_er}*9)/DECODE(#{b_ip},0,NULL,#{b_ip}),0) WHERE b_seq=#{b_seq}")
	public void updateBase(BaseCommand base);
	@Update("UPDATE g_basketball SET b_score=#{b_score},b_assist=#{b_assist},b_rebound=#{b_rebound},b_steel=#{b_steel},b_block=#{b_block},b_3point=#{b_3point} WHERE b_seq=#{b_seq}")
	public void updateBasket(BasketCommand basket);
	@Update("UPDATE g_football SET f_shoot=#{f_shoot},f_assist=#{f_assist},f_goal=#{f_goal},f_attack=#{f_attack} WHERE f_seq=#{f_seq}")
	public void updateFoot(FootCommand foot);
	
	public BaseCommand selectBase(Integer b_seq);
	public BasketCommand selectBasket(Integer b_seq);
	public FootCommand selectFoot(Integer f_seq);
	
	// 개인별 통합랭킹 리스트
	public List<BaseCommand> listBase(Map<String, Object> map);
	public List<BasketCommand> listBasket(Map<String, Object> map);	
	public List<FootCommand> listFoot(Map<String, Object> map);
	
}
