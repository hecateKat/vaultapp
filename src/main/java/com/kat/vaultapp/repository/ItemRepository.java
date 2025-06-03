package com.kat.vaultapp.repository;

import com.kat.vaultapp.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
