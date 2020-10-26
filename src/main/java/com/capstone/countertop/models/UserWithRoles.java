package com.capstone.countertop.models;

import com.capstone.countertop.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserWithRoles extends User implements UserDetails {
    public UserWithRoles(User user) { // takes an input argument of a 'User'
        super(user); // this returns a copy of the superclass, which is 'User'

        // Seems redundant, doesn't it?
        // Send in a user to the constructor, get a user out of it..... huh?
        // This is so we can use polymorphism to have an object that looks EXACTLY like a user to other parts of our code, but can do additional things, like those in the UserDetails interface.
        // Let's see that now!
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
