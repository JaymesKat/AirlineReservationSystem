package edu.miu.ars.controller;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.service.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    @Autowired
    IAppUser appUserService;


    @GetMapping("/{id}")
    public AppUser getAirport(@PathVariable int id){
        return appUserService.getAppUser(id);
    }

    @GetMapping
    public List<AppUser> getAppUsers(){
        return appUserService.getAppUsers();
    }

    @PostMapping
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return appUserService.addAppUser(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable long id, @RequestBody AppUser appUser){
        return appUserService.updateAppUser(id, appUser);
    }

    @DeleteMapping("/{id}")
    public String removeAppUser(@PathVariable long id){
        return appUserService.removeAppUser(id);
    }
}
