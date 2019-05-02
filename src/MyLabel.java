import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    Font font = new Font("SansSerif", Font.BOLD, 12);

    public MyLabel(String text){
        super(text);
        this.setFont(font);
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
