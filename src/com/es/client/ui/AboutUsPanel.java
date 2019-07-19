package com.es.client.ui;

import java.awt.CardLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutUsPanel extends JPanel{
	Box box1,box2,box,boxx;
	public AboutUsPanel(){
		setLayout(new CardLayout());
		
		box1=Box.createVerticalBox();
		box1.add(Box.createVerticalGlue());
		box1.add(new JLabel("制作人员:"));
		box1.add(Box.createVerticalStrut(10));
		box1.add(new JLabel("联系方式:"));
		box1.add(Box.createVerticalGlue());
		box2=Box.createVerticalBox();
		box2.add(Box.createVerticalGlue());
		box2.add(new JLabel("郑其光，陈浩"));
		box2.add(Box.createVerticalStrut(10));
		box2.add(new JLabel("123456"));
		box2.add(Box.createVerticalGlue());
		box=Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(box1);
		box.add(box2);
		box.add(Box.createHorizontalGlue());
		boxx=Box.createVerticalBox();
		boxx.add(Box.createVerticalGlue());
		boxx.add(box);
		boxx.add(Box.createVerticalGlue());
		add(boxx);
	}
}
