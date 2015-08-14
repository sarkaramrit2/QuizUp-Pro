package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Admin;
import com.entity.Category;
import com.entity.QuestionBank;

public class AdminDao implements AdminDaoI{


	private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public int addQues(QuestionBank q) {
		hibernateTemplate.save(q);
		return 1;
	}

	@Override
	public int modifyQues(int ques_id, int cat_id, String question,
			String option1, String option2, String option3, String option4,
			int right_answer) {
		// TODO Auto-generated method stub
		QuestionBank q = (QuestionBank)hibernateTemplate.get(QuestionBank.class, ques_id);
		q.setCategory_id(cat_id);
		q.setOption1(option1);
		q.setOption2(option2);
		q.setOption3(option3);
		q.setOption4(option4);
		q.setQuestion(question);
		q.setRight_answer(right_answer);
		hibernateTemplate.update(q);

		return 0;
	}

	@Override
	public int deleteQues(int ques_id) {
		// TODO Auto-generated method stub
		QuestionBank q=(QuestionBank)hibernateTemplate.get(QuestionBank.class, ques_id);
		hibernateTemplate.delete(q);
		return 1;
	}

	@Override
	public int addCategory(int cat_id, String cat_name, String cat_logo,
			String cat_desc) {
		// TODO Auto-generated method stub
		Category cat = new Category();
		cat.setCategory_desc(cat_desc);
		cat.setCategory_logo(cat_logo);
		cat.setCategory_name(cat_name);
		cat.setCategory_id(cat_id);
		
		hibernateTemplate.save(cat);
		return 1;
	}

	@Override
	public QuestionBank showQues(int ques_id) {
		// TODO Auto-generated method stub
		QuestionBank qb=(QuestionBank)hibernateTemplate.get(QuestionBank.class, ques_id);
		return qb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> showCat() {
		// TODO Auto-generated method stub
		String hql = "select category_name from category";
		List<String> lst = hibernateTemplate.find(hql);
		return lst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBank> allQuesInCat(int cat_id) {
		// TODO Auto-generated method stub
		String hql = "from question_bank where category_id=?";
		Object[] params = {cat_id};
		List<QuestionBank> lst = hibernateTemplate.find(hql,params);
		return lst;
	}

	@Override
	public int updatePassword(int admin_id, String pass) {
		// TODO Auto-generated method stub
		Admin admin=(Admin)hibernateTemplate.get(Admin.class, admin_id);
		admin.setPassword(pass);
		hibernateTemplate.update(admin);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int maxKey(String table_name, String id) {
		// TODO Auto-generated method stub
		String hql = "select max ("+id+") from "+table_name+"";
		List<Integer> lst = hibernateTemplate.find(hql);
		if(lst == null) return 0;
		return lst.get(0);
	}


}