package edu.miu.ars.service.impl;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.repository.AppUserRepository;
import edu.miu.ars.service.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements IAppUser {

    @Autowired
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser getByEmail(String email) {
        return null;
    }

    @Override
    public AppUser addAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> getAppUsers() { //pagination
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public AppUser updateAppUser(long id, AppUser appUser) {
        AppUser updateAppUser = appUserRepository.findById(id).orElse(null);
        if(updateAppUser != null) {
            updateAppUser.setFirstName(appUser.getFirstName());
            updateAppUser.setLastName(appUser.getLastName());
            updateAppUser.setEmail(appUser.getEmail());
            // TODO: Parse date string into Date Object
            updateAppUser.setDateOfBirth(appUser.getDateOfBirth());

            return appUserRepository.save(updateAppUser);
        }
        return null;
    }

    @Override
    public AppUser removeAppUser(long id) {
        AppUser user= appUserRepository.getById(id);
        if(user != null)
             appUserRepository.delete(user);
        return user;
    }
}
