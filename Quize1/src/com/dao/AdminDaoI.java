package com.dao;

import java.util.List;

import com.entity.QuestionBank;

public interface AdminDaoI {

	public int addQues(QuestionBank q);
	public int modifyQues(int ques_id, int cat_id, String question, String option1, String option2, String option3, String option4, int right_answer);
	public int deleteQues(int ques_id);
	public int addCategory(int cat_id, String cat_name, String cat_logo, String cat_desc);
	public QuestionBank showQues(int ques_id);
	public List<String> showCat();
	public List<QuestionBank> allQuesInCat(int cat_id);
	public int updatePassword(int admin_id, String pass);
	public int maxKey(String table_name, String id);
}
