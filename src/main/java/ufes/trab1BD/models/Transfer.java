package ufes.trab1BD.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int transfer_id;

	@ManyToOne
	@JoinColumn(name = "payer_id")
    public User payer;

	@ManyToOne
	@JoinColumn(name = "payee_id")
    public User payee;

	@Column
    public float value;

	@Column
    public String date;

	public Transfer(){
		
	}

	public int getTransfer_id() {
		return this.transfer_id;
	}

	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}

	public User getPayer() {
		return this.payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public User getPayee() {
		return this.payee;
	}

	public void setPayee(User payee) {
		this.payee = payee;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
