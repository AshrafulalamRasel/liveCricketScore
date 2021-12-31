package com.example.webX.backend.TechnicalAssignment.livescore.model.repository;

import com.example.webX.backend.TechnicalAssignment.livescore.model.domain.LiveScoreEntity;
import liquibase.pro.packaged.S;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveScoreRepository extends JpaRepository<LiveScoreEntity, String> {


}
