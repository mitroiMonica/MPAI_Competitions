package ro.ase.mpai.main;

import ro.ase.mpai.service.HibernateConnection;
import ro.ase.mpai.view.menu.utils.Menu;

import java.util.logging.Logger;

public class Main {
    private static void stopHibernateLogs() {
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(java.util.logging.Level.SEVERE);
    }

    private static void connectToDatabase() {
        HibernateConnection.getSessionFactory();
    }

    public static void main(String[] args) {
        stopHibernateLogs();
        connectToDatabase();
        Menu.start();
    }
}

