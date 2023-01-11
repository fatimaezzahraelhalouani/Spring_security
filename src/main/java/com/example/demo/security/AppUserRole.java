package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.security.AppUserPermission.*;

public enum AppUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ));
    private final Set<AppUserPermission> permissions;
    AppUserRole(Set<AppUserPermission> permissions) { this.permissions = permissions;
    }
    public Set<AppUserPermission> getPermissions() { return permissions;
    }
}
