package com.vinehds.dailysinc.config;

import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.repository.DailyRepository;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import com.vinehds.dailysinc.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final DeveloperRepository developerRepository;

    private final DailyRepository dailyRepository;

    private final TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {

        Team team = new Team(null, null,"Team 1");
        teamRepository.save(team);

    }
}
