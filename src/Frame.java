import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {
    private JPanel content_panel;
    MyTextField hours, minutes, seconds;
    JButton SSbutton;

    String mode = "-s";

    private Frame(){
        setMinimumSize(new Dimension(580, 300));
        setSize(800, 600);
        setTitle("Shutdown Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        content_panel = new JPanel();
        add(content_panel);
        setVisible(true);
    }

    private void addContents(){
        GridBagLayout layout = new GridBagLayout();
        content_panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        //Row 0
        gbc.gridy = 0;
        gbc.ipady = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        MyLabel hLabel = new MyLabel("Hours");
        content_panel.add(hLabel,gbc);

        gbc.gridx = 1;
        MyLabel mLabel = new MyLabel("Minutes");
        content_panel.add(mLabel,gbc);

        gbc.gridx = 2;
        MyLabel sLabel = new MyLabel("Seconds");
        content_panel.add(sLabel,gbc);


        //Row 1
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        hours = new MyTextField("00");
        content_panel.add(hours,gbc);

        gbc.gridx = 1;
        minutes = new MyTextField("00");
        content_panel.add(minutes,gbc);

        gbc.gridx = 2;
        seconds = new MyTextField("00");
        content_panel.add(seconds,gbc);


        //Row 2
        gbc.gridy = 2;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        ButtonGroup modeSelection = new ButtonGroup();
        MyRadioButton modeShutdown = new MyRadioButton("Shutdown", true);
        MyRadioButton modeRestart = new MyRadioButton("Restart", false);
        MyRadioButton modeLogout = new MyRadioButton("Log out", false);

        modeSelection.add(modeShutdown);
        modeSelection.add(modeRestart);
        modeSelection.add(modeLogout);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 3));
        radioPanel.add(modeShutdown);
        radioPanel.add(modeRestart);
        radioPanel.add(modeLogout);

        gbc.gridwidth = 2;
        content_panel.add(radioPanel,gbc);

        gbc.gridx = 2;
        SSbutton = new JButton("Start");
        SSbutton.setPreferredSize(new Dimension(150, 40));

        gbc.gridwidth = 1;
        content_panel.add(SSbutton,gbc);


        //Action listeners
        hours.addActionListener(actionEvent -> Operations.addValCheck(hours, minutes, seconds));

        minutes.addActionListener(actionEvent -> Operations.addValCheck(hours, minutes, seconds));

        seconds.addActionListener(actionEvent -> Operations.addValCheck(hours, minutes, seconds));


        SSbutton.addActionListener(actionEvent -> {
            Operations.addValCheck(hours, minutes, seconds);
            if (SSbutton.getText().equals("Start")){
                hours.setEditable(false);
                minutes.setEditable(false);
                seconds.setEditable(false);
                modeShutdown.setEnabled(false);
                modeRestart.setEnabled(false);
                modeLogout.setEnabled(false);
                if (modeShutdown.isSelected()){
                    mode = "-s";
                }
                else if (modeRestart.isSelected()){
                    mode = "-r";
                }
                else {
                    mode = "-l";
                }

                SSbutton.setText("Stop");
                startCount();
            }
            else {

                hours.setEditable(true);
                minutes.setEditable(true);
                seconds.setEditable(true);
                modeShutdown.setEnabled(true);
                modeRestart.setEnabled(true);
                modeLogout.setEnabled(true);

                SSbutton.setText("Start");
            }

        });

        this.pack();
    }

    private void startCount(){
        Thread countThread = new Thread(new Countdown(this));
        countThread.start();
    }

    public static void main(String[] args) {
        Frame window = new Frame();
        window.addContents();
    }
}
