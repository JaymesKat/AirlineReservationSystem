package edu.miu.ars.controller;

import edu.miu.ars.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {



    @GetMapping("/{id}")
    public AppUser getAirport(@PathVariable int id){
        return null;
    }

    @GetMapping
    public List<AppUser> getAppUsers(){
        return null;
    }

    @PostMapping
    public AppUser addAppUser(@RequestBody AppUser appUser){
        return null;
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable long id, @RequestBody AppUser appUser){
        return null;
    }

    @DeleteMapping("/{id}")
    public String removeAppUser(@PathVariable long id){
        return "";
    }
}
