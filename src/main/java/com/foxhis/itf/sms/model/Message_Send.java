package com.foxhis.itf.sms.model;

import java.util.Date;

/**
 * message_send模型
 * @author tq
 *
 */
public class Message_Send {

	private int sendid;
	private String hotelid;
	private String sender;
	private String custno;
	private String shortmsgno;
	private String shortmsgbody;
	private int sendstate;
	private Date requesttime;
	private Date sendtime;
	private Date starttime;
	private Date finishtime;
	private String resultdescrib;
	private int batchno;
	private String mouldcatalog;
	private String mouldno;
	private String accnt;
	private Date timelimit;
	private int sendpriority;
	
	private SM_Sendplus sm_sendplus;
	
	public SM_Sendplus getSm_sendplus() {
		return sm_sendplus;
	}
	public void setSm_sendplus(SM_Sendplus sm_sendplus) {
		this.sm_sendplus = sm_sendplus;
	}
	
	public int getSendid() {
		return sendid;
	}
	public void setSendid(int sendid) {
		this.sendid = sendid;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getShortmsgno() {
		return shortmsgno;
	}
	public void setShortmsgno(String shortmsgno) {
		this.shortmsgno = shortmsgno;
	}
	public String getShortmsgbody() {
		return shortmsgbody;
	}
	public void setShortmsgbody(String shortmsgbody) {
		this.shortmsgbody = shortmsgbody;
	}
	public int getSendstate() {
		return sendstate;
	}
	public void setSendstate(int sendstate) {
		this.sendstate = sendstate;
	}
	public Date getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}
	public String getResultdescrib() {
		return resultdescrib;
	}
	public void setResultdescrib(String resultdescrib) {
		this.resultdescrib = resultdescrib;
	}
	public int getBatchno() {
		return batchno;
	}
	public void setBatchno(int batchno) {
		this.batchno = batchno;
	}
	public String getMouldcatalog() {
		return mouldcatalog;
	}
	public void setMouldcatalog(String mouldcatalog) {
		this.mouldcatalog = mouldcatalog;
	}
	public String getMouldno() {
		return mouldno;
	}
	public void setMouldno(String mouldno) {
		this.mouldno = mouldno;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public Date getTimelimit() {
		return timelimit;
	}
	public void setTimelimit(Date timelimit) {
		this.timelimit = timelimit;
	}
	public int getSendpriority() {
		return sendpriority;
	}
	public void setSendpriority(int sendpriority) {
		this.sendpriority = sendpriority;
	}
	
	
}
