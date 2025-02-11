package comps;

import main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import static utilz.Constants.Properties.ICON_SIZE;

public class PropsMenuBar extends JMenuBar implements ActionListener {
    private App app;
    private JMenu detailsMenu;

    public PropsMenuBar(final App app, final String text, final String name) {
        super();
        this.app = app;
        this.detailsMenu = new JMenu(text);
        this.setName(name.toLowerCase());
        this.add(this.detailsMenu);

        this.app.getAppPanel().getRightPanel().getPropertiesPanel().addPropsMenuBar(this);
    }

    public void initMenuBar(final HashMap<Integer, Color> tilesType) {
        for(Integer i : tilesType.keySet()) {
            int iconSize = ICON_SIZE;
            Color c = this.app.getAppPanel().getCanvasPanel().getTileTypes().get(i);
            JMenuItem item = new JMenuItem(i + " ::: " + this.app.getAppPanel().getCanvasPanel().getNbOfTypes().get(c));
            BufferedImage image = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB); // créer une icone pour les JMenuItem

            Graphics2D g2 = image.createGraphics();
            g2.setColor(c);
            g2.fillRect(0, 0, iconSize, iconSize);
            g2.dispose();

            item.setIcon(new ImageIcon(image));

            this.detailsMenu.add(item, i-1);
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        System.out.println("Menu clicked");
    }

    public JMenu getMenu() {
        return this.detailsMenu;
    }

    public void addItem(final JMenuItem item, final int index) {
        this.add(item, index);
    }

    public void updateMenuBar(final HashMap<Integer, Color> tilesType) {
        for(Integer i : tilesType.keySet()) {
            Color c = this.app.getAppPanel().getCanvasPanel().getTileTypes().get(i);
            JMenuItem item = this.detailsMenu.getItem(i-1);
            item.setText(i + " ::: " + this.app.getAppPanel().getCanvasPanel().getNbOfTypes().get(c));
        }
    }
}
