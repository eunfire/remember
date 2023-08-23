package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

import dto.CommentDTO;
import dto.LikeDTO;
import dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MypageService;

@Controller
public class MypageController {
	@Autowired
	MypageService service;

	// 유저 정보 조회 페이지 출력
	@GetMapping("/myinfo")
	public ModelAndView myInfo(HttpSession session,	HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		
		String userId = (String) session.getAttribute("user_id");

		if (userId != null) {
			UserDTO user = service.getUser(userId);

			mv.addObject("user", user);
			mv.setViewName("mypage/myinfo");
		} else {
			mv.setViewName("Ylogin");
		}

		return mv;
	}

	// 유저 정보 수정 페이지 출력
	@GetMapping("/myinfoedit")
	public ModelAndView myedit(HttpSession session,	HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		
		String userId = (String) session.getAttribute("user_id");

		if (userId != null) {
			UserDTO user = service.getUser(userId);
			
			mv.addObject("user", user);
			mv.setViewName("mypage/myinfoedit");
		} else {
			mv.setViewName("Ylogin");
		}

		return mv;
	}

	// 유저 정보 수정
	@PostMapping("/editinfo")
	public String myupdate(HttpSession session,	HttpServletResponse response, @RequestParam String password) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		String userId = (String) session.getAttribute("user_id");
		
		if (userId != null) {
			service.userUpdate(userId, password);

			return "redirect:/myinfo";
		} else {
			return "redirect:/";
		}
	}

	// 유저 정보 삭제
	@RequestMapping("/myinfodelete")
	public String myDelete(HttpSession session,
			HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		String userId = (String) session.getAttribute("user_id");

		if (userId != null) {
			service.deleteComment(userId);
			service.deleteLike(userId);
			service.deleteUser(userId);
		}

		return "redirect:/";
	}

	// 존경해요 한 글
	@GetMapping("/mylike")
	public ModelAndView mylike(HttpSession session,	HttpServletResponse response) {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		
		String userId = (String) session.getAttribute("user_id");

		if (userId != null) {
			List<LikeDTO> like = service.getLike(userId);

			List<String> mngNoList = new ArrayList<>();
			for (LikeDTO likeItem : like) {
				mngNoList.add(String.valueOf(likeItem.getMng_no()));
			}

			mv.addObject("like", like);
			mv.addObject("user", userId);
			mv.addObject("mngNoList", mngNoList);
			mv.setViewName("mypage/mylike");
			
		} else {
			mv.setViewName("Ylogin");
		}

		return mv;
	}

	// 작성 댓글
	@GetMapping("/mycomment")
	public ModelAndView mycomment(HttpSession session,	HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		
		String userId = (String) session.getAttribute("user_id");

		if (userId != null) {
			List<CommentDTO> comment = service.getComment(userId);

			mv.addObject("user", userId);
			mv.addObject("comment", comment);
			mv.setViewName("mypage/mycomment");
		} else {
			mv.setViewName("Ylogin");
		}

		return mv;
	}
}
