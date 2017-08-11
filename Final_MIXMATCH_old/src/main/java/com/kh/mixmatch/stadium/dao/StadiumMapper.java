package com.kh.mixmatch.stadium.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;

public interface StadiumMapper {
	
	// 寃쎄린�옣 �벑濡�
	@Insert("INSERT INTO g_stadium(s_seq,s_type,s_name,s_address1,s_address2,s_logo_name,s_logo,s_regdate) VALUES(g_stadium_seq.nextval,#{s_type},#{s_name},#{s_address1},#{s_address2},#{s_logo_name,jdbcType=VARCHAR},#{s_logo,jdbcType=BLOB},sysdate)")
	public void insertStadium(StadiumCommand stadium);
	public void updateStadium(StadiumCommand stadium);
	public void deleteStadium(Integer s_seq);
	@Select("SELECT * FROM g_stadium WHERE s_seq=#{s_seq}")
	public StadiumCommand selectStadium(Integer s_seq);	// 1媛� 寃쎄린�옣
	
	public List<StadiumCommand> listStadium(Map<String, Object> map); // 珥� 寃쎄린�옣 紐⑸줉
	public int getTotalCountStadium(Map<String, Object> map);// 珥앷꼍湲곗옣 媛��닔
	
	// �삁�빟 
	public void insertBooking(BookingCommand booking);
	public void updateBooking(BookingCommand booking);
	public void deleteBooking(Integer b_seq);
	public BookingCommand selectBooking(Integer b_seq); // 1媛� �삁�빟 �젙蹂�
	
	public List<BookingCommand> listBooking(Map<String, Object> map);	// 해당구장 예약 리스트
	public int getTotalCountBooking(Map<String, Object> map);	// 해당 구장 예약갯수
}
