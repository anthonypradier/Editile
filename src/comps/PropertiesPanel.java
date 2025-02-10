package comps;

import main.App;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PropertiesPanel extends JPanel implements InputsMethods {
    private App app;
    private int nbTW = 0;
    private int nbTH = 0;
    private JLabel size = new JLabel("X: " + this.nbTW + "  Y: " + this.nbTH);
    private JPanel props;
    private JLabel sizeLabel = new JLabel("Size: "), tilesLabel = new JLabel("Tiles: ");
    private JMenuBar tilesDetailsMenuBar;
    private JMenu tilesDetailsMenu;
    private int itemId = 1;

    public PropertiesPanel(final App app) {
        super();
        this.app = app;

        this.build();

        System.out.println("PropertiesPanel created");
    }

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

    private void buildProps() {
        this.props = new JPanel();
        GridLayout propsGrid = new GridLayout(2, 2);
        this.props.setLayout(propsGrid);

        JPanel tilesDetailsPanel = new JPanel();
        this.tilesDetailsMenuBar = new JMenuBar();
        this.tilesDetailsMenu = new JMenu("Details");

        this.props.add("size Label", this.sizeLabel);
        this.props.add("size values", this.size);
        this.props.add("tiles label", this.tilesLabel);

        this.tilesDetailsMenuBar.add(this.tilesDetailsMenu);
        tilesDetailsPanel.add(this.tilesDetailsMenuBar);
        this.props.add("tiles value", tilesDetailsPanel);
//        this.buildTilesMenu();
    }

    private void buildTilesMenu() {

        for(Integer i : this.app.getAppPanel().getCanvasPanel().getTileTypes().keySet()) {
            int iconSize = 15;
            Color c = this.app.getAppPanel().getCanvasPanel().getTileTypes().get(i);
            JMenuItem item = new JMenuItem(i + " ::: " + this.app.getAppPanel().getCanvasPanel().getNbOfTypes().get(c));
            BufferedImage image = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB); // créer une icone pour les JMenuItem

            Graphics2D g2 = image.createGraphics();
            g2.setColor(c);
            g2.fillRect(0, 0, iconSize, iconSize);
            g2.dispose();

            item.setIcon(new ImageIcon(image));

            this.tilesDetailsMenu.add(item, i-1);
        }

        this.tilesDetailsMenuBar.add(this.tilesDetailsMenu);
        this.tilesDetailsMenuBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                this.updateTilesValue();
                System.out.println("Menu clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void initProperties() {
        this.buildTilesMenu();
        this.updatesValues();
    }

    public void updateMenuItemsValues() {
        for(Integer i : this.app.getAppPanel().getCanvasPanel().getTileTypes().keySet()) {
            Color c = this.app.getAppPanel().getCanvasPanel().getTileTypes().get(i);
            JMenuItem item = this.tilesDetailsMenu.getItem(i-1);
            item.setText(i + " ::: " + this.app.getAppPanel().getCanvasPanel().getNbOfTypes().get(c));
        }
    }

    public void updatesValues() {
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

    public void incrementID() {
        this.itemId++;
        this.tilesDetailsMenu.getItem(0).setText("MenuItem " + this.itemId);
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
