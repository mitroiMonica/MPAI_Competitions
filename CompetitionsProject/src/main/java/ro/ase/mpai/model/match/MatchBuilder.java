package ro.ase.mpai.model.match;

import ro.ase.mpai.model.Competition;
import ro.ase.mpai.model.Team;
import ro.ase.mpai.model.utils.enums.Stage;

import java.time.LocalDateTime;

public class MatchBuilder {
    private LocalDateTime date;
    private Stage stage;
    private String referee;
    private int scoreTeam1;
    private int scoreTeam2;
    private Competition competition;
    private Team team1;
    private Team team2;

    public MatchBuilder addDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public MatchBuilder addStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public MatchBuilder addReferee(String referee) {
        this.referee = referee;
        return this;
    }

    public MatchBuilder addScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
        return this;
    }

    public MatchBuilder addScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
        return this;
    }

    public MatchBuilder addCompetition(Competition competition) {
        this.competition = competition;
        return this;
    }

    public MatchBuilder addTeam1(Team team1) {
        this.team1 = team1;
        return this;
    }

    public MatchBuilder addTeam2(Team team2) {
        this.team2 = team2;
        return this;
    }

    public Match build() {
        return new Match(date, stage, referee, scoreTeam1, scoreTeam2, competition, team1, team2);
    }
}
