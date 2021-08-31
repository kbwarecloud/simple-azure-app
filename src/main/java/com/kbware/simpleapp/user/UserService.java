package com.kbware.simpleapp.user;

import com.kbware.simpleapp.common.exception.NotFoundException;
import com.kbware.simpleapp.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SelfieRepository selfieRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User get(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user", id));
    }

    public User uploadSelfie(long id, MultipartFile file) {
        var user = get(id);
        user.setSelfie(selfieRepository.store(file));
        return userRepository.save(user);
    }
}
