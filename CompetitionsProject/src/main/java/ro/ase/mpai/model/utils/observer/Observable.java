package ro.ase.mpai.model.utils.observer;

public abstract class Observable {
    public static void subscribe(Observer observer) {
        observer.changeSubscription(true);
    }

    public static void unsubscribe(Observer observer) {
        observer.changeSubscription(false);
    }

    public abstract void sendNotificationToAll(String message);
}
