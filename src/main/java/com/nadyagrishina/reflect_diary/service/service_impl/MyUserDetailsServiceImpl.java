package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.repository.UserRepository;
import com.nadyagrishina.reflect_diary.secutiry.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new MyUserDetails(user);
    }
}
