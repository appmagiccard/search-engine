package com.magicauction.searchengine.controller;

import com.magicauction.searchengine.entity.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchEngineController {

    private final CardRepository repository;
    private static final Logger log = LoggerFactory.getLogger(SearchEngineController.class);

    @Autowired
    public SearchEngineController(CardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autoCompleteSuggestions(@RequestParam String q, @RequestParam(defaultValue = "10") int limit){
        String term1 = q+"%";
        String term2 = "% "+q+"%";
        List<String> names = repository.getCardNamesByProximity(term1, term2, limit);
        log.info("Cards found: {}", names);
        return ResponseEntity.ok(names);
    }
}
