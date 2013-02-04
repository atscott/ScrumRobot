package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
    
    private static final String sBatteryText = "% Battery remaining";
    private static final String sSpeedText = " Meters per second";
    
    private JLabel mSpeed;
    private JLabel mBattery;
    
    public StatsPanel() {
        setPreferredSize(new Dimension(Constants.GRID_WIDTH(), Constants.STATS_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }
    
    public void initSubviews() {
        mSpeed = new JLabel();
        mBattery = new JLabel();
        
        add(mSpeed);
        add(mBattery);
        
        setSpeed(0);
        setBattery(100);
    }
    
    public void setSpeed(int speed) {
        mSpeed.setText(Integer.toString(speed) + sSpeedText);
    }
    
    public void setBattery(int level) {
        mBattery.setText(Integer.toString(level) + sBatteryText);
    }

}
