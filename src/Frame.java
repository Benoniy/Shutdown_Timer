import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {
    JPanel content_panel;
    JTextField hours, minutes, seconds;
    JButton SSbutton;

    String mode = "-s";

    public Frame(){
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

    public void addContents(){
        GridBagLayout layout = new GridBagLayout();
        content_panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        Font areaFont = new Font("SansSerif", Font.BOLD, 80);
        Font radioFont = new Font("SansSerif", Font.BOLD, 12);
        Dimension size = new Dimension(150, 150);

        JLabel hLabel = new JLabel("Hours");
        hLabel.setFont(radioFont);
        hLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        content_panel.add(hLabel,gbc);

        JLabel mLabel = new JLabel("Minutes");
        mLabel.setFont(radioFont);
        mLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        content_panel.add(mLabel,gbc);

        JLabel sLabel = new JLabel("Seconds");
        sLabel.setFont(radioFont);
        sLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        content_panel.add(sLabel,gbc);

        hours = new JTextField("00");
        hours.setPreferredSize(size);
        hours.setHorizontalAlignment(JTextField.CENTER);
        hours.setFont(areaFont);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        content_panel.add(hours,gbc);

        minutes = new JTextField("00");
        minutes.setPreferredSize(size);
        minutes.setHorizontalAlignment(JTextField.CENTER);
        minutes.setFont(areaFont);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        content_panel.add(minutes,gbc);

        seconds = new JTextField("00");
        seconds.setPreferredSize(size);
        seconds.setHorizontalAlignment(JTextField.CENTER);
        seconds.setFont(areaFont);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        content_panel.add(seconds,gbc);

        ButtonGroup modeSelection = new ButtonGroup();
        JRadioButton modeShutdown = new JRadioButton("Shutdown", true);
        JRadioButton modeRestart = new JRadioButton("Restart", false);
        JRadioButton modeLogout = new JRadioButton("Log out", false);

        modeShutdown.setFont(radioFont);
        modeRestart.setFont(radioFont);
        modeLogout.setFont(radioFont);

        modeSelection.add(modeShutdown);
        modeSelection.add(modeRestart);
        modeSelection.add(modeLogout);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 3));
        radioPanel.add(modeShutdown);
        radioPanel.add(modeRestart);
        radioPanel.add(modeLogout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        content_panel.add(radioPanel,gbc);


        SSbutton = new JButton("Start");
        SSbutton.setFont(radioFont);
        SSbutton.setPreferredSize(new Dimension(150, 40));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        content_panel.add(SSbutton,gbc);


        hours.addActionListener(actionEvent -> {
            Operations.addValCheck(hours, minutes, seconds);
        });
        minutes.addActionListener(actionEvent -> {
            Operations.addValCheck(hours, minutes, seconds);
        });
        seconds.addActionListener(actionEvent -> {
            Operations.addValCheck(hours, minutes, seconds);
        });




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

    public void startCount(){
        Thread countThread = new Thread(new Countdown(this));
        countThread.start();
    }

    public static void main(String[] args) {
        Frame window = new Frame();
        window.addContents();
    }
}
