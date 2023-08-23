package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.CommentDTO;
import dto.LikeDTO;
import dto.UserDTO;

@Repository
@Mapper
public interface MypageDAO {
	public UserDTO getUser(String user_id);

	public int userUpdate(String user_id, String password);

	public int deleteUser(String user_id);

	public int deleteLike(String user_id);

	public int deleteComment(String user_id);
	
	public List<LikeDTO> getLike(String user_id);
	
	public List<CommentDTO> getComment(String user_id);
	
}
