package panels;

import comps.InputsMethods;
import comps.PropsMenuBar;
import main.App;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PropertiesPanel extends JPanel implements InputsMethods {
    private App app;
    private int nbTW = 0;
    private int nbTH = 0;
    private JLabel size = new JLabel("X: " + this.nbTW + "  Y: " + this.nbTH);
    private JPanel props;
    private JLabel sizeLabel = new JLabel("Size: "), tilesLabel = new JLabel("Tiles: ");
    private PropsMenuBar tilesMenuBar;
    private ArrayList<PropsMenuBar> propsMenuBars;

    public PropertiesPanel(final App app) {
        super();
        this.app = app;
        this.propsMenuBars = new ArrayList<PropsMenuBar>();

    }

    /**
     * Initialize the propertiesPanel
     */
    public void init() {
        this.build();
        this.initProperties();

        System.out.println("PropertiesPanel created");
    }

    /**
     * Build the propertiesPanel
     */
    private void build() {
//        Dimension dim = new Dimension(200, 200);
//        this.setMaximumSize(dim);
//        this.setPreferredSize(dim);
        this.setBackground(Color.pink);
        this.setName("Properties panel");

        this.setLayout(new BorderLayout());
        this.add(new JLabel("Properties"), BorderLayout.NORTH);

        this.buildProps();

        this.add(this.props, BorderLayout.CENTER);
    }

    /**
     * Build the panel which contains the properties
     */
    private void buildProps() {
        this.props = new JPanel();
        GridLayout propsGrid = new GridLayout(2, 2);
        this.props.setLayout(propsGrid);

        JPanel tilesDetailsPanel = new JPanel();
        this.buildTilesMenus();

        this.props.add("size Label", this.sizeLabel);
        this.props.add("size values", this.size);

        this.props.add("tiles label", this.tilesLabel);
        tilesDetailsPanel.add(this.tilesMenuBar);
        this.props.add("tiles value", tilesDetailsPanel);
    }

    /**
     * Create and initialize all the menus of the propertiesPanel, one by one
     */
    private void buildTilesMenus() {
        this.tilesMenuBar = new PropsMenuBar(this.app, "Details", "tiles");
        this.tilesMenuBar.initMenuBar(this.app.getAppPanel().getCanvasPanel().getTileTypes());
    }

    /**
     * Initialize the properties
     */
    public void initProperties() {
        this.updateMapSize();
        this.buildTilesMenus();
    }

    /**
     * Update the values of the map's size
     */
    public void updateMapSize() {
        this.nbTH = this.app.getAppPanel().getCanvasPanel().getNbTileHeight();
        this.nbTW = this.app.getAppPanel().getCanvasPanel().getNbTileWidth();

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

    /**
     * This method should be used only in the PropsMenuBar class. When a propsMenuBar is created, it is automatically added in the ArrayList
     * @param m a PropsMenuBar
     */
    public void addPropsMenuBar(final PropsMenuBar m) {
        this.propsMenuBars.add(m);
    }

    /**
     * Update the values of all the propsMenuBars
     */
    public void updateMenuBars() {
        // HashMap de HashMap <String, HashMap> pour récupérer la HashMap correspondante au nom du PropsMenuBar
        // ou une arrayList de hashMap, mais peu pratique pour passer une hashMap spécifique en paramètre
        for(PropsMenuBar m : this.propsMenuBars) {
            m.updateMenuBar(this.app.getAppPanel().getCanvasPanel().getTileTypes());
        }
        System.out.println("Menu updated");
    }
}
