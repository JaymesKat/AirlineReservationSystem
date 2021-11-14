package edu.miu.ars.controller;

import edu.miu.ars.domain.Airport;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.service.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    @Autowired
    IAppUser iAppUser;


    @GetMapping("/{id}")
    public AppUser getAirport(@PathVariable int id){
        return iAppUser.getAppUser(id);
    }

    @GetMapping
    public List<AppUser> getAppUsers(){
        return iAppUser.getAppUsers();
    }

    @PostMapping
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return iAppUser.addAppUser(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable long id, @RequestBody AppUser appUser){
        return iAppUser.updateAppUser(id, appUser);
    }

    @DeleteMapping("/{id}")
    public String removeAppUser(@PathVariable long id){
        return iAppUser.removeAppUser(id);
    }
}
