package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 4846524799433655631L;
	
	private JTextField xTextField, yTextField;
	
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
		add(xTextField);
		add(yTextField);
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
