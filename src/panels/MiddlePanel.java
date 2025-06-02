package panels;

import main.App;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private App app;
    private ToolsPanel toolsPanel;
    private CanvasPanel canvasPanel;
    private TilesPanel tilesPanel;

    public MiddlePanel(final App app) {
        super();
        this.app = app;
    }

    public void init() {
        this.canvasPanel = new CanvasPanel(this.app);
        this.tilesPanel = new TilesPanel(this.app);
        this.toolsPanel = new ToolsPanel(this.app, this.canvasPanel);
        this.canvasPanel.init();
        this.build();
    }

    private void build() {
        this.setLayout(new BorderLayout());
        this.add(this.toolsPanel, BorderLayout.NORTH);
        this.add(this.canvasPanel, BorderLayout.CENTER);
        this.add(this.tilesPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.canvasPanel.repaint();
    }

    public CanvasPanel getCanvasPanel() {
        return this.canvasPanel;
    }

    public ToolsPanel getToolsPanel() {
        return this.toolsPanel;
    }
}
