/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsender.application.bot.config.utilities;

import java.util.Date;

/**
 * This is a data capsule for a WhatsApp message.
 * @author ALEXANDRE
 */
public class Message {
    private String sender;
    private String content;
    private Date timeStamp;
	
	public Message() {
		
	}
	
	/**
	 * 
	 * @param sender is the contact that sent the message.
	 * @param content is the text inside the message.
	 * @param timeStamp is when the message was sent.
	 */
	public Message(String sender, String content, Date timeStamp) {
		this.sender = sender;
		this.content = content;
		this.timeStamp = timeStamp;
	}



	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}
