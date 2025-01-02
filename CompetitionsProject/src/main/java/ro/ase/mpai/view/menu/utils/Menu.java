package ro.ase.mpai.view.menu.utils;

import ro.ase.mpai.service.HibernateConnection;
import ro.ase.mpai.view.menu.factory.AbstractMenuFactory;
import ro.ase.mpai.view.menu.factory.MenuFactory;
import ro.ase.mpai.view.menu.types.AbstractMenu;
import ro.ase.mpai.view.menu.types.decorator.MatchMenuDecorator;
import ro.ase.mpai.view.menu.types.decorator.TeamMenuDecorator;
import ro.ase.mpai.view.utils.Entity;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    private static void getCompetitionMenu(AbstractMenu menu) {
        while (true) {
            try {
                System.out.println("1.1. Get all competitions");
                System.out.println("1.2. Add new competition");
                System.out.println("1.3. Update a competition");
                System.out.println("1.4. Delete a competition");
                System.out.println("1.5. Back to main menu");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) {
                    menu.getAllEntities();
                } else if (option == 2) {
                    menu.addEntity();
                } else if (option == 3) {
                    menu.updateEntity();
                } else if (option == 4) {
                    menu.deleteEntity();
                } else if (option == 5) {
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void getMatchMenu(AbstractMenu menu) {
        while (true) {
            try {
                System.out.println("1.1. Get all matches");
                System.out.println("1.2. Get all matches for a specific competition");
                System.out.println("1.3. Add new match");
                System.out.println("1.4. Update a match");
                System.out.println("1.5. Delete a match");
                System.out.println("1.6. Back to main menu");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) {
                    menu.getAllEntities();
                } else if (option == 2) {
                    MatchMenuDecorator decorator = new MatchMenuDecorator(menu);
                    decorator.getCompetitionMatches();
                } else if (option == 3) {
                    menu.addEntity();
                } else if (option == 4) {
                    menu.updateEntity();
                } else if (option == 5) {
                    menu.deleteEntity();
                } else if (option == 6) {
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void getTeamMenu(AbstractMenu menu) {
        TeamMenuDecorator decorator = new TeamMenuDecorator(menu);
        while (true) {
            try {
                System.out.println("1.1. Get all teams");
                System.out.println("1.2. Get all teams for a specific competition");
                System.out.println("1.3. Add new team");
                System.out.println("1.4. Change subscription for a team");
                System.out.println("1.5. Update a team details");
                System.out.println("1.6. Delete a team");
                System.out.println("1.7. Back to main menu");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) {
                    menu.getAllEntities();
                } else if (option == 2) {
                    decorator.getCompetitionTeams();
                } else if (option == 3) {
                    menu.addEntity();
                } else if (option == 4) {
                    decorator.changeSubscription();
                } else if (option == 5) {
                    menu.updateEntity();
                } else if (option == 6) {
                    menu.deleteEntity();
                } else if (option == 7) {
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void selectEntity() {
        AbstractMenuFactory factory = new MenuFactory();

        while (true) {
            try {
                System.out.println("1. Competitions");
                System.out.println("2. Matches");
                System.out.println("3. Teams");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option == 1) {
                    getCompetitionMenu(factory.getMenu(Entity.COMPETITION));
                } else if (option == 2) {
                    getMatchMenu(factory.getMenu(Entity.MATCH));
                } else if (option == 3) {
                    getTeamMenu(factory.getMenu(Entity.TEAM));
                } else if (option == 4) {
                    HibernateConnection.shutdown();
                    System.out.println("Application terminated.");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void start() {
        selectEntity();
    }
}
