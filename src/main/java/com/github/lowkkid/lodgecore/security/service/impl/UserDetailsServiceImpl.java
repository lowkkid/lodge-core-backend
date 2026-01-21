package com.github.lowkkid.lodgecore.security.service.impl;

import com.github.lowkkid.lodgecore.security.model.UserDetailsImpl;
import com.github.lowkkid.lodgecore.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with name " + username + " not found"));

        return new UserDetailsImpl(
                user.getUsername(), user.getPassword(), user.getRole(), user.getId(), user.getAvatar());
    }
}
