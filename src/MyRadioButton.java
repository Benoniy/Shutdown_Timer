import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {
    Font font = new Font("SansSerif", Font.BOLD, 12);

    public MyRadioButton(String text, boolean bool){
        super(text,bool);
        this.setFont(font);
    }
}
