package com.miu.estate.service;

import com.miu.estate.model.SearchHistory;
import com.miu.estate.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;


    public Optional<List<SearchHistory>> getSearchHistoryByUserId(Long userId) {
        return Optional.of(searchHistoryRepository.findAllByUserId(userId));
    }

    public Optional<Object> createOne(SearchHistory searchHistory) {
        return Optional.of(searchHistoryRepository.save(searchHistory));
    }

    public void deleteOne(Long id) {
        searchHistoryRepository.deleteById(id);
    }
}
