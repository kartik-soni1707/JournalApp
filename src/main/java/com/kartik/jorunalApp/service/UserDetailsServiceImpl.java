package com.kartik.jorunalApp.service;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserEntryRepo urepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u =urepo.findByname(username);
        if (u!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(u.getName())
                    .password(u.getPassword())
                    .roles(u.getRoles().toArray(new String[0]))
                    .build();

        }
        throw new UsernameNotFoundException("username not found");
    }
}
