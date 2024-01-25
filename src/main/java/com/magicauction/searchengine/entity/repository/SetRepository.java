package com.magicauction.searchengine.entity.repository;


import com.magicauction.searchengine.entity.MagicSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<MagicSet, Long> {

    Optional<MagicSet> findByCode(String code);
}
