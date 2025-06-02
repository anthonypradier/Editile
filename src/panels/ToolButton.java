package panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton implements ActionListener {
    private CanvasPanel canvas;
    private String name;

    public ToolButton(final Integer name, CanvasPanel canvas) {
        super(name);
        this.canvas = canvas;
        this.name = name;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.canvas.setTool(name);
    }
}
