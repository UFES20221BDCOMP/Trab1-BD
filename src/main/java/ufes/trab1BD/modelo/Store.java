package ufes.trab1BD.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int store_id;

	@Column
    public String name;

	@Column
    public float balance;

	@Column
    public int owner_id;

	public int getStore_id() {
		return this.store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getOwner_id() {
		return this.owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

}
