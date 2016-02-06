package RobotCenter.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-02-06.
 */
public class ModelViewGui {

    private JFrame frame;
    private JButton closeButton;
    private JPanel modelJPanel;

    public ModelViewGui() {

        frame = new JFrame("3D Model");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1000, 800);
        setCloseButton();
        setModelViewGui();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addCloseListener(ActionListener listenForCloseButton) {

        closeButton.addActionListener(listenForCloseButton);
    }

    private void setCloseButton() {

        closeButton = new JButton("CLOSE");
        closeButton.setBounds(425, 705, 150, 40);
        frame.add(closeButton);
    }

    private void setModelViewGui() {

        modelJPanel = new JPanel();
        modelJPanel.setBounds(5, 5, 990, 745);
        frame.add(modelJPanel);
    }

    public void add3DModel(JPanel modelView) {

        modelJPanel.setLayout(new BorderLayout());
        modelJPanel.add(modelView);
        modelView.setBounds(0, 0, 900, 600);
    }

    public void closeModelViewGuiGuiFrame() {
        frame.dispose();
    }

}
