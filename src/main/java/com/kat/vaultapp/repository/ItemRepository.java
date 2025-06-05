package com.kat.vaultapp.repository;

import com.kat.vaultapp.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.kat.vaultapp.entity.user.User;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByUser(User user);
}
