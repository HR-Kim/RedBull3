package kr.co.redbull.board.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.redbull.board.service.BoardService;

@Controller
public class BoardController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	private final String VIEW_DETAIL  ="board/board_detail"; 
	private final String VIEW_LIST_NM ="board/board_list";
	private final String VIEW_MNG_NM  ="board/board_mng";
	
	
}
