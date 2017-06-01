package ru.kpfu.itis.NovikovRuslan.authentication;





import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Component;
import ru.kpfu.itis.NovikovRuslan.entities.User;
import ru.kpfu.itis.NovikovRuslan.service.UserService;

import java.util.HashSet;
import java.util.Set;



@Component(value = "authProvider")
@ComponentScan(basePackages = "ru.kpfu.itis.NovikovRuslan")
public class AuthProviderImpl implements AuthenticationProvider {


    @Autowired
    UserService userService;

    public AuthProviderImpl(){

    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String name = authentication.getName();
        System.out.println(name);
        System.out.println(authentication.getCredentials().toString());
        if(userService.findUser(name) == null){
            throw new UsernameNotFoundException("user not found");

        }

        User user = userService.findUser(name);

        String password = authentication.getCredentials().toString();
        String cryptPassword = DigestUtils.md5Hex(password);
        System.out.println(cryptPassword);
        if(!cryptPassword.equals(user.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new UsernamePasswordAuthenticationToken(user, null,grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
