package com.kbware.simpleapp.user;

import com.kbware.simpleapp.common.exception.NotFoundException;
import com.kbware.simpleapp.user.model.Selfie;
import com.kbware.simpleapp.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final Map<Long, User> users = new HashMap<>() {{
        put(1L, User.builder().id(1L).name("User_1").build());
        put(2L, User.builder().id(2L).name("User_1").build());
    }};

    @Value("${app.storage.selfie}")
    String storageUrl;

    @GetMapping("/")
    public Collection<User> getAll() {
        return users.values();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id) {
        return Optional.ofNullable(users.get(id))
                .orElseThrow(() -> new NotFoundException("user", id));
    }

    @PostMapping("/{id}/selfie")
    public User get(@PathVariable("id") long id, @RequestBody MultipartFile file) {
        var user = get(id);
        //TODO upload file to Blob Storage
        user.setSelfie(Selfie.builder().url(storageUrl + "test.jpg").build());
        return user;
    }
}
