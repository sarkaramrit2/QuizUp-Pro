package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.AdminDao;
import com.entity.QuestionBank;


@Controller
public class AdminController {

	@Autowired
	AdminDao adminDao;
	
	@RequestMapping(value="addQues.htm",method=RequestMethod.GET)
	public String showQuesForm(Model model) {
		return "AddQuestion";
	}
	
	
	@RequestMapping(value="add.htm",method=RequestMethod.POST)
	public String addQuestion(HttpServletRequest request,Model model) 
	{
		QuestionBank q = new QuestionBank();
		int ques_id =adminDao.maxKey("QuestionBank","ques_id")+1;
		q.setQues_id(ques_id);
		q.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
		q.setOption1(request.getParameter("option1"));
		q.setOption2(request.getParameter("option2"));
		q.setOption3(request.getParameter("option3"));
		q.setOption4(request.getParameter("option4"));
		q.setQuestion(request.getParameter("question"));
		q.setRight_answer(Integer.parseInt(request.getParameter("right_answer")));
		q.setQues_audio(null);
		q.setQues_image(null);
		int i=adminDao.addQues(q);
		model.addAttribute("success_entry",i);
		return "Admin_Console";
	}
	
	@RequestMapping(value="showQues.htm",method=RequestMethod.GET)
	public String showQuesEntry(Model model) {
		return "showQues";
	}
	
	
	@RequestMapping(value="show.htm",method=RequestMethod.POST)
	public String showQuestion(HttpServletRequest request,Model model) {
		int ques_id=Integer.parseInt(request.getParameter("ques_id"));
		QuestionBank q = adminDao.showQues(ques_id);
		model.addAttribute("cat_id",q.getCategory_id());
		model.addAttribute("question",q.getQuestion());
		model.addAttribute("option1",q.getOption1());
		model.addAttribute("option2",q.getOption2());
		model.addAttribute("option3",q.getOption3());
		model.addAttribute("option4",q.getOption4());
		model.addAttribute("right_answer",q.getRight_answer());	
		return "successful_show";
	}
	
	
	
	@RequestMapping(value="modifyQues.htm",method=RequestMethod.GET)
	public String modifyQuesEntry(Model model) {
		return "ModifyQuestion";
	}
	
	
	@RequestMapping(value="show1.htm",method=RequestMethod.POST)
	public String modifyshowQuestion(HttpServletRequest request,Model model) {
		int ques_id=Integer.parseInt(request.getParameter("ques_id"));
		QuestionBank q = adminDao.showQues(ques_id);
		model.addAttribute("ques_id",ques_id);
		model.addAttribute("cat_id",q.getCategory_id());
		model.addAttribute("question",q.getQuestion());
		model.addAttribute("option1",q.getOption1());
		model.addAttribute("option2",q.getOption2());
		model.addAttribute("option3",q.getOption3());
		model.addAttribute("option4",q.getOption4());
		model.addAttribute("right_answer",q.getRight_answer());	
		return "ModifyCurrentQuestion";
	}
	
	
	
	@RequestMapping(value="mod.htm",method=RequestMethod.POST)
	public String modifyQuestion(HttpServletRequest request,Model model) {
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		int ques_id = Integer.parseInt(request.getParameter("ques_id"));
		String question=request.getParameter("question");
		String option1=request.getParameter("option1");
		String option2=request.getParameter("option2");
		String option3=request.getParameter("option3");
		String option4=request.getParameter("option4");
		int right_answer = Integer.parseInt(request.getParameter("right_answer"));
		int success = adminDao.modifyQues(ques_id, category_id, question, option1, option2, option3, option4, right_answer);
		model.addAttribute("success_modify",success);
		return "Admin_Console";
	}
	
	
	@RequestMapping(value="deleteQues.htm",method=RequestMethod.GET)
	public String deleteQuesEntry(Model model) {
		return "deleteQues";
	}
	
	@RequestMapping(value="home.htm",method=RequestMethod.GET)
	public String home(Model model) {
		return "Admin_Console";
	}
	
	
	@RequestMapping(value="delete.htm",method=RequestMethod.POST)
	public String deleteQuestion(HttpServletRequest request,Model model) {
		int ques_id = Integer.parseInt(request.getParameter("ques_id"));
		int success = adminDao.deleteQues(ques_id);
		model.addAttribute("success_delete",success);
		return "successful_delete";
	}
	
	@RequestMapping(value="addCat.htm",method=RequestMethod.GET)
	public String addCat(Model model) {
		return "addCat";
	}
	
	
	@RequestMapping(value="Cat.htm",method=RequestMethod.POST)
	public String addCategory(HttpServletRequest request,Model model) {
		int category_id =adminDao.maxKey("Category","category_id")+1;
		String category_name=request.getParameter("category_name");
		String category_logo=request.getParameter("category_logo");
		String category_desc=request.getParameter("category_desc");
		int success = adminDao.addCategory(category_id,category_name, category_logo,  category_desc);
		model.addAttribute("success_cat_add",success);
		return "Admin_Console";
	}
	
	@RequestMapping(value="showCat.htm",method=RequestMethod.GET)
	public String showCat(Model model) {
		List<String> lst = adminDao.showCat();
		model.addAttribute("List",lst);
		return "showCategory";
	}
	
	@RequestMapping(value="showQuesinCat.htm",method=RequestMethod.POST)
	public String showQuesinCat(HttpServletRequest request,Model model) {
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		List<QuestionBank> q = adminDao.allQuesInCat(category_id);
		model.addAttribute("allQues", q);
		return "successful_ques_cat_show";
	}
	
}
