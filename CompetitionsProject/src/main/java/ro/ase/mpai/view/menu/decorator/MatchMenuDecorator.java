package ro.ase.mpai.view.menu.decorator;

import ro.ase.mpai.presenter.MatchPresenter;
import ro.ase.mpai.model.match.Match;
import ro.ase.mpai.view.menu.factory.AbstractMenu;

import java.util.List;
import java.util.Scanner;

public class MatchMenuDecorator extends AbstractMenuDecorator {
    private final Scanner scanner = new Scanner(System.in);

    public MatchMenuDecorator(AbstractMenu abstractMenu) {
        super(abstractMenu);
    }

    public void getCompetitionMatches() {
        System.out.print("Enter competition id: ");
        int competitionId = Integer.parseInt(scanner.nextLine());

        List<Match> matches = MatchPresenter.getCompetitionMatches(competitionId);
        for (Match m : matches)
            System.out.println(m);
    }
}
