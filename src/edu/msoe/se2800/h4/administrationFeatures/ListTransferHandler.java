package edu.msoe.se2800.h4.administrationFeatures;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

public class ListTransferHandler extends TransferHandler {
	
	/** Generated serialVersionUID */
	private static final long serialVersionUID = 5054261398193798520L;
	private JList list;
	
	
	public ListTransferHandler(JList list) {
		this.list = list;
	}
	
	/**
	 * Imports data flavors and returns a true or false if the flavor is flavored or not
	 * 
	 * @return Boolean
	 */
	public boolean canImport(TransferHandler.TransferSupport info) {
        // we only import Strings
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        if (dl.getIndex() == -1) {
            return false;
        }
        return true;
    }

	/**
	 * Imports data if the specific flavor is supported
	 * 
	 * @return Boolean
	 */
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }
        
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            System.out.println("Data flavor not supported.");
        	return false;
        }

        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        DefaultListModel listModel = (DefaultListModel)list.getModel();
        int index = dl.getIndex();
        boolean insert = dl.isInsert();
        // Get the current string under the drop.
        //String value = (String)listModel.getElementAt(index);

        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception e) { 
        	return false;
        }
        
        /*// Display a dialog with the drop information.
        String dropValue = "\"" + data + "\" dropped ";
        if (dl.isInsert()) {
            if (dl.getIndex() == 0) {
                displayDropLocation(dropValue + "at beginning of list");
            } else if (dl.getIndex() >= list.getModel().getSize()) {
                displayDropLocation(dropValue + "at end of list");
            } else {
                String value1 = (String)list.getModel().getElementAt(dl.getIndex() - 1);
                String value2 = (String)list.getModel().getElementAt(dl.getIndex());
                displayDropLocation(dropValue + "between \"" + value1 + "\" and \"" + value2 + "\"");
            }
        } else {
            displayDropLocation(dropValue + "on top of " + "\"" + value + "\"");
        }*/
        
        /**  This is commented out for the basicdemo.html tutorial page.
         **  If you add this code snippet back and delete the
         **  "return false;" line, the list will accept drops
         **  of type string.*/
        // Perform the actual import.  
        if (insert) {
            listModel.add(index, data);
        } else {
            listModel.set(index, data);
        }
        return true;
    }
    
    /**
     * Gets actions based on the JComponent c
     */
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
    
    /**
     * creates a transferable object
     * 
     * @return the Transferable object
     */
    protected Transferable createTransferable(JComponent c) {
        JList list = (JList)c;
        Object[] values = list.getSelectedValues();

        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < values.length; i++) {
            Object val = values[i];
            buff.append(val == null ? "" : val.toString());
            if (i != values.length - 1) {
                buff.append("\n");
            }
        }
        return new StringSelection(buff.toString());
    }
    
    /**
     * Exports all the information based on source and data
     * 
     */
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
    	if (action == TransferHandler.MOVE) {
    		DefaultListModel listModel = (DefaultListModel)list.getModel();
    		try {
				listModel.removeElement(data.getTransferData(DataFlavor.stringFlavor));
			} catch (UnsupportedFlavorException e) {
				System.out.println("UnsupportedOperationException in export done");
			} catch (IOException e) {
				System.out.println("IOException in export done");
			}
    	}
    }

}
