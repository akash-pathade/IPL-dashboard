package com.akash.dashboardservice.data;

import com.akash.dashboardservice.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput input) throws Exception {

        Match match = new Match();

        match.setId(Long.parseLong(input.getId()));
        match.setCity(input.getCity());
        match.setDate(LocalDate.parse(input.getDate(), DateTimeFormatter.ofPattern("yyyy-mm-dd")));
        match.setPlayerOfMatch(input.getPlayer_of_match());
        match.setVenue(input.getVenue());

        //set team 1 and team 2 depending on the innings order

        String first, second;

        if("bat".equals(input.getToss_decision())){
            first = input.getToss_winner();
            second = input.getToss_winner().equals(input.getTeam1())
                    ? input.getTeam2(): input.getTeam1();
        }
        else {
            second = input.getToss_winner();
            first = input.getToss_winner().equals(input.getTeam1())
                    ? input.getTeam2(): input.getTeam1();
        }

        match.setTeam1(first);
        match.setTeam2(second);
        match.setTossWinner(input.getToss_winner());
        match.setTossDecision(input.getToss_decision());
        match.setMatchWinner(input.getWinner());
        match.setResult(input.getResult());
        match.setResultMargin(input.getResult_margin());
        match.setUmpire1(input.getUmpire1());
        match.setUmpire2(input.getUmpire2());


        return match;
    }
}
