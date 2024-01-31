package com.magicauction.search_engine.entity.repository;


import com.magicauction.search_engine.entity.MagicSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<MagicSet, Long> {

    Optional<MagicSet> findByCode(String code);
}
