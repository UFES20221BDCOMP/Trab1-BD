package ufes.trab1BD.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int purchase_id;

    @Column
    public int user_id;

    @Column
    public int store_id;

    @Column
    public float value;

    @Column
    public String date;

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStore_id() {
		return this.store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
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
