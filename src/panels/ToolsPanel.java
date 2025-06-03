package panels;

import main.App;
import tools.Tools;
import utilz.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.*;

//import static utilz.Constants.Tools.*;

public class ToolsPanel extends JPanel {
    private App app;
    private CanvasPanel canvas;
    private JPanel btnsPanel;
    private EnumMap<Tools, ToolButton> buttonsEnumMap;

    public ToolsPanel(final App app, final CanvasPanel canvas) {
        super();
        this.app = app;
        this.canvas = canvas;
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

        for(Tools tool : Tools.values()) {
            this.add(this.buttonsEnumMap.get(tool));
        }

    }

    private void initTools() {
        this.buttonsEnumMap = new EnumMap<Tools, ToolButton>(Tools.class);
    }

    private void initButtons() {
        for (Tools tool : Tools.values()) {
            ToolButton toolButton = new ToolButton(this.canvas, tool); // ou tool.getLabel() si tu ajoutes un champ
            this.buttonsEnumMap.put(tool, toolButton);
        }
    }

    public EnumMap<Tools, ToolButton> getButtons() {
        return this.buttonsEnumMap;
    }

}
