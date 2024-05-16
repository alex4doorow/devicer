package com.afa.devicer.core.services;

import com.afa.devicer.core.bl.entities.SEUser;
import com.afa.devicer.core.bl.repositories.UserRepository;
import com.afa.devicer.core.errors.CoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SEUser getCurrentUser() throws CoreException {
        return userRepository
                .findById(1L)
                .orElseThrow(() -> new CoreException("USER", "user not found", CoreException.THROWS));
    }
}
