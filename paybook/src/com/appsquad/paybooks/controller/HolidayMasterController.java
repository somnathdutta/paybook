package com.appsquad.paybooks.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.HolidayBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.LoadAllListDao;
import com.appsquad.paybooks.model.service.HolidayService;
import com.appsquad.paybooks.model.utils.Dateformatter;

public class HolidayMasterController {

	private MonthMasterBean monthMasterBean = new MonthMasterBean();
	private MonthMasterBean yearMasterBean = new MonthMasterBean();
	private MonthMasterBean yearBean = new MonthMasterBean();
	private ArrayList<MonthMasterBean> monthList;
	private ArrayList<MonthMasterBean> yearList;
	ArrayList<MonthMasterBean> dbYearList;
	private ArrayList<Integer> dayList;
	private LinkedHashSet<HolidayBean> holidayList = new LinkedHashSet<HolidayBean>();
	private LinkedHashSet<HolidayBean> existingHolidayList ;
	private String nameOfHoliday;
	private Date selectedDateOfYear;
	private Integer day ;
	private Session session = null;
	private boolean datePopUp = false,holidayNameDisabled=true;
	private String userId;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("createholiday.zul. . .");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		onLoad();
		loadYearList();
	}
	
	public void onLoad(){	
		monthList = LoadAllListDao.loadmonths();
	}
	
	public void loadYearList(){
		dbYearList = LoadAllListDao.loadyears();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		yearList = new ArrayList<MonthMasterBean>();
		for(MonthMasterBean dbYr : dbYearList){
			if(Integer.parseInt(dbYr.getYr())>=currentYear){
				yearList.add(dbYr);
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickNew(){
		loadYearList();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectMonth(){
		//String dateStr = "1-"+monthMasterBean.getMonth()+"-"+yearMasterBean.getYr();
		//selectedDateOfYear = Dateformatter.todate(dateStr);
		//System.out.println(selectedDateOfYear);
		//datePopUp = true;
		//selectedDateOfYear = new java.util.Date();
		dayList = HolidayService.getDayList(Integer.valueOf(yearMasterBean.getYr()) , (monthMasterBean.getMonthId()-1));
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectDay(){
		selectedDateOfYear = Dateformatter.getDate(String.valueOf(day), monthMasterBean.getMonth(), yearMasterBean.getYr());
		holidayNameDisabled = false;
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeDate(){
		System.out.println("Selected date: "+selectedDateOfYear);
		String dateStr = Dateformatter.toStringdate(selectedDateOfYear);
		System.out.println(dateStr);
		datePopUp = false;
		String dateArr[] = dateStr.split("-");
		if(HolidayService.isValidSelection(yearMasterBean, monthMasterBean, dateArr)){
			holidayNameDisabled = false;
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickAdd(){
		if(HolidayService.isValid(yearMasterBean, monthMasterBean, selectedDateOfYear,nameOfHoliday)){
			HolidayBean holiday = new HolidayBean();
			holiday.setDay(day);
			holiday.setYear(Integer.parseInt(yearMasterBean.getYr()) );
			holiday.setMonth(monthMasterBean.getMonth());
			holiday.setDateOfYear(selectedDateOfYear);
			holiday.setNameOfHoliday(nameOfHoliday);
			holidayList.add(holiday);
			
			day = null;
			nameOfHoliday = null;
			selectedDateOfYear = null;
			holidayNameDisabled = true;
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(holidayList.size()>0){
			if(HolidayService.saveHolidayList(holidayList, userId) > 0){
				holidayList.clear();
			}
		}else{
			Messagebox.show("No holiday list found to save!","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExisting(){
		yearList = dbYearList;
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectYear(){
		loadExistingHolidayList();
	}
	
	public void loadExistingHolidayList(){
		//yearMasterBean.setYr(null);
		existingHolidayList = HolidayService.loadAllSavedHolidays(yearBean.getYr());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickRemove(@BindingParam("bean")HolidayBean bean){
		holidayList.remove(bean);
	}
	
	public MonthMasterBean getMonthMasterBean() {
		return monthMasterBean;
	}
	public void setMonthMasterBean(MonthMasterBean monthMasterBean) {
		this.monthMasterBean = monthMasterBean;
	}
	public MonthMasterBean getYearMasterBean() {
		return yearMasterBean;
	}
	public void setYearMasterBean(MonthMasterBean yearMasterBean) {
		this.yearMasterBean = yearMasterBean;
	}
	public ArrayList<MonthMasterBean> getmonthList() {
		return monthList;
	}
	public void setmonthList(ArrayList<MonthMasterBean> monthList) {
		this.monthList = monthList;
	}
	
	public ArrayList<Integer> getDayList() {
		return dayList;
	}
	public void setDayList(ArrayList<Integer> dayList) {
		this.dayList = dayList;
	}
	
	public String getNameOfHoliday() {
		return nameOfHoliday;
	}
	public void setNameOfHoliday(String nameOfHoliday) {
		this.nameOfHoliday = nameOfHoliday;
	}

	public ArrayList<MonthMasterBean> getYearList() {
		return yearList;
	}

	public void setYearList(ArrayList<MonthMasterBean> yearList) {
		this.yearList = yearList;
	}

	public ArrayList<MonthMasterBean> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<MonthMasterBean> monthList) {
		this.monthList = monthList;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getSelectedDateOfYear() {
		return selectedDateOfYear;
	}

	public void setSelectedDateOfYear(Date selectedDateOfYear) {
		this.selectedDateOfYear = selectedDateOfYear;
	}

	public boolean isDatePopUp() {
		return datePopUp;
	}

	public void setDatePopUp(boolean datePopUp) {
		this.datePopUp = datePopUp;
	}

	public boolean isHolidayNameDisabled() {
		return holidayNameDisabled;
	}

	public void setHolidayNameDisabled(boolean holidayNameDisabled) {
		this.holidayNameDisabled = holidayNameDisabled;
	}

	public LinkedHashSet<HolidayBean> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(LinkedHashSet<HolidayBean> holidayList) {
		this.holidayList = holidayList;
	}

	public LinkedHashSet<HolidayBean> getExistingHolidayList() {
		return existingHolidayList;
	}

	public void setExistingHolidayList(
			LinkedHashSet<HolidayBean> existingHolidayList) {
		this.existingHolidayList = existingHolidayList;
	}

	public ArrayList<MonthMasterBean> getDbYearList() {
		return dbYearList;
	}

	public void setDbYearList(ArrayList<MonthMasterBean> dbYearList) {
		this.dbYearList = dbYearList;
	}

	public MonthMasterBean getYearBean() {
		return yearBean;
	}

	public void setYearBean(MonthMasterBean yearBean) {
		this.yearBean = yearBean;
	}


}
