package beaver;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ListOfEmployees extends JFrame implements ActionListener {
	private JTable table;
	private JButton btnConfirm;
	private JButton btnUpdate;
	private JButton btnDelete;
	private Main main;
	private Object[][] data;
	
	
	public ListOfEmployees(Main main) {
		super("List of Employees");
		this.main = main;
		addFrame();
		addButton();
		addTable();
		setVisible(true);
		

	}
	
	public void addFrame() {
		setResizable(false);
		setBounds(200, 50, 800, 600);
		setLayout(null);
	}
	
	public void addButton() {
		btnConfirm = new JButton("Exit");
		
		btnConfirm.addActionListener(this);
		btnConfirm.setBounds(600, 500, 150, 40);
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(40, 500, 150, 40);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(200, 500, 150, 40);
		btnDelete.addActionListener(this);
		add(btnConfirm);
		add(btnUpdate);
		add(btnDelete);
	}
	
	
	public void addTable() {
		String[] columnNames = {"ID(Person Number)", "Name",  "Position", "Start", "End date", "% full time"};
		ArrayList<Object[]> list = main.getEmployees();
		data = new Object[list.size()][7];
		for(int i = 0; i<list.size();i++) {
			data[i] = list.get(i);
			
		}
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		
		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		header.setReorderingAllowed(false);
		header.setResizingAllowed(false);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setBounds(0,0,800,460);
		
		add(panel);
		
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnConfirm) {
			dispose();
		} else if(e.getSource() == btnUpdate) {
			int index = table.getSelectedRow();
			Object id = table.getValueAt(index, 0);
			String name ="";
			String personnummer ="";
			String position ="";
			String start ="";
			String endDate ="";
			String fullTime ="";
			if(table.getValueAt(index, 1) != null) {
			name = table.getValueAt(index, 1).toString();
			}
			if(table.getValueAt(index, 2) != null) {
			personnummer = table.getValueAt(index, 2).toString();
			}
			if(table.getValueAt(index, 3) != null) {
			position = table.getValueAt(index, 3).toString();
			}
			if(table.getValueAt(index, 4) != null) {
			start = table.getValueAt(index, 4).toString();
			}
			if(table.getValueAt(index, 5) != null) {
			endDate = table.getValueAt(index, 5).toString();
			}
			if(table.getValueAt(index, 6) != null) {
			fullTime = table.getValueAt(index, 6).toString();
			}
			
			main.updateEmployee(id, name, personnummer, position, start, endDate, fullTime);
			
		} else if(e.getSource() == btnDelete) {
			int index = table.getSelectedRow();
			Object id = table.getValueAt(index, 0);
			main.deleteEmployee(id);
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.removeRow(index);
		}
		
	}

}
