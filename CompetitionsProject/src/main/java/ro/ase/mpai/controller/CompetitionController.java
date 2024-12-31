package ro.ase.mpai.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.ase.mpai.model.match.Match;
import ro.ase.mpai.service.HibernateConnection;
import ro.ase.mpai.model.Competition;

import java.util.List;

public class CompetitionController {
    public static void addCompetition(Competition competition) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(competition);
        transaction.commit();
        session.close();
    }

    public static Competition getCompetition(int id) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Competition competition = session.get(Competition.class, id);
        session.close();
        return competition;
    }

    public static List<Competition> getAllCompetitions() {
        Session session = HibernateConnection.getSessionFactory().openSession();
        List<Competition> competitions = session.createQuery("from Competition", Competition.class).list();
        session.close();
        return competitions;
    }

    public static void updateCompetition(Competition updatedCompetition) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatedCompetition);
        updatedCompetition.sendNotificationToAll("Competition " + updatedCompetition.getName() + " has changed!");
        transaction.commit();
        session.close();
    }

    public static void deleteCompetition(int competitionId) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Competition competition = session.get(Competition.class, competitionId);
        competition.sendNotificationToAll("Competition " + competitionId + " has been removed!");
        session.delete(competition);
        List<Match> matches = MatchController.getCompetitionMatches(competitionId);
        for (Match m : matches) {
            MatchController.deleteMatch(m.getId());
        }
        transaction.commit();
        session.close();
    }

}
