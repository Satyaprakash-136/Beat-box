 package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;
		
	@Override
	public void saveUser(User user)  
	{
		userRepository.save(user);
	}

	@Override
	public boolean emailExists(User user) 
	{
		User existingUser=userRepository.findByEmail(user.getEmail());
		
		if(existingUser!=null)
		{
			System.out.println("present");
			return true;
		}
		else
		{
			System.out.println("Absent");
			return false;
		}
	}

	@Override
	public boolean validUser(String email, String password) {
		{
			//email is passed to the db and based on that db returns an user entity that would contain all data
			User user=userRepository.findByEmail(email);
			
			//only password is further taken out to compare
			String dbpwd=user.getPassword();
			//if matched he is a valid user and redirected to form page
			if(password.equals(dbpwd))
			{
				return true;
			}
			else {
				return false;
			}
		}
			
	}

	@Override
	public String getRole(String email) {
		User user=userRepository.findByEmail(email);
		return user.getRole();
	}
	
	
	

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}
}


