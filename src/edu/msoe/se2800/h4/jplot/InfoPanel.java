package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 4846524799433655631L;
	
	private JTextField xTextField, yTextField;
	private JList pointsList;
	
	public InfoPanel() {
		setPreferredSize(new Dimension(Constants.INFO_PANEL_WIDTH, Constants.GRID_HEIGHT));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		initSubviews();
		setVisible(true);
	}
	
	public void initSubviews() {
		xTextField = new JTextField(3);
		yTextField = new JTextField(3);
		xTextField.addKeyListener(new EnterListener());
		yTextField.addKeyListener(new EnterListener());
		JLabel label = new JLabel("x, y");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(100,20));
		add(label);
		
		pointsList = new JList();
		
		JButton zoomIn = new JButton("+");
		zoomIn.setActionCommand("zoom_in");
		zoomIn.addActionListener(new ZoomListener());
		
		JButton zoomOut = new JButton("-");
		zoomOut.setActionCommand("zoom_out");
		zoomOut.addActionListener(new ZoomListener());
		
		zoomIn.setPreferredSize(new Dimension(70,30));
		zoomOut.setPreferredSize(new Dimension(70,30));
		
		//TODO add list of points
		
		add(xTextField);
		add(yTextField);
		add(zoomIn);
		add(zoomOut);
	}
	
	public class ZoomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("zoom_in")) {
				Grid.getInstance().zoomIn();
			} else if (e.getActionCommand().equalsIgnoreCase("zoom_out")) {
				Grid.getInstance().zoomOut();
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
					Point p = Grid.getInstance().getHighlightedPoint();
					if (p != null) {
						p.x = x;
						p.y = y;
						Grid.getInstance().redraw();
					}
				} catch (NumberFormatException nfe) {
					//pass and ignore
					xTextField.setText("");
					yTextField.setText("");
				}
			}
		}
		
	}

}