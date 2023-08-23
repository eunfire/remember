package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import dao.SignDAO;
import dto.UserDTO;

@Service("SignService")
public class SignServiceimple implements SignService{
	
	@Autowired
	SignDAO dao;
	
	@Override
	public int insertMember(UserDTO userDTO) {
		return dao.insertMember(userDTO);
	}
	@Override
	public int dupliIDcheck(String user_id) {
		return dao.dupliIDcheck(user_id);
	}
	@Override
	public int dupliEmailcheck(String email) {
		return dao.dupliEmailcheck(email);
	}
	@Override
	public int dupliFindEmailcheck(String user_id,String email) {
		return dao.dupliFindEmailcheck(user_id,email);
	}
	
	@Override
	public UserDTO MyInfo(String id) {
		return dao.MyInfo(id);
	}
	@Override
	public int updatemember(UserDTO UserDTO) {
		return dao.updatemember(UserDTO);
	}

	

	@Override
	public void register(UserDTO user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public UserDTO login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ModelAndView signup() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ModelAndView signin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ModelAndView alert() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deletemember(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void Findpwupdate(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}
		

}
