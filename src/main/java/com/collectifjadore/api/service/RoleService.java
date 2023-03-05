package com.collectifjadore.api.service;

import com.collectifjadore.api.models.Role;

import java.util.Set;

public interface RoleService {
    Set<Role>  getRoles(Set<String> strRoles);
}
