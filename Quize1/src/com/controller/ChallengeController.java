package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.ChallengeDaoImpl;
import com.entity.User;

@Controller
public class ChallengeController {

	@Autowired
	private ChallengeDaoImpl challengedao;

	public void setDao(ChallengeDaoImpl challengedao) {
		this.challengedao = (ChallengeDaoImpl) challengedao;
	}
	
	@RequestMapping(value="viewchallenges.htm",method=RequestMethod.GET)
	public String getChallengeRequests(HttpServletRequest request,Model model){
		
		HttpSession sess=request.getSession(false);
		if(sess!=null){
			
			List<String> challengerList=challengedao.getChallenge((Integer)sess.getAttribute("user_id"));
			model.addAttribute("challengerList",challengerList);
			return "challengelist";
		}
		return "";
	}
	
	@RequestMapping(value="startchallenge.htm",method=RequestMethod.GET)
	public String startChallenge(HttpServletRequest request,Model model){
		
		String challengerName=request.getParameter("challengerName");
		model.addAttribute("challenger",getChallenger(challengerName));
		return "startquiz";
		
	}
	
	@RequestMapping(value="getChallenger.htm",method=RequestMethod.GET)
	public User getChallenger(String challengerName){
		return challengedao.getChallenger(challengerName);		
	}

	@RequestMapping(value="getWinner.htm",method=RequestMethod.GET)
	public String getWinner(HttpServletRequest request,@ModelAttribute("challenger") User challenger,Model model){
		HttpSession sess=request.getSession(false);
		if(sess!=null){
			int score=0;
			int userId = (Integer)sess.getAttribute("user_id");
			String winner=challengedao.getWinner(challenger.getUser_id(),userId,score);
			model.addAttribute("winner",winner);
			return "challengelist";
		}
		return "";
	}
}
