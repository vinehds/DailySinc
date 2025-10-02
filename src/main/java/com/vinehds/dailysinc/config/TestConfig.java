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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

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


        Developer dev = new Developer(
                null, "Vinicius", new BCryptPasswordEncoder().encode("111111"), team, null, UserRole.ADMIN,
                Department.WEB_DEVELOPER, "vini@teste.com");

        var test = developerRepository.save(dev);

        Daily daily = new Daily();
        daily.setId(null);
        daily.setAuthor(dev);
        daily.setDate(LocalDate.now());
        daily.setLastDayLog("Fiz x");
        daily.setNextDayPlan("Farei y");

        /*Daily daily2 = new Daily();
        daily.setId(null);
        daily.setAuthor(dev);
        daily.setDate(LocalDate.now());
        daily.setLastDayLog("Fiz x");
        daily.setNextDayPlan("Farei y");*/

        dailyRepository.saveAll(Arrays.asList(daily));
    }
}
