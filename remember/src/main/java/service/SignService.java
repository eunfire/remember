package service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;

public interface SignService {
	void register(UserDTO user);
    UserDTO login(String email, String password);
    
    ModelAndView signup();
    
    ModelAndView signin();
    
    ModelAndView alert();
    
    int insertMember(UserDTO userDTO);
    
    int dupliIDcheck(String user_id);
    
    int dupliEmailcheck(String email);
    
    int dupliFindEmailcheck(String user_id, String email);
    
    void Findpwupdate(UserDTO userDTO);
	UserDTO MyInfo(String id);
	int updatemember(UserDTO UserDTO);
	int deletemember(String user_id);
	

}
    

