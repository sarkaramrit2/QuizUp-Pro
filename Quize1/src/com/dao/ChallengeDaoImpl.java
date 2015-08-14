package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Challenge;
import com.entity.User;

public class ChallengeDaoImpl implements ChallengeDaoI {

	private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate1) {
		this.hibernateTemplate = hibernateTemplate1;
	}


	@Override
	public List<String> getChallenge(int uid) {
	
		String hql="select challenger_id from Challenge where challengee_id=? and completion=?";
		Object[] params={uid,false};
		List<Integer> lst=hibernateTemplate.find(hql,params);
		List<String> names=new ArrayList<String>();
		for(Integer i:lst){
			hql="select username from User where user_id=?";
			Object[] par={i};
			names.addAll(hibernateTemplate.find(hql,par));
		}
		return names;
	}

	@Override
	public String getWinner(int challenger_id,int challengee_id, int score2) {
		
		String hql="from Challenge where challenger_id=? and challengee_id=?";
		Object[] params={challenger_id,challengee_id};
		List<Challenge> lst=hibernateTemplate.find(hql,params);
		Challenge c=lst.get(0);
		int score1=c.getScore1(); int winnerId;
		if(score2>score1)
			winnerId=challengee_id;
		else if(score2<score1)
			winnerId=challenger_id;
		else
			winnerId=-1;
		c.setScore2(score2);
		c.setWinner_id(winnerId);
		c.setCompletion(true);
		hibernateTemplate.update(c);
		if(winnerId!=-1){
			hql="select username from User where user_id=?";
			Object[] params1={winnerId};
			List<String> lst1=hibernateTemplate.find(hql,params1);
			return lst1.get(0);
		}
		return "Tied";
	}

	
	@Override
	public User getChallenger(String challengerName) {
		
		User challenger=hibernateTemplate.get(User.class,challengerName);
		return challenger;
	}
	
	
	
}
