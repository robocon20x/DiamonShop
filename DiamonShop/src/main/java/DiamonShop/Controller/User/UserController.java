package DiamonShop.Controller.User;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Entity.Users;
import DiamonShop.Service.User.AccountServiceImpl;

@Controller
public class UserController extends BaseController{
	
	@Autowired
	AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
	
	@RequestMapping(value ="/dang-ky", method = RequestMethod.GET)
	public ModelAndView Register() {
		
		_mvShare.setViewName("user/account/register");
		_mvShare.addObject("user",new Users() );
		
		
		return _mvShare;
	}
	
	@RequestMapping(value ="/dang-ky", method = RequestMethod.POST)
	public ModelAndView CreateAcc(@ModelAttribute("user") Users user) {
		int count  = accountServiceImpl.AddAccount(user);
		if(count>0) {
			_mvShare.addObject("status", true);
		}
		else {
			_mvShare.addObject("status", false);
		}
		
		_mvShare.setViewName("user/account/register");		
		
		return _mvShare;
	}
	
	@RequestMapping(value ="/dang-nhap", method = RequestMethod.POST)
	public ModelAndView Login(HttpSession session ,@ModelAttribute("user") Users user) {
		boolean check = accountServiceImpl.CheckAccount(user);
		if(check) {
			_mvShare.setViewName("redirect:/trang-chu");	
			session.setAttribute("LoginInfo", user);
			_mvShare.addObject("statusLogin", true);
		}
		else {
			_mvShare.addObject("statusLogin", false);
		}
	
		
		return _mvShare;
	}
}
