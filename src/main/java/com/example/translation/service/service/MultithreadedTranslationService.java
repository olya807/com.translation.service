package com.example.translation.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MultithreadedTranslationService {

    @Autowired
    private TranslationService translationService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public List<String> translateWords(List<String> words, String sourceLang, String targetLang) {
        List<CompletableFuture<String>> futures = words.stream()
                .map(word -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return translationService.translate(word, sourceLang, targetLang);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null; // или обработка ошибки по-другому
                    }
                }, taskExecutor))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}