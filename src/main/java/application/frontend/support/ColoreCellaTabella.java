package application.frontend.support;

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
		if(column == 4) {
			Object result = table.getModel().getValueAt(row, column);
			String stato = result.toString();
			
			if(stato.equals("Accesa") || stato.equals("Aperta")  || stato.equals("Ottimo") || stato.equals("In Funzione"))
				c.setBackground(Color.GREEN);
			else if(stato.equals("Spenta") || stato.equals("Chiusa") || stato.equals("Spento"))
				c.setBackground(Color.RED);
			else if(stato.equals("Misurazione") || stato.equals("Riscalda") || stato.equals("Raffredda"))
				c.setBackground(Color.ORANGE);
		}
		else c.setBackground(null);
			
		return c;
	}

}
