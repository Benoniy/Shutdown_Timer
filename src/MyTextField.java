import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField{
    Dimension size = new Dimension(150, 150);
    Font font = new Font("SansSerif", Font.BOLD, 80);

    public MyTextField(String text){
        super(text);
        this.setFont(font);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(size);
    }
}
