package com.example.translation.service.service;

import com.example.translation.service.entity.TranslationRequest;

public interface TranslationService {
    String translate(String inputString, String sourceLang, String targetLang) throws Exception;

    void saveTranslationRequest(TranslationRequest translationRequest);
}