package com.vinehds.dailysinc.config;

import com.vinehds.dailysinc.model.entitie.Daily;
import com.vinehds.dailysinc.model.entitie.Developer;
import com.vinehds.dailysinc.model.entitie.Team;
import com.vinehds.dailysinc.model.enums.Department;
import com.vinehds.dailysinc.model.enums.UserRole;
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


        Developer dev2 = new Developer(
                null, "Vinicius", "12345", team, null, UserRole.ADMIN,
                Department.WEB_DEVELOPER, "vini@teste.com");

        developerRepository.save(dev2);

        /*Daily daily = new Daily();
        daily.setId(null);
        daily.setAuthor(dev2);
        daily.setDate(LocalDate.now());
        daily.setLastDayLog("Fiz x");
        daily.setNextDayPlan("Farei y");

        dailyRepository.save(daily);

        Daily daily2 = new Daily();
        daily.setId(null);
        daily.setAuthor(dev2);
        daily.setDate(LocalDate.now());
        daily.setLastDayLog("Fiz x");
        daily.setNextDayPlan("Farei y");

        dailyRepository.save(daily);
        dailyRepository.save(daily2);*/
    }
}
