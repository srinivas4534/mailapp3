package com.muraai.mailapp3;

import java.util.Date;

public class MailMessage {
	
	String subject;
	Date date;
	public MailMessage(String subject, Date date) {
		super();
		this.subject = subject;
		this.date = date;
	}
	public MailMessage() {
		super();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
