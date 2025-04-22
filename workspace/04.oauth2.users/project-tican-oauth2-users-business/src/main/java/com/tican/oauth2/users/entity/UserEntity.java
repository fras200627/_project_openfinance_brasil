package com.tican.oauth2.users.entity;

import com.tican.oauth2.users.enums.UserType;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//Schema+table Spring-Security defaults
@Table(schema = "user", name = "user_entity")
@Entity(name = "UserEntity")
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Data @Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name= "ID")
    private Long id;

    @Column(name= "NAME")
    @ColumnTransformer(write="LOWER(?)")
    private String name;

    @Column(name= "EMAIL")
    @ColumnTransformer(write="LOWER(?)")
    private String email;
    
    @Column(name= "PASSWORD")
    private String password;

    @Column(name= "TYPE")
    private String type;

    @Column(name= "CREATED_AT")
    private Date createdAt;

    @Column(name= "STATUS")
    private String status;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.type.equals(UserType.ADMIN)) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (this.status.equals("EXPIRED")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.status.equals("LOCKED")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (this.status.equals("EXPIRED")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEnabled() {
        if (this.status.equals("ENABLED")) {
            return true;
        } else {
            return false;
        }
    }
    
}
