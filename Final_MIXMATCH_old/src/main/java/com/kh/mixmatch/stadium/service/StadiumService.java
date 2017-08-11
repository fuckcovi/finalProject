package com.kh.mixmatch.stadium.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;

@Transactional
public interface StadiumService {
	// 寃쎄린�옣 �벑濡�
	public void insertStadium(StadiumCommand stadium);
	public void updateStadium(StadiumCommand stadium);
	public void deleteStadium(Integer s_seq);
	@Transactional(readOnly=true)
	public StadiumCommand selectStadium(Integer s_seq);	// 1媛� 寃쎄린�옣
	@Transactional(readOnly=true)
	public List<StadiumCommand> listStadium(Map<String, Object> map);
	@Transactional(readOnly=true)
	public int getTotalCountStadium(Map<String, Object> map);

	
	// �삁�빟 
	public void insertBooking(BookingCommand booking);
	public void updateBooking(BookingCommand booking);
	public void deleteBooking(Integer b_seq);
	@Transactional(readOnly=true)
	public BookingCommand selectBooking(Integer b_seq); // 1媛� �삁�빟 �젙蹂�
	@Transactional(readOnly=true)
	public List<BookingCommand> listBooking(Map<String, Object> map);	// 해당구장 예약 리스트
	@Transactional(readOnly=true)
	public int getTotalCountBooking(Map<String, Object> map);
}
