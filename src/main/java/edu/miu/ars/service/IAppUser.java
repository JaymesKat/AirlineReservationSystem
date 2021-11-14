package edu.miu.ars.service;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.domain.Flight;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAppUser {
    AppUser getByEmail(String email);
    AppUser addAppUser(AppUser appUser);
    List<AppUser> getAppUsers();
    AppUser getAppUser(long id);
    AppUser updateAppUser(long id, AppUser appUser);
    AppUser removeAppUser(long id);
}
