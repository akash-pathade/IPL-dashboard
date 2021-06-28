package com.akash.dashboardservice.controller;

import com.akash.dashboardservice.model.Team;
import com.akash.dashboardservice.repository.MatchRepository;
import com.akash.dashboardservice.repository.TeamRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;

    private MatchRepository matchRepository;

    public TeamController (TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("team/{teamName}")
    public Team getTeamByTeamName(@PathVariable String teamName){
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));
        return team;
    }
}
