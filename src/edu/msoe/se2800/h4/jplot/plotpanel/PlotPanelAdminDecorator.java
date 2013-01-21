package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.JPoint;
import edu.msoe.se2800.h4.jplot.grid.Grid;

public class PlotPanelAdminDecorator extends PlotPanelDecorator {
	
	private PopUpDemo popUp;

	public PlotPanelAdminDecorator(PlotPanelInterface plotPanel) {
		super(plotPanel);
		getComponent().addMouseListener(new PlotMouseAdapter());
	}
	
	/** Listeners and Adapters **/
	private class PlotMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				JPoint point = plotPanel.translateToNearestPoint(new JPoint(event.getX(), event.getY()));
				boolean found = false;
				for (JPoint p : Grid.getInstance().getPathPoints()) {
					if (p.x == point.x && p.y == point.y) {
						found = true;
						Grid.getInstance().setHighlightedPoint(Grid.getInstance().getPathPoints().indexOf(p));
					}
				}
				if (!found) {
					Grid.getInstance().setHighlightedPoint(-5);
				}
				Grid.getInstance().redraw();
			} else if (event.getButton() == MouseEvent.BUTTON3) {
				doPop(event);
			}
		}
		@Override
		public void mousePressed(MouseEvent event) {
			JPoint p = new JPoint(event.getX(), event.getY());
			setActivePoint(getInterceptedPoint(p));
			setActivePointIndexHolder(Grid.getInstance().getPathPoints().indexOf(getActivePoint()));
			Constants.DRAGGING_INDEX = getActivePointIndexHolder();
		}
		@Override
		public void mouseReleased(MouseEvent event) {
			setActivePoint(null);
			setActivePointIndexHolder(-5);
			Constants.DRAGGING_INDEX = -5;
			Grid.getInstance().repaint();
			Constants.HOVER_INDEX = -5;
		}
	}
	
	/** copied this from the interwebs **/
	private void doPop(MouseEvent e){
		popUp = new PopUpDemo(e);
		popUp.show(e.getComponent(), e.getX(), e.getY());
	}
	private class PopUpDemo extends JPopupMenu {
	    /** Generated serialVersionUID */
		private static final long serialVersionUID = -926882311315622109L;
		JMenuItem add;
	    JMenuItem delete;
	    JPoint clickedPoint;
	    MenuListener menuListener;
	    public PopUpDemo(MouseEvent e){
	    	menuListener = new MenuListener();
	    	clickedPoint = new JPoint(e.getX(), e.getY());
	        add = new JMenuItem("Add point");
	        add.setActionCommand("add_point");
	        add.addActionListener(menuListener);
	        delete = new JMenuItem("Delete point");
	        delete.setActionCommand("delete_point");
	        delete.addActionListener(menuListener);
	        add(add);
	        add(delete);
	    }
	    private class MenuListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("add_point")) {
					Grid.getInstance().addPoint(translateToNearestPoint(clickedPoint));
				} else if (e.getActionCommand().equalsIgnoreCase("delete_point")) {
					JPoint p = getInterceptedPoint(clickedPoint);
					if (p != null) {
						Grid.getInstance().removePoint(Grid.getInstance().getPathPoints().indexOf(p));
					}
				}
			}
	    }
	}

}
