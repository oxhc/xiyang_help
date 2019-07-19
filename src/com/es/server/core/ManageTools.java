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
		System.out.println("请输入要进行的操作");
		System.out.println("1.添加老人信息");
		System.out.println("2.通过ID删除一条记录");
		System.out.println("3.删除某姓名的所有记录");
		System.out.println("4.查看老人信息");
		System.out.println("5.退出");
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
						System.out.println("请输入数据，按如下格式\n(老人姓名， 个人信息， 出生日期【如1999-08-08】)");
						sil.add(new SeniorCitizen(cin.next(), cin.next(), DateFormatUtils.StringToDate(cin.next())));
						System.out.println("添加成功");
						break;
					case 2:
						System.out.println("请输入要删除的记录的ID");
						sil.remove(cin.nextInt());
						System.out.println("删除成功");
						break;
					case 3:
						System.out.println("请输入要删除的记录的姓名");
						sil.remove(cin.next());
						System.out.println("删除成功");
						break;
					case 4:
						System.out.println("请输入查询的ID或姓名");
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
					System.out.println("输入格式错误");
				}
				if(flag)showMenu();
				
			}
			sil.close();
			vil.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作失败");
		}
		cin.close();
	}
}
