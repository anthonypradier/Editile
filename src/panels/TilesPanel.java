package panels;

import main.App;

import javax.swing.*;
import java.awt.*;

public class TilesPanel extends JPanel {
    private App app;

    public TilesPanel(final App app) {
        super();
        this.build();
    }

    private void build() {
        Dimension dim = new Dimension(10, 100);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.yellow);
    }
}
