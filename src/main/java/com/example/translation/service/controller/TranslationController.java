package com.example.translation.service.controller;

import com.example.translation.service.entity.TranslationRequest;
import com.example.translation.service.service.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping
    public ResponseEntity<Map<String, String>> translateText(
            @RequestParam String inputString,
            @RequestParam String sourceLang,
            @RequestParam String targetLang,
            HttpServletRequest request) {
        try {
            // Получение IP-адреса пользователя
            String ipAddress = request.getRemoteAddr();

            // Создание объекта запроса
            TranslationRequest translationRequest = new TranslationRequest();
            translationRequest.setInputString(inputString);
            translationRequest.setIpAddress(ipAddress);
            translationRequest.setRequestTime(LocalDateTime.now());

            // Выполнение перевода
            String translatedText = translationService.translate(inputString, sourceLang, targetLang);

            // Сохранение результата перевода в объект запроса
            translationRequest.setTranslatedString(translatedText);

            // Сохранение объекта запроса в базе данных
            translationService.saveTranslationRequest(translationRequest);

            // Возвращение переведенного текста
            Map<String, String> response = new HashMap<>();
            response.put("translatedText", translatedText);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}