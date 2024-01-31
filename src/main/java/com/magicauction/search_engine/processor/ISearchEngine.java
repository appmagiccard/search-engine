package com.magicauction.search_engine.processor;

import com.magicauction.search_engine.entity.CardPojo;
import com.magicauction.search_engine.entity.ScryfallSetPojo;

import java.util.List;
import java.util.Optional;

public interface ISearchEngine {

    List<String> searchByProximity(String q, int limit);

    Optional<CardPojo> searchByCardId(Long id);

    Optional<CardPojo> searchByScryfallId(String id);

    List<CardPojo> searchByName(String name);

    List<ScryfallSetPojo> searchSets();

    Optional<ScryfallSetPojo> searchSetById(Long id);

    List<ScryfallSetPojo> searchSetsByName(String name);

    List<CardPojo> searchCardsBySetsName(String name);

    List<CardPojo> searchCardsBySetsId(Long id);
}
