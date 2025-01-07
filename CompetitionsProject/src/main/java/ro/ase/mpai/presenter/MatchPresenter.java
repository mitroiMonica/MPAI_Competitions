package ro.ase.mpai.presenter;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.ase.mpai.service.HibernateConnection;
import ro.ase.mpai.model.match.Match;

import java.util.List;

public class MatchPresenter {
    public static void addMatch(Match match) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(match);
        transaction.commit();
        session.close();
    }

    public static Match getMatch(int matchId) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Match match = session.get(Match.class, matchId);
        session.close();
        return match;
    }

    public static List<Match> getAllMatches() {
        Session session = HibernateConnection.getSessionFactory().openSession();
        List<Match> matches = session.createQuery("from Match", Match.class).list();
        session.close();
        return matches;
    }

    public static List<Match> getCompetitionMatches(int competitionId) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        List<Match> matches = session.createQuery("from Match where competition.id = :competitionId", Match.class)
                .setParameter("competitionId", competitionId)
                .list();
        session.close();
        return matches;
    }

    public static void updateMatch(Match updatedMatch) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatedMatch);
        updatedMatch.sendNotificationToAll("Match " + updatedMatch.getId() + " has changed!");
        transaction.commit();
        session.close();
    }

    public static void deleteMatch(int matchId) {
        Session session = HibernateConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Match match = session.get(Match.class, matchId);
        session.delete(match);
        match.sendNotificationToAll("Match " + matchId + " has been removed!");
        transaction.commit();
        session.close();
    }
}
