import javax.swing.*;

public class Operations {
    public static void valCheck(JTextField hours, JTextField minutes, JTextField seconds){
        int sec_val;
        int min_val;
        int hour_val;

        if (seconds.getText().length() == 0){seconds.setText("00");}
        if (minutes.getText().length() == 0){minutes.setText("00");}
        if (hours.getText().length() == 0){hours.setText("00");}

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


        long up = 0;
        String str;

        if (sec_val > 86399){sec_val = 86399;}

        //sec
        if (hours.getText().equals("23") && minutes.getText().equals("59") && sec_val > 59){
            sec_val = 59;
        }
        if (sec_val > 59){
            up = sec_val / 60;
            long remainder = sec_val % 60;
            str = String.valueOf(remainder);
        }
        else {
            str = String.valueOf(sec_val);
        }

        if (str.length() == 1){str = "0" + str;}
        seconds.setText(str);


        //min






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
            str = String.valueOf(remainder);
        }
        else {
            str = String.valueOf(min_val);
        }
        if (str.length() == 1){str = "0" + str;}
        minutes.setText(str);

        //hour

        try{
            hour_val = Integer.parseInt(hours.getText());
            hour_val += up;
        }
        catch (NumberFormatException ex){
            hour_val = Integer.MAX_VALUE;
        }



        if (hour_val > 23){
            str = String.valueOf(23);
            minutes.setText("59");
            seconds.setText("59");
        }
        else {
            str = String.valueOf(hour_val);
        }

        if (str.length() == 1){str = "0" + str;}
        hours.setText(str);
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
