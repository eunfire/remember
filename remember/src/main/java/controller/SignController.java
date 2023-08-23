package controller;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import service.SignService;
@Controller
public class SignController{

	@Autowired
	@Qualifier("SignService")
	SignService Ss;
	
	//로그인구현	
	@RequestMapping("/Login")
	public String loginprocess(String id, String password, HttpSession session) {
		UserDTO my_info = Ss.MyInfo(id);
		if(my_info != null && my_info.getPassword().equals(password)) {
				session.setAttribute("user_id",my_info.getUser_id());
				session.setAttribute("id", my_info.getId());
				
				return "redirect:/" ;
			}
		else {
		session.setAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
		session.setAttribute("url", "/Login");
		return "redirect:/alert";
	}
		

	}
	
	
	
	
	//회원가입
	@RequestMapping("/Signup")
	public ModelAndView signup() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Signup");	
		return mv;
	}
	
	
	@PostMapping("/signup")
	public String signup(UserDTO UserDTO) {
		
		String password = UserDTO.getPassword();
		
		if(Ss.insertMember(UserDTO)>0) {
			return "redirect:/Signin";
		}
		else return "Signup";
	}
	
	@RequestMapping("/Signin")
	public ModelAndView signin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Signin");	
		return mv;
	}
	
	@RequestMapping("/alert")
	public ModelAndView alert() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("alert");	
		return mv;
	}
	
	
	
	//회원가입시 중복체크
	@PostMapping("/dupliIDCheck")
	@ResponseBody
	public int duplieIDcheck(@RequestParam("user_id") String user_id) {
			int cnt=Ss.dupliIDcheck(user_id);
			System.out.println(user_id);
			return cnt;
	}
	
	@PostMapping("/dupliEmailCheck")
	@ResponseBody
	public int duplieEmailcheck(@RequestParam("email") String email) {
			int cnt=Ss.dupliEmailcheck(email);
			return cnt;
	}
	//비밀번호찾기시 id와 이메일 대조 
	@PostMapping("/dupliFindEmailCheck")
	@ResponseBody
	public int duplieFindEmailcheck(@RequestParam("user_id") String user_id,@RequestParam("email") String email) {
			int cnt=Ss.dupliFindEmailcheck(user_id,email);
			return cnt;
	}
	
	
	//비밀번호찾기시 임시비밀번호 변경
		@PostMapping("/Findpwupdate")
		public String Findpwupdate(UserDTO UserDTO) {
			String password = UserDTO.getPassword();			
			Ss.Findpwupdate(UserDTO);
			
			return"/Findpasswordend";
		}
		
		
	//이메일 구현단
		@Autowired
		JavaMailSender javaMailSender;
		
		@PostMapping("/CheckMail") 
		@ResponseBody  
		//회원가입시 이메일 인증번호 발송
		public String SendMail(@RequestParam("email")String email) {
			Random random=new Random(); 
			String key=""; 
			for(int i =0; i<3;i++) {
				int index=random.nextInt(25)+65; 
				key+=(char)index;
			}
			int numIndex=random.nextInt(99999)+10000; 
			key+=numIndex;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
			String mail = "dlsdlqordl@naver.com";
			try {
				helper.setFrom(mail);
				helper.setTo(email);
				
				helper.setSubject("인증번호 입력을 위한 메일 전송");
				helper.setText("[기억하길] 인증번호["+key+"]를 입력해 주세요." ,true);
				javaMailSender.send(message);
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
	        return key;
		}
		
		@PostMapping("/FindpwSendEmail")
		@ResponseBody
		//비밀번호 찾기시 이메일로 임시 비밀번호 전송
		public String FindSendMail(String email) {
			Random random=new Random(); 
			String key="!@";  

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email); 
			for(int i =0; i<4;i++) {
				int index=random.nextInt(25)+65; 
				key+=(char)index;
			}
			int numIndex=random.nextInt(99999)+10000; 
			key+=numIndex;
			message.setSubject("임시비밀번호을 위한 메일 전송");
			message.setText("[기억하길] 임시비밀번호는: "+key+"입니다.");
			message.setFrom("yob2500@naver.com");
			javaMailSender.send(message);
			
			
	        return key;	
	        }
		
	
}