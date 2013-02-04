package edu.msoe.se2800.h4.jplot;

import com.google.common.eventbus.Subscribe;

import edu.msoe.se2800.h4.StatsUpdateEvent;

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
    
    @Subscribe
    public void recordStatsChange(StatsUpdateEvent e) {
        setBattery(e.milliVolts);
        setSpeed(e.velocity);
        repaint();
    }
    
    public void setSpeed(int speed) {
        mSpeed.setText(Integer.toString(speed) + sSpeedText);
    }
    
    public void setBattery(int milliVolts) {
        mBattery.setText(Integer.toString(milliVolts) + sBatteryText);
    }

}
