package comps;

import main.App;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PropertiesPanel extends JPanel implements InputsMethods {
    private App app;
    private int nbTW = 0;
    private int nbTH = 0;
    private JLabel size = new JLabel("X: " + this.nbTW + "  Y: " + this.nbTH);
    private JPanel props;
    private JLabel sizeLabel = new JLabel("Size: "), tilesLabel = new JLabel("Tiles: ");
    private JMenuBar tilesDetailsMenuBar;

    public PropertiesPanel(final App app) {
        super();
        this.app = app;

        this.build();

        System.out.println("PropertiesPanel created");
    }

    private void build() {
        Dimension dim = new Dimension(200, 200);
        this.setMaximumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.pink);
        this.setName("Properties panel");

        this.setLayout(new BorderLayout());
        this.add(new JLabel("Properties"), BorderLayout.NORTH);

        this.buildProps();

        this.add(this.props, BorderLayout.CENTER);
    }

    private void buildProps() {
        this.props = new JPanel();
        GridLayout propsGrid = new GridLayout(2, 2);
        this.props.setLayout(propsGrid);

        this.props.add("size Label", this.sizeLabel);
        this.props.add("size values", this.size);
        this.props.add("tiles label", this.tilesLabel);
        this.buildPropsMenus();
//        this.props.add("tiles value", this.tilesDetailsMenuBar);
    }

    private void buildPropsMenus() {
        this.tilesDetailsMenuBar = new JMenuBar();
        this.tilesDetailsMenuBar.setLayout(new BoxLayout(this.tilesDetailsMenuBar, BoxLayout.X_AXIS));
        JPanel tilesDetailsPanel = new JPanel();
        tilesDetailsPanel.setLayout(new BorderLayout());

        JMenu tilesDetailsMenu = new JMenu("Details");
        tilesDetailsMenu.add(new JMenuItem("Menu item 1"));
        tilesDetailsMenu.add(new JMenuItem("Menu item 2"));
        tilesDetailsMenu.add(new JMenuItem("Menu item 3"));

        this.tilesDetailsMenuBar.add(tilesDetailsMenu);
        this.tilesDetailsMenuBar.add(Box.createHorizontalGlue());
        tilesDetailsPanel.add(this.tilesDetailsMenuBar, BorderLayout.CENTER);
        this.props.add("tiles value", tilesDetailsPanel);
    }

    public void initProperties() {
        this.nbTW = this.app.getAppPanel().getCanvasPanel().getNbTileWidth();
        this.nbTH = this.app.getAppPanel().getCanvasPanel().getNbTileHeight();
        System.out.println(this.nbTW + " | " + this.nbTH);
        this.size.setText("X: " + this.nbTW + "  Y: " + this.nbTH);
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
