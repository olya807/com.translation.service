package com.example.translation.service.repository;

import com.example.translation.service.entity.TranslationRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRequestRepository extends CrudRepository<TranslationRequest, Long> {
}