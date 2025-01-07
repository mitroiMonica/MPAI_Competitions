package ro.ase.mpai.view.menu.types;

import ro.ase.mpai.presenter.CompetitionPresenter;
import ro.ase.mpai.presenter.MatchPresenter;
import ro.ase.mpai.presenter.TeamPresenter;
import ro.ase.mpai.model.Competition;
import ro.ase.mpai.model.Team;
import ro.ase.mpai.model.match.Match;
import ro.ase.mpai.model.match.MatchBuilder;
import ro.ase.mpai.model.utils.enums.Stage;
import ro.ase.mpai.model.utils.specification.ISpecification;
import ro.ase.mpai.model.utils.specification.MatchDateSpecification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MatchMenu implements AbstractMenu {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void getAllEntities() {
        List<Match> matches = MatchPresenter.getAllMatches();
        for (Match m : matches) {
            System.out.println(m);
        }
    }

    @Override
    public void addEntity() {
        MatchBuilder builder = new MatchBuilder();

        Competition competition = new CompetitionMenu().getCompetition();
        builder.addCompetition(competition);

        LocalDateTime time = readTime("[Press Enter for unknown date]");
        if (time != null) {
            if(!isValidDate (competition, time)) {
                return;
            }
            builder.addDate(time);
        }

        Stage stage = readStage("[Press Enter for unknown stage]");
        if (stage != null) {
            builder.addStage(stage);
        }

        System.out.print("Enter referee name: ");
        String referee = scanner.nextLine();
        builder.addReferee(referee);

        System.out.println("Add team 1:");
        Team team1 = new TeamMenu().getTeam();
        builder.addTeam1(team1);

        System.out.println("Add team 2:");
        Team team2 = new TeamMenu().getTeam();
        builder.addTeam2(team2);

        MatchPresenter.addMatch(builder.build());
        System.out.println("Match added successfully!");
    }

    @Override
    public void updateEntity() {
        MatchBuilder builder = new MatchBuilder();

        Match actualMatch = getMatch();
        System.out.println("Match chosen: ");
        System.out.println(actualMatch);

        LocalDateTime time = readTime("[press Enter for no change]");
        if (time != null) {
            if(!isValidDate (actualMatch.getCompetition(), time)) {
                return;
            }
            builder.addDate(time);
        } else {
            builder.addDate(actualMatch.getDate());
        }

        Stage stage = readStage("[press Enter for no change]");
        if (stage != null) {
            builder.addStage(stage);
        } else {
            builder.addStage(actualMatch.getStage());
        }

        System.out.print("Enter referee name [press Enter for no change]: ");
        String newReferee = scanner.nextLine();
        if ("".equals(newReferee)) {
            builder.addReferee(actualMatch.getReferee());
        } else {
            builder.addReferee(newReferee);
        }

        System.out.print("Enter score team 1 [press Enter for no change]: ");
        String scoreTeam1 = scanner.nextLine();
        if ("".equals(scoreTeam1)) {
            builder.addScoreTeam1(actualMatch.getScoreTeam1());
        } else {
            builder.addScoreTeam1(Integer.parseInt(scoreTeam1));
        }

        System.out.print("Enter score team 2 [press Enter for no change]: ");
        String scoreTeam2 = scanner.nextLine();
        if ("".equals(scoreTeam2)) {
            builder.addScoreTeam2(actualMatch.getScoreTeam2());
        } else {
            builder.addScoreTeam2(Integer.parseInt(scoreTeam2));
        }

        System.out.print("Enter team 1 name [press Enter for no change]: ");
        String name1 = scanner.nextLine();
        if ("".equals(name1)) {
            builder.addTeam1(actualMatch.getTeam1());
        } else {
            Team team = TeamPresenter.getTeam(name1);
            while (team == null && !"".equals(name1)) {
                System.out.println("Team " + name1 + " does not exist! Use a valid team name.");
                System.out.print("Enter team 1 name [press Enter for no change]: ");
                name1 = scanner.nextLine();
                team = TeamPresenter.getTeam(name1);
            }
            builder.addTeam1(team);
        }

        System.out.print("Enter team 2 name [press Enter for no change]: ");
        String name2 = scanner.nextLine();
        if ("".equals(name2)) {
            builder.addTeam2(actualMatch.getTeam2());
        } else {
            Team team = TeamPresenter.getTeam(name2);
            while (team == null && !"".equals(name2)) {
                System.out.println("Team " + name2 + " does not exist! Use a valid team name.");
                System.out.print("Enter team 2 name [press Enter for no change]: ");
                name2 = scanner.nextLine();
                team = TeamPresenter.getTeam(name2);
            }
            builder.addTeam2(team);
        }

        System.out.print("Enter competition id [press Enter for no change]: ");
        String competitionId = scanner.nextLine();
        if ("".equals(competitionId)) {
            builder.addCompetition(actualMatch.getCompetition());
        } else {
            Competition competition = CompetitionPresenter.getCompetition(Integer.parseInt(competitionId));
            while (competition == null && !"".equals(competitionId)) {
                System.out.println("Competition " + competitionId + " does not exist! Use a valid competition id.");
                System.out.print("Enter competition id [press Enter for no change]: ");
                competitionId = scanner.nextLine();
                competition = CompetitionPresenter.getCompetition(Integer.parseInt(competitionId));
            }
            builder.addCompetition(competition);
        }


        Match updatedMatch = builder.build();
        updatedMatch.setId(actualMatch.getId());
        MatchPresenter.updateMatch(updatedMatch);

        System.out.println("Match updated successfully!");
    }

    @Override
    public void deleteEntity() {
        Match match = getMatch();
        MatchPresenter.deleteMatch(match.getId());
        System.out.println(match + " has been deleted!");
    }

    private Stage readStage(String message) {
        Stage stage = null;
        try {
            System.out.print("Enter competition stage " + message + ": ");
            String stageString = scanner.nextLine();
            if (!"".equals(stageString)) {
                stage = Stage.valueOf(stageString.toUpperCase());
            }
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            System.out.println("Possibilities: " + Arrays.toString(Stage.values()));
            return readStage(message);
        }
        return stage;
    }

    private LocalDateTime readTime(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:m");
        LocalDateTime date = null;
        while (date == null) {
            System.out.print("Enter match start time [DD.MM.YYYY HH:mm]" + message + ": ");
            String input = scanner.nextLine();
            if ("".equals(input)) {
                break;
            }
            try {
                date = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please repeat.");
            }
        }
        return date;
    }

    private Match getMatch() {
        System.out.print("Enter match id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Match match = MatchPresenter.getMatch(id);
        while (match == null) {
            System.out.println("Match " + id + " does not exist! Use a valid match id.");
            System.out.print("Enter match id: ");
            id = Integer.parseInt(scanner.nextLine());
            match = MatchPresenter.getMatch(id);
        }
        return match;
    }

    private boolean isValidDate (Competition competition, LocalDateTime time) {
        ISpecification<LocalDateTime> dateSpecification = new MatchDateSpecification(
                competition.getStartDate(),
                competition.getEndDate()
        );

        if (!dateSpecification.isSatisfiedBy(time)) {
            System.out.println("Error: The match date is not within the competition period.");
            return false;
        }
        return true;
    }
}
