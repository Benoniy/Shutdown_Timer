import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField{
    Dimension size = new Dimension(130, 120);
    Font font = new Font("SansSerif", Font.BOLD, 110);
    boolean invisible = false;

    public MyTextField(String text){
        super(text);
        this.setFont(font);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(size);
        this.setBackground(Frame.backgroundColour);
        this.setBorder(null);
    }
}
