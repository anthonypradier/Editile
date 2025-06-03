package panels;

import comps.InputsMethods;
import comps.TileMap;
import main.App;
import tools.Tools;

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
    private HashMap<Color, Integer> nbOfType;

    private Tools currentTool;

    /**
     * Index of the selected tile
     */
    private int tileValue = 1;

    public CanvasPanel(final App app) {
        super();
        this.app = app;
        this.tileTypes = new HashMap<Integer, Color>();
        this.nbOfType = new HashMap<Color, Integer>();
        this.setupTiles();
//        this.resetNbOfType();
        this.build();

        System.out.println("CanvasPanel created");
    }

    /**
     * Initialize the CanvasPanel. Used to avoid errors when creating classes
     */
    public void init() {
        this.setupTiles();
        this.currentColor = Color.red;
//        this.build();

        this.createMap();
//        this.app.getAppPanel().getRightPanel().getPropertiesPanel().initProperties();
        this.currentTool = Tools.PEN;

        System.out.println("CanvasPanel created");
    }

    /**
     * Build the CanvasPanel
     */
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

    /**
     * Set up the HashMap which contains the tiles
     */
    private void setupTiles() {
        this.tileTypes.put(1, Color.red);
        this.tileTypes.put(2, Color.blue);
        this.tileTypes.put(3, Color.green);
    }

    /**
     * Reset the number of each tiles in the current HashMap
     */
    private void resetNbOfType() {
        this.nbOfType.put(this.tileTypes.get(1), 0);
        this.nbOfType.put(this.tileTypes.get(2), 0);
        this.nbOfType.put(this.tileTypes.get(3), 0);
    }

    /**
     * Create the map
     */
    public void createMap() {
        this.tileMap = new TileMap(this.app);
        this.resetNbOfType();
        System.out.println("Map created");
    }

    /**
     * Clear the map
     */
    public void clearMap() {
        this.tileMap.clearMap();
        this.resetNbOfType();
        //TODO: need to repaint / update the values inside the properties panel to correspond the empty map
        this.app.getAppPanel().getRightPanel().getPropertiesPanel().getPropsMenuBar().getMenu().repaint();
        System.out.println("The map has been clear !");
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    /**
     * Draw the component only if there is a map stored in the attribute tileMap
     * @param g the general graphics
     */
    public void draw(final Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if(this.tileMap != null) {
            this.drawMap(g2);
            this.drawGrid(g2);
        }
    }

    /**
     * Draw the grid depending on the current tile size and the scale
     * @param g2
     */
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

    /**
     * Change the value of the tile in position x and y with the current value of the tile
     * @param x
     * @param y
     */
    public void addTile(final int x, final int y) {
        int currentTile = this.tileMap.getTile(x, y);
        if(currentTile != this.tileValue) {
            this.tileMap.setTile(this.tileValue, x, y);
            //TODO: créer une méthode updateNbTiles qui est appelée uniquement lorsque la souris est relachée. Compter le nombre de tuiles dessinées et effacées
            this.nbOfType.replace(this.tileTypes.get(this.tileValue), this.nbOfType.get(this.tileTypes.get(this.tileValue)) + 1);
            if(currentTile != 0) {
                this.nbOfType.replace(this.tileTypes.get(currentTile), this.nbOfType.get(this.tileTypes.get(currentTile)) - 1);
            }
        }
    }

    /**
     * Remove the value of the tile at the x and y position
     * @param x
     * @param y
     */
    public void removeTile(final int x, final int y) {
        if(this.tileMap.getTile(x, y) != 0) {
            if(this.nbOfType.get(this.tileTypes.get(this.tileValue)) - 1 >= 0) {
                this.nbOfType.replace(this.tileTypes.get(this.tileMap.getTile(x, y)), this.nbOfType.get(this.tileTypes.get(this.tileMap.getTile(x, y))) - 1);
            }
            this.tileMap.eraseTile(x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.tileMap != null) {
//            int xPos = e.getX() / App.TILE_SIZE;
//            int yPos = e.getY() / App.TILE_SIZE;
            Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), this); // calcule les coordonnées par rapport à la source en paramètre 1
            int xPos = p.x / App.TILE_SIZE; // résoud partiellement le problème, de manière pas très propre
            int yPos = p.y / App.TILE_SIZE;

            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    switch (this.currentTool) {
                        case PEN:
                        case RECT:
                            this.addTile(xPos, yPos);
                            break;
                        case RUBBER:
                            this.removeTile(xPos, yPos);
                            break;
                    }
                    break;
                case MouseEvent.BUTTON3:
                    this.removeTile(xPos, yPos);
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
        this.app.getAppPanel().getRightPanel().getPropertiesPanel().updateMenuBars();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.tileMap != null) {
//            int xPos = e.getX() / App.TILE_SIZE;
//            int yPos = e.getY() / App.TILE_SIZE;
            Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), this); // calcule les coordonnées par rapport à la source en paramètre 1
            int xPos = p.x / App.TILE_SIZE; // résoud partiellement le problème, de manière pas très propre
            int yPos = p.y / App.TILE_SIZE;


            switch (this.lastButtonPressed) {
                case MouseEvent.BUTTON1:
                    switch (this.currentTool) {
                        case PEN:
                            this.addTile(xPos, yPos);
                            break;
                        case RECT:

                        case RUBBER:
                            this.removeTile(xPos, yPos);
                            break;
                    }
                    break;
                case MouseEvent.BUTTON3:
                    this.removeTile(xPos, yPos);
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
                    this.app.getAppPanel().getRightPanel().getPropertiesPanel().updateMenuBars();
                }
                break;
            // Change the tile
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
            // Change the tool
            case KeyEvent.VK_B:
                this.setTool(Tools.PEN);
                break;
            case KeyEvent.VK_E:
                this.setTool(Tools.RUBBER);
                break;
            case KeyEvent.VK_R:
                this.setTool(Tools.RECT);
                break;
            // System shortcut
            case KeyEvent.VK_N:
                this.createMap();
                this.app.getAppPanel().getRightPanel().getPropertiesPanel().initProperties();
                this.app.getAppPanel().getRightPanel().getPropertiesPanel().updateMenuBars();
                break;
            case KeyEvent.VK_H:
                System.out.println(this.nbOfType.toString());
                this.app.getAppPanel().getRightPanel().getPropertiesPanel().updateMenuBars();
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

    public HashMap<Integer, Color> getTileTypes() {
        return this.tileTypes;
    }

    public HashMap<Color, Integer> getNbOfTypes() {
        return this.nbOfType;
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

    public Tools getCurrentTool() {
        return this.currentTool;
    }

    public void setTool(final Tools tool) {
//        Vérifier l'existence du tool dans l'enum
//        if() {
//            System.out.println("Error while changing the tool... It is not in the tool list");
//            return;
//        }
        this.currentTool = tool;
        System.out.println("Tool changed to " + tool);
    }
}
