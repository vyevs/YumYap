package com.yumyap.service;

import java.sql.Date;
import java.util.List;

import com.yumyap.beans.*;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

public interface ServiceInterface {

	// CREATE
	public UserDto createUser(UserDto userDto);
//	public User createUser(String email, String password, String firstname, String lastname);
	// food is added to the database
	public boolean addFood(Food food);
	
	// Verify that user != follower
	// follower is added to user's list of followers
	public boolean addFollower(User user, User follower);
	public RecipesDto addRecipe(RecipesDto recipeDto);
	// comment is added to recipe's list of comments
	public boolean addComment(Recipe recipe, String comment, UserDto user);
	
	// READ
	// Return a list of user's followers
	public List<User> getFollowing(User user);

	//Gets user's dashboard
	public RecipesDto getDashboard(UserDto user, int page);
	
	// UPDATE
	// rating is added to recipe's rating
	public boolean favoriteRecipe(Recipe recipe, UserDto user);
	public boolean deactivateUser(int userId);

	
	// VALIDATION
	public boolean validateUser(String username, String password);
	public boolean isEmailValid(String email);
	public boolean isEmailAvailable(String email);
	public boolean isUsernameAvailable(String username);
	public UserDto validateUser(UserDto userDto);
	public UserDto logoutUser(UserDto userDto);

}
