package inputs;

import comps.CanvasPanel;
import comps.PropertiesPanel;
import comps.RightPanel;
import main.App;
import main.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private AppPanel appPanel;

    public KeyboardInputs(final AppPanel panel) {
        this.appPanel = panel;
        System.out.println(this.appPanel);
        System.out.println(this.appPanel.getCanvasPanel());
        Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        System.out.println(focusedComponent);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JPanel focusedPanel = this.appPanel.getFocusedElement();
        if(focusedPanel == this.appPanel.getCanvasPanel()) {
            ((CanvasPanel)focusedPanel).keyPressed(e);
        } else if(focusedPanel == this.appPanel.getRightPanel()) {
            ((RightPanel)focusedPanel).keyPressed(e);
        }
        this.appPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
