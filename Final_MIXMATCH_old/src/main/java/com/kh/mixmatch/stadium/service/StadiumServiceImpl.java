package com.kh.mixmatch.stadium.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.stadium.dao.StadiumMapper;
import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;

@Service("stadiumService")
public class StadiumServiceImpl implements StadiumService{

	@Resource
	private StadiumMapper stadiumMapper;
	
	@Override
	public void insertStadium(StadiumCommand stadium) {
		stadiumMapper.insertStadium(stadium);
	}

	@Override
	public void updateStadium(StadiumCommand stadium) {
		stadiumMapper.updateStadium(stadium);
	}

	@Override
	public void deleteStadium(Integer s_seq) {
		stadiumMapper.deleteStadium(s_seq);
	}

	@Override
	public StadiumCommand selectStadium(Integer s_seq) {
		return stadiumMapper.selectStadium(s_seq);
	}

	@Override
	public void insertBooking(BookingCommand booking) {
		stadiumMapper.insertBooking(booking);
	}

	@Override
	public void updateBooking(BookingCommand booking) {
		stadiumMapper.updateBooking(booking);
	}

	@Override
	public void deleteBooking(Integer b_seq) {
		stadiumMapper.deleteBooking(b_seq);
	}

	@Override
	public BookingCommand selectBooking(Integer b_seq) {
		return stadiumMapper.selectBooking(b_seq);
	}
	
}
