package ro.ase.mpai.model;

import jakarta.persistence.*;
import ro.ase.mpai.presenter.MatchPresenter;
import ro.ase.mpai.model.match.Match;
import ro.ase.mpai.model.utils.enums.Level;
import ro.ase.mpai.model.utils.observer.Observable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition extends Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column(nullable = false)
    private String location;

    public Competition() {
    }

    public Competition(String name, String type, Level level, LocalDate startDate, LocalDate endDate, String location) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    @Override
    public void sendNotificationToAll(String message) {
        Set<Team> notifiedTeams = new HashSet<>();
        for (Match match : MatchPresenter.getCompetitionMatches(this.id)) {
            if (match.getTeam1() != null && match.getTeam1().isSubscribed()) {
                notifiedTeams.add(match.getTeam1());
            }
            if (match.getTeam2() != null && match.getTeam2().isSubscribed()) {
                notifiedTeams.add(match.getTeam2());
            }
        }
        System.out.println("Competition notifications sent to:");
        for (Team team : notifiedTeams) {
            team.receiveNotification(message);
        }
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                ", location='" + location + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Level getLevel() {
        return level;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    //Used for competition update
    public void setId(int id) {
        this.id = id;
    }
}
