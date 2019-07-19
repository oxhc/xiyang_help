package com.es.client.ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.es.client.core.ClientMain;

public class SignInPanel extends JFrame{
	JLabel nameLabel,infoLabel,dateLabe;
	JButton loginButton;
	Box box1,box2,box,baseBox;
	JPanel basePanel;
	ClientMain cm = null;
	JMenuBar jmenu = null;
	JMenu jj = null;
	MainFrame jf = null;
	public SignInPanel(ClientMain m, MainFrame p){
		this.cm = m;
		this.jf = p;
		setSize(200,200);
		setTitle("登录");
		setLocationRelativeTo(p);
		setLayout(new CardLayout());
		box1=Box.createVerticalBox();
		box1.add(new JLabel("用  户 名    "));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new JLabel("密    码    "));
		box1.add(Box.createVerticalStrut(8));//添加垂直间距
		box2=Box.createVerticalBox();
		final JTextField nameField=new JTextField(8);//添加姓名框
		box2.add(nameField);
		box2.add(Box.createVerticalStrut(8));
		final JPasswordField infoField=new JPasswordField(8);//添加信息框
		box2.add(infoField);
		box2.add(Box.createVerticalStrut(8));
		final JTextField dateField=new JTextField(8);
		
		box2.add(Box.createVerticalStrut(8));
		box=Box.createHorizontalBox();
		box.add(box1);
		box.add(Box.createVerticalStrut(8));
		box.add(box2);
		basePanel=new JPanel(new FlowLayout());
		basePanel.add(box);
		basePanel.add(Box.createVerticalStrut(20));
		loginButton=new JButton("登录");
		final JFrame that = this;
		loginButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "用户名不允许为空");
					return;
				}
				else if(infoField.getPassword().length ==0){
					JOptionPane.showMessageDialog(null, "密码不允许为空");
					return;
				}
				else {
					String user  = nameField.getText();
					char[] passwd = infoField.getPassword();
					if(cm.signIn(user, new String(passwd))) {
						that.dispose();
						JOptionPane.showMessageDialog(null, "登录成功");
					} else {
						JOptionPane.showMessageDialog(null, "登录失败");
					}
					
				}
			}
		});
		baseBox=Box.createVerticalBox();
		baseBox.add(box);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(loginButton);
		basePanel.add(baseBox);
		Box verticBox = Box.createVerticalBox();
		verticBox.add(Box.createVerticalGlue());
		verticBox.add(basePanel);
		verticBox.add(Box.createVerticalGlue());
		Box horBox = Box.createHorizontalBox();
		horBox.add(Box.createHorizontalGlue());
		horBox.add(verticBox);
		horBox.add(Box.createHorizontalGlue());
		add(horBox);
	}
}

