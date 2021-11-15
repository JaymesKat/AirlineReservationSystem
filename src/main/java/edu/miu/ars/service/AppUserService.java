package edu.miu.ars.service;


import edu.miu.ars.domain.AppUser;

public interface AppUserService extends GenericService<AppUser> {
    AppUser getByEmail(String email);
}
