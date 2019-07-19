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
	//改变字体
	public static void initGlobalFont(){
	    FontUIResource fontUIResource = new FontUIResource(new Font("微软雅黑",Font.PLAIN, 12));
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
		setTitle("夕阳帮扶");
		init();
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(!cm.isLogin) 
			setVisible(false);
	}
	
	void init() {
		final JMenuItem logoutItem=new JMenuItem("登出");	
		logoutItem.addActionListener(this);
		accountMenu=new JMenu("账户");
		JMenu movePage = new JMenu("翻页");
		final JMenuItem prePage=new JMenuItem("上一页");	
		prePage.addActionListener(this);
		movePage.add(prePage);
		final JMenuItem nextPage=new JMenuItem("下一页");	
		nextPage.addActionListener(this);
		movePage.add(nextPage);
		accountMenu.add(logoutItem);
		Show=new JMenu("操作");
		final JMenuItem showItem=new JMenuItem("刷新");
		Show.add(showItem);
		showItem.addActionListener(this);
		Exit=new JMenu("退出");
		final JMenuItem exitItem=new JMenuItem("退出");
		Exit.add(exitItem);
		exitItem.addActionListener(this);
		/*exitItem.addActionListener(this);*/
		aboutUs=new JMenu("关于我们");
		final JMenuItem aboutUsItem=new JMenuItem("关于我们");
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
		if(e.getActionCommand()=="登出"){
			this.dispose();
			new SignInFrame().setVisible(true); 
		}
		else if(e.getActionCommand()=="刷新"){
			
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			
		}
		else if(e.getActionCommand()=="上一页"){
			if(this.page >= row)
				this.page -= row;
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			//JOptionPane.showMessageDialog(null, "null");
			
		}
		else if(e.getActionCommand()=="下一页"){
			this.page += row;
			basePanel.remove(showPanel);
			showPanel=new showInfoPanel(cm, page, row);
			basePanel.add(showPanel,"show");
			card.show(basePanel,"show");
			//JOptionPane.showMessageDialog(null, "null");
			
		}
		else if(e.getActionCommand()=="关于我们"){
			card.show(basePanel,"aboutUs");
			System.out.println("关于我们");
		}
		else if(e.getActionCommand()=="退出"){
			System.out.println("正在退出");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new SignInFrame().setVisible(true);
	}
}