package ro.ase.mpai.model.utils.observer;

public interface Observer {
    void receiveNotification(String message);

    void changeSubscription(boolean isSubscribed);
}
