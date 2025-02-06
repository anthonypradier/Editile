package main;

import comps.CanvasPanel;

import javax.swing.*;

public class Window extends JFrame {
    private AppPanel appPanel;

    public Window(final AppPanel panel) {
        super("EdiTile");
        this.appPanel = panel;
        this.add(this.appPanel);
        this.setup();
    }

    private void setup() {
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
