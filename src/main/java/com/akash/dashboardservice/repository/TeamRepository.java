package com.akash.dashboardservice.repository;

import com.akash.dashboardservice.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

    Team findByTeamName (String teamName);

}
