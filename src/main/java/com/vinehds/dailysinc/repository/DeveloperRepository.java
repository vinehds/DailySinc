package com.vinehds.dailysinc.repository;

import com.vinehds.dailysinc.model.entitie.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
