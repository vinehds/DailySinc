package com.vinehds.dailysinc.repository;

import com.vinehds.dailysinc.model.entitie.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyRepository extends JpaRepository<Daily,Long> {

    List<Daily> findByDate(LocalDate date);
}
