package ro.ase.mpai.view.menu.factory;

import ro.ase.mpai.view.utils.Entity;
import ro.ase.mpai.view.menu.types.AbstractMenu;

public interface AbstractMenuFactory {
    AbstractMenu getMenu(Entity entity);
}
