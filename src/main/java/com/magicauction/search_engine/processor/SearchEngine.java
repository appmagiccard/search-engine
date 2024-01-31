package com.magicauction.search_engine.processor;


import com.magicauction.search_engine.controller.SearchEngineController;
import com.magicauction.search_engine.entity.CardPojo;
import com.magicauction.search_engine.entity.ScryfallSetPojo;
import com.magicauction.search_engine.entity.repository.CardRepository;
import com.magicauction.search_engine.entity.repository.SetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SearchEngine implements ISearchEngine{

    private final CardRepository repository;
    private final SetRepository setRepository;
    private static final Logger log = LoggerFactory.getLogger(SearchEngineController.class);

    @Autowired
    public SearchEngine(CardRepository repository, SetRepository setRepository) {
        this.repository = repository;
        this.setRepository = setRepository;
    }

    @Override
    public List<String> searchByProximity(String q, int limit){
        String term1 = q+"%";
        String term2 = "% "+q+"%";
        return repository.getCardNamesByProximity(term1, term2, limit);
    }

    @Override
    public List<CardPojo> advancedSearch(Map<String, String> params) {
        return null;
    }

    @Override
    public Optional<CardPojo> searchByCardId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CardPojo> searchByScryfallId(String id) {
        return Optional.empty();
    }

    @Override
    public List<CardPojo> searchByName(String name) {
        return null;
    }

    @Override
    public List<ScryfallSetPojo> searchSets() {
        return null;
    }

    @Override
    public Optional<ScryfallSetPojo> searchSetById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ScryfallSetPojo> searchSetsByName(String name) {
        return null;
    }

    @Override
    public List<CardPojo> searchCardsBySetsName(String name) {
        return null;
    }

    @Override
    public List<CardPojo> searchCardsBySetsId(Long id) {
        return null;
    }


}
