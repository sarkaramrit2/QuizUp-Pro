package com.dao;

import java.util.List;

import com.entity.User;

public interface ChallengeDaoI {

	public List<String> getChallenge(int uid);
	public String getWinner(int uid,int challengerId,int score);
	public User getChallenger(String challengerName);
}
