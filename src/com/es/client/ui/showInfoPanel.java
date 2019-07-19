package com.es.client.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.es.client.core.ClientMain;
import com.es.model.SeniorCitizen;
import com.es.util.DateFormatUtils;

public class showInfoPanel extends JPanel {
	private JScrollPane scroll=null;
	DefaultTableModel defaultModel=null;
	ClientMain cm = null;
	JTable table;
	DefaultTableModel model = null;
	
	public showInfoPanel(ClientMain m, int page, int row){
		this.cm = m;
		table = new JTable();
		model=new DefaultTableModel();
		scroll = new JScrollPane();
		SeniorCitizen[] scList = cm.getSeniorCitizenByPage(page, row);
		int j;
		for(j=0;j<scList.length;j++){
			if(scList[j]==null)break;
		}
		Object[][] rowData=new Object[j][6];
		Object[] name=new Object[]{"ID","����","��Ϣ","����","������"};
		JButton[]button=new JButton[scList.length];
		for(int i=0;i<j;i++) {
			if(scList[i] == null) break;
			rowData[i][0] = scList[i].getId();
			rowData[i][1]=scList[i].getName();
			rowData[i][2]=scList[i].getInfo();
			rowData[i][3] = DateFormatUtils.getAge(scList[i].getBirth());
			rowData[i][4]=button[i];
		}
		
		table=new JTable(rowData, name);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);//�����п�
		//table.setRowHeight(40);
		ActionPanelEditorRenderer er = new ActionPanelEditorRenderer(cm);
		TableColumn column = table.getColumnModel().getColumn(4);
		column.setCellRenderer(new MyButtonRenderer());
		column.setCellEditor(er);//table.getColumnModel().getColumn(2),
		table.setDefaultRenderer(Object.class,new TableViewRenderer());
		scroll.setViewportView(table);
		this.add(scroll);
		Box loginSignUp = Box.createHorizontalBox();
	
		
	}
	public static void main(String[] args) {
		new SignInFrame().setVisible(true);
	}
}

@SuppressWarnings("serial")
class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

	ClientMain cm = null;
	public ActionPanelEditorRenderer(ClientMain m) {
		super();
		 cm = m;
	}
	
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component getTableCellEditorComponent(JTable table, Object arg1,
			boolean isLected, int row, int col) {
		// TODO Auto-generated method stub
		
		String str;
		if(col==4) {
			int id = (Integer) table.getValueAt(row, 0);
			System.out.println("---"+id);
			if(!cm.isLogin) {
				JOptionPane.showMessageDialog(null, "�㻹δ��¼�� ���ȵ�¼");
				
			} else {
				String info = JOptionPane.showInputDialog(null, "������Ҫ�������˵�����");
				cm.helpSeniorCitizen(cm.getVtID(), id, info);
				JOptionPane.showMessageDialog(null, "ѡ������ɹ��� ������֪ͨ");
			}
			
		}
		return null;
	}

	public Component getTableCellRendererComponent(JTable table, Object arg1,
		boolean isLected, boolean arg3, int row, int col) {
			return null;
		}
	
}

class MyButtonRenderer extends DefaultTableCellRenderer {
    private JPanel panel;

    private JButton button;
    
    private int num;

    public MyButtonRenderer() {
        initButton();

        initPanel();
        setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(button, BorderLayout.CENTER);
    }

    private void initButton() {
        button = new JButton("����������");

    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
       /* num = (Integer) value;
        
        button.setText(value == null ? "" : String.valueOf(value));*/

        return panel;
    }
    
}
class TableViewRenderer extends JTextArea implements TableCellRenderer{

	public TableViewRenderer(){
		setLineWrap(true);//�Ƿ���
		setWrapStyleWord(true); 
		
	}
	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		//��������
		
		int maxPreferredHeight = 0; 
		for (int i = 0; i < table.getColumnCount(); i++) { 
			setText("" + table.getValueAt(row, i)); 
			setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
			maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height); 
			}  
		if (table.getRowHeight(row) != maxPreferredHeight) // ��������������Ϲæ 
			table.setRowHeight(row, maxPreferredHeight); 
		setText(obj==null ? "" : obj.toString());
		return this;
	}
	
}