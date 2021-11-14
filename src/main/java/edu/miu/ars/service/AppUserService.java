package edu.miu.ars.service;

import edu.miu.ars.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends GenericService<AppUser>, UserDetailsService {
    AppUser getByEmail(String email);
}
