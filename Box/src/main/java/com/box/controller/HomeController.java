package com.box.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.box.dao.ContactDAO;
import com.box.model.Contact;
import com.box.model.User;
import com.box.services.AuthenticationService;



/**
 * This controller routes accesses to the application to the appropriate hanlder
 * methods.
 * 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;
	
	//@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/")
	public ModelAndView login(ModelAndView model) throws IOException {
		User newUser = new User();
		model.addObject("user", newUser);
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("login");

		return model;
	}

	// @RequestMapping(value="/")
	@RequestMapping(value = "/home")
	public ModelAndView listContact(ModelAndView model, @ModelAttribute User user) throws IOException {	
		// testing REST based call
		CoachingEngineController cec = new CoachingEngineController();
		ModelMap mm = new ModelMap();
		cec.getTest(mm);
		System.out.println (mm.get("test") + "<<<<<<------------");
		
		// authenticate user
        authenticationService = new AuthenticationService();
        boolean isAuthenticated = authenticationService.isAuthenticated(user);
		if (isAuthenticated){
			List<Contact> listContact = contactDAO.list();
			model.addObject("listContact", listContact);
			model.setViewName("home");
		}
		return model;
	}

	@RequestMapping(value = "/testCoachingEngine")
	public ModelAndView testCoachingEngine(ModelAndView model) throws IOException {	
		// testing REST based call
		CoachingEngineController cec = new CoachingEngineController();
		ModelMap mm = new ModelMap();
		cec.getTest(mm);
		System.out.println (mm.get("test") + "<<<<<<------------");
		
		model.addObject("result", mm.get("test"));
		model.setViewName("testCoachingEngine");
		return model;
	}
	
	@RequestMapping(value = "/backToHome")
	public ModelAndView backToListContact(ModelAndView model, @ModelAttribute User user) throws IOException {
			List<Contact> listContact = contactDAO.list();
			model.addObject("listContact", listContact);
			model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactDAO.saveOrUpdate(contact);
		return new ModelAndView("redirect:/backToHome");
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/backToHome");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}
}
