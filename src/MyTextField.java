import javax.swing.*;
import java.awt.*;

class MyTextField extends JTextField{
    private Dimension size = new Dimension(130, 120);
    private Font font = new Font("SansSerif", Font.BOLD, 110);

    MyTextField(String text){
        super(text);
        this.setFont(font);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(size);
        this.setBackground(Frame.backgroundColour);
        this.setBorder(null);
        this.setEditable(false);
    }
}
