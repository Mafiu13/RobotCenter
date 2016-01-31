package RobotCenter.view;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

/**
 * Created by YapYap on 2016-01-17.
 */
public class GraphicViewGui {
    private JButton closeButton;
    private JPanel graphicViewJPanel;
    private JPanel robotViewJPanel;
    private JFrame frame;

    private Line2D line2D;
    private BasicStroke basicStroke;

    public GraphicViewGui() {

        frame = new JFrame("Graphic View");
        frame.setContentPane(graphicViewJPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);

        line2D = new Line2D.Double();
        basicStroke = new BasicStroke((float) 5);
        robotViewJPanel.setBackground(Color.white);
    }

    public void addCloseListener(ActionListener listenForCloseButton) {

        closeButton.addActionListener(listenForCloseButton);
    }

    public Graphics2D getGraphics() {

        Graphics g = robotViewJPanel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.setStroke(basicStroke);

        return g2;
    }

    public Line2D getLine2D() {

        return line2D;
    }

    public int getHeightRobotViewJPanel() {

        return robotViewJPanel.getHeight();
    }

    public int getWidthRobotViewJPanel() {

        return robotViewJPanel.getWidth();
    }

    public void setVisibleFrame(boolean flag) {

        frame.setVisible(flag);
    }

    public void closeGraphicViewGuiFrame() {

        frame.dispose();
    }


}
