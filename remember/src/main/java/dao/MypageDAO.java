package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.UserDTO;

@Repository
@Mapper
public interface MypageDAO {
	public UserDTO getUser(String user_id);
}
