package com.appsquad.paybooks.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.HolidayBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.HolidayDao;

public class HolidayService {

	public static ArrayList<Integer> getDayList(int year, int month){
		ArrayList<Integer> dayList = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		int noOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("No of days = "+noOfDays);
		for(int i = 1;i<=noOfDays;i++){
			dayList.add(i);
		}
		return dayList;
	}

	public static boolean isValid(MonthMasterBean year,MonthMasterBean month,Date selectedDate,String nameOfHoliday){
		if(year.getYr()!=null){
			if(month.getMonth()!=null){
				if(selectedDate!=null){
					if(nameOfHoliday!=null){
						return true;
					}else{
						Messagebox.show("Name of holiday required!","Information Required",Messagebox.OK,Messagebox.EXCLAMATION);
						return false;
					}
				}else{
					Messagebox.show("Day required!","Information Required",Messagebox.OK,Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("Month required!","Information Required",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Year required!","Information Required",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean isValidSelection(MonthMasterBean year, MonthMasterBean month,String[] dateArr){
		if(dateArr[1].equals(month.getMonth())){
			if(dateArr[2].equals(year.getYr())){
				return true;
			}else{
				Messagebox.show("Year can not be changed!","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Month can not be changed!","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static int saveHolidayList(LinkedHashSet<HolidayBean> holidayList,String user){
		return HolidayDao.saveHoliday(holidayList, user);
	}
	
	public static LinkedHashSet<HolidayBean> loadAllSavedHolidays(String year){
		return HolidayDao.loadSavedHolidayList(year);
	}
}
