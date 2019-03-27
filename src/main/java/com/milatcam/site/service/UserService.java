package com.milatcam.site.service;

import com.milatcam.site.model.Item;
import com.milatcam.site.model.User;
import com.milatcam.site.model.UserAddDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    void addUser(UserAddDTO form);

    Iterable<User> getUsers();

    void deleteItemById(long id);

    List<String> getUsernames();

    User getUserByUsername(String username);

    User getUserByUsername(User username);

    Optional<User> getUserById(long id);

    Map<String, List<Item>> numberOfItemsByType(long userId);
}
