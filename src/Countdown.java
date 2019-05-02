import javax.swing.*;

public class Countdown implements Runnable {
    private JTextField hours, minutes, seconds;
    private JButton button;
    private long time;
    private long duration;
    private String mode;


    public Countdown(Frame frame){
        this.hours = frame.hours;
        this.minutes = frame.minutes;
        this.seconds = frame.seconds;
        this.button = frame.SSbutton;
        this.time = System.currentTimeMillis();
        this.mode = frame.mode;

        this.duration = ((Integer.parseInt(hours.getText()) * 60) * 60) + (Integer.parseInt(minutes.getText()) * 60) +
                Integer.parseInt(seconds.getText());
    }

    @Override
    public void run() {
        time = System.currentTimeMillis();

        while (button.getText().equals("Stop") && duration > 0){
            if (System.currentTimeMillis() >= time + 1000){
                time = System.currentTimeMillis();
                duration -= 1;
                Operations.subOne(hours, minutes, seconds);
            }
        }

        if (button.getText().equals("Stop")){
            Cmd_Interaction.runCMD("\"shutdown -t 0 "+ mode + "\"");
            button.setText("Start");
        }
    }
}
