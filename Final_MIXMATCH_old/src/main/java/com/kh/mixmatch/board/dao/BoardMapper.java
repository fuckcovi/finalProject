package com.kh.mixmatch.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.board.domain.GBoardCommand;
import com.kh.mixmatch.board.domain.GBoardReplyCommand;
import com.kh.mixmatch.board.domain.GNoticeCommand;
import com.kh.mixmatch.board.domain.GNoticeReplyCommand;
import com.kh.mixmatch.board.domain.GTBoardCommand;
import com.kh.mixmatch.board.domain.GTBoardReplyCommand;



public interface BoardMapper {
		//공지사항
		public List<GNoticeCommand> GNlist(Map<String,Object> map);
		public int GNgetRowCount(Map<String,Object> map);
		@Insert("INSERT INTO g_notice(gn_seq,gn_title,gn_content,gn_regdate,gn_uploadfile,gn_filename,ip,id) VALUES (g_notice_seq.nextval,#{gn_title},#{gn_content},sysdate,#{gn_uploadfile,jdbcType=BLOB},#{gn_filename,jdbcType=VARCHAR},#{ip},#{id})")
		public void GNinsert(GNoticeCommand board);
		@Select("SELECT * FROM g_notice WHERE gn_seq=#{gn_seq}")
		public GNoticeCommand GNselectBoard(Integer seq);
		@Update("UPDATE g_notice SET gn_hit=gn_hit+1 WHERE gn_seq=#{gn_seq}")
		public void GNupdateHit(Integer seq);
		@Update("UPDATE g_notice SET gn_title=#{gn_title},gn_content=#{gn_content},gn_uploadfile=#{gn_uploadfile,jdbcType=BLOB},gn_filename=#{gn_filename,jdbcType=VARCHAR},ip=#{ip} WHERE gn_seq=#{gn_seq}")
		public void GNupdate(GNoticeCommand board);
		@Delete("DELETE FROM g_notice WHERE gn_seq=#{gn_seq}")
		public void GNdelete(Integer seq);
		//공지사항 댓글
		public List<GNoticeReplyCommand> GNlistReply(Map<String,Object> Map);
		@Select("SELECT count(*) FROM g_notice_re WHERE gnre_seq=#{gnre_seq}")
		public int GNgetRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO g_notice_re (gnre_no,gnre_content,gnre_date,ip,gnre_seq,id) VALUES (gnbre_seq.nextval,#{gnre_content},sysdate,#{ip},#{gnre_seq},#{id})")
		public void GNinsertReply(GNoticeReplyCommand boardReply);
		@Update("UPDATE g_notice_re SET gnre_content=#{gnre_content},ip=#{ip} WHERE gnre_no=#{gnre_no}")
		public void GNupdateReply(GNoticeReplyCommand boardReply);
		@Delete("DELETE FROM g_notice_re WHERE gnre_no=#{gnre_no}")
		public void GNdeleteReply(Integer re_no);
		
		//공지사항 삭제시 공지사항댓글이 존재하면 공지사항 삭제전 공지사항댓글 삭제
		@Delete("DELETE FROM g_notice_re WHERE gnbre_seq=#{gnbre_seq}")
		public void GNdeleteReplyBySeq(Integer seq);
		
		//자유게시판
		public List<GBoardCommand> Glist(Map<String,Object> map);
		public int GgetRowCount(Map<String,Object> map);
		@Insert("INSERT INTO g_board(gb_seq,gb_title,gb_content,gb_regdate,gb_uploadfile,gb_filename,ip,id) VALUES (g_board_seq.nextval,#{gb_title},#{gb_content},sysdate,#{gb_uploadfile,jdbcType=BLOB},#{gb_filename,jdbcType=VARCHAR},#{ip},#{id})")
		public void Ginsert(GBoardCommand board);
		@Select("SELECT * FROM g_board WHERE gb_seq=#{gb_seq}")
		public GBoardCommand GselectBoard(Integer seq);
		@Update("UPDATE g_board SET gb_hit=gb_hit+1 WHERE gb_seq=#{gb_seq}")
		public void GupdateHit(Integer seq);
		@Update("UPDATE g_board SET gb_title=#{gb_title},gb_content=#{gb_content},gb_uploadfile=#{gb_uploadfile,jdbcType=BLOB},gb_filename=#{gb_filename,jdbcType=VARCHAR},ip=#{ip} WHERE gb_seq=#{gb_seq}")
		public void Gupdate(GBoardCommand board);
		@Delete("DELETE FROM g_board WHERE gb_seq=#{gb_seq}")
		public void Gdelete(Integer seq);
		//자유게시판 댓글
		public List<GBoardReplyCommand> GlistReply(Map<String,Object> Map);
		@Select("SELECT count(*) FROM g_board_re WHERE gbre_seq=#{gbre_seq}")
		public int GgetRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO g_board_re (gbre_no,gbre_content,gbre_date,ip,gbre_seq,id) VALUES (gbre_seq.nextval,#{gbre_content},sysdate,#{ip},#{gbre_seq},#{id})")
		public void GinsertReply(GBoardReplyCommand boardReply);
		@Update("UPDATE g_board_re SET gbre_content=#{gbre_content},ip=#{ip} WHERE gbre_no=#{gbre_no}")
		public void GupdateReply(GBoardReplyCommand boardReply);
		@Delete("DELETE FROM g_board_re WHERE gbre_no=#{gbre_no}")
		public void GdeleteReply(Integer re_no);
		
		//자유게시판 삭제시 자유게시판댓글이 존재하면 자유게시판 삭제전 자유게시판 삭제
		@Delete("DELETE FROM spboard_reply WHERE gbre_seq=#{gbre_seq}")
		public void GdeleteReplyBySeq(Integer seq);
		
		//팀게시판
		public List<GTBoardCommand> GTlist(Map<String,Object> map);
		public int GTgetRowCount(Map<String,Object> map);
		@Insert("INSERT INTO g_teamboard(gt_seq,gt_title,gt_content,gt_regdate,gt_uploadfile,gt_filename,ip,id) VALUES (g_teamboard_seq.nextval,#{gt_title},#{gt_content},sysdate,#{gt_uploadfile,jdbcType=BLOB},#{gt_filename,jdbcType=VARCHAR},#{ip},#{id})")
		public void GTinsert(GTBoardCommand board);
		@Select("SELECT * FROM g_teamboard WHERE gt_seq=#{gt_seq}")
		public GTBoardCommand GTselectBoard(Integer seq);
		@Update("UPDATE g_teamboard SET gt_hit=gt_hit+1 WHERE gt_seq=#{gt_seq}")
		public void GTupdateHit(Integer seq);
		@Update("UPDATE g_teamboard SET gt_title=#{gt_title},gt_content=#{gt_content},gt_uploadfile=#{gt_uploadfile,jdbcType=BLOB},gt_filename=#{gt_filename,jdbcType=VARCHAR},ip=#{ip} WHERE gt_seq=#{gt_seq}")
		public void GTupdate(GTBoardCommand board);
		@Delete("DELETE FROM g_teamboard WHERE gt_seq=#{gt_seq}")
		public void GTdelete(Integer seq);
		//팀게시판댓글
		public List<GTBoardReplyCommand> GTlistReply(Map<String,Object> Map);
		@Select("SELECT count(*) FROM g_teamboard_re WHERE gtre_seq=#{gtre_seq}")
		public int GTgetRowCountReply(Map<String,Object> map);
		@Insert("INSERT INTO g_teamboard_re (gtre_no,gtre_content,gtre_date,ip,seq,id) VALUES (gtbre_seq.nextval,#{gtre_content},sysdate,#{ip},#{gtre_seq},#{id})")
		public void GTinsertReply(GTBoardReplyCommand boardReply);
		@Update("UPDATE g_teamboard_re SET gtre_content=#{gtre_content},ip=#{ip} WHERE gtre_no=#{gtre_no}")
		public void GTupdateReply(GTBoardReplyCommand boardReply);
		@Delete("DELETE FROM g_teamboard_re WHERE re_no=#{gtre_no}")
		public void GTdeleteReply(Integer re_no);
		
		//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
		@Delete("DELETE FROM g_teamboard_re WHERE gtre_seq=#{gtre_seq}")
		public void GTdeleteReplyBySeq(Integer seq);
}
