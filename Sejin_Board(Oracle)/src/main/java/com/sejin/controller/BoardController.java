package com.sejin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sejin.model.BoardVO;
import com.sejin.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 목록 페이지 접속
	@GetMapping("/list")
	// => @RequestMapping(value="list", method=RequestMethod.GET)
	public void boardListGET(Model model) {
		
		log.info("게시판 목록 페이지 진입");
		
		model.addAttribute("list", boardService.getList());
	}
	
	// 게시판 등록 페이지 접속
	@GetMapping("/enroll")
	public void boardEnrollGET() {
		
		log.info("게시판 등록 페이지 진입");
	}
	
	// 게시판 등록
	@PostMapping("/enroll")
	public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {
		log.info("BoardVO : " + board);
		
		boardService.enroll(board);
		
		rttr.addFlashAttribute("result", "enrol success");
		
		return "redirect:/board/list";
	}
	
	// 게시판 조회
	@GetMapping("/get")
	public void boardGetPage(int bno, Model model) {
		
		model.addAttribute("pageInfo", boardService.getPage(bno));
		
	}
	
}
