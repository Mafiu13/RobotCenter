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
public class RobotModelController {

    private MainGui mainGui;
    private RobotModelGui robotModelGui;
    private List<RobotData> robotDatas;

    public RobotModelController(MainGui mainGui, List<RobotData> robotDatas) {

        this.mainGui = mainGui;
        this.robotDatas = robotDatas;
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


        robotModelGui.setAxesInfoLabel(TypeConverter.convertIntToStr(robotDatas.get(idx).getAxes()));

        for (int i = 1; i < 7; i++) {

            robotModelGui.setAxisMinJPoseTextField(i,robotDatas.get(idx).getMinJPose().getStrAxis(i));
            robotModelGui.setAxisMaxJPoseTextField(i,robotDatas.get(idx).getMaxJPose().getStrAxis(i));
        }

        robotModelGui.setMaxSpeedTextField(TypeConverter.convertIntToStr(robotDatas.get(idx).getMaxSpeed()));

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

}
