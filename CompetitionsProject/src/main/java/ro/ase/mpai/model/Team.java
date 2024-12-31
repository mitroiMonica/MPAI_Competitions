package ro.ase.mpai.model;

import jakarta.persistence.*;
import ro.ase.mpai.controller.TeamController;
import ro.ase.mpai.model.utils.observer.Observer;

import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team implements Observer {
    @Id
    private String name;
    @Column
    private int numberOfMembers;
    @Column(nullable = false)
    private String coach;
    @Column(nullable = false)
    private String captain;
    @Column
    private boolean isSubscribed;

    public Team() {
    }

    public Team(String name, int numberOfMembers, String coach, String captain) {
        this.name = name;
        this.numberOfMembers = numberOfMembers;
        this.coach = coach;
        this.captain = captain;
        this.isSubscribed = true;
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("\s\s" + this.name + ": " + message);
    }

    @Override
    public void changeSubscription(boolean isSubscribed) {
        TeamController.changeSubscription(this.name, isSubscribed);
    }

    //Used for the Set in the sendNotificationToAll method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", numberOfMembers=" + numberOfMembers +
                ", coach='" + coach + '\'' +
                ", captain='" + captain + '\'' +
                ", isSubscribed=" + isSubscribed +
                '}';
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public String getCoach() {
        return coach;
    }

    public String getCaptain() {
        return captain;
    }
}
