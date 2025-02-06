package comps;

import main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PropertiesPanel extends JPanel implements InputsMethods {
    private App app;
    private int nbTW = 0;
    private int nbTH = 0;
    private JLabel size = new JLabel("Size    X: " + this.nbTW + "  Y: " + this.nbTH);
    private JLabel xSize;
    private JLabel ySize;

    public PropertiesPanel(final App app) {
        super();
        this.app = app;

        this.build();

        System.out.println("PropertiesPanel created");
    }

    private void build() {
        Dimension dim = new Dimension(200, 100);
        this.setMaximumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.pink);
        this.setName("Properties panel");

        this.setLayout(new BorderLayout());

        this.add(new JLabel("Properties"), BorderLayout.NORTH);
        this.add(this.size, BorderLayout.CENTER);
    }

    public void initProperties() {
//        this.xSize = new JLabel("X: " + this.app.getAppPanel().getCanvasPanel().getNbTileWidth());
//        this.ySize = new JLabel("Y: " + this.app.getAppPanel().getCanvasPanel().getNbTileHeight());
        this.nbTW = this.app.getAppPanel().getCanvasPanel().getNbTileWidth();
        this.nbTH = this.app.getAppPanel().getCanvasPanel().getNbTileHeight();
        System.out.println(this.nbTW + " | " + this.nbTH);
        this.size.setText("Size    X: " + this.nbTW + "  Y: " + this.nbTH);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if(this.app.getAppPanel().getCanvasPanel().getTileMap() != null) {
//            this.draw(g);
        }
    }

    private void draw(final Graphics g) {
        this.displayProperties();
    }

    public void displayProperties() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Properties clicked");
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
        System.out.println("Properties " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
