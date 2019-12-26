package com.yana.internship.service;

import com.yana.internship.entity.Role;
import com.yana.internship.entity.User;
import com.yana.internship.repository.RoleRepository;
import com.yana.internship.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Spy
    @InjectMocks
    UserService service;
    @Mock
    UserRepository repository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void createShouldReturnCorrectResult() {
        when(repository.findByEmail(any(String.class))).thenReturn(null);
        when(repository.save(any(User.class))).thenReturn(createUser());
        when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode(any(String.class))).thenReturn("");
        service.create(createUser());
        verify(repository).save(any(User.class));

    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setSurname("Test");
        user.setEmail("dubov@gmail.com");
        user.setPassword("123456");
        user.setRoles(new ArrayList<>());
        return user;
    }
}