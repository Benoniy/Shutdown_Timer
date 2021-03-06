import javax.swing.*;
import java.time.LocalTime;

public class Timer implements Runnable {
    private MyTextField hours, minutes, seconds;
    private boolean alarm;
    private JButton button;
    private long time;
    private long duration;
    private String commandMode;


    public Timer(Frame frame, boolean alarm){
        this.alarm = alarm;
        this.hours = frame.hours;
        this.minutes = frame.minutes;
        this.seconds = frame.seconds;
        this.button = frame.SSbutton;
        this.time = System.currentTimeMillis();
        this.commandMode = frame.cmdMode;

        if (!alarm) { // Countdown mode
            this.duration = ((Integer.parseInt(hours.getText()) * 60) * 60) + (Integer.parseInt(minutes.getText()) * 60) +
                    Integer.parseInt(seconds.getText());
        }
        else{ // Alarm mode
            LocalTime currentTime = LocalTime.now();
            long currentTimeSec = ((currentTime.getHour() * 60) * 60) + (currentTime.getMinute() * 60) + currentTime.getSecond();

            long targetTime = ((Integer.parseInt(hours.getText()) * 60) * 60) + (Integer.parseInt(minutes.getText()) * 60) +
                    Integer.parseInt(seconds.getText());

            if (targetTime > currentTimeSec){
                duration = targetTime - currentTimeSec;
            }
            else{
                duration = ((24 * 60) * 60) - (currentTimeSec - targetTime);
            }
        }
    }

    @Override
    public void run() {
        time = System.currentTimeMillis();

        if (!alarm){
            while (button.getText().equals("Stop") && duration > 0){
                if (System.currentTimeMillis() >= time + 1000){
                    time = System.currentTimeMillis();
                    duration -= 1;
                    Operations.subOne(hours, minutes, seconds);
                }
            }
        }
        else {

            //Got to fix this so that it doesn't just countdown
            while (button.getText().equals("Stop") && duration > 0){
                if (System.currentTimeMillis() == time + 500){
                    Operations.flash(hours, minutes, seconds);
                }
                if (System.currentTimeMillis() >= time + 1000){
                    time = System.currentTimeMillis();
                    duration -= 1;
                    Operations.flash(hours, minutes, seconds);
                }
            }
        }


        if (button.getText().equals("Stop")){
            Cmd_Interaction.runCMD("\"shutdown -t 0 "+ commandMode + "\"");
            button.setText("Start");
        }
    }
}
