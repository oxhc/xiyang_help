package com.es.server.core;

import java.util.Scanner;

import com.es.model.SeniorCitizen;
import com.es.model.Volunteer;
import com.es.server.dao.SeniorCitizenImpl;
import com.es.server.dao.VolunteerImpl;
import com.es.util.DateFormatUtils;

public class ManageTools {
	public static void showMenu() {
		System.out.println("---------------------------");
		System.out.println("������Ҫ���еĲ���");
		System.out.println("1.���������Ϣ");
		System.out.println("2.ͨ��IDɾ��һ����¼");
		System.out.println("3.ɾ��ĳ���������м�¼");
		System.out.println("4.�鿴������Ϣ");
		System.out.println("5.�˳�");
	}

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		SeniorCitizenImpl sil = new SeniorCitizenImpl();
		VolunteerImpl vil = new VolunteerImpl();
		boolean flag = true;
		try{
			showMenu();
			while(cin.hasNext() && flag) {
				String cmd = cin.nextLine();
				if(cmd.matches("\\d")) {
					int num = Integer.parseInt(cmd);
					switch(num) {
					case 1:
						System.out.println("���������ݣ������¸�ʽ\n(���������� ������Ϣ�� �������ڡ���1999-08-08��)");
						sil.add(new SeniorCitizen(cin.next(), cin.next(), DateFormatUtils.StringToDate(cin.next())));
						System.out.println("��ӳɹ�");
						break;
					case 2:
						System.out.println("������Ҫɾ���ļ�¼��ID");
						sil.remove(cin.nextInt());
						System.out.println("ɾ���ɹ�");
						break;
					case 3:
						System.out.println("������Ҫɾ���ļ�¼������");
						sil.remove(cin.next());
						System.out.println("ɾ���ɹ�");
						break;
					case 4:
						System.out.println("�������ѯ��ID������");
						String qu = cin.next();
						
						if(qu.matches("\\d")) {
							SeniorCitizen sc = sil.query(Integer.parseInt(qu));
							if(sc == null) break;
							String vts = sc.getVts();
							String[] v = vts.split(",");
							for(String vt:v) {
								if(vt.equals("") || vt == null) continue;
								Volunteer volun = vil.query(Integer.parseInt(vt));
								if(volun!=null) System.out.println(volun);
							}
						} else {
							SeniorCitizen sc = sil.query(qu);
							if(sc == null) break;
							String vts = sc.getVts();
							String[] v = vts.split(",");
							for(String vt:v) {
								if(vt.equals("") || vt == null) continue;
								Volunteer volun = vil.query(Integer.parseInt(vt));
								if(volun!=null) System.out.println(volun);
							}
						}
						break;
					case 5:
						flag = false;
					}
					
				} else {
					System.out.println("�����ʽ����");
				}
				if(flag)showMenu();
				
			}
			sil.close();
			vil.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
		cin.close();
	}
}
