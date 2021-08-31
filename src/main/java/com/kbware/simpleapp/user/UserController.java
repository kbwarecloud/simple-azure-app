package com.kbware.simpleapp.user;

import com.kbware.simpleapp.common.exception.NotFoundException;
import com.kbware.simpleapp.user.model.Selfie;
import com.kbware.simpleapp.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Map<Long, User> users = new HashMap<>();

    private final SelfieRepository selfieRepository;

    @PostConstruct
    void init() {
        var u1 = User.builder()
                .id(1L)
                .name("User_1")
                .selfie(new Selfie("https://kbwarestorage.blob.core.windows.net/selfie-storage/test.jpg", true))
                .build();
        var u2 = User.builder()
                .id(2L)
                .name("User_1")
                .build();

        users.put(u1.getId(), u1);
        users.put(u2.getId(), u2);
    }

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
    public User get(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        var user = get(id);
        user.setSelfie(selfieRepository.store(file));
        return user;
    }
}
