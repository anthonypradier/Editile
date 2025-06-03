package panels;

import main.App;
import main.AppPanel;
import tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton implements ActionListener {
    private CanvasPanel canvas;
    private Tools tool;

    public ToolButton(final CanvasPanel canvas, final Tools tool) {
        super(tool.getLabel());
        this.canvas = canvas;
        this.tool = tool;
        this.addActionListener(this);
        this.setFocusable(false); // avoids the buttons to absorb the focus of the panel
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(this.tool == Tools.CLEAR) {
            this.canvas.clearMap();
            this.canvas.repaint();
        } else {
            this.canvas.setTool(this.tool);
        }
    }
}
