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

import com.abc.health.dto.AppointmentDTO;
import com.abc.health.dto.BookedTestDTO;
import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.TestDTO;
import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;
import com.abc.health.form.AppointmentForm;
import com.abc.health.form.CenterForm;
import com.abc.health.form.TestForm;
import com.abc.health.service.AppointmentServiceInt;
import com.abc.health.service.BookedTestServiceInt;
import com.abc.health.service.CenterServiceInt;
import com.abc.health.service.TestServiceInt;
import com.abc.health.service.UserServiceInt;
import com.abc.health.util.DataUtility;

@Controller
public class AppointmentController extends BaseCtl {

	private Logger log = Logger.getLogger(AppointmentController.class.getName());
	protected static final String OP_CONFIRMAPPOINTMENT = "Confirm Appointment";
	protected static final String OP_APPROVEAPPOINTMENT = "Approve Appointment";
	protected static final String OP_CANCELAPPOINTMENT = "Cancel Appointment";
	@Autowired
	private CenterServiceInt centerService;

	@Autowired
	private UserServiceInt userService;

	@Autowired
	private AppointmentServiceInt appointmentService;

	@Autowired
	private TestServiceInt testService;
	
	@Autowired
	private BookedTestServiceInt bookedTestService;

	@ModelAttribute
	public void preload(Model model) {
		System.out.println("model " + centerService.search(null).size());
		model.addAttribute("list", centerService.search(null));

		model.addAttribute("testList", testService.search(null));
	}

	@GetMapping("/home/login/appointment")
	public String display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AppointmentForm form, HttpSession session, Model model) {

		return "appointment";
	}

	@GetMapping("/home/login/appointment/test")
	public String displayTests(@RequestParam(required = false) Long id, Long pId,@ModelAttribute("form") TestForm form,
			@ModelAttribute("form2") AppointmentForm form2, HttpSession session, Model model) {
		System.out.println("center id " + id);
		//if (id > 0) {
			List<TestDTO> bean = testService.listByCenterId(id);
			model.addAttribute("list", bean);
		//}
		session.setAttribute("id", id);
		model.addAttribute("centerId", id);
		return "confirmAppointment";
	}

	@PostMapping("/home/login/appointment/test")
	public String submit(@RequestParam(required = false) Long id, @Valid @ModelAttribute("form2") AppointmentForm form,
			BindingResult bindingResult, HttpSession session, Model model, HttpServletRequest request)
			throws DuplicateRecordException {
		System.out.println("form " + request.getParameter("timeslot"));
		System.out.println("center id " + request.getParameter("id"));
		System.out.println("form " + form.getTimeSlot());
		
		System.out.println("op " + form.getOperation());
		Long sessionId = 0L;
		if (OP_CONFIRMAPPOINTMENT.equalsIgnoreCase(form.getOperation())) {			
			AppointmentDTO bean = (AppointmentDTO) populateDTO(form.getDTO(), request);
			sessionId = (Long) session.getAttribute("id");
			bean.setCenterId((Long) session.getAttribute("id"));
			bean.setCenter(centerService.findBypk(sessionId));
			UserDTO userBean = (UserDTO) session.getAttribute("user");
			Long userId = userBean.getId();
			bean.setUserId(userId);
			bean.setUser(userService.findBypk(userId));
			bean.setStatus("ADD");
			String ids[] = (request.getParameterValues("ids"));
			System.out.println("arra "+ids.length);
			
			Long pk =  appointmentService.add(bean);
			
			for (String testid : ids) {
				BookedTestDTO bookedTestDTO = new BookedTestDTO();
				bookedTestDTO.setTestId(DataUtility.getLong(testid));
				bookedTestDTO.setBookedTest(testService.findBypk(DataUtility.getInt(testid)));
				System.out.println("Long id = " + DataUtility.getInt(testid));
				bookedTestDTO.setAppointmentId(pk);
				bookedTestDTO.setAppointment(appointmentService.findBypk(pk));
				bookedTestService.add(bookedTestDTO);
			}			
			
			model.addAttribute("success", "Appoinment Added Successfully! Your Appintment Id is: APP_ID_"+bean.getId());
		}
		List<TestDTO> bean = testService.listByCenterId(sessionId);
		model.addAttribute("list", bean);
		
			return "confirmAppointment";
	}
	@RequestMapping(value = "/home/login/appointment/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AppointmentForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) throws DuplicateRecordException {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/appointment/search";
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
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					appointmentService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		if (OP_APPROVEAPPOINTMENT.equals(operation)) {
			pageNo = 1;
			System.out.println("ids "+form.getIds());
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					System.out.println("id "+id);
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					dto.setStatus("APPROVED");
					appointmentService.update(dto);
				}
				model.addAttribute("success", "Appointment Approved Successfully!!!");
			} else {
				model.addAttribute("error", "Select one record");
			}
		}
		if (OP_CANCELAPPOINTMENT.equals(operation)) {
			pageNo = 1;
			System.out.println("ids "+form.getIds());
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					System.out.println("id "+id);
					AppointmentDTO dto = new AppointmentDTO();
					dto.setId(id);
					dto.setStatus("CANCEL");
					appointmentService.cancelAppointment(dto);
				}
				model.addAttribute("success", "Appointment Canceled Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AppointmentDTO dto = (AppointmentDTO) form.getDTO();
		
		List<AppointmentDTO> list = appointmentService.search(dto, pageNo, pageSize);
		List<AppointmentDTO> totallist = appointmentService.search(dto);
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
		return "appointmentList";
	}
}
