package com.shodom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shodom.model.WebUser;
import com.shodom.repository.WebUserRepository;

@Service
public class MongoDBUserService implements UserDetailsService {

	@Autowired
	private WebUserRepository userRepository;
	
	
	@Override
	@SuppressWarnings("unused")
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetails loadedUser;

        try {
        	WebUser user = userRepository.getUser(userName);
            loadedUser = new User(user.getUserName(), user.getPassword(), user.getUserRole());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        
        return loadedUser;
	}

}
