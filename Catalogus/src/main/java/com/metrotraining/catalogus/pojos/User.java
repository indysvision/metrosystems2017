package com.metrotraining.catalogus.pojos;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    @Column(name="USER_ID")
	private long id;
    
    @Column(name="USER_NAME", nullable=false)
    private String name;
    
	private String category;
	private String description;
	private String email;
	
    @Lob
	@Column(name="PHOTO", nullable=false, columnDefinition="mediumblob")
	private byte[] photo;
	private String photoUrl;
	
	@Enumerated(EnumType.STRING)
	private UserRole type;
	
	private long birthday;
	private long lastLogin;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	public User() {}
	
	public User(String name, String category, String description, String email, String photoUrl, UserRole type,
			long birthday, long lastLogin, String password, UserStatus status) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.email = email;
		this.photoUrl = photoUrl;
		this.type = type;
		this.birthday = birthday;
		this.lastLogin = lastLogin;
		this.password = password;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public String getDescription() {
		return description;
	}
	public String getEmail() {
		return email;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public UserRole getType() {
		return type;
	}
	public long getBirthday() {
		return birthday;
	}
	public long getLastLogin() {
		return lastLogin;
	}
	public String getPassword() {
		return password;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public void setType(UserRole type) {
		this.type = type;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", category=" + category + ", description=" + description + ", email=" + email
				+ ", photoUrl=" + photoUrl + ", type=" + type + ", birthday=" + birthday + ", lastLogin=" + lastLogin
				+ ", password=" + password + ", status=" + status + ", id=" + id + "]";
	}
}
