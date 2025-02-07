package inputs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import comps.PropertiesPanel;
import comps.RightPanel;
import main.App;
import comps.CanvasPanel;
import main.AppPanel;

import javax.swing.*;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private AppPanel appPanel;

    public MouseInputs(final AppPanel p) {
        this.appPanel = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component comp = SwingUtilities.getDeepestComponentAt(this.appPanel, e.getX(), e.getY());

        if(comp == this.appPanel.getCanvasPanel()) {
            ((CanvasPanel) comp).mouseClicked(e);
        } else if(comp == this.appPanel.getRightPanel()) {
            ((RightPanel) comp).mouseClicked(e);
        }
        this.appPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component comp = SwingUtilities.getDeepestComponentAt(this.appPanel, e.getX(), e.getY());
        this.appPanel.setFocusedElement((JPanel)comp);
        if(comp == this.appPanel.getCanvasPanel()) {
            ((CanvasPanel) comp).mousePressed(e);
        } else if(comp == this.appPanel.getRightPanel()) {
            ((RightPanel) comp).mousePressed(e);
        }
        this.appPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Component comp = SwingUtilities.getDeepestComponentAt(this.appPanel, e.getX(), e.getY());
        if(comp == this.appPanel.getCanvasPanel()) {
            ((CanvasPanel) comp).mouseReleased(e);
        } else if(comp == this.appPanel.getRightPanel()) {
            ((RightPanel) comp).mouseReleased(e);
        }
        this.appPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Component comp = SwingUtilities.getDeepestComponentAt(this.appPanel, e.getX(), e.getY());
        if(comp == this.appPanel.getCanvasPanel()) {
            ((CanvasPanel) comp).mouseDragged(e);
        } else if(comp == this.appPanel.getRightPanel()) {
            ((RightPanel) comp).mouseDragged(e);
        }
        this.appPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) { // outil dessin, boutton 1 pressed && mouse move || faire un effet de hover de tile

    }
}
