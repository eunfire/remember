package dao;

import dto.UserDTO;

public interface SignDAO {
	void insertUser(UserDTO user);
	public int insertMember(UserDTO UserDTO);
	public int dupliIDcheck(String user_id);
	public int dupliEmailcheck(String email);
	public int dupliFindEmailcheck(String user_id,String email);
	UserDTO findByUserId(String userId);
    UserDTO findByEmailAndPassword(String email, String password);
	UserDTO MyInfo(String id);
	int updatemember(UserDTO userDTO);
	int Findpwupdate(UserDTO userDTO);
	
}
