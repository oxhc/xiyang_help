package com.es.client.ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.es.client.core.ClientMain;
import com.es.model.Volunteer;

public class RegisterFrame  extends JFrame{
	
	JLabel nameLabel,infoLabel,dateLabe;
	JButton loginButton;
	Box box1,box2,box,baseBox;
	JPanel basePanel;
	ClientMain cm = null;
	public RegisterFrame(ClientMain m){
		this.cm = m;
		setSize(250,250);
		setTitle("ע��");
		setLocationRelativeTo(null);
		setLayout(new CardLayout());
		box1=Box.createVerticalBox();
		box1.add(new JLabel("��  �� ��    "));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new JLabel("��      ��     "));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new JLabel("��      Ϣ    "));
		box1.add(Box.createVerticalStrut(8));
		box1.add(new JLabel("��      ��    "));
		box1.add(Box.createVerticalStrut(8));//��Ӵ�ֱ���
		box1.add(new JLabel("ȷ������    "));
		box1.add(Box.createVerticalStrut(8));
		box2=Box.createVerticalBox();
		final JTextField userField=new JTextField(8);//���������
		box2.add(userField);
		box2.add(Box.createVerticalStrut(8));
		final JTextField userNameField=new JTextField();
		box2.add(userNameField);
		box2.add(Box.createVerticalStrut(8));
		final JTextField infoField=new JTextField();
		box2.add(infoField);
		box2.add(Box.createVerticalStrut(8));
		final JPasswordField passwordField=new JPasswordField(8);//�����Ϣ��
		box2.add(passwordField);
		box2.add(Box.createVerticalStrut(8));
		final JPasswordField againPasswordField=new JPasswordField(8);
		box2.add(againPasswordField);//������ݿ�
		box2.add(Box.createVerticalStrut(8));
		box=Box.createHorizontalBox();
		box.add(box1);
		box.add(Box.createVerticalStrut(8));
		box.add(box2);
		basePanel=new JPanel(new FlowLayout());
		basePanel.add(box);
		basePanel.add(Box.createVerticalStrut(20));
		loginButton=new JButton("ȷ��ע��");
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String user = userField.getText();
				String userName=userNameField.getText();
				String infos=infoField.getText();
				if(userField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "�û���������Ϊ��");
					//return;
				}
				else if(userNameField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "����������Ϊ��");
				}
				else if(infoField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "��Ϣ������Ϊ��");
				}
				else if(passwordField.getPassword().length==0||againPasswordField.getPassword().length==0){
					JOptionPane.showMessageDialog(null, "���벻����Ϊ��");
					//return;
				}
				else {
					char[] password1 = passwordField.getPassword();
					char[] password2 = againPasswordField.getPassword();
					String s1 = new String(password1);
					String s2 = new String(password2);
					if(!s1.equals(s2))
					JOptionPane.showMessageDialog(null, "���벻һ��");
					else {
						Volunteer vt = new Volunteer(user, userName, infos, new String(password1));
						if(cm.signUp(vt)) {
							JOptionPane.showMessageDialog(null, "ע��ɹ�");
						} else {
							JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ� ���û����ѱ�ע���ǰ���粻��");
						}
						
					}
					//return;
				}
				
				// ----
			}
		});
		baseBox=Box.createVerticalBox();
		baseBox.add(box);
		baseBox.createVerticalStrut(20);
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
