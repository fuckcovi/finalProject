package com.kh.mixmatch.stadium.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<StadiumCommand> listStadium(Map<String, Object> map) {
		return stadiumMapper.listStadium(map);
	}

	@Override
	public int getTotalCountStadium(Map<String, Object> map) {
		return stadiumMapper.getTotalCountStadium(map);
	}

	@Override
	public List<BookingCommand> listBooking(Map<String, Object> map) {
		return stadiumMapper.listBooking(map);
	}

	@Override
	public int getTotalCountBooking(Map<String, Object> map) {
		return stadiumMapper.getTotalCountBooking(map);
	}

	@Override
	public List<BookingCommand> listBookingTeam(String id) {
		// TODO Auto-generated method stub
		return stadiumMapper.listBookingTeam(id);
	}

	@Override
	public int booklistCount(String t_name) {
		// TODO Auto-generated method stub
		return stadiumMapper.booklistCount(t_name);
	}

	@Override
	public void updateCheckBooking(Integer b_seq) {
		stadiumMapper.updateCheckBooking(b_seq);
		
	}
	
}
