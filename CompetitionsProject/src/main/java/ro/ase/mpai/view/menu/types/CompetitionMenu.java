package ro.ase.mpai.view.menu.types;

import ro.ase.mpai.controller.CompetitionController;
import ro.ase.mpai.model.Competition;
import ro.ase.mpai.model.utils.enums.Level;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CompetitionMenu implements AbstractMenu {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void getAllEntities() {
        List<Competition> competitions = CompetitionController.getAllCompetitions();
        for (Competition c : competitions) {
            System.out.println(c);
        }
    }

    @Override
    public void addEntity() {
        System.out.print("Enter competition name: ");
        String name = scanner.nextLine();

        System.out.print("Enter competition type: ");
        String type = scanner.nextLine();

        Level level = readLevel("[press Enter for unknown stage]");

        LocalDate startDate = readDate("start date [press Enter for unknown stage]");
        LocalDate endDate = readDate("end date [press Enter for unknown stage]");

        System.out.print("Enter competition location: ");
        String location = scanner.nextLine();

        Competition competition = new Competition(name, type, level, startDate, endDate, location);
        CompetitionController.addCompetition(competition);
        System.out.println("Competition added successfully!");
    }

    @Override
    public void updateEntity() {
        Competition competition = getCompetition();
        System.out.println("Competition chosen: ");
        System.out.println(competition);


        System.out.print("Enter competition name [press Enter for no change]: ");
        String newName = scanner.nextLine();
        String actualName = competition.getName();
        String name = "".equals(newName) ? actualName : newName;

        System.out.print("Enter competition type [press Enter for no change]: ");
        String newType = scanner.nextLine();
        String actualType = competition.getType();
        String type = "".equals(newType) ? actualType : newType;

        Level level = readLevel("[press Enter for no change]");
        if (level == null) {
            level = competition.getLevel();
        }

        LocalDate startDate = readDate("start date [Press Enter for no change]");
        if (startDate == null) {
            startDate = competition.getStartDate();
        }

        LocalDate endDate = readDate("end date [Press Enter for no change]");
        if (endDate == null) {
            endDate = competition.getEndDate();
        }

        System.out.print("Enter competition location [press Enter for no change]: ");
        String newLocation = scanner.nextLine();
        String actualLocation = competition.getLocation();
        String location = "".equals(newLocation) ? actualLocation : newLocation;

        Competition newCompetition = new Competition(name, type, level, startDate, endDate, location);
        newCompetition.setId(competition.getId());
        CompetitionController.updateCompetition(newCompetition);

        System.out.println("Competition updated successfully!");
    }

    @Override
    public void deleteEntity() {
        Competition competition = getCompetition();
        CompetitionController.deleteCompetition(competition.getId());
        System.out.println("Competition " + competition.getId() + " has been deleted along with its matches!");
    }

    private LocalDate readDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter competition " + message + "[DD.MM.YYYY]: ");
            String input = scanner.nextLine();
            if ("".equals(input)) {
                break;
            }
            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please repeat.");
            }
        }
        return date;
    }

    private Level readLevel(String message) {
        Level level = null;
        try {
            System.out.print("Enter competition level " + message + ": ");
            String levelString = scanner.nextLine();
            if (!"".equals(levelString)) {
                level = Level.valueOf(levelString.toUpperCase());
            }
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            System.out.println("Possibilities: " + Arrays.toString(Level.values()));
            return readLevel(message);
        }
        return level;
    }

    protected Competition getCompetition() {
        System.out.print("Enter competition id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Competition competition = CompetitionController.getCompetition(id);
        while (competition == null) {
            System.out.println("Competition " + id + " does not exist! Use a valid competition id.");
            System.out.print("Enter competition id: ");
            id = Integer.parseInt(scanner.nextLine());
            competition = CompetitionController.getCompetition(id);
        }
        return competition;
    }
}
