package RobotCenter.controller;

import RobotCenter.model.RobotData;
import RobotCenter.model.TypeConverter;
import RobotCenter.view.MainGui;
import RobotCenter.view.RobotModelGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by YapYap on 2016-01-15.
 */
public class RobotModelsController {

    private MainGui mainGui;
    private RobotModelGui robotModelGui;
    private List<RobotData> robotDatas;
    private TypeConverter typeConverter;

    public RobotModelsController(MainGui mainGui, List<RobotData> robotDatas, TypeConverter typeConverter) {

        this.mainGui = mainGui;
        this.robotDatas = robotDatas;
        this.typeConverter = typeConverter;
        mainGui.setEnableRobotModelsButton(false);

        robotModelGui = new RobotModelGui();
        addRobotModelsToComboBox();
        addRobotModelInformation(0);

        robotModelGui.addOKListener(new OK());
        robotModelGui.addRobotModelListener(new robotModel());
    }

    class OK implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            closeRobotModelsController();
        }
    }

    class robotModel implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           addRobotModelInformation(robotModelGui.getRobotModelIdxComboBox());

        }
    }

    private void addRobotModelInformation(int idx){

        robotModelGui.setAxesInfoLabel("5");

        String lel = Integer.toString(robotDatas.get(idx).getAxes());
        robotModelGui.setAxesInfoLabel(lel);
        for (int i = 1; i < 7; i++) {

            robotModelGui.setAxisMinJPoseTextField(i,robotDatas.get(idx).getMinJPose().getStrAxis(i));
            robotModelGui.setAxisMaxJPoseTextField(i,robotDatas.get(idx).getMaxJPose().getStrAxis(i));
        }

        robotModelGui.setMaxSpeedTextField(convertIntToStr(robotDatas.get(idx).getMaxSpeed()));

    }

    private void addRobotModelsToComboBox(){

        for (int i = 0; i < robotDatas.size(); i++) {
            robotModelGui.setRobotModelComboBox(robotDatas.get(i).getRobotModel());
        }

    }

    private void closeRobotModelsController() {

        robotModelGui.closeRobotModelsGuiFrame();
        mainGui.setEnableRobotModelsButton(true);
    }

    public String convertIntToStr(int intToStr) {

        String str = Integer.toString(intToStr);
        return str;
    }

}
