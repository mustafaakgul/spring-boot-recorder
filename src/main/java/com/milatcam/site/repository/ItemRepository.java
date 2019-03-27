package com.milatcam.site.repository;

import com.milatcam.site.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
