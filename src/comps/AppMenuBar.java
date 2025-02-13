package comps;

import main.App;

import javax.swing.*;
import java.util.ArrayList;

public class AppMenuBar extends JMenuBar {
    private App app;
    private AppMenu fileMenu;
    private AppMenu optionMenu;
    private ArrayList<AppMenu> menus;

    public AppMenuBar(final App app) {
        super();
        this.app = app;
        this.menus = new ArrayList<AppMenu>();
    }

    public void init() {
        this.createMenus();

        this.add(this.fileMenu);
        this.add(this.optionMenu);
        System.out.println("AppMenuBar created");
    }

    private void createMenus() {
        this.fileMenu = new AppMenu(this.app, "file");
        this.optionMenu = new AppMenu(this.app, "options");
    }

    public void addAppMenu(final AppMenu appMenu) {
        this.menus.add(appMenu);
    }
}
