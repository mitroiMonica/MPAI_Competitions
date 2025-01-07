package ro.ase.mpai.presenter;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.ase.mpai.service.HibernateConnection;
import ro.ase.mpai.model.Team;
import ro.ase.mpai.model.match.Match;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamPresenter {
    public static void addTeam(Team team) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(team);
        transaction.commit();
        session.close();
    }

    public static void changeSubscription(String teamName, boolean isSubscribed) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Team team = session.get(Team.class, teamName);
        team.setSubscribed(isSubscribed);
        session.save(team);
        transaction.commit();
        session.close();
    }

    public static Team getTeam(String name) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Team team = session.get(Team.class, name);
        session.close();
        return team;
    }

    public static List<Team> getAllTeams() {
        Session session = HibernateConnection.getSessionFactory().openSession();
        List<Team> teams = session.createQuery("from Team", Team.class).list();
        session.close();
        return teams;
    }

    public static Set<Team> getCompetitionTeams(int competitionId) {
        Set<Team> teams = new HashSet<>();
        for (Match match : MatchPresenter.getCompetitionMatches(competitionId)) {
            if (match.getTeam1() != null) {
                teams.add(match.getTeam1());
            }
            if (match.getTeam2() != null) {
                teams.add(match.getTeam2());
            }
        }
        return teams;
    }

    public static void updateTeam(Team updatedTeam) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatedTeam);
        transaction.commit();
        session.close();
    }

    public static void deleteTeam(String teamName) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Team team = session.get(Team.class, teamName);
        session.delete(team);
        transaction.commit();
        session.close();
    }
}
