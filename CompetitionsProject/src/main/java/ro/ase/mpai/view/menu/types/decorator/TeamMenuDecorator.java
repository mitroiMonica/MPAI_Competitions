package ro.ase.mpai.view.menu.types.decorator;

import ro.ase.mpai.presenter.TeamPresenter;
import ro.ase.mpai.model.Team;
import ro.ase.mpai.model.utils.observer.Observable;
import ro.ase.mpai.view.menu.types.AbstractMenu;

import java.util.Scanner;
import java.util.Set;

public class TeamMenuDecorator extends AbstractMenuDecorator {
    private final Scanner scanner = new Scanner(System.in);

    public TeamMenuDecorator(AbstractMenu abstractMenu) {
        super(abstractMenu);
    }

    public void changeSubscription() {
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();

        Team team = TeamPresenter.getTeam(name);
        if (team.isSubscribed()) {
            Observable.unsubscribe(team);
        } else {
            Observable.subscribe(team);
        }

        System.out.println("Team " + team.getName() + (team.isSubscribed() ? " is now unsubscribed for notifications"
                : " is now subscribed to notifications"));
    }

    public void getCompetitionTeams() {
        System.out.print("Enter competition id: ");
        int competitionId = Integer.parseInt(scanner.nextLine());

        Set<Team> teams = TeamPresenter.getCompetitionTeams(competitionId);
        for (Team t : teams) {
            System.out.println(t);
        }
    }
}
