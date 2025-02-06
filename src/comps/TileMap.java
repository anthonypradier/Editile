package comps;

import main.App;
import main.AppPanel;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class TileMap {
    private App app;
    private int[][] map; // faire un tableau 2d de nombre qui représente les indices des tuiles, tout sera dessiné dans le canvas en fonction des index
    private HashMap<Color, Integer> nbOfType;
    // Créer une map de secours qui est celle avant de clear la map (ou plusieurs)

    public TileMap(final App app) {
        this.app = app;
        this.nbOfType = new HashMap<Color, Integer>();
        this.newMap();
    }

    public void newMap() {
        this.map = new int[this.app.getAppPanel().getCanvasPanel().getNbTileWidth()][this.app.getAppPanel().getCanvasPanel().getNbTileHeight()];
    }

    public void setTile(final int tileType, final int x, final int y) {
        this.map[x][y] = tileType;
    }

    public void setTileHover(final int x, final int y) {
//        this.map[x][y] = Color.gray;
    }

    public void eraseTile(final int x, final int y) {
        this.map[x][y] = 0;
    }

    public void clearMap() {
        this.newMap();
        System.out.println("Map cleared");
    }

    public int getTile(final int x, final int y) {
        return this.map[x][y];
    }
}
