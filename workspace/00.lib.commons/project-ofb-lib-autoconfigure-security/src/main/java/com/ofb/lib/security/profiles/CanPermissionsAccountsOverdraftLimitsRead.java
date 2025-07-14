package com.ofb.lib.security.profiles;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('USER') or hasAuthority('SCOPE_ACCOUNTS_OVERDRAFT_LIMITS_READ')")
public @interface CanPermissionsAccountsOverdraftLimitsRead {
}
