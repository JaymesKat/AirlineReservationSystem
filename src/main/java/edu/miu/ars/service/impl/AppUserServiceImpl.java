package edu.miu.ars.service.impl;

import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.domain.Agent;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.domain.Passenger;
import edu.miu.ars.repository.AppUserRepository;
import edu.miu.ars.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser save(AppUser appUser) {
        edu.miu.ars.domain.User user = appUser.getUser();
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        if (appUser.getRole().equals(AppConstant.ROLE_AGENT)) {
            Agent agent = new Agent();
            agent.setEmail(user.getEmail());
            agent.setAddress(user.getAddress());
            agent.setFirstName(user.getFirstName());
            agent.setLastName(user.getLastName());
            agent.setDateOfBirth(user.getDateOfBirth());
            appUser.setUser(agent);
        } else if (appUser.getRole().equals(AppConstant.ROLE_USER)) {
            Passenger passenger = new Passenger();
            passenger.setEmail(user.getEmail());
            passenger.setAddress(user.getAddress());
            passenger.setFirstName(user.getFirstName());
            passenger.setLastName(user.getLastName());
            passenger.setDateOfBirth(user.getDateOfBirth());
            appUser.setUser(passenger);
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(AppUser appUser, Long id) {
        AppUser appUserFromDB = findById(id);
        if (null != appUserFromDB) {
            appUserFromDB.setUser(appUser.getUser());
            appUserFromDB.setEmail(appUser.getEmail());
            appUserFromDB.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUserFromDB.setRole(appUser.getRole());
            save(appUserFromDB);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = getByEmail(email);
        return new User(user.getEmail(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
    }
}
