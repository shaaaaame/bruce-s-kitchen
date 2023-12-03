package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        this.add(label);
        this.add(textField);

        this.setPreferredSize(new Dimension(100, 120));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setMaximumSize(new Dimension(9999, 150));
    }
}