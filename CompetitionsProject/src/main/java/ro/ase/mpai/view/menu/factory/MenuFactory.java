package ro.ase.mpai.view.menu.factory;

import ro.ase.mpai.view.utils.Entity;
import ro.ase.mpai.view.menu.types.AbstractMenu;
import ro.ase.mpai.view.menu.types.CompetitionMenu;
import ro.ase.mpai.view.menu.types.MatchMenu;
import ro.ase.mpai.view.menu.types.TeamMenu;

public class MenuFactory implements AbstractMenuFactory {
    @Override
    public AbstractMenu getMenu(Entity entity) {
        switch (entity) {
            case COMPETITION: return new CompetitionMenu();
            case MATCH: return new MatchMenu();
            case TEAM: return new TeamMenu();
            default: throw new UnsupportedOperationException();
        }
    }
}
