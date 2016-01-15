package RobotCenter.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import  java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import  java.lang.Long;

/**
 * Created by YapYap on 2016-01-06.
 */
public class JSONParse {

    RobotData robotData;
    List<RobotData> robotDatas = new ArrayList<RobotData>();

    public  JSONParse(){

        robotDataParse();
    }


    private void  robotDataParse() {

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/Data Base/RobotData.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Robot Data");
            for (Object object : jsonArray) {

                JSONObject datas = (JSONObject) object;

                Long robotModelIDLong = (Long) datas.get("RobotModelID");
                Integer robotModelID = Integer.valueOf(robotModelIDLong.intValue());
                System.out.println("RobotModelID:" + robotModelID);

                String robotModel = (String) datas.get("RobotModel");
                System.out.println("RobotModel:" + robotModel);

                Long axesLong = (Long)datas.get("Axes");
                Integer axes = Integer.valueOf(axesLong.intValue());
                System.out.println("Axes:" + axes);

                JSONObject minJPoseData = (JSONObject)((JSONObject) object).get("MinJPose");
                JointPosition minJPose = new JointPosition();
                System.out.println("MinJPose:" );
                for(int i=1;i<7;i++){

                    Double axis = (Double) minJPoseData.get("Axis"+i);
                    System.out.println("Axis:" + axis);
                    minJPose.setJointPosition(i,axis);
                }

                JSONObject maxJPoseData = (JSONObject)((JSONObject) object).get("MaxJPose");
                JointPosition maxJPose = new JointPosition();
                System.out.println("MaxJPose:" );
                for(int i=1;i<7;i++){

                    Double axis = (Double)  maxJPoseData.get("Axis"+i);
                    System.out.println("Axis:" + axis);
                    maxJPose.setJointPosition(i,axis);
                }

                Long maxSpeedLong = (Long)datas.get("MaxSpeed");
                Integer maxSpeed = Integer.valueOf(maxSpeedLong.intValue());
                System.out.println("MaxSpeed:" + maxSpeed);

                System.out.println("\n");

                robotData = new RobotData(robotModelID, robotModel,axes, minJPose, maxJPose, maxSpeed);
                robotDatas.add(robotData);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        public List<RobotData> getRobotDatasList(){

        return robotDatas;

    }




}
