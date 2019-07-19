package com.es.client.ui;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.es.client.core.ClientMain;

public class MainFrame extends JFrame implements ActionListener{
	//�ı�����
	public static void initGlobalFont(){
	    FontUIResource fontUIResource = new FontUIResource(new Font("΢���ź�",Font.PLAIN, 12));
	    for (Enumeration<?> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
	        Object key = keys.nextElement();
	        Object value= UIManager.get(key);
	        if (value instanceof FontUIResource) {
	            UIManager.put(key, fontUIResource);
	        }  
	    }
	}
	Box box,baseBox;
	JMenu accountMenu,Show,Exit,aboutUs,movePage;
	JMenuBar menuBar;
	JPanel basePanel,showPanel,aboutUsPanel;
	JFrame signInPanel;
	ClientMain cm = null;
	int page = 0;
	int row = 23;
	CardLayout card=new CardLayout(5,5);
	public MainFrame(ClientMain m){
		this.cm = m;
		initGlobalFont();
		setTitle("Ϧ�����");
		init();
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(!cm.isLogin) 
			setVisible(false);
	}
	
	void init() {
		final JMenuItem logoutItem=new JMenuItem("�ǳ�");	
		logoutItem.addActionListener(this);
		accountMenu=new JMenu("�˻�");
		JMenu movePage = new JMenu("��ҳ");
		final JMenuItem prePage=new JMenuItem("��һҳ");	
		prePage.addActionListener(this);
		movePage.add(prePage);
		final JMenuItem nextPage=new JMenuItem("��һҳ");	
		nextPage.addActionListener(this);
		movePage.add(nextPage);
		accountMenu.add(logoutItem);
		Show=new JMenu("����");
		final JMenuItem showItem=new JMenuItem("ˢ��");
		Show.add(showItem);
		showItem.addActionListener(this);
		Exit=new JMenu("�˳�");
		final JMenuItem exitItem=new JMenuItem("�˳�");
		Exit.add(exitItem);
		exitItem.addActionListener(this);
		/*exitItem.addActionListener(this);*/
		aboutUs=new JMenu("��������");
		final JMenuItem aboutUsItem=new JMenuItem("��������");
		aboutUs.add(aboutUsItem);
		aboutUsItem.addActionListener(this);
		menuBar=new JMenuBar();
		menuBar.add(accountMenu);
		menuBar.add(Show);
		menuBar.add(movePage);
		menuBar.add(aboutUs);
		menuBar.add(Exit);
		setJMenuBar(menuBar);
		basePanel=new JPanel(card);
		showPanel=new showInfoPanel(cm, page, row);
		basePanel.add(showPanel,"show");
		aboutUsPanel=new AboutUsPanel();
		basePanel.add(aboutUsPanel,"aboutUs");
		this.getContentPane().add(basePanel);
	
	}
	

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="�ǳ�"){
			this.dispose();
			new SignInFrame().setVisible(true); 
		}
		else if(e.getActionCommand()=="ˢ��"){
			
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			
		}
		else if(e.getActionCommand()=="��һҳ"){
			if(this.page >= row)
				this.page -= row;
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			//JOptionPane.showMessageDialog(null, "null");
			
		}
		else if(e.getActionCommand()=="��һҳ"){
			this.page += row;
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			//JOptionPane.showMessageDialog(null, "null");
			
		}
		else if(e.getActionCommand()=="��������"){
			card.show(basePanel,"aboutUs");
			System.out.println("��������");
		}
		else if(e.getActionCommand()=="�˳�"){
			System.out.println("�����˳�");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new SignInFrame().setVisible(true);
	}
}