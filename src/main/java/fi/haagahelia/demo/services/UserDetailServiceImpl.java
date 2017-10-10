package fi.haagahelia.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.demo.models.User;
import fi.haagahelia.demo.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private UserRepository userrepo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userrepo) {
		this.userrepo = userrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userrepo.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username,
				u.getPassword(), AuthorityUtils.createAuthorityList(u.getRole()));
		return user;
	}

}
