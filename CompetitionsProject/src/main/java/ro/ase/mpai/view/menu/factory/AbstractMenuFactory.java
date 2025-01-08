package ro.ase.mpai.view.menu.factory;

import ro.ase.mpai.view.utils.Entity;

public interface AbstractMenuFactory {
    AbstractMenu getMenu(Entity entity);
}
