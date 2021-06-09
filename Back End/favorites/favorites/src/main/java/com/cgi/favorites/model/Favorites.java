package com.cgi.favorites.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favorites {
	@Id
	private String favId;
	private String uniqueId;
	private String team1;
	private String team2;
	private String userId;
	
	
	public String getFavId() {
		return favId;
	}
	public void setFavId(String favId) {
		this.favId = favId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Favorites(String favId, String uniqueId, String team1, String team2, String userId) {
		super();
		this.favId = favId;
		this.uniqueId = uniqueId;
		this.team1 = team1;
		this.team2 = team2;
		this.userId = userId;
	}
	public Favorites() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Favorites [favId=" + favId + ", uniqueId=" + uniqueId + ", team1=" + team1 + ", team2=" + team2
				+ ", userId=" + userId + "]";
	}
	
	

}
