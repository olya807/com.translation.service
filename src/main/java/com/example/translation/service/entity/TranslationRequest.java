package com.example.translation.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("translation_requests")
public class TranslationRequest {

    @Id
    @org.springframework.data.annotation.GeneratedValue // Эта аннотация для автоматической генерации ID
    private Long id;

    @Column("ip_address")
    private String ipAddress;

    @Column("input_string")
    private String inputString;

    @Column("translated_string")
    private String translatedString;

    @Column("request_time")
    private LocalDateTime requestTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getTranslatedString() {
        return translatedString;
    }

    public void setTranslatedString(String translatedString) {
        this.translatedString = translatedString;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}