package tn.esprit.com.foyer.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;



@RequiredArgsConstructor
public enum Role {
  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN");

  private final String roleName;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(roleName));
  }

}
