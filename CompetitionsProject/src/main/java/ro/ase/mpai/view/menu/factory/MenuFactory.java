package ro.ase.mpai.view.menu.factory;

import ro.ase.mpai.view.utils.Entity;

public class MenuFactory implements AbstractMenuFactory {
    @Override
    public AbstractMenu getMenu(Entity entity) {
        switch (entity) {
            case COMPETITION:
                return new CompetitionMenu();
            case MATCH:
                return new MatchMenu();
            case TEAM:
                return new TeamMenu();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
