package main;

import comps.AppMenuBar;

import javax.swing.*;

public class Window extends JFrame {
    private AppPanel appPanel;
    private AppMenuBar appMenuBar;

    public Window(final AppPanel panel, final AppMenuBar menuBar) {
        super("EdiTile");
        this.appPanel = panel;
        this.appMenuBar = menuBar;
        this.add(this.appPanel);
        this.setJMenuBar(this.appMenuBar);
        this.setup();
    }

    private void setup() {
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
