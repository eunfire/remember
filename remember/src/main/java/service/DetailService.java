package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DetailDAO;
import dto.CommentDTO;

@Service
public class DetailService {
	@Autowired
	DetailDAO dao;
	
	public int likeCount(int mng_no) {
		return dao.likeCount(mng_no); 
	}
	public void insertLike(int mng_no, String id) {
		dao.insertLike(mng_no, id);
	}
	public List<CommentDTO> selectComment(int mng_no, int page){
		return dao.selectComment(mng_no, page);
	}
	public void insertComment(int mng_no, String id, String contents) {
		dao.insertComment(mng_no, id, contents);
	}
	public void updateComment(String contents, String comment_id) {
		dao.updateComment(contents, comment_id);
	}
	public void deleteComment(String comment_id) {
		dao.deleteComment(comment_id);
	}
}
