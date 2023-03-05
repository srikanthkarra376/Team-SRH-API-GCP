package com.collectifjadore.api.service.impl;

import com.collectifjadore.api.models.ERole;
import com.collectifjadore.api.models.Role;
import com.collectifjadore.api.repository.RoleRepository;
import com.collectifjadore.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Set<Role> getRoles( Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "editor":
                        Role editorRole = roleRepository.findByName(ERole.ROLE_CONTENT_EDITOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(editorRole);

                        break;
                    case "tutor":
                        Role tutorRole = roleRepository.findByName(ERole.ROLE_TUTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(tutorRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }
}
