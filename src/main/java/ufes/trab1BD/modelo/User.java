package ufes.trab1BD.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties(value = {"user_id"})
@Entity
public class User {
	@JsonIgnore
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;

    @Column
    public String name;

    @Column
    public String date_of_birth;

    @Column
    public float balance;

	public User(){
		
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_of_birth() {
		return this.date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString(){
		String str = "";

		str += "user_id: " + this.user_id + "\n";
		str += "name: " + this.name + "\n";
		str += "date_of_birth: " + this.date_of_birth + "\n";
		str += "balance: " + this.balance + "\n";

		return str;
	}
}
