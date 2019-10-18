package kr.co.redbull.board.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import kr.co.redbull.board.service.Board;
import kr.co.redbull.board.service.BoardService;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.comment.service.Comment;
import kr.co.redbull.comment.service.CommentService;
import kr.co.redbull.file.service.FileService;
import kr.co.redbull.file.service.FileVO;

@Controller
public class BoardController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	FileService fileService;
	
	private final String VIEW_DETAIL  ="board/board_detail";
	private String viewListNm ="board/question_list";
	private final String VIEW_MNG_NM  ="board/board_mng";
	
	@RequestMapping(value="board/do_save_img.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String do_save_img(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException {
		
		LOG.debug("===============================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("===============================");
		
		//root_path 전달
		String UPLOAD_ROOT = "C:\\Users\\sist\\git/RedBull3/RedBull/src/main/webapp/img/board";
		String SAVE_ROOT = "img"+File.separator+"board";

		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		String refNum = StringUtil.nvl(mReg.getParameter("refNum"));
		String fileId = StringUtil.nvl(mReg.getParameter("fileId"));

		LOG.debug("=@Controller fileId="+fileId);
		
		//01.동적으로 UPLOAD_ROOT 디렉토리 생성
		File  fileRootDir = new File(UPLOAD_ROOT);
		if(fileRootDir.isDirectory() ==false) {  
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
		}
		
		//02.년월 디렉토리 생성:D:\\HR_FILE\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller mm="+mm);
		String datePath = UPLOAD_ROOT+File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		SAVE_ROOT = SAVE_ROOT+File.separator+yyyy+File.separator+mm;
		
		File  fileYearMM = new File(datePath);  
		
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();  
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		
		int flag  =0;
		Message message=new Message();
		
		String saveFileNm = "";
		
		//01.파일 Read      
		Iterator<String> files = mReg.getFileNames();
		while(files.hasNext()) {
			FileVO fileVO =new FileVO();
			String orgFileNm  = "";//원본파일명
			//String saveFileNm = "";//저장파일명
			long   fileSize   = 0L;//파일사이즈
			String extNm      = "";//확장자
			
			String uploadFileNm = files.next();//file01
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNm = mFile.getOriginalFilename();
			//file선택이 않되면 continue
			if(null==orgFileNm || orgFileNm.equals(""))continue;
			
			
			LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
			LOG.debug("=@Controller orgFileNm="+orgFileNm);
			fileSize = mFile.getSize();//file size byte
			
			if(orgFileNm.indexOf(".")>-1) {
				extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller extNm="+extNm);
			File orgFileCheck = new File(datePath,orgFileNm);
			
			String newFile = orgFileCheck.toString();
			String saveFile = SAVE_ROOT+File.separator+orgFileNm;
			
			//04.파일 rename: README -> README1~9999
			if(orgFileCheck.exists()==true) {
				newFile = StringUtil.fileRename_board(orgFileCheck);
				saveFile = SAVE_ROOT+File.separator+StringUtil.fileRenameShort(orgFileCheck);
			}
			LOG.debug("=@Controller newFile="+newFile);
			//http://localhost:8080/redbull/resources/img/board/2019/10/noimage.jpg
			//-----------------------------------------------
			//-FileId 존재 유무로 Key생성 유무 판단.
			//-----------------------------------------------
			//FileId 없는 경우
			if(fileId.equals("0") || fileId.length()!=40) {
				String yyyymmdd = StringUtil.cureDate("yyyyMMdd");
				String fileIdKey= yyyymmdd+StringUtil.getUUID();
				LOG.debug("yyyymmdd:"+yyyymmdd);
				LOG.debug("fileIdKey:"+fileIdKey);
				fileVO.setFileId(fileIdKey);
				fileVO.setNum(1);
				fileId = fileIdKey;
			//fileID가 있는 경우.	
			}else {
				fileVO.setFileId(fileId);
				//max num
				int maxNum = this.fileService.num_max_plus_one(fileVO);
				LOG.debug("maxNum:"+maxNum);
				fileVO.setNum(maxNum);
			}
			
			fileVO.setOrgFileNm(orgFileNm);
			fileVO.setSaveFileNm(saveFile);
			fileVO.setFileSize(fileSize);
			fileVO.setExtNm(extNm);
			fileList.add(fileVO);
			mFile.transferTo(new File(newFile));
			
			LOG.debug("newFile:"+newFile);
			LOG.debug("saveFile:"+saveFile);
			//LOG.debug("UPLOAD_ROOT+newFile:"+UPLOAD_ROOT+newFile);
			
			flag = fileService.do_save(fileVO);
			LOG.debug("flag:"+flag);
			
			saveFileNm = fileVO.getSaveFileNm();
			LOG.debug("=@Controller saveFileNm="+saveFileNm);
			//model.addAttribute("saveFileNm", saveFileNm);
		}
		
		//등록성공
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg(saveFileNm);
		//등록실패	
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록실패.");	
		}
		Gson gson=new Gson();
		
		String gsonStr = gson.toJson(message);
		LOG.debug("gsonStr:"+gsonStr);
		
		return gsonStr;
				
		//return saveFileNm;
	}
	
	@RequestMapping(value = "board/do_write.do")
	public String do_write(Board board, Search search, Model model) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		if(null==search.getSearchDiv() || "".equals(search.getSearchDiv())) {
			search.setSearchDiv("20");
		}
		
		Integer bNum = board.getbNum();
				
		Board outVO = new Board();
		
		if(0==bNum) {
			outVO.setbNum(0);
		}else {
			outVO = (Board)boardService.get_selectOne(board);		
		}
		
		model.addAttribute("board", outVO);		
		model.addAttribute("search", search);
		
		return VIEW_MNG_NM;
	}
	
	@RequestMapping(value = "board/get_retrieve.do", method = RequestMethod.GET)
	public String get_retrieve(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		if(search.getPageSize()==0) search.setPageSize(10);
		if(search.getPageNum()==0)  search.setPageNum(1);
		
		
		model.addAttribute("vo", search);
		
		List<Board> list = (List<Board>)boardService.get_retrieve(search);
		
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		if(search.getSearchDiv().equals("10")) {
			viewListNm = "board/notice_list";
		}else if(search.getSearchDiv().equals("20")) {
			viewListNm = "board/question_list";
		}
		
		return viewListNm;
	}
	
	@RequestMapping(value = "board/get_selectOne.do", method = RequestMethod.GET)
	public String get_selectOne(Board board, Model model, Search search) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		if(board.getbNum()==0) throw new IllegalArgumentException("글이 존재하지 않습니다.");
		
		Board outVO = (Board)boardService.get_selectOne(board);
		
		model.addAttribute("vo", outVO);
		
		//댓글		
		Search searchVO = new Search();
		searchVO.setPageSize(10);
		if(search.getPageNum()==0) {
			searchVO.setPageNum(1);
		}else {
			searchVO.setPageNum(search.getPageNum());
		}
		searchVO.setSearchDiv(String.valueOf(outVO.gettNum()));
		searchVO.setSearchWord(String.valueOf(outVO.getbNum()));
		
		model.addAttribute("searchVO", searchVO);
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		List<Comment> commentList = (List<Comment>)commentService.get_retrieve(searchVO);
		model.addAttribute("commentList", commentList);
		
		//총건수
		int totalCnt = 0;
		if(null!=commentList && commentList.size()>0) {
			totalCnt = commentList.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		return VIEW_DETAIL;
	}
	
	@RequestMapping(value = "board/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(Board board) {
		
		LOG.debug("================================");
		LOG.debug("board:"+board);
		LOG.debug("================================");
		
		int flag = boardService.do_update(board);
		
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
		
		int flag = boardService.do_save(board);
		
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
		
		int flag = boardService.do_delete(board);
		
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
