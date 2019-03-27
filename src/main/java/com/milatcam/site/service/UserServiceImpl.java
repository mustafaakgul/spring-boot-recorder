package com.milatcam.site.service;

import com.milatcam.site.model.Item;
import com.milatcam.site.model.User;
import com.milatcam.site.model.UserAddDTO;
import com.milatcam.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserAddDTO form) {
        String encoded = bCryptPasswordEncoder.encode(form.getPassword());
        //String encoded = "{noop}" + form.getPassword();
        User user = new User(form.getUsername(), encoded, form.getName(), form.getLastName());
        userRepository.save(user);
        //System.out.println(encoded);
        System.out.println(userRepository.findById(user.getId()));
    }

    @Override
    public Iterable<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public void deleteItemById(long id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByUsername(User username) {
        return (User) userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Map<String, List<Item>> numberOfItemsByType(long userId) {
        //ispresette check at user yoksa patlar
        Map<String, List<Item>> map = new HashMap<String, List<Item>>();
        Set<Item> items = getUserById(userId).get().getItems();
        for (Item item: items) {
            List<Item> itemList = new ArrayList<Item>();
            String key = item.getType().toLowerCase();
            if (map.containsKey(key))
                itemList = map.get(key);
            itemList.add(item);
            map.put(key, itemList);
        }
        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<SimpleGrantedAuthority> auth = (List<SimpleGrantedAuthority>) user.getAuthorities();
        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }    }

    /** @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }
    }*/
}
