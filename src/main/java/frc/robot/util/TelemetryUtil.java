/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;


public class TelemetryUtil {

    private TelemetryUtil() {

    }

    public enum PrintStyle {
        INFO("###", "###"),
        WARNING("<<< WARNING: " , " >>>"),
        ERROR("!!! ERROR: ", " !!!"),
        NONE("", ""),
        SENSOR_VALUE("||| Sensor: ", " |||");

        public final String startKey;
        public final String endKey;

        private PrintStyle(final String startKey, final String endKey) {
            this.startKey = startKey;
            this.endKey = endKey;
        }

    };

    public static void print(String message, PrintStyle style, boolean log){
        String startKey = log ? "S_LOG " + style.startKey + "<" + String.format("%.2g%n" ,Timer.getFPGATimestamp()) + "> "
            : style.startKey;
        String endKey = log ? style.endKey + " E_LOG" : style.endKey;

        switch(style) {
            case WARNING:
                DriverStation.reportWarning(message, false);
                break;
            case ERROR:
                message = message.toUpperCase();
                DriverStation.reportError(message, false);
                break;
            case SENSOR_VALUE:
                if(message.indexOf(".") >= 0){
                    String[] parts = message.split("(?<=.)");
                    if(parts[1].length() > 5) { message = parts[0]+ parts[1].substring(0, 4); }
                }
                break;
            default:
                break;
        }

        System.out.println(startKey + message + endKey);
    }
}
