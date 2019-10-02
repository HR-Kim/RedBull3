package kr.co.redbull.comment.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.redbull.board.service.Board;
import kr.co.redbull.board.service.BoardService;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.comment.service.Comment;
import kr.co.redbull.comment.service.CommentService;

//@Controller
public class CommentController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommentService commentService;
	
	private String viewDetailNm ="board/board_detail";
	
	@RequestMapping(value = "comment/get_retrieve.do", method = RequestMethod.GET)
	public String get_retrieve(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		if(search.getPageSize()==0) search.setPageSize(10);
		if(search.getPageNum()==0)  search.setPageNum(1);
		
		
		model.addAttribute("vo", search);
		
		List<Board> list = (List<Board>)commentService.get_retrieve(search);
		
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
//		if(search.getSearchDiv().equals("10")) {
//			viewListNm = "board/notice_list";
//		}else if(search.getSearchDiv().equals("20")) {
//			viewListNm = "board/question_list";
//		}
		
		return viewDetailNm;
	}
	
	@RequestMapping(value = "board/get_selectOne.do", method = RequestMethod.GET)
	public String get_selectOne(Board board, Model model) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		if(board.getbNum()==0) throw new IllegalArgumentException("글이 존재하지 않습니다.");
		
		Board outVO = (Board)commentService.get_selectOne(board);
		
		model.addAttribute("vo", outVO);
		
		//댓글		
		Search search = new Search();
		search.setPageSize(10);
		search.setPageNum(1);
		search.setSearchDiv(String.valueOf(outVO.gettNum()));
		search.setSearchWord(String.valueOf(outVO.getbNum()));
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		List<Comment> commentList = (List<Comment>)commentService.get_retrieve(search);
		model.addAttribute("commentList", commentList);
		
		return viewDetailNm;
	}
	
	@RequestMapping(value = "board/do_update.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(Board board) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		int flag = commentService.do_update(board);
		
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("================================");
		LOG.debug("gsonStr:"+gsonStr);
		LOG.debug("================================");
		
		return gsonStr;
	}
	
	@RequestMapping(value = "board/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(Board board) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		int flag = commentService.do_save(board);
		
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("================================");
		LOG.debug("gsonStr:"+gsonStr);
		LOG.debug("================================");
		
		return gsonStr;
	}
	
	@RequestMapping(value = "board/do_delete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(Board board) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		int flag = commentService.do_delete(board);
		
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("================================");
		LOG.debug("gsonStr:"+gsonStr);
		LOG.debug("================================");
		
		return gsonStr;
	}
}
