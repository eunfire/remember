package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.CommentDTO;
import jakarta.servlet.http.HttpSession;
import service.DetailService;

@Controller
public class DetailController {
	@Autowired
	DetailService service;

	@RequestMapping("/detail")
	public String detail(Model model, HttpSession session, @RequestParam("mng_no") int mng_no) {
		int likeCount = service.likeCount(mng_no);

		model.addAttribute("likeCount", likeCount);

		return "detail";
	}

	@ResponseBody
	@RequestMapping("/api/like")
	public int inserLike(HttpSession session, int mng_no) {
		int likeCount = 0;

		if (session.getAttribute("user_id") != null) {
			String user_id = session.getAttribute("user_id").toString();

			service.insertLike(mng_no, user_id);
			likeCount = service.likeCount(mng_no);
		}

		return likeCount;
	}

	@ResponseBody
	@RequestMapping("/api/commentList")
	public List<CommentDTO> listComment(int mng_no, int page) {
		int pages = (page - 1) * 5;
		
		List<CommentDTO> commentList = service.selectComment(mng_no, pages);
		
		return commentList;
	}

	@ResponseBody
	@RequestMapping("/api/comment")
	public void insertComment(HttpSession session, @RequestParam("mng_no") int mng_no, String contents) {
		if (session.getAttribute("user_id") != null & contents != null) {
			String user_id = session.getAttribute("user_id").toString();

			service.insertComment(mng_no, user_id, contents);
		}
	}

	@ResponseBody
	@RequestMapping("/api/updateComment")
	public void UpdateComment(HttpSession session, String comment_id, String contents) {
		if (session.getAttribute("user_id") != null & contents != null) {
			service.updateComment(contents, comment_id);
		}
	}

	@ResponseBody
	@RequestMapping("/api/deleteComment")
	public void deleteComment(Model model, HttpSession session, String comment_id) {
		if (session.getAttribute("user_id") != null) {
			service.deleteComment(comment_id);
		}
	}

}
