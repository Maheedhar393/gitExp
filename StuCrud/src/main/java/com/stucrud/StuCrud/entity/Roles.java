package com.stucrud.StuCrud.entity;

import java.util.Set;

public enum Roles {

    ADMIN(Set.of(Permissions.STUDENT_READ, Permissions.STUDENT_WRITE, Permissions.STUDENT_DELETE)),
    USER(Set.of(Permissions.STUDENT_READ));

    private final Set<Permissions> permissions;

     Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
