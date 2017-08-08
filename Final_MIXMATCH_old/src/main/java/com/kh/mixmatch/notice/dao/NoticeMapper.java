package com.kh.mixmatch.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.notice.domain.NoticeCommand;


public interface NoticeMapper {
	@Insert("INSERT INTO g_notice (n_seq,n_title,n_content,n_hit,n_file,n_file_name,n_regdate,id) VALUES(g_notice_seq.nextval,#{n_title},#{n_content},#{n_hit},#{n_file,jdbcType=BLOB},#{n_file_name,jdbcType=VARCHAR},sysdate,#{id})")
	public void noticeInsert(NoticeCommand notice);
	@Delete("DELETE FROM g_notice WHERE n_seq=#{n_seq}")
	public void noticeDelete(Integer n_seq);
	@Update("UPDATE g_notice SET n_title=#{n_title},n_content=#{n_content},n_file=#{n_file,jdbcType=BLOB},n_file_name=#{n_file_name,jdbcType=VARCHAR} WHERE n_seq=#{n_seq}")
	public NoticeCommand noticeUpdate(NoticeCommand notice);
	@Update("UPDATE g_notice SET n_hit=n_hit+1 WHERE n_seq=#{n_seq}")
	public NoticeCommand noticeUpdateHit(Integer n_seq);
	@Select("SELECT * FROM g_notice WHERE n_seq=#{n_seq}")
	public NoticeCommand noticeSelect(Integer n_seq);
	@Select("SELECT COUNT(*) FROM g_notice")
	public int getRowCount(Map<String, Object> map);
	public List<NoticeCommand> noticeList(Map<String, Object> map);
}
