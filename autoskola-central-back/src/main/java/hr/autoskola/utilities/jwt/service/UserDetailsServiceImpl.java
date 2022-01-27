package hr.autoskola.utilities.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.utilities.jwt.model.User;
import hr.autoskola.utilities.jwt.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String korIme) {
		User user = userRepository.getEntity(korIme);
		
		return UserDetailsImpl.build(user);
	}

}
