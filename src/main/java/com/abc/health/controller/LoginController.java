package com.abc.health.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;
import com.abc.health.form.LoginForm;
import com.abc.health.form.UserRegistrationForm;
import com.abc.health.service.UserServiceInt;




@Controller
public class LoginController extends BaseCtl {
	
	private Logger log = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private UserServiceInt userServiceInt;

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_REGISTER = "Register";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_CONFIRM = "Confirm";
	
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
	}
	
	@GetMapping("/home/login")
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		log.info("LoginCtl login display method End");
		return "login";
	}
	
	@GetMapping("/home/register")
	public String display(@RequestParam(required = false) Long id,@ModelAttribute("form") UserRegistrationForm form, Model model, HttpSession session) {
		log.info("LoginCtl signUp display method start");
		
			log.info("LoginCtl signUp display method End");
		return "register";
	}
	
	@PostMapping("/home/register")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/register";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "register";
		}

		try {
			if (OP_REGISTER.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				userServiceInt.add(entity);
				model.addAttribute("success", "User Registerd Successfully!!!!");
				return "register";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}

		log.info("LoginCtl signUp submit method end");
		return "register";
	}
	
	@PostMapping("/home/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login";
		}


		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO bean = userServiceInt.authentication((UserDTO) form.getDTO());

		if (bean != null) {
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			return "redirect:/home";
		}

		if (bean == null) {

			model.addAttribute("error", "Login Id & Password Invalid");
		}
		log.info("LoginCtl login submit method End");
		return "login";
	}
	
	

}
