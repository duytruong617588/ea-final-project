package com.miu.estate.controller;

import com.miu.estate.dto.request.SearchHistoryRequest;
import com.miu.estate.model.SearchHistory;
//import com.miu.estate.model.User;
import com.miu.estate.service.SearchHistoryService;
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
//        Optional<User> userLogin = RequestUtil.getUserLogin(request);
//        Long userId = userLogin.orElseThrow(() -> new RuntimeException("User not found!")).getId();
        Long userId = 1L;
        return searchHistoryService.getSearchHistoryByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody SearchHistoryRequest searchHistory, HttpServletRequest request) {
        SearchHistory sh = new SearchHistory();
//        Optional<User> userLogin = RequestUtil.getUserLogin(request);
//        sh.setUser(userLogin.orElseThrow(() -> new RuntimeException("User not found!")));
        sh.setParams(String.valueOf(searchHistory.getParams()));
        return searchHistoryService.createOne(sh)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        searchHistoryService.deleteOne(id);
    }

}
