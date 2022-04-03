package net.codejava.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	
	@Column(unique=true)
	private String email;
	private String password;
	private String firstName;
    private String LastName;

    
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
    private List<Doc> documents = new ArrayList<>();
    
    public User()
    {
    	
    }
    
	public User(Long id, String email, String password, String firstName, String lastName) {
	this.userid = id;
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	LastName = lastName;
}


	public List<Doc> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Doc> documents) {
		this.documents = documents;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	

}
