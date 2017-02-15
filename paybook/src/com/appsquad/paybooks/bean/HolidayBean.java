package com.appsquad.paybooks.bean;

import java.util.Date;

public class HolidayBean {

	private String month,nameOfHoliday,holidayType;
	private int year,monthId,day,holidayId;
	private Date dateOfYear;
	public String getNameOfHoliday() {
		return nameOfHoliday;
	}
	public void setNameOfHoliday(String nameOfHoliday) {
		this.nameOfHoliday = nameOfHoliday;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonthId() {
		return monthId;
	}
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}
	public Date getDateOfYear() {
		return dateOfYear;
	}
	public void setDateOfYear(Date dateOfYear) {
		this.dateOfYear = dateOfYear;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
}
