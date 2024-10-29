package com.kodnest.tunehub.service;

import com.kodnest.tunehub.entity.User;

public interface UserService 

{
public void saveUser(User user);

public boolean emailExists(User user);

public boolean validUser(String email, String password);

public String getRole(String email);

public static User getUser(String mail) {
	
	return null;
}

public void updateUser(User user);
}