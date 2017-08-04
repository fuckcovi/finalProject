package com.kh.mixmatch.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.board.dao.BoardMapper;
import com.kh.mixmatch.board.domain.GBoardCommand;
import com.kh.mixmatch.board.domain.GBoardReplyCommand;
import com.kh.mixmatch.board.domain.GNoticeCommand;
import com.kh.mixmatch.board.domain.GNoticeReplyCommand;
import com.kh.mixmatch.board.domain.GTBoardCommand;
import com.kh.mixmatch.board.domain.GTBoardReplyCommand;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Resource
	private BoardMapper boardMapper;
	
	@Override
	public List<GTBoardCommand> GTlist(Map<String, Object> map) {		
		return boardMapper.GTlist(map);
	}

	@Override
	public int GTgetRowCount(Map<String, Object> map) {
		//
		return boardMapper.GTgetRowCount(map);
	}

	@Override
	public void GTinsert(GTBoardCommand board) {
		// TODO Auto-generated method stub
		boardMapper.GTinsert(board);
	}

	@Override
	public GTBoardCommand GTselectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return boardMapper.GTselectBoard(seq);
	}

	@Override
	public void GTupdateHit(Integer seq) {
		// TODO Auto-generated method stub
		boardMapper.GNupdateHit(seq);
	}

	@Override
	public void GTupdate(GTBoardCommand board) {
		// TODO Auto-generated method stub
		boardMapper.GTupdate(board);
	}

	@Override
	public void GTdelete(Integer seq) {
		//팀게시판댓글이 존재하면 팀게시판댓글을 우선 삭제하고 팀게시판을 삭제
		boardMapper.GTdeleteReplyBySeq(seq);
		//이 후에 팀게시판 삭제
		boardMapper.Gdelete(seq);
	}

	@Override
	public List<GTBoardReplyCommand> GTlistReply(Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return boardMapper.GTlistReply(Map);
	}

	@Override
	public int GTgetRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GTgetRowCount(map);
	}

	@Override
	public void GTinsertReply(GTBoardReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GTinsertReply(boardReply);
	}

	@Override
	public void GTupdateReply(GTBoardReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GTupdateReply(boardReply);
	}

	@Override
	public void GTdeleteReply(Integer re_no) {
		// TODO Auto-generated method stub
		boardMapper.GTdeleteReply(re_no);
	}

	@Override
	public List<GBoardCommand> Glist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.Glist(map);
	}

	@Override
	public int GgetRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GgetRowCount(map);
	}

	@Override
	public void Ginsert(GBoardCommand board) {
		// TODO Auto-generated method stub
		boardMapper.Ginsert(board);
	}

	@Override
	public GBoardCommand GselectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return boardMapper.GselectBoard(seq);
	}

	@Override
	public void GupdateHit(Integer seq) {
		// TODO Auto-generated method stub
		boardMapper.GupdateHit(seq);
	}

	@Override
	public void Gupdate(GBoardCommand board) {
		// TODO Auto-generated method stub
		boardMapper.Gupdate(board);
	}

	@Override
	public void Gdelete(Integer seq) {
		// TODO Auto-generated method stub
		boardMapper.Gdelete(seq);
	}

	@Override
	public List<GBoardReplyCommand> GlistReply(Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return boardMapper.GlistReply(Map);
	}

	@Override
	public int GgetRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GgetRowCountReply(map);
	}

	@Override
	public void GinsertReply(GBoardReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GinsertReply(boardReply);
	}

	@Override
	public void GupdateReply(GBoardReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GupdateReply(boardReply);
	}

	@Override
	public void GdeleteReply(Integer re_no) {
		// TODO Auto-generated method stub
		boardMapper.GdeleteReply(re_no);
	}

	@Override
	public List<GNoticeCommand> GNlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GNlist(map);
	}

	@Override
	public int GNgetRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GNgetRowCount(map);
	}

	@Override
	public void GNinsert(GNoticeCommand board) {
		// TODO Auto-generated method stub
		boardMapper.GNinsert(board);
	}

	@Override
	public GNoticeCommand GNselectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return boardMapper.GNselectBoard(seq);
	}

	@Override
	public void GNupdateHit(Integer seq) {
		// TODO Auto-generated method stub
		boardMapper.GNupdateHit(seq);
	}

	@Override
	public void GNupdate(GNoticeCommand board) {
		// TODO Auto-generated method stub
		boardMapper.GNupdate(board);
	}

	@Override
	public void GNdelete(Integer seq) {
		// TODO Auto-generated method stub
		boardMapper.GNdelete(seq);
	}

	@Override
	public List<GNoticeReplyCommand> GNlistReply(Map<String, Object> Map) {
		// TODO Auto-generated method stub
		return boardMapper.GNlistReply(Map);
	}

	@Override
	public int GNgetRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.GNgetRowCountReply(map);
	}

	@Override
	public void GNinsertReply(GNoticeReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GNinsertReply(boardReply);
	}

	@Override
	public void GNupdateReply(GNoticeReplyCommand boardReply) {
		// TODO Auto-generated method stub
		boardMapper.GNupdateReply(boardReply);
	}

	@Override
	public void GNdeleteReply(Integer re_no) {
		// TODO Auto-generated method stub
		boardMapper.GNdeleteReply(re_no);
	}

}
