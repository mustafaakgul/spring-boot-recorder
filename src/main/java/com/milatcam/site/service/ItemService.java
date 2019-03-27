package com.milatcam.site.service;

import com.milatcam.site.model.Item;
import com.milatcam.site.model.ItemAddDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ItemService {

    void addItem(ItemAddDTO form);

    void addItem(ItemAddDTO form, String user);

    Iterable<Item> getItems();

    void deleteItemById(long id);

    Optional<Item> getItemById(long id);

    Item assignItem(String username, long itemId);

    //void editItem(ItemAddForm form);
}
