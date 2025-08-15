package com.vinehds.dailysinc.repository;

import com.vinehds.dailysinc.model.entitie.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRepository extends JpaRepository<Daily,Long> {
}
