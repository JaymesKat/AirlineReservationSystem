package edu.miu.ars.controller;

import edu.miu.ars.constant.ResponseConstant;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> findAll() {
        return appUserService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AppUser appUser) {
        return null != appUserService.save(appUser) ? ResponseEntity.ok(ResponseConstant.SAVE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AppUser appUser = appUserService.findById(id);
        return null != appUser ? ResponseEntity.ok(appUser) :
                ResponseEntity.badRequest().body(ResponseConstant.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AppUser appUser) {
        if (id.equals(appUser.getId())) {
            return appUserService.update(appUser, id) ? ResponseEntity.ok(ResponseConstant.UPDATE_SUCCESS) :
                    ResponseEntity.badRequest().body(ResponseConstant.UPDATE_FAILED);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return appUserService.deleteById(id) ? ResponseEntity.ok(ResponseConstant.DELETE_SUCCESS) :
                ResponseEntity.badRequest().body(ResponseConstant.DELETE_FAILED);
    }

}
