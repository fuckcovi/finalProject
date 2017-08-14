package com.kh.mixmatch.stadium.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

public interface StadiumMapper {
	
	@Insert("INSERT INTO g_stadium(s_seq,s_type,s_name,s_address1,s_address2,s_logo_name,s_logo,s_regdate) VALUES(g_stadium_seq.nextval,#{s_type},#{s_name},#{s_address1},#{s_address2},#{s_logo_name,jdbcType=VARCHAR},#{s_logo,jdbcType=BLOB},sysdate)")
	public void insertStadium(StadiumCommand stadium);
	@Update("UPDATE g_stadium SET s_type=#{s_type},s_name=#{s_name},s_address1=#{s_address1},s_address2=#{s_address2},s_logo_name=#{s_logo_name,jdbcType=VARCHAR},s_logo=#{s_logo,jdbcType=BLOB} WHERE s_seq=#{s_seq}")
	public void updateStadium(StadiumCommand stadium);
	@Delete("DELETE FROM g_stadium WHERE s_seq=#{s_seq}")
	public void deleteStadium(Integer s_seq);
	@Select("SELECT * FROM g_stadium WHERE s_seq=#{s_seq}")
	public StadiumCommand selectStadium(Integer s_seq);
	
	public List<StadiumCommand> listStadium(Map<String, Object> map);
	public int getTotalCountStadium(Map<String, Object> map);
	
	@Insert("INSERT INTO g_booking(b_seq,b_regdate,b_time,b_check,t_name,s_seq) VALUES(g_booking_seq.nextval,#{b_regdate},#{b_time},1,#{t_name},#{s_seq})")
	public void insertBooking(BookingCommand booking);
	@Update("UPDATE g_booking SET b_time=#{b_time},b_regdate=#{b_regdate} WHERE b_seq=#{b_seq}")
	public void updateBooking(BookingCommand booking);
	@Delete("DELETE FROM g_booking WHERE b_seq=#{b_seq}")
	public void deleteBooking(Integer b_seq);
	public BookingCommand selectBooking(Integer b_seq);
	
	
	// 해당 예약 확인 
	@Update("UPDATE g_booking SET b_check=2 WHERE b_seq=#{b_seq}")
	public void updateCheckBooking(Integer b_seq);
	// 팀마스터인 팀의 경기장 예약 리스트
	@Select("SELECT b.* FROM g_booking b ,(SELECT * FROM g_team WHERE id=#{id})m where b.t_name=m.t_name")
	public List<BookingCommand> listBookingTeam(String id);
	@Select("SELECT COUNT(*) FROM g_booking WHERE t_name=#{t_name}")
	public int booklistCount(String t_name);
	
	public List<BookingCommand> listBooking(Map<String, Object> map);	// 해당구장 예약 리스트
	public int getTotalCountBooking(Map<String, Object> map);	// 해당 구장 예약갯수
}
