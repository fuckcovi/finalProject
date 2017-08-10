package com.kh.mixmatch.stadium.service;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;

@Transactional
public interface StadiumService {
	// 경기장 등록
	public void insertStadium(StadiumCommand stadium);
	public void updateStadium(StadiumCommand stadium);
	public void deleteStadium(Integer s_seq);
	@Transactional(readOnly=true)
	public StadiumCommand selectStadium(Integer s_seq);	// 1개 경기장
	
	
	// 예약 
	public void insertBooking(BookingCommand booking);
	public void updateBooking(BookingCommand booking);
	public void deleteBooking(Integer b_seq);
	@Transactional(readOnly=true)
	public BookingCommand selectBooking(Integer b_seq); // 1개 예약 정보
}
