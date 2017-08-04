package com.kh.mixmatch.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.board.domain.GBoardCommand;
import com.kh.mixmatch.board.domain.GBoardReplyCommand;
import com.kh.mixmatch.board.domain.GNoticeCommand;
import com.kh.mixmatch.board.domain.GNoticeReplyCommand;
import com.kh.mixmatch.board.domain.GTBoardCommand;
import com.kh.mixmatch.board.domain.GTBoardReplyCommand;


@Transactional
public interface BoardService {
		//팀게시판
		@Transactional(readOnly=true)
		public List<GTBoardCommand> GTlist(Map<String,Object> map);
		@Transactional(readOnly=true)
		public int GTgetRowCount(Map<String,Object> map);
		public void GTinsert(GTBoardCommand board);
		@Transactional(readOnly=true)
		public GTBoardCommand GTselectBoard(Integer seq);
		public void GTupdateHit(Integer seq);
		public void GTupdate(GTBoardCommand board);
		public void GTdelete(Integer seq);
		//팀게시판댓글
		@Transactional(readOnly=true)
		public List<GTBoardReplyCommand> GTlistReply(Map<String,Object> Map);
		@Transactional(readOnly=true)
		public int GTgetRowCountReply(Map<String,Object> map);
		public void GTinsertReply(GTBoardReplyCommand boardReply);
		public void GTupdateReply(GTBoardReplyCommand boardReply);
		public void GTdeleteReply(Integer re_no);
		
		
		//자유게시판
		@Transactional(readOnly=true)
		public List<GBoardCommand> Glist(Map<String,Object> map);
		@Transactional(readOnly=true)
		public int GgetRowCount(Map<String,Object> map);
		public void Ginsert(GBoardCommand board);
		@Transactional(readOnly=true)
		public GBoardCommand GselectBoard(Integer seq);
		public void GupdateHit(Integer seq);
		public void Gupdate(GBoardCommand board);
		public void Gdelete(Integer seq);
		//자유게시판댓글
		@Transactional(readOnly=true)
		public List<GBoardReplyCommand> GlistReply(Map<String,Object> Map);
		@Transactional(readOnly=true)
		public int GgetRowCountReply(Map<String,Object> map);
		public void GinsertReply(GBoardReplyCommand boardReply);
		public void GupdateReply(GBoardReplyCommand boardReply);
		public void GdeleteReply(Integer re_no);
		
		//공지사항
		@Transactional(readOnly=true)
		public List<GNoticeCommand> GNlist(Map<String,Object> map);
		@Transactional(readOnly=true)
		public int GNgetRowCount(Map<String,Object> map);
		public void GNinsert(GNoticeCommand board);
		@Transactional(readOnly=true)
		public GNoticeCommand GNselectBoard(Integer seq);
		public void GNupdateHit(Integer seq);
		public void GNupdate(GNoticeCommand board);
		public void GNdelete(Integer seq);
		//공지사항댓글
		@Transactional(readOnly=true)
		public List<GNoticeReplyCommand> GNlistReply(Map<String,Object> Map);
		@Transactional(readOnly=true)
		public int GNgetRowCountReply(Map<String,Object> map);
		public void GNinsertReply(GNoticeReplyCommand boardReply);
		public void GNupdateReply(GNoticeReplyCommand boardReply);
		public void GNdeleteReply(Integer re_no);
}
