package ro.ase.mpai.model.match;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ro.ase.mpai.model.Competition;
import ro.ase.mpai.model.Team;
import ro.ase.mpai.model.utils.enums.Stage;
import ro.ase.mpai.model.utils.observer.Observable;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match extends Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Stage stage;
    @Column(nullable = false)
    private String referee;
    @Column
    private int scoreTeam1;
    @Column
    private int scoreTeam2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team1_name", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Team team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team2_name", nullable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Team team2;

    private Match() {
    }

    Match(LocalDateTime date, Stage stage, String referee, int scoreTeam1, int scoreTeam2,
          Competition competition, Team team1, Team team2) {
        this.date = date;
        this.stage = stage;
        this.referee = referee;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.competition = competition;
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", date=" + date +
                ", stage=" + stage +
                ", referee='" + referee + '\'' +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", competition=" + competition +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }

    @Override
    public void sendNotificationToAll(String message) {
        System.out.println("Match notifications sent to:");
        if (team1 != null && team1.isSubscribed()) {
            team1.receiveNotification(message);
        }
        if (team2 != null && team2.isSubscribed()) {
            team2.receiveNotification(message);
        }
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Stage getStage() {
        return stage;
    }

    public String getReferee() {
        return referee;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public Competition getCompetition() {
        return competition;
    }

    //Used for updateEntity
    public void setId(int id) {
        this.id = id;
    }
}
