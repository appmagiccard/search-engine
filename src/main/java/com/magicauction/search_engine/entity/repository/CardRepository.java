package com.magicauction.search_engine.entity.repository;


import com.magicauction.search_engine.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByScryfallId(String scryfallId);
    List<Card> findAllByScryfallIdIn(Iterable<String> scryfallId);

    @Query(nativeQuery = true,value = "select distinct c.name from db_magicauction.card c where ((c.name like :term1) or (c.name like :term2)) limit :n")
    List<String> getCardNamesByProximity(@Param("term1") String term1, @Param("term2") String term2, @Param("n") int n);
}
