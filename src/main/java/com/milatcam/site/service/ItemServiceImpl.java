package com.milatcam.site.service;

import com.milatcam.site.model.Item;
import com.milatcam.site.model.ItemAddDTO;
import com.milatcam.site.model.User;
import com.milatcam.site.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public void addItem(ItemAddDTO form) {

    }

    /**@Override
    public void addItem(ItemAddDTO form) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10);
            Item item = new Item(inventoryCode, form.getItemName(), form.getItemType(), form.getItemlength(), form.getItemwidth());
            itemRepository.save(item);
            System.out.println(itemRepository.findById(item.getId()));
        }
    }*/

    @Override
    public void addItem(ItemAddDTO form, String user) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Item item = new Item(inventoryCode, form.getItemName(), form.getItemType(), form.getItemlength(), form.getItemwidth());
            item.setUser(userService.getUserByUsername(user));
            itemRepository.save(item);
            System.out.println(itemRepository.findById(item.getId()));
        }
    }

    @Override
    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> getItemById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item assignItem(String username, long itemId) {
        //return Item
        //or orelse cak
        User user = userService.getUserByUsername(username);

        Optional<Item> item = getItemById(itemId);

        Set<Item> itemList = user.getItems();
        Item toBeAddedItem;

        if (item.isPresent()) {
            toBeAddedItem = item.get();
            itemList.add(toBeAddedItem);

            user.setItems(itemList);
            toBeAddedItem.setUser(user);
            return itemRepository.save(toBeAddedItem);
        } else {
            System.out.println("error");
            return new Item();
        }
    }
}
