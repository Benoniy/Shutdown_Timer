import javax.swing.*;

public class Operations {

    private static void letterCheck(JTextField textField){
        char[] cArray = textField.getText().toCharArray();
        StringBuilder out = new StringBuilder();

        for (char c : cArray){
            if (c > 47 && c < 58){
                out.append(c);
            }
        }

        textField.setText(out.toString());
    }

    public static void addValCheck(JTextField hours, JTextField minutes, JTextField seconds){
        letterCheck(hours);
        letterCheck(minutes);
        letterCheck(seconds);

        if (seconds.getText().length() == 0){seconds.setText("00");}

        int val;

        try{
            val = Integer.parseInt(seconds.getText());
        }
        catch (NumberFormatException ex){
            val = Integer.MAX_VALUE;
        }

        long up = 0;
        String str;

        if (val > 86399){val = 86399;}

        //sec
        if (hours.getText().equals("23") && minutes.getText().equals("59") && val > 59){
            val = 59;
        }
        if (val > 59){
            up = val / 60;
            long remainder = val % 60;
            str = String.valueOf(remainder);
        }
        else {
            str = String.valueOf(val);
        }

        if (str.length() == 1){str = "0" + str;}
        seconds.setText(str);


        //min
        if (minutes.getText().length() == 0){minutes.setText("00");}

        try {
            val = Integer.parseInt(minutes.getText());
        }
        catch (NumberFormatException ex){
            val = Integer.MAX_VALUE;
        }

        if (val > 1439){val = 1439;}

        if (hours.getText().equals("23") && val + up > 59){
            val = 59;
        }
        else{
            val += up;
        }
        up = 0;

        if (val > 59){
            up = val / 60;
            long remainder = val % 60;

            str = String.valueOf(remainder);
        }
        else {
            str = String.valueOf(val);
        }
        if (str.length() == 1){str = "0" + str;}
        minutes.setText(str);

        //hour
        if (hours.getText().length() == 0){hours.setText("00");}
        try{
            val = Integer.parseInt(hours.getText());
            val += up;
        }
        catch (NumberFormatException ex){
            val = Integer.MAX_VALUE;
        }



        if (val > 23){
            str = String.valueOf(23);
            minutes.setText("59");
            seconds.setText("59");
        }
        else {
            str = String.valueOf(val);
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
