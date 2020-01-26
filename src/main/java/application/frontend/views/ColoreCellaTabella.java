package application.frontend.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ColoreCellaTabella implements TableCellRenderer{
	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(column == 2) {
			Object result = table.getModel().getValueAt(row, column);
			String stato = result.toString();
			
			if(stato.equals("Accesa") || stato.equals("Aperta"))
				c.setBackground(Color.GREEN);
			else 
				c.setBackground(Color.red);
		}
		else c.setBackground(null);
			
		return c;
	}

}