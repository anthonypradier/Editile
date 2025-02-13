package main;

import comps.AppMenuBar;

import javax.swing.*;

public class App {
    private Window window;
    private AppPanel appPanel;
    private AppMenuBar appMenuBar;
    public static final int TILE_SIZE = 32;
    public static float SCALE = 1f;


    public App() {
        this.initClasses();
        this.window.setVisible(true);
    }

    private void initClasses() {
        this.appPanel = new AppPanel(this);
        this.appPanel.init();
        this.appPanel.getCanvasPanel().init();
        this.appPanel.getRightPanel().init();
        this.appPanel.getRightPanel().getPropertiesPanel().init();
        this.appMenuBar = new AppMenuBar(this);
        this.appMenuBar.init();
        this.window = new Window(this.appPanel, this.appMenuBar);
    }

    public Window getWindow() {
        return this.window;
    }

    public AppPanel getAppPanel() {
        return this.appPanel;
    }

    public AppMenuBar getAppMenuBar() {
        return this.appMenuBar;
    }
}
