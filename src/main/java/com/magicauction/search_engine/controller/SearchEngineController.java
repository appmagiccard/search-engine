package com.magicauction.search_engine.controller;

import com.magicauction.search_engine.entity.CardPojo;
import com.magicauction.search_engine.entity.ScryfallSetPojo;
import com.magicauction.search_engine.entity.repository.CardRepository;
import com.magicauction.search_engine.processor.ISearchEngine;
import com.magicauction.search_engine.processor.SearchEngine;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchEngineController {

    private final ISearchEngine engine;
    private static final Logger log = LoggerFactory.getLogger(SearchEngineController.class);

    @Autowired
    public SearchEngineController(ISearchEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autoCompleteSuggestions(@RequestParam String q, @RequestParam(defaultValue = "10") int limit){
        List<String> names = engine.searchByProximity(q, limit);
        log.info("Cards found: {}", names);
        return ResponseEntity.ok(names);
    }

    @GetMapping("/advanced-search")
    public ResponseEntity<List<CardPojo>> advanceSearch(@RequestParam String set, @RequestParam String name, @RequestParam String colors){
        log.info("!--- Params for advance search ---!");
        log.info("[name {}] - [colors {}] - [set {}]", name, colors, set);
        log.info("!--- Aca faltan parametros... hay que pensarlos ---!");
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/byCardId/{id}")
    public ResponseEntity<CardPojo> findByCardId(@PathVariable Long id){
        return ResponseEntity.ok(engine.searchByCardId(id).get());
    }

    @GetMapping("/byScryfallId/{id}")
    public ResponseEntity<CardPojo> findByScryfallId(@PathVariable String id){
        return ResponseEntity.ok(engine.searchByScryfallId(id).get());
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<CardPojo>> findByName(@PathVariable String name){
        return ResponseEntity.ok(engine.searchByName(name));
    }

    @GetMapping("/sets")
    public ResponseEntity<List<ScryfallSetPojo>> getAllSets(){
        return ResponseEntity.ok(engine.searchSets());
    }

    @GetMapping("/sets/{id}")
    public ResponseEntity<ScryfallSetPojo> getSetById(@PathVariable Long id){
        return ResponseEntity.ok(engine.searchSetById(id).get());
    }

    @GetMapping("/sets/{name}")
    public ResponseEntity<List<ScryfallSetPojo>> getSetsByName(@PathVariable String name){
        return ResponseEntity.ok(engine.searchSetsByName(name));
    }

    @GetMapping("/sets/{name}/cards")
    public ResponseEntity<List<CardPojo>> getCardsBySetName(@PathVariable String name){
        return ResponseEntity.ok(engine.searchCardsBySetsName(name));
    }

    @GetMapping("/sets/{id}/cards")
    public ResponseEntity<List<CardPojo>> getCardsBySetName(@PathVariable Long id){
        return ResponseEntity.ok(engine.searchCardsBySetsId(id));
    }

}
