package com.miu.estate.controller;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.request.SearchHistoryRequest;
import com.miu.estate.exception.InvalidTokenException;
import com.miu.estate.model.SearchHistory;
//import com.miu.estate.model.User;
import com.miu.estate.service.SearchHistoryService;
import com.ttd.core.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search-histories")
public class SearchHistoryController {
    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public ResponseEntity<List<SearchHistory>> getSearchHistoryByUserId(HttpServletRequest request) {
        return searchHistoryService.getSearchHistoryByUserId()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody SearchHistoryRequest searchHistory, HttpServletRequest request) {
        return searchHistoryService.createOne(searchHistory)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        searchHistoryService.deleteOne(id);
    }

}
