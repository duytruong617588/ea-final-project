package com.miu.estate.service;

import com.miu.estate.client.UserClient;
import com.miu.estate.dto.request.SearchHistoryRequest;
import com.miu.estate.exception.InvalidTokenException;
import com.miu.estate.model.SearchHistory;
import com.miu.estate.repository.SearchHistoryRepository;
import com.ttd.core.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;
    private final UserClient userClient;
    private final HttpServletRequest request;


    public Optional<List<SearchHistory>> getSearchHistoryByUserId() {
        User user = userClient.getUserByToken(request.getHeader("Authorization"));
        if (user == null) {
            return Optional.empty();
        }
        Long userId = user.getId();
        return Optional.of(searchHistoryRepository.findAllByUserId(userId));
    }

    public Optional<Object> createOne(SearchHistoryRequest searchHistory) {
        SearchHistory sh = new SearchHistory();
        User user = userClient.getUserByToken(request.getHeader("Authorization"));
        if (user == null) {
            throw new InvalidTokenException("User not found");
        }
        sh.setUserId(user.getId());
        sh.setParams(String.valueOf(searchHistory.getParams()));
        return Optional.of(searchHistoryRepository.save(sh));
    }

    public void deleteOne(Long id) {
        searchHistoryRepository.deleteById(id);
    }
}
