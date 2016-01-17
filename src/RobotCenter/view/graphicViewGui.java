package RobotCenter.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-01-17.
 */
public class GraphicViewGui {
    private JButton closeButton;
    private JPanel graphicViewJPanel;

    private JFrame frame;

    public GraphicViewGui() {

        frame = new JFrame("Graphic View");
        frame.setContentPane(graphicViewJPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

    }

    public void addCloseListener(ActionListener listenForCloseButton) {

        closeButton.addActionListener(listenForCloseButton);
    }

    public void setVisibleFrame(boolean flag) {

        frame.setVisible(flag);
    }

    public void closeGraphicViewGuiFrame() {

        frame.dispose();
    }


}
