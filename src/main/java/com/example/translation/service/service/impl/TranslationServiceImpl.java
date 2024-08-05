package com.example.translation.service.service.impl;

import com.example.translation.service.entity.TranslationRequest;
import com.example.translation.service.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String TRANSLATION_API_URL = "https://api.translation.example/translate"; // Пример URL API перевода

    @Override
    public String translate(String inputString, String sourceLang, String targetLang) throws Exception {
        // Логика вызова стороннего API перевода
        String url = String.format("%s?text=%s&source=%s&target=%s", TRANSLATION_API_URL, inputString, sourceLang, targetLang);
        String translatedText = restTemplate.getForObject(url, String.class);
        if (translatedText == null) {
            throw new Exception("Ошибка перевода");
        }
        return translatedText;
    }

    @Override
    public void saveTranslationRequest(TranslationRequest translationRequest) {

    }
}
