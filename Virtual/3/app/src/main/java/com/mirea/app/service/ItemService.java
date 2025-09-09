package com.mirea.app.service;

import com.mirea.app.model.Item;
import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    List<Item> getAllItems();
}
