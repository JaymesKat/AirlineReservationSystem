package edu.miu.ars.service.impl;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.repository.AppUserRepository;
import edu.miu.ars.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
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
    public AppUser save(AppUser appUser) {
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return null;
    }

    @Override
    public boolean update(AppUser appUser, Long id) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
