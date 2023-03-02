package com.uniovi.sdi2223307spring.services;

import org.springframework.stereotype.*;

@Service
public class RolesService {
    String[] roles = {"ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN"};

    public String[] getRoles() {
        return roles;
    }
}
