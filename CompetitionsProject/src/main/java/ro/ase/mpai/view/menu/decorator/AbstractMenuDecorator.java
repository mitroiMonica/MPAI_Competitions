package ro.ase.mpai.view.menu.decorator;

import ro.ase.mpai.view.menu.factory.AbstractMenu;

public abstract class AbstractMenuDecorator implements AbstractMenu {
    private final AbstractMenu abstractMenu;

    public AbstractMenuDecorator(AbstractMenu abstractMenu) {
        this.abstractMenu = abstractMenu;
    }

    @Override
    public void getAllEntities() {
        abstractMenu.getAllEntities();
    }

    @Override
    public void addEntity() {
        abstractMenu.addEntity();
    }

    @Override
    public void updateEntity() {
        abstractMenu.updateEntity();
    }

    @Override
    public void deleteEntity() {
        abstractMenu.deleteEntity();
    }
}
