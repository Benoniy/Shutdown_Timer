import javax.swing.*;

public class Operations {
    public static void valCheck(JTextField hours, JTextField minutes, JTextField seconds){
        // Initialise values
        int sec_val, min_val, hour_val;
        String sec_str, min_str,  hour_str;

        // Formatting strings to look pretty
        if (seconds.getText().length() == 0){seconds.setText("00");}
        if (minutes.getText().length() == 0){minutes.setText("00");}
        if (hours.getText().length() == 0){hours.setText("00");}

        // Parse seconds and minutes and set them to max if there is an error
        try{
            sec_val = Integer.parseInt(seconds.getText());
        }
        catch (NumberFormatException ex){
            sec_val = Integer.MAX_VALUE;
        }

        try {
            min_val = Integer.parseInt(minutes.getText());
        }
        catch (NumberFormatException ex){
            min_val = Integer.MAX_VALUE;
        }

        long up = 0; // For tracking overflow

        //seconds
        if (sec_val > 86399){sec_val = 86399;}

        if (hours.getText().equals("23") && minutes.getText().equals("59") && sec_val > 59){
            sec_val = 59;
        }
        if (sec_val > 59){
            up = sec_val / 60;
            long remainder = sec_val % 60;
            sec_str = String.valueOf(remainder);
        }
        else {
            sec_str = String.valueOf(sec_val);
        }

        if (sec_str.length() == 1){sec_str = "0" + sec_str;}
        seconds.setText(sec_str);

        //minutes
        if (min_val > 1439){min_val = 1439;}

        if (hours.getText().equals("23") && min_val + up > 59){
            min_val = 59;
        }
        else{
            min_val += up;
        }
        up = 0;

        if (min_val > 59){
            up = min_val / 60;
            long remainder = min_val % 60;
            min_str = String.valueOf(remainder);
        }
        else {
            min_str = String.valueOf(min_val);
        }
        if (min_str.length() == 1){min_str = "0" + min_str;}
        minutes.setText(min_str);

        //hours
        try{
            hour_val = Integer.parseInt(hours.getText());
            hour_val += up;
        }
        catch (NumberFormatException ex){
            hour_val = Integer.MAX_VALUE;
        }

        if (hour_val > 23){
            hour_str = String.valueOf(23);
            minutes.setText("59");
            seconds.setText("59");
        }
        else {
            hour_str = String.valueOf(hour_val);
        }

        if (hour_str.length() == 1){hour_str = "0" + hour_str;}
        hours.setText(hour_str);
    }

    public static void subOne(JTextField hours, JTextField minutes, JTextField seconds){
        int val = Integer.parseInt(seconds.getText());
        String str;

        if (val > 0){
            val -= 1;
            str = String.valueOf(val);
            if (str.length() == 1){str = "0" + str;}
            seconds.setText(str);

            return;
        }
        else{
            seconds.setText("59");
        }

        val = Integer.parseInt(minutes.getText());
        if (val > 0){
            val -= 1;
            str = String.valueOf(val);
            if (str.length() == 1){str = "0" + str;}
            minutes.setText(str);
            return;
        }
        else{
            minutes.setText("59");
        }

        val = Integer.parseInt(hours.getText());
        if (val > 0){
            val -= 1;
            str = String.valueOf(val);
            if (str.length() == 1){str = "0" + str;}
            hours.setText(str);
        }

    }

    public static void flash(MyTextField hours, MyTextField minutes, MyTextField seconds){
        if (hours.isVisible()){
            hours.setVisible(false);
            minutes.setVisible(false);
            seconds.setVisible(false);
        }
        else {
            hours.setVisible(true);
            minutes.setVisible(true);
            seconds.setVisible(true);
        }
    }
}
