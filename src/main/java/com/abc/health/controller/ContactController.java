package com.abc.health.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.ContactDTO;
import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;
import com.abc.health.form.CenterForm;
import com.abc.health.form.ContactForm;
import com.abc.health.form.UserRegistrationForm;
import com.abc.health.service.ContactServiceInt;

@Controller
public class ContactController extends BaseCtl{
	
	private Logger log = Logger.getLogger(ContactController.class.getName());
	
	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_REGISTER = "Register";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_CONFIRM = "Confirm";
	@Autowired
	private ContactServiceInt contactServiceInt;
	
	@GetMapping("/contact")
	public String display(@RequestParam(required = false) Long id,@ModelAttribute("form") ContactForm form, Model model, HttpSession session) {
		log.info("ContactCtl signUp display method start");
		
			log.info("ContactCtl signUp display method End");
		return "contact";
	}

	@PostMapping("/contact")
	public String submitContact(@RequestParam String operation, @Valid @ModelAttribute("form") ContactForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		log.info("ContactController submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/contact";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "contact";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				ContactDTO entity = (ContactDTO) populateDTO(form.getDTO(), request);
				contactServiceInt.add(entity);
				model.addAttribute("success", "Message is Send Successfully!!!!");
				return "contact";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "contact";
		}

		log.info("ContactController submit method end");
		return "contact";
	}
	
	@RequestMapping(value = "/home/login/contact/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ContactForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/contact/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} 
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		
		ContactDTO dto = (ContactDTO) form.getDTO();
		
		List<ContactDTO> list = contactServiceInt.search(dto, pageNo, pageSize);
		List<ContactDTO> totallist = contactServiceInt.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "contactList";
	}
}
