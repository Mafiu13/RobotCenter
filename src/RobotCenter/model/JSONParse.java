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
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/RobotData 2.txt"));
            for (Object object : jsonArray) {

                JSONObject datas = (JSONObject) object;

                Long robotModelID2 = (Long) datas.get("RobotModelID");
                Integer robotModelID = Integer.valueOf(robotModelID2.intValue());
                System.out.println("RobotModelID:" + robotModelID);

                String robotModel = (String) datas.get("RobotModel");
                System.out.println("RobotModel:" + robotModel);

                Double minJPose = (Double) datas.get("MinJPose");
                System.out.println("MinJPose:" + minJPose);

                Double maxJPose = (Double) datas.get("MaxJPose");
                System.out.println("MaxJPose:" + maxJPose);

                System.out.println("\n");

                robotData = new RobotData(robotModelID, robotModel, minJPose, maxJPose);
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
