package com.yumyap.dto;

import java.util.List;
import java.util.Set;

import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;

public class UserDto {

	private int id;

	private Set<User> following;

	private String firstname;

	private String lastname;

	private String username;

	private String password;

	private String email;

	private int active;

	private List<Recipe> favoriteRecipes;

	private int loggedIn;

	public UserDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public int getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", following=" + following + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", active=" + active
				+ ", favoriteRecipes=" + favoriteRecipes + ", loggedIn=" + loggedIn + "]";
	}

}