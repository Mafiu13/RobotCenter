package RobotCenter.model;

/**
 * Created by YapYap on 2015-12-30.
 */
public enum Command {
    CONTINUE ("0"),
    MOVE ("1"),
    STOP ("3"),
    ABORT ("4");

    private String commandValue;

    Command(String commandValue){
        this.commandValue =commandValue;
    }

    public String getCommandValue(){
        return  commandValue;
    }

}
