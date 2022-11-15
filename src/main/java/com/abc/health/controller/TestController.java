package com.abc.health.controller;

import java.util.HashMap;
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
import com.abc.health.dto.TestDTO;
import com.abc.health.exception.DuplicateRecordException;
import com.abc.health.form.CenterForm;
import com.abc.health.form.TestForm;
import com.abc.health.service.CenterServiceInt;
import com.abc.health.service.TestServiceInt;

@Controller
public class TestController extends BaseCtl {

private Logger log = Logger.getLogger(CenterController.class.getName());
	
	@Autowired
	private CenterServiceInt centerService;
	@Autowired
	private TestServiceInt testService;
	
	@ModelAttribute
	public void preload(Model model) {
		System.out.println("model "+centerService.search(null).size());
		model.addAttribute("centerList", centerService.search(null));
	}
	
	
	@GetMapping("/home/login/test")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") TestForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			TestDTO bean = testService.findBypk(id);
			form.populate(bean);
		}
		return "test";
	}

	@PostMapping("/home/login/test")
	public String submit(@Valid @ModelAttribute("form") TestForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/test";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "test";
				}
				TestDTO bean = (TestDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					bean.setCenter(centerService.findBypk(form.getCenterId()));
					bean.setCenterId(form.getCenterId());
					testService.update(bean);
					model.addAttribute("success", "Test update Successfully!!!!");
				} else {
					System.out.println("form ==> "+form.getCenterId());
					bean.setCenter(centerService.findBypk(form.getCenterId()));
					bean.setCenterId(form.getCenterId());
					testService.add(bean);
					model.addAttribute("success", "Test Added Successfully!!!!");
				}
				return "test";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "test";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/test/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") TestForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/test/search";
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

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					TestDTO dto = new TestDTO();
					dto.setId(id);
					testService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		TestDTO dto = (TestDTO) form.getDTO();
		
		List<TestDTO> list = testService.search(dto, pageNo, pageSize);
		List<TestDTO> totallist = testService.search(dto);
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
		return "testList";
	}
}
