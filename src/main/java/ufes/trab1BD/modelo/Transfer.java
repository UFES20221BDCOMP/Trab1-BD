package ufes.trab1BD.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int transfer_id;

	@Column
    public int payer;

	@Column
    public int payee;

	@Column
    public float value;

	@Column
    public String date;

	public int getPayer() {
		return this.payer;
	}

	public void setPayer(int payer) {
		this.payer = payer;
	}

	public int getPayee() {
		return this.payee;
	}

	public void setPayee(int payee) {
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
