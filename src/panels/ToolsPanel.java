package panels;

import main.App;
import utilz.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static utilz.Constants.Tools.*;

public class ToolsPanel extends JPanel {
    private App app;
    private CanvasPanel canvas;
    private JPanel btnsPanel;
    private HashMap<String, ToolButton> buttonsMap;
    private final Integer[] toolNames = {PEN, RUBBER};
    //TODO: changer la arraylist, hashmap par un enum. voir chatgpt
    public ArrayList<Integer> tools = new ArrayList<Integer>();

    public ToolsPanel(final App app, final CanvasPanel canvas) {
        super();
        this.app = app;
        this.canvas = canvas;
        this.buttonsMap = new HashMap<String, ToolButton>();
        this.btnsPanel = new JPanel();
        this.initTools();
        this.initButtons();
        this.build();
    }

    private void build() {
        Dimension dim = new Dimension(10, 30);
        this.setMinimumSize(dim);
        this.setPreferredSize(dim);
        this.setBackground(Color.orange);
        this.setLayout(new GridLayout());

        for(int btnName : this.tools) {
            this.add(this.buttonsMap.get(btnName));
        }

    }

    private void initTools() {
        this.tools.addAll(Arrays.asList(this.toolNames));
    }

    private void initButtons() {
        for(int btnName : this.tools) {
            this.buttonsMap.put(btnName, new ToolButton(btnName, this.canvas));
        }
    }

    public HashMap<String, ToolButton> getButtons() {
        return this.buttonsMap;
    }

    public ArrayList<Integer> getTools() {
        return this.tools;
    }
}
