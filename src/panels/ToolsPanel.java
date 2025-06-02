package panels;

import main.App;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ToolsPanel extends JPanel {
    private App app;
    private JPanel btnsPanel;
    private HashMap<String, ToolButton> buttonsMap;
    private static final String[] BUTTON_NAME = {"pen", "rubber"};

    public ToolsPanel(final App app) {
        super();
        this.app = app;
        this.buttonsMap = new HashMap<String, ToolButton>();
        this.btnsPanel = new JPanel();
        this.initButtons();
        this.build();
    }

    private void build() {
        Dimension dim = new Dimension(10, 30);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.orange);
        this.setLayout(new GridLayout());

        for(String btnName : BUTTON_NAME) {
            this.add(this.buttonsMap.get(btnName));
        }

    }

    private void initButtons() {
        for(String btnName : BUTTON_NAME) {
            this.buttonsMap.put(btnName, new ToolButton(btnName));
        }
        for(String func : this.buttonsMap.keySet()) {
//            this.buttonsMap.get(func).setPreferredSize(new Dimension(20, 20));
        }
    }

    public HashMap<String, ToolButton> getButtons() {
        return this.buttonsMap;
    }
}
