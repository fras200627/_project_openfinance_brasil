package com.ofb.lib.security.profiles;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('USER') or hasAuthority('SCOPE_CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ')")
public @interface CanPermissionsCustomersPersonalIdentificationsRead {
}
