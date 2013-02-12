package edu.msoe.se2800.h4.jplot;

import edu.msoe.se2800.h4.FileIO;
import edu.msoe.se2800.h4.IRobotController;
import lejos.robotics.navigation.Waypoint;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InfoPanel extends JPanel {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 4846524799433655631L;

    private JTextField xTextField, yTextField;
    private JList pointsList;
    private JLabel numPoints;
    private SaveListener mSaveListener;
    private IRobotController IRobotC;

    public InfoPanel() {
        setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, Constants.GRID_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
        IRobotC = JPlotController.getInstance().getRobotController();
    }

    public void initSubviews() {
        mSaveListener = new SaveListener();

        Font font = new Font("Arial", Font.PLAIN, 12);
        xTextField = new JTextField(3);
        yTextField = new JTextField(3);
        xTextField.setName("x_textfield");
        yTextField.setName("y_textfield");
        xTextField.addKeyListener(new EnterListener());
        yTextField.addKeyListener(new EnterListener());

        numPoints = new JLabel("Number of points: "
                + JPlotController.getInstance().getPath().size());
        numPoints.setFont(font);
        numPoints.setName("number_of_points");

        JLabel label = new JLabel("x, y");
        label.setName("xy");
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, 20));
        add(label);

        pointsList = new JList();
        pointsList.setName("points_list");
        pointsList.setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, 150));
        ArrayList<String> points = new ArrayList<String>();
        for (Object o : JPlotController.getInstance().getPath().toArray()) {
            points.add(((Waypoint) o).x + ", " + ((Waypoint) o).y);
        }
        ;
        pointsList.setListData(points.toArray());
        pointsList.addMouseListener(new PointsMouseListener());
        pointsList.addListSelectionListener(new PointsListListener());

        JButton zoomIn = new JButton("Zoom +");
        zoomIn.setFont(font);
        zoomIn.setName("zoom_in");
        zoomIn.setActionCommand("zoom_in");
        zoomIn.addActionListener(new ZoomListener());
        zoomIn.setMargin(new Insets(0, 0, 0, 0));

        JButton zoomOut = new JButton("Zoom -");
        zoomOut.setFont(font);
        zoomOut.setName("zoom_out");
        zoomOut.setActionCommand("zoom_out");
        zoomOut.addActionListener(new ZoomListener());
        zoomOut.setMargin(new Insets(0, 0, 0, 0));

        JButton load = new JButton("Load");
        load.setFont(font);
        load.setName("load");
        load.setActionCommand("load");
        load.addActionListener(new LoadListener());

        JButton save = new JButton("Save");
        save.setFont(font);
        save.setName("save");
        save.setActionCommand("save");
        save.addActionListener(mSaveListener);

        JButton saveAs = new JButton("Save as...");
        saveAs.setFont(font);
        saveAs.setName("save_as");
        saveAs.setActionCommand("save_as");
        saveAs.addActionListener(mSaveListener);

 //Robot Control Panel
        JPanel robotControlPanel = new JPanel();
        robotControlPanel.setLayout(new GridBagLayout());
        robotControlPanel.setEnabled(false);

        GridBagConstraints rcpConstraints = new GridBagConstraints();
        rcpConstraints.fill = GridBagConstraints.HORIZONTAL;

        //RobotControl Label & Properties
        JLabel robotControlLabel = new JLabel();
        Font f = robotControlLabel.getFont();
        f.isBold();
        robotControlLabel.setText("ROBOT CONTROLS");
        rcpConstraints.gridwidth = GridBagConstraints.REMAINDER;
        rcpConstraints.gridy = 0;
        robotControlPanel.add(robotControlLabel,rcpConstraints);

        //Forward and Reverse Buttons
        ButtonGroup bg = new ButtonGroup();
        JToggleButton forward = new JToggleButton("Forward", true);
        forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.setReverse(false);
            }
        });

        JToggleButton reverse = new JToggleButton("Reverse");
        reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.setReverse(true);
            }
        });
        bg.add(forward);
        bg.add(reverse);
        rcpConstraints.gridx = 0;
        rcpConstraints.gridy = 1;
        robotControlPanel.add(forward,rcpConstraints);
        rcpConstraints.gridx = 0;
        rcpConstraints.gridy = 2;
        robotControlPanel.add(reverse,rcpConstraints);

        //Go button and its properties
        JButton go = new JButton("Go");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.followRoute();
            }
        });
        rcpConstraints.gridx = GridBagConstraints.REMAINDER;
        rcpConstraints.gridy = 3;
        robotControlPanel.add(go,rcpConstraints);

        //Stop button and its properties
        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.stopImmediate();
            }
        });
        rcpConstraints.gridx = GridBagConstraints.REMAINDER;
        rcpConstraints.gridy = 4;
        robotControlPanel.add(stop,rcpConstraints);

        //Stop Immediate Button & its properties
        JButton stopNow = new JButton("Stop Now");
        stopNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.stopImmediate();
            }
        });
        rcpConstraints.gridx = GridBagConstraints.REMAINDER;
        rcpConstraints.gridy = 5;
        robotControlPanel.add(stopNow,rcpConstraints);

        //Single Step button and its properties
        final JCheckBox singleStep = new JCheckBox("Single Step");
        singleStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IRobotC.singleStep(singleStep.isSelected());
            }
        });
        rcpConstraints.gridx = GridBagConstraints.REMAINDER;
        rcpConstraints.gridy = 6;
        robotControlPanel.add(singleStep,rcpConstraints);

        //Zoom buttons & their properties
        zoomIn.setPreferredSize(new Dimension(70, 30));
        zoomOut.setPreferredSize(new Dimension(70, 30));
        load.setPreferredSize(new Dimension(70, 30));
        save.setPreferredSize(new Dimension(70, 30));
        saveAs.setPreferredSize(new Dimension(100, 30));

        add(xTextField);
        add(yTextField);
        add(pointsList);
        add(numPoints);
        add(zoomIn);
        add(zoomOut);
        add(load);
        add(save);
        add(saveAs);
        add(robotControlPanel);

    }

    public void disableSubviews() {
        for (Component c : this.getComponents()) {
            if(c instanceof JPanel){
                for(Component d: ((JPanel) c).getComponents())
                    d.setEnabled(false);
            }
            c.setEnabled(false);
        }
    }

    public void setPointsLabel(int num) {
        numPoints.setText("Number of points: " + num);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<String> points = new ArrayList<String>();
        for (Object o : JPlotController.getInstance().getPath().toArray()) {
            points.add(((Waypoint) o).x + ", " + ((Waypoint) o).y);
        }
        ;
        pointsList.setListData(points.toArray());
        pointsList.repaint();
    }

    public class PointsListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getLastIndex() >= 0) {
                JPlotController.getInstance().setHighlightedPoint(event.getLastIndex());
                JPlotController.getInstance().getGrid().redraw();
            }
        }

    }

    public class PointsMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {

            if (event.getSource().equals(pointsList)) {

                int index = pointsList.locationToIndex(event.getPoint());
                if (event.getButton() == MouseEvent.BUTTON1) {
                    // left click only once
                    if (event.getClickCount() == 1) {
                        if (index >= 0) { // if they clicked on an actual JList item, continue
                            JPlotController.getInstance().setHighlightedPoint(index);
                            JPlotController.getInstance().getGrid().redraw();
                        }
                    }
                }
            }
        }
    }

    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("save_as")) {
                FileIO.saveAs();
            } else {
                FileIO.save();
            }
        }
    }

    public class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileIO.load();
        }
    }

    public class ZoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("zoom_in")) {
                JPlotController.getInstance().zoomIn();
            } else if (e.getActionCommand().equalsIgnoreCase("zoom_out")) {
                JPlotController.getInstance().zoomOut();
            }
        }

    }

    public class EnterListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent arg0) {
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
        }

        @Override
        public void keyTyped(KeyEvent event) {
            if (event.getKeyChar() == '\n') {
                try {
                    int x = Integer.parseInt(xTextField.getText().toString());
                    int y = Integer.parseInt(yTextField.getText().toString());
                    Waypoint p = JPlotController.getInstance().getHighlightedPoint();
                    if (p != null) {
                        p.x = x;
                        p.y = y;
                        JPlotController.getInstance().getGrid().redraw();
                    }
                } catch (NumberFormatException nfe) {
                    // pass and ignore
                    xTextField.setText("");
                    yTextField.setText("");
                }
            }
        }

    }

}
