package main;

import panels.CanvasPanel;
import panels.MiddlePanel;
import panels.RightPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    private App app;
//    private CanvasPanel canvasPanel;
    private MiddlePanel middlePanel;
    private RightPanel rightPanel;
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private JPanel focusedElement;

    public AppPanel(final App app) {
        super();
        this.app = app;
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void init() {
        this.initClasses();
        this.build();
//        this.focusedElement = this.canvasPanel;
        System.out.println("AppPanel created");
    }

    private void initClasses() {
//        this.canvasPanel = new CanvasPanel(this.app);
        this.middlePanel = new MiddlePanel(this.app);
        this.rightPanel = new RightPanel(this.app);
        this.mouseInputs = new MouseInputs(this);
        this.keyboardInputs = new KeyboardInputs(this);
    }

    private void build() {
        this.setLayout(new BorderLayout());
//        this.add(this.canvasPanel, BorderLayout.CENTER);
        this.add(this.middlePanel, BorderLayout.CENTER);
        this.add(this.rightPanel, BorderLayout.EAST);
        this.addMouseListener(this.mouseInputs);
        this.addMouseMotionListener(this.mouseInputs);
        this.addKeyListener(this.keyboardInputs);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.middlePanel.repaint();
        this.rightPanel.repaint();
    }

    public JPanel getFocusedElement() {
        return this.focusedElement;
    }

    public void setFocusedElement(final JPanel p) {
        this.focusedElement = p;
        System.out.println("Focus set to " + p.getName());
    }

//    public CanvasPanel getCanvasPanel() {
//        return this.canvasPanel;
//    }

    public RightPanel getRightPanel () {
        return this.rightPanel;
    }

    public MiddlePanel getMiddlePanel () {
        return this.middlePanel;
    }
}
