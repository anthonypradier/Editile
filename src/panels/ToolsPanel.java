package panels;

import main.App;

import javax.swing.*;
import java.awt.*;

public class ToolsPanel extends JPanel {
    private App app;

    public ToolsPanel(final App app) {
        super();
        this.app = app;
        this.build();
    }

    private void build() {
        Dimension dim = new Dimension(10, 30);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.orange);
    }
}
