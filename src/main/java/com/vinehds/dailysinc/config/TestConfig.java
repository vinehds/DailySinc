package com.vinehds.dailysinc.config;

import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.Responsability;
import com.vinehds.dailysinc.repository.DailyRepository;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import com.vinehds.dailysinc.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final DeveloperRepository developerRepository;

    private final DailyRepository dailyRepository;

    private final TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {

        Team team = new Team(null, "Team 1", null,"Desenvolvimento Java");
        teamRepository.save(team);

        Developer dev = new Developer(null, "Vinicius Teste", team, null, Responsability.MEMBER,
                Department.WEB_DEVELOPER);

        developerRepository.save(dev);

        Daily daily = new Daily();
        daily.setId(null);
        daily.setAuthor(dev);
        daily.setDate(LocalDate.now());
        daily.setLastDayLog("Fiz x");
        daily.setNextDayPlan("Farei y");

        dailyRepository.save(daily);

    }
}
