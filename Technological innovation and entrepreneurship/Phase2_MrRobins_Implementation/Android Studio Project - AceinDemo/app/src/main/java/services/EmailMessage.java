package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class illustrates an E-mail Message.
 */
public class EmailMessage {
	    private EmailAddress from = new EmailAddress("reservations@AInsuRNNs.gr");
	    private EmailAddress to;
	    private String subject;
	    private String body;

		public EmailMessage(EmailAddress to, String subject, String body) {
	    	this.to = to;
	    	this.subject = subject;
	    	this.body = body;
	    }

		public EmailMessage(String subject, String body) {
			this.subject = subject;
			this.body = body;
		}

	    public void setFrom(EmailAddress from) {
	        this.from = from;
	    }

	    public EmailAddress getFrom() {
	        return from;
	    }

	    public void setTo(EmailAddress to) {
	        this.to = to;
	    }

	    public EmailAddress getTo() {
	        return to;
	    }

	    public void setSubject(String subject) {
	        this.subject = subject;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public void setBody(String body) {
	        this.body = body;
	    }

	    public String getBody() {
	        return body;
	    }

	    public void appendToBody(String text) {
	        subject += text;
	    }
	    
	    public String toString() {
	    	return "Subject: " + this.subject + "\n" + this.body;
	    }
}
