package ro.ase.mpai.view.menu.types;

import ro.ase.mpai.presenter.TeamPresenter;
import ro.ase.mpai.model.Team;

import java.util.List;
import java.util.Scanner;

public class TeamMenu implements AbstractMenu {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void getAllEntities() {
        List<Team> teams = TeamPresenter.getAllTeams();
        for (Team t : teams) {
            System.out.println(t);
        }
    }

    @Override
    public void addEntity() {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter number of members: ");
        int noMembers = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();

        System.out.print("Enter captain name: ");
        String captainName = scanner.nextLine();

        Team team = new Team(teamName, noMembers, coachName, captainName);
        TeamPresenter.addTeam(team);

        System.out.println("Team added successfully!");
    }

    @Override
    public void updateEntity() {
        Team team = getTeam();
        System.out.println("Team chosen: ");
        System.out.println(team);

        System.out.print("Enter number of members [press Enter for no change]: ");
        String newNoMembers = scanner.nextLine();
        String actualNumber = String.valueOf(team.getNumberOfMembers());
        int noMembers = Integer.parseInt("".equals(newNoMembers) ? actualNumber : newNoMembers);

        System.out.print("Enter coach name [press Enter for no change]: ");
        String newCoachName = scanner.nextLine();
        String actualCoachName = team.getCoach();
        String coachName = "".equals(newCoachName) ? actualCoachName : newCoachName;

        System.out.print("Enter captain name [press Enter for no change]: ");
        String newCaptainName = scanner.nextLine();
        String actualCaptainName = team.getCaptain();
        String captainName = "".equals(newCaptainName) ? actualCaptainName : newCaptainName;

        Team newTeam = new Team(team.getName(), noMembers, coachName, captainName);
        TeamPresenter.updateTeam(newTeam);

        System.out.println("Team updated successfully!");
    }

    @Override
    public void deleteEntity() {
        Team team = getTeam();
        TeamPresenter.deleteTeam(team.getName());
        System.out.println(team + " has been deleted!");
    }

    protected Team getTeam() {
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        Team team = TeamPresenter.getTeam(name);
        while (team == null) {
            System.out.println("Team " + name + " does not exist! Use a valid team name.");
            System.out.print("Enter team name: ");
            name = scanner.nextLine();
            team = TeamPresenter.getTeam(name);
        }
        return team;
    }
}
