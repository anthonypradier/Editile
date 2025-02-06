package main;

import comps.CanvasPanel;

import java.awt.*;

public class App {
    private Window window;
    private CanvasPanel canvas;
    private AppPanel appPanel;
    public static final int TILE_SIZE = 32;
    public static float SCALE = 1f;


    public App() {
        this.initClasses();
        this.window.setVisible(true);
    }

    private void initClasses() {
        this.appPanel = new AppPanel(this);
        this.window = new Window(this.appPanel);
    }

    public Window getWindow() {
        return this.window;
    }

    public AppPanel getAppPanel() {
        return this.appPanel;
    }

    public CanvasPanel getCanvas() {
        return this.canvas;
    }
}
