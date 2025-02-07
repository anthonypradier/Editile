package comps;

import main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class CanvasPanel extends JPanel implements InputsMethods {
    private App app;
    private TileMap tileMap;
    private final int nbTileWidth = 32;
    private final int nbTileHeight = 18;

    private boolean isPenPressed;
    private boolean isRubberPressed;
    private Color currentColor;
    private int lastButtonPressed = 0;

    private HashMap<Integer, Color> tileTypes;
    private int tileValue = 1;

    public CanvasPanel(final App app) {
        super();
        this.app = app;
        this.tileTypes = new HashMap<Integer, Color>();
        this.setupTiles();
        this.currentColor = Color.red;
        this.build();

        System.out.println("CanvasPanel created");
    }

    private void build() {
        Dimension dim = new Dimension(this.nbTileWidth * App.TILE_SIZE, this.nbTileHeight * App.TILE_SIZE);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setFocusable(true);
        this.setName("Canvas");

        //SwingUtilities.invokeLater(this::requestFocusInWindow); // focus à faire perdre pour éviter les raccourcis claviers non intensionnels
        // invokelater pour que le focus soit donné aprés le chargement de la fenêtre

        this.setBackground(Color.darkGray);

//        this.keyboardInputs = new KeyboardInputs(this.app.getAppPanel());
    }

    private void setupTiles() {
        this.tileTypes.put(1, Color.red);
        this.tileTypes.put(2, Color.blue);
        this.tileTypes.put(3, Color.green);
    }

    public void createMap() {
        this.tileMap = new TileMap(this.app);
        System.out.println("Map created");
    }

    public void clearMap() {
        this.tileMap.clearMap();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    public void draw(final Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if(this.tileMap != null) {
            this.drawMap(g2);
            this.drawGrid(g2);
        }
    }

    private void drawGrid(final Graphics2D g2) {
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.gray);

        // Grid
        for(int i = 0; i < this.nbTileHeight; i++) {
            g2.drawLine(0, i * App.TILE_SIZE, this.getWidth(), i * App.TILE_SIZE);
        }

        for(int j = 0; j < this.nbTileWidth; j++) {
            g2.drawLine(j * App.TILE_SIZE, 0, j * App.TILE_SIZE, this.getHeight());
        }
    }

    private void drawMap(final Graphics2D g2) {
        int tileValue;
        for(int i = 0; i < this.nbTileWidth; i++) {
            for(int j = 0; j < this.nbTileHeight; j++) {
                tileValue = this.tileMap.getTile(i, j);
                if(tileValue == 0) {
                    continue;
                } else {
                    g2.setColor(this.tileTypes.get(tileValue));
                    g2.fillRect(i * App.TILE_SIZE, j * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("canvas clicked");
        if(this.tileMap != null) {
            int xPos = e.getX() / App.TILE_SIZE;
            int yPos = e.getY() / App.TILE_SIZE;

            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    this.tileMap.setTile(this.tileValue, xPos, yPos);
                    break;
                case MouseEvent.BUTTON3:
                    this.tileMap.eraseTile(xPos, yPos);
                    break;
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.lastButtonPressed = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.tileMap != null) {
            int xPos = e.getX() / App.TILE_SIZE;
            int yPos = e.getY() / App.TILE_SIZE;

            switch (this.lastButtonPressed) {
                case MouseEvent.BUTTON1:
                    this.tileMap.setTile(this.tileValue, xPos, yPos);
                    break;
                case MouseEvent.BUTTON3:
                    this.tileMap.eraseTile(xPos, yPos);
                    break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Canvas " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                if(this.tileMap == null) {
                    System.out.println("No map to clear");
                } else {
                    this.clearMap();
                }
                break;
            case KeyEvent.VK_1:
                this.tileValue = 1;
                this.setCurrentColor(Color.red);
                System.out.println("Color changed to red");
                break;
            case KeyEvent.VK_2:
                this.tileValue = 2;
                this.setCurrentColor(Color.blue);
                System.out.println("Color changed to blue");
                break;
            case KeyEvent.VK_3:
                this.tileValue = 3;
                this.setCurrentColor(Color.green);
                System.out.println("Color changed to green");
                break;
            case KeyEvent.VK_N:
                this.createMap();
                this.app.getAppPanel().getRightPanel().getPropertiesPanel().initProperties();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getNbTileWidth() {
        return this.nbTileWidth;
    }

    public int getNbTileHeight() {
        return this.nbTileHeight;
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public boolean isPenPressed() {
        return this.isPenPressed;
    }

    public void setPenPressed(final boolean b) {
        this.isPenPressed = b;
    }

    public boolean isRubberPressed() {
        return this.isRubberPressed;
    }

    public void setRubberPressed(final boolean b) {
        this.isRubberPressed = b;
    }

    public Color getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(final Color c) {
        this.currentColor = c;
    }
}
