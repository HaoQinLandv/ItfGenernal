package com.foxhis.itf.police.model;

import java.util.Date;

/**
 *   id	char(20)	null,
	 roomno	varchar(5)	null,
	 guesttype	varchar(3)	null,
	 lname	varchar(40)	null,
	 fname	varchar(40)	null,
	 name	varchar(40)	null,
	 name2	varchar(40)	null,
	 sex	char(4)	null,
	 birthday	varchar(10)	null,
	 race	varchar(10)	null,
	 country	varchar(3)	null,
	 nation	varchar(4)	null,
	 address	varchar(200)	null,
	 prv	varchar(6)	null,
	 idtype	varchar(4)	null,
	 idcode	varchar(25)	null,
	 cardtype	varchar(4)	null,
	 cardid	varchar(25)	null,
	 scantime	varchar(8)	null,
	 date1	datetime	null,
	 date2	datetime	null,
	 date3	datetime	null,
	 rjplace	char(6)	null,
	 rjdate	datetime	null,
	 visaid	char(2)	null,
	 visano	char(32)	null,
	 visaend	datetime	null,
	 done	char(1)	DEFAULT  'F'	null,
	 no	char(12)	DEFAULT  ''	null

 * @author tq
 *
 */
public class IdScan_ga {
	
	private String id;
	private String roomno;
	private String guesttype;
	private String lname;
	private String fname;
	private String name;
	private String name2;
	private String sex;	
	private String birthday;
	private String race;
	private String country;
	private String nation;
	private String address;
	private String prv;
	private String idtype;
	private String idcode;
	private String cardtype;
	private String cardid;
	private String scantime;
	private Date date1;
	private Date date2;
	private Date date3;
	private String rjplace;	
	private Date rjdate;
	private Date visaend;
	private String visaid;
	private String visano;
	private String done;
	private String no;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getGuesttype() {
		return guesttype;
	}
	public void setGuesttype(String guesttype) {
		this.guesttype = guesttype;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrv() {
		return prv;
	}
	public void setPrv(String prv) {
		this.prv = prv;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdcode() {
		return idcode;
	}
	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getScantime() {
		return scantime;
	}
	public void setScantime(String scantime) {
		this.scantime = scantime;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	public String getRjplace() {
		return rjplace;
	}
	public void setRjplace(String rjplace) {
		this.rjplace = rjplace;
	}
	public Date getRjdate() {
		return rjdate;
	}
	public void setRjdate(Date rjdate) {
		this.rjdate = rjdate;
	}
	public Date getVisaend() {
		return visaend;
	}
	public void setVisaend(Date visaend) {
		this.visaend = visaend;
	}
	public String getVisaid() {
		return visaid;
	}
	public void setVisaid(String visaid) {
		this.visaid = visaid;
	}
	public String getVisano() {
		return visano;
	}
	public void setVisano(String visano) {
		this.visano = visano;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	
	


}
