package ufes.trab1BD.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int store_id;

	@Column
    public String name;

	@Column
    public float balance;

	@ManyToOne
	@JoinColumn(name = "owner_id")
    public User owner;

	public Store(){
		
	}

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

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
