package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MypageDAO;
import dto.CommentDTO;
import dto.LikeDTO;
import dto.UserDTO;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	MypageDAO mypagedao;
	
	public UserDTO getUser(String user_id) {
		return mypagedao.getUser(user_id);
	}
	
	public int userUpdate(String user_id, String password) {
		return mypagedao.userUpdate(user_id, password);
	}
	
	public int deleteUser(String user_id) {
		return mypagedao.deleteUser(user_id);
	}

	public int deleteLike(String user_id) {
		return mypagedao.deleteLike(user_id);
	}

	public int deleteComment(String user_id) {
		return mypagedao.deleteComment(user_id);
	}

	public List<LikeDTO> getLike(String user_id) {
		return mypagedao.getLike(user_id);
	}
	
	public List<CommentDTO> getComment(String user_id) {
		return mypagedao.getComment(user_id);
	}
}
