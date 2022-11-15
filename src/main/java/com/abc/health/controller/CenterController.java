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
import com.abc.health.exception.DuplicateRecordException;
import com.abc.health.form.CenterForm;
import com.abc.health.service.CenterServiceInt;


@Controller
public class CenterController extends BaseCtl {

	private Logger log = Logger.getLogger(CenterController.class.getName());
	
	@Autowired
	private CenterServiceInt service;
	
	@GetMapping("/home/login/center")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") CenterForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			CenterDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "center";
	}

	@PostMapping("/home/login/center")
	public String submit(@Valid @ModelAttribute("form") CenterForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/center";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "center";
				}
				CenterDTO bean = (CenterDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Center update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Center Added Successfully!!!!");
				}
				return "center";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "center";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/center/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") CenterForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/center/search";
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
					CenterDTO dto = new CenterDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		CenterDTO dto = (CenterDTO) form.getDTO();
		
		List<CenterDTO> list = service.search(dto, pageNo, pageSize);
		List<CenterDTO> totallist = service.search(dto);
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
		return "centerList";
	}
	
}
