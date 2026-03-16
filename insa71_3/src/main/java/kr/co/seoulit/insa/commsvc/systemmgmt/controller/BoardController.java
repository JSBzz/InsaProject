package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.BoardTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ListFormTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.ResultTO;
import kr.co.seoulit.insa.sys.util.BoardFile;
import kr.co.seoulit.insa.sys.util.BoardFileUploadUtil;

@RequestMapping("/systemmgmt/*")
@RestController
public class BoardController {
	
	@Autowired
	private SystemMgmtService systemMgmtService;	
	
	@PostMapping("board")
	public ModelAndView registBoard(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelAndView = new ModelAndView();		
        BoardTO board=new BoardTO();
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        
		MultipartFile reportFile = multipartRequest.getFile("uploadFile");
		
        String fileName = (reportFile != null) ? reportFile.getOriginalFilename() : null;
        
		if ((fileName != null) && (reportFile.getSize() > 0)) {
			BoardFile boardFile = null;
			try {
				boardFile = BoardFileUploadUtil.doFileUpload(reportFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			board.addBoardFile(boardFile);
		}
			
			try {
				
			board.setName(request.getParameter("name"));
			board.setContent(request.getParameter("content"));
			board.setTitle(request.getParameter("title"));
			board.setBoard_seq(Integer.parseInt(request.getParameter("board_seq")));
			board.setReg_date(request.getParameter("reg_date"));
			
			systemMgmtService.addBoard(board);
			
			modelAndView.addObject("errorMsg", "게시글이 등록되었습니다.");
			modelAndView.addObject("errorCode",0);
			modelAndView.setViewName("redirect:" + "/comm/listBoard/view");
			
		}catch (Exception e){
			modelAndView.addObject("errorMsg", e.getMessage());
			modelAndView.addObject("errorCode", -1);		
        }
		return modelAndView;
	}
	
	
	@GetMapping("listboard")
	public ResultTO findBoardList(@RequestParam(value="pn", required=false) String pn, 
                                 @RequestParam String selectValue) {
		ResultTO result = new ResultTO();
		ArrayList<BoardTO> list=null;
		ListFormTO boardList = null;
		int pagenum, sr, er, dbCount, selectValueInt;
		
		try {
			pagenum = (pn != null) ? Integer.parseInt(pn) : 1;
			selectValueInt = Integer.parseInt(selectValue);
			boardList=new ListFormTO();
			dbCount=systemMgmtService.getRowCount();
			boardList.setRowsize(selectValueInt);
			boardList.setDbcount(dbCount);
			boardList.setPagenum(pagenum);
			sr=boardList.getStartrow();
			er=boardList.getEndrow();
			list=systemMgmtService.getBoardList(sr,er);
			boardList.setList(list);
					
			result.setErrorMsg("success");
			result.setErrorCode("0");
			result.setAttribute("boardlist", list);
			result.setAttribute("board", boardList);

		}catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());	
		}
		return result;
	}

	
	@GetMapping("detail-board")
	public ResultTO findDetailBoardList(@RequestParam String board_seq, HttpServletRequest request) {
		ResultTO result = new ResultTO();
		int board_seq_int;
		String sessionId=null;
		
		try {
			board_seq_int = Integer.parseInt(board_seq);
			sessionId=(String)request.getSession().getAttribute("id");
			BoardTO board = systemMgmtService.getBoard(sessionId, board_seq_int);
			
			result.setErrorMsg("success");
			result.setErrorCode("0");
			result.setAttribute("board", board);

		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("detail-board")
	public ResultTO removeBoard(@RequestParam String board_seq) {
		ResultTO result = new ResultTO();
		try {
			int board_seq_int = Integer.parseInt(board_seq);
			systemMgmtService.removeBoard(board_seq_int);		
			result.setErrorMsg("게시글이 삭제되었습니다");
			result.setErrorCode("0");
			
		} catch (Exception e) {
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());
		}
		return result;
	}
	
	@GetMapping("detail-board/file")
	public ResultTO downloadFile(@RequestParam String tempFileName, 
                                @RequestParam String fileName, 
                                HttpServletResponse response){
		ResultTO result = new ResultTO();
		response.setCharacterEncoding("utf-8");		
		
		try {
			String filePath="C:\\dev\\nginx\\nginx-1.20.2\\html\\upload\\"+tempFileName;
			java.io.File tempFile = new java.io.File(filePath);
			int filesize = (int) tempFile.length();		
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=" + "" + new String(fileName.getBytes(),"iso-8859-1"));
			response.setHeader("Content-Transper-Encoding","binary");
			response.setContentLength(filesize);

			try (OutputStream servletoutputstream = response.getOutputStream()) {
				dumpFile(tempFile, servletoutputstream);
				servletoutputstream.flush();
			}

		}catch (Exception e){
			result.setErrorCode("-1");
			result.setErrorMsg(e.getMessage());			
        }
		return result;
	}
	
	private void dumpFile(File realFile, OutputStream outputstream) {
		byte readByte[] = new byte[4096];
		try (BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(realFile))) {
			int i;
			while ((i = bufferedinputstream.read(readByte, 0, 4096)) != -1)
				outputstream.write(readByte, 0, i);		
		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
	}
	
}
