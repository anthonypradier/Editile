package panels;

import comps.InputsMethods;
import main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class RightPanel extends JPanel implements InputsMethods {
    private App app;
    private PropertiesPanel propertiesPanel;
//    private BackgoundPanel backgoundPanel;
//    private SpritesPanel spritesPanel;

    public RightPanel(final App app) {
        super();
        this.app = app;
    }

    public void init() {
        this.build();
    }

    private void build() {
        Dimension dim = new Dimension(200, 100);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setName("RightPanel");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.cyan);

        this.propertiesPanel = new PropertiesPanel(this.app);

        this.add(this.propertiesPanel, BorderLayout.NORTH);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.propertiesPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public PropertiesPanel getPropertiesPanel() {
        return this.propertiesPanel;
    }
}
