package com.foxhis.itf.police.model;

import java.util.Date;

/**
 * 对应sybase的idscan表结构
 * @author tq
 *
 */
public class IdScan {
	
	private String no;
	private String idtype;
	private String name;
	private String ref;
	private String haccnt;
	
	private String idtext;
	private byte[] idpic;
	private Date date1;
	
	private String pc_id;
	private String empno1;
	private String empno2;
	private Date date2;
	
	public String getEmpno1() {
		return empno1;
	}
	public void setEmpno1(String empno1) {
		this.empno1 = empno1;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getHaccnt() {
		return haccnt;
	}
	public void setHaccnt(String haccnt) {
		this.haccnt = haccnt;
	}
	public String getIdtext() {
		return idtext;
	}
	public void setIdtext(String idtext) {
		this.idtext = idtext;
	}
	public byte[] getIdpic() {
		return idpic;
	}
	public void setIdpic(byte[] idpic) {
		this.idpic = idpic;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public String getPc_id() {
		return pc_id;
	}
	public void setPc_id(String pc_id) {
		this.pc_id = pc_id;
	}
	public String getEmpno2() {
		return empno2;
	}
	public void setEmpno2(String empno2) {
		this.empno2 = empno2;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	
}
