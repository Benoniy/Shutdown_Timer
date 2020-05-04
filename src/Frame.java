import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {
    private JPanel content_panel;
    MyTextField hours, minutes, seconds;
    JButton SSbutton;
    static Color backgroundColour = new Color(240,240,240);

    String mode = "-s";

    private Frame(){
        setMinimumSize(new Dimension(580, 320));
        setSize(800, 700);
        setTitle("Shutdown Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        content_panel = new JPanel();
        content_panel.setBackground(backgroundColour);
        add(content_panel);
        setVisible(true);
    }

    private void addContents(){
        GridBagLayout layout = new GridBagLayout();
        content_panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        //Row 0
        gbc.gridy = 0;
        gbc.ipady = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        MyLabel hLabel = new MyLabel("Hours");
        content_panel.add(hLabel,gbc);

        gbc.gridx = 2;
        MyLabel mLabel = new MyLabel("Minutes");
        content_panel.add(mLabel,gbc);

        gbc.gridx = 4;
        MyLabel sLabel = new MyLabel("Seconds");
        content_panel.add(sLabel,gbc);

        //Row 1
        gbc.gridy = 1;
        gbc.ipady = 0;

        gbc.gridx = 0;
        JButton h_val_up = new JButton("▲");
        h_val_up.setPreferredSize(new Dimension(100,15));
        content_panel.add(h_val_up, gbc);

        gbc.gridx = 2;
        JButton m_val_up = new JButton("▲");
        m_val_up.setPreferredSize(new Dimension(100,15));
        content_panel.add(m_val_up, gbc);

        gbc.gridx = 4;
        JButton s_val_up = new JButton("▲");
        s_val_up.setPreferredSize(new Dimension(100,15));
        content_panel.add(s_val_up, gbc);

        //Row 2
        gbc.gridy = 2;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        hours = new MyTextField("00");
        content_panel.add(hours,gbc);

        gbc.gridx = 1;
        JLabel colon1 = new JLabel(":");
        colon1.setPreferredSize(new Dimension(40, 120));
        colon1.setFont(new Font("SansSerif", Font.BOLD, 110));
        content_panel.add(colon1,gbc);

        gbc.gridx = 2;
        minutes = new MyTextField("00");
        content_panel.add(minutes,gbc);

        gbc.gridx = 3;
        JLabel colon2 = new JLabel(":");
        colon2.setPreferredSize(new Dimension(40, 120));
        colon2.setFont(new Font("SansSerif", Font.BOLD, 110));
        content_panel.add(colon2,gbc);

        gbc.gridx = 4;
        seconds = new MyTextField("00");
        content_panel.add(seconds,gbc);

        //Row 3
        gbc.gridy = 3;

        gbc.gridx = 0;
        JButton h_val_down = new JButton("▼");
        h_val_down.setPreferredSize(new Dimension(100,15));
        content_panel.add(h_val_down, gbc);

        gbc.gridx = 2;
        JButton m_val_down = new JButton("▼");
        m_val_down.setPreferredSize(new Dimension(100,15));
        content_panel.add(m_val_down, gbc);

        gbc.gridx = 4;
        JButton s_val_down = new JButton("▼");
        s_val_down.setPreferredSize(new Dimension(100,15));
        content_panel.add(s_val_down, gbc);

        //Row 4
        gbc.gridy = 4;

        gbc.gridx = 2;
        content_panel.add(Box.createVerticalStrut(10), gbc);

        //Row 5
        gbc.gridy = 5;

        gbc.gridx = 0;
        content_panel.add(new JSeparator(), gbc);

        gbc.gridx = 1;
        content_panel.add(new JSeparator(), gbc);

        gbc.gridx = 2;
        content_panel.add(new JSeparator(), gbc);

        gbc.gridx = 3;
        content_panel.add(new JSeparator(), gbc);

        gbc.gridx = 4;
        content_panel.add(new JSeparator(), gbc);


        //Row 6
        gbc.gridy = 6;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        ButtonGroup timerSelection = new ButtonGroup();
        MyRadioButton modeCountdown = new MyRadioButton("Countdown", true);
        MyRadioButton modeAlarm = new MyRadioButton("Time", false);

        timerSelection.add(modeCountdown);
        timerSelection.add(modeAlarm);

        JPanel timerPanel = new JPanel(new GridLayout(2,1));
        timerPanel.add(modeCountdown);
        timerPanel.add(modeAlarm);

        content_panel.add(timerPanel, gbc);

        gbc.gridx = 2;
        ButtonGroup modeSelection = new ButtonGroup();
        MyRadioButton modeShutdown = new MyRadioButton("Shutdown", true);
        MyRadioButton modeRestart = new MyRadioButton("Restart", false);
        MyRadioButton modeLogout = new MyRadioButton("Log out", false);

        modeSelection.add(modeShutdown);
        modeSelection.add(modeRestart);
        modeSelection.add(modeLogout);

        JPanel modePanel = new JPanel(new GridLayout(3, 1));
        modePanel.add(modeShutdown);
        modePanel.add(modeRestart);
        modePanel.add(modeLogout);

        gbc.gridwidth = 1;
        content_panel.add(modePanel,gbc);

        gbc.gridx = 4;
        SSbutton = new JButton("Start");
        SSbutton.setPreferredSize(new Dimension(130, 40));

        gbc.gridwidth = 1;
        content_panel.add(SSbutton,gbc);


        //Action listeners
        h_val_up.addActionListener(actionEvent -> {
            hours.setText(String.valueOf(Integer.parseInt(hours.getText()) + 1));
            Operations.addValCheck(hours, minutes, seconds);
        });

        h_val_down.addActionListener(actionEvent -> {
            int count = Integer.parseInt(hours.getText());
            if (count == 0){
                count = 25;
            }
            hours.setText(String.valueOf(count - 1));
            Operations.addValCheck(hours, minutes, seconds);
        });

        m_val_up.addActionListener(actionEvent -> {
            minutes.setText(String.valueOf(Integer.parseInt(minutes.getText()) + 1));
            Operations.addValCheck(hours, minutes, seconds);
        });

        m_val_down.addActionListener(actionEvent -> {
            int count = Integer.parseInt(minutes.getText());
            if (count == 0){
                if (Integer.parseInt(hours.getText()) > 0) {
                    hours.setText(String.valueOf(Integer.parseInt(hours.getText()) - 1));
                }
                count = 60;
            }
            minutes.setText(String.valueOf(count - 1));
            Operations.addValCheck(hours, minutes, seconds);
        });

        s_val_up.addActionListener(actionEvent -> {
            seconds.setText(String.valueOf(Integer.parseInt(seconds.getText()) + 1));
            Operations.addValCheck(hours, minutes, seconds);
        });

        s_val_down.addActionListener(actionEvent -> {
            int count = Integer.parseInt(seconds.getText());
            if (count == 0){
                if (Integer.parseInt(seconds.getText()) > 0) {
                    minutes.setText(String.valueOf(Integer.parseInt(minutes.getText()) - 1));
                }
                count = 60;
            }
            seconds.setText(String.valueOf(count - 1));
            Operations.addValCheck(hours, minutes, seconds);
        });


        SSbutton.addActionListener(actionEvent -> {
            Operations.addValCheck(hours, minutes, seconds);
            if (SSbutton.getText().equals("Start")){
                hours.setEditable(false);
                minutes.setEditable(false);
                seconds.setEditable(false);
                modeCountdown.setEnabled(false);
                modeAlarm.setEnabled(false);
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

                if (modeCountdown.isSelected()){
                    startCountdown(false);
                }
                else {
                    startCountdown(true);
                }
            }
            else {
                hours.setVisible(true);
                minutes.setVisible(true);
                seconds.setVisible(true);

                hours.setEditable(true);
                minutes.setEditable(true);
                seconds.setEditable(true);
                modeCountdown.setEnabled(true);
                modeAlarm.setEnabled(true);
                modeShutdown.setEnabled(true);
                modeRestart.setEnabled(true);
                modeLogout.setEnabled(true);

                SSbutton.setText("Start");
            }

        });

        this.pack();
    }

    private void startCountdown(boolean alarm){
        Thread countThread = new Thread(new Timer(this, alarm));
        countThread.start();
    }

    public static void main(String[] args) {
        Frame window = new Frame();
        window.addContents();
    }
}
