
package edu.msoe.se2800.h4.jplot;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.msoe.se2800.h4.FileIO;
import edu.msoe.se2800.h4.Path.BadFormatException;

public class InfoPanel extends JPanel {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 4846524799433655631L;

    private JTextField xTextField, yTextField;
    private JList pointsList;
    private JLabel numPoints;
    private File mPathFile;

    public InfoPanel() {
        mPathFile = null;
        setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, Constants.GRID_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }

    public void initSubviews() {
        Font font = new Font("Arial", Font.PLAIN, 12);
        xTextField = new JTextField(3);
        yTextField = new JTextField(3);
        xTextField.addKeyListener(new EnterListener());
        yTextField.addKeyListener(new EnterListener());

        numPoints = new JLabel("Number of points: " + JPlotController.getInstance().getPathPoints().size());
        numPoints.setFont(font);

        JLabel label = new JLabel("x, y");
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, 20));
        add(label);

        pointsList = new JList();
        pointsList.setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, 350));
        pointsList.setListData(JPlotController.getInstance().getPathPoints().toArray());
        pointsList.addMouseListener(new PointsMouseListener());
        pointsList.addListSelectionListener(new PointsListListener());

        JButton zoomIn = new JButton("Zoom +");
        zoomIn.setFont(font);
        zoomIn.setActionCommand("zoom_in");
        zoomIn.addActionListener(new ZoomListener());
        zoomIn.setMargin(new Insets(0, 0, 0, 0));

        JButton zoomOut = new JButton("Zoom -");
        zoomOut.setFont(font);
        zoomOut.setActionCommand("zoom_out");
        zoomOut.addActionListener(new ZoomListener());
        zoomOut.setMargin(new Insets(0, 0, 0, 0));

        JButton load = new JButton("Load");
        load.setFont(font);
        load.setActionCommand("load");
        load.addActionListener(new LoadListener());

        JButton save = new JButton("Save");
        save.setFont(font);
        save.setActionCommand("save");
        save.addActionListener(new SaveListener());

        JButton saveAs = new JButton("Save as...");
        saveAs.setFont(font);
        saveAs.setActionCommand("save_as");
        saveAs.addActionListener(new SaveListener());

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
    }

    public void disableSubviews() {
        for (Component c : this.getComponents()) {
            c.setEnabled(false);
        }
    }

    public void setPointsLabel(int num) {
        numPoints.setText("Number of points: " + num);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pointsList.setListData(JPlotController.getInstance().getPathPoints().toArray());
        pointsList.repaint();
    }

    public class PointsListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getLastIndex() >= 0) {
            	JPlotController.getInstance().getGrid().setHighlightedPoint(event.getLastIndex());
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
                            JPlotController.getInstance().getGrid().setHighlightedPoint(index);
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

            String actionCommand = e.getActionCommand();

            // default to save as flow if we don't already have a file chosen
            if (mPathFile == null) {
                actionCommand = "save_as";
            }

            File toSave = mPathFile;
            
            // If performing save as... show the file chooser
            if (actionCommand.equalsIgnoreCase("save_as")) {
                toSave = FileIO.save();
                
                // Save the fact that we nowhave a file for future use
                mPathFile = toSave;
            }
            
            boolean fileWasSaved = false;
            
            // If the save was not cancelled, save the path
            if (toSave != null) {
                fileWasSaved = JPlotController.getInstance().getPath().writeToFile(toSave);
            }
            
            // Report on success/failure
            if (fileWasSaved) {
                JOptionPane.showMessageDialog(null, "Path saved succesfully!");
            } else {
                JOptionPane.showMessageDialog(null, "An unknown error occurred while saving the Path.", "Uh-oh!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // We already have a path loaded
            if (mPathFile != null) {
                
            }
            
            mPathFile = FileIO.open();
            try {
            	JPlotController.getInstance().getPath().readFromFile(mPathFile);
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "The selected file is no longer available",
                        "Uh-oh!", JOptionPane.ERROR_MESSAGE);
            } catch (BadFormatException e1) {
                JOptionPane.showMessageDialog(null,
                        "The selected file is corrupt! You may be able to manually recover it.",
                        "Uh-oh!", JOptionPane.ERROR_MESSAGE);
            }
            JPlotController.getInstance().getGrid().redraw();
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
                    Point p = JPlotController.getInstance().getGrid().getHighlightedPoint();
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
