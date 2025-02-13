package comps;

import javax.swing.*;
import main.App;

public class AppMenu extends JMenu {
    private App app;

    public AppMenu(final App app, final String text) {
        super(text);
        this.app = app;

        this.setItems();

        this.app.getAppMenuBar().addAppMenu(this);
    }

    private void setItems() {
        this.add(new JMenuItem("save"));
    }
}
