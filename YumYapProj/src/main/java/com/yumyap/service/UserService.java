package com.yumyap.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yumyap.beans.Comment;
import com.yumyap.beans.Recipe;
import com.yumyap.beans.User;
import com.yumyap.dao.Dao;
import com.yumyap.dto.ProfileDto;
import com.yumyap.dto.RecipeDto;
import com.yumyap.dto.RecipesDto;
import com.yumyap.dto.UserDto;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private Dao DaoImpl;

	public UserService() {
	}

	public UserService(Dao DaoImpl) {
		super();
		this.DaoImpl = DaoImpl;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// Validation
		if (userDto == null)
			return null;
		if (userDto.getEmail().equals("") || userDto.getFirstname().equals("") ||
				userDto.getLastname().equals("") || userDto.getPassword().equals("")) {
			System.out.println("One or more required fields are empty");
			return null;
		}
		User thisUser = DaoImpl.getUser(userDto.getEmail());
		if (thisUser != null && thisUser.getEmail().equals(userDto.getEmail())) {
			System.out.println("A user already exists with the given email");
			return null;
		}
		if (!isEmailValid(userDto.getEmail())) {
			System.out.println("Given email is invalid");
			return null;
		}
		
		try {
			User user = new User(userDto);
			user.setActive(1);
			user = DaoImpl.addUser(user);
			userDto.setId(user.getId());
		
			return userDto;
			
		} catch (DataIntegrityViolationException e) {
			// This catch block should never be reached. Table validation is
			// done before the try block
			System.out.println("A constraint on the user table was violated");
			return null;
		}
	}
	@Override
	public boolean addFollowing(User user, User follower) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addComment(RecipesDto recipeDto, Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getFollowing(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean favoriteRecipe(RecipeDto recipe, UserDto user) {
		List<Recipe> recipes = user.getFavoriteRecipes();
		recipes.add(new Recipe(recipe));
		User u = new User(user);
		DaoImpl.updateUser(u);
		return true;
	}
	@Override
	public RecipesDto getDashboard(UserDto user, int page) {
		Set<User> following = user.getFollowing();
		Comparator<Recipe> byCreation = (Recipe o1, Recipe o2) -> o1.getCreated().compareTo(o2.getCreated());
		Set<Recipe> recipes = new ConcurrentSkipListSet<>(byCreation);
		for (User u : following) {
			recipes.addAll(u.getFavoriteRecipes());
		}
		List<RecipeDto> recs = new ArrayList<RecipeDto>();
		Iterator<Recipe> r = recipes.iterator();
		ArrayList<String> desc = new ArrayList<String>();
		while(r.hasNext()) {
			recs.add(new RecipeDto(r.next()));
		}
		return new RecipesDto(recs);
	}

	
	@Override
	public ProfileDto getProfile(String email) {
		ProfileDto profile = new ProfileDto();
		User user = DaoImpl.getUser(email);
		List<Recipe> recs = user.getFavoriteRecipes();
		profile.setRecipes(recs);
		profile.setUser(user);
		return null;
	}

	@Override
	public boolean deactivateUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto validateUser(UserDto userDto) {
		User user = DaoImpl.getUser(userDto.getEmail());

		if (user != null && (user.getPassword().equals(userDto.getPassword()))) {
			System.out.println("setting userDto to true");
			userDto.setLoggedIn(true);
			
		} else {
			return null;
		}
		System.out.println("returning user dto" + userDto.toString());
		return userDto;
	}
	
	@Override
	public boolean isEmailValid(String email) {
		if (email.contains("@") && email.contains(".com"))
			if (email.lastIndexOf('@') < email.lastIndexOf(".com"))
				return true;

		System.out.println("Email addresses must contain a valid domain such as \"something@something.com\"");
		return false;
	}

	@Override
	public UserDto logoutUser(UserDto userDto) {
		if (!userDto.isLoggedIn()) {
			System.out.println("No user is currently logged in");
			return userDto;
		}
		System.out.println("Logging out user: " + userDto.getEmail());
		userDto.setLoggedIn(false);
		return userDto;
	}



}
