package com.kbware.simpleapp.user;

import com.kbware.simpleapp.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public Page<User> findAll(@PageableDefault(sort = "name") Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id) {
        return userService.get(id);
    }

    @PostMapping("/{id}/selfie")
    public User upload(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return userService.uploadSelfie(id, file);
    }
}
