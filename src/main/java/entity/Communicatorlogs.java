package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@NamedQuery(name="Communicatorlogs.findAll", query="SELECT o FROM  Communicatorlogs o")
public class Communicatorlogs implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int id;

	@Column
	private Timestamp date;
	
	@Column
	private String messagetype;
	
	@Column
	private String sender;
	
	@Column
	private String receiver;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getMmessagetype() {
		return messagetype;
	}

	public void setMmessagetype(String mmessagetype) {
		this.messagetype = mmessagetype;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}