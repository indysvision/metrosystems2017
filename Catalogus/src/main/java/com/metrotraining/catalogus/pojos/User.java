package com.metrotraining.catalogus.pojos;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    @Column(nullable=false)
    private String name;
    
    @Column( nullable=false )
	private String category;
	private String description;
	
	@Column( unique=true, nullable=false ) 
	private String email;
	
    @Lob
	@Column( name="PHOTO", columnDefinition="mediumblob") //  nullable=false,
	private byte[] photo;
	private String photoUrl;
	
	@Column( nullable=false )
	private long birthday;
	private long lastLogin;
	
	@Column( nullable=false )
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column( nullable=false )
	private UserRole type;
	
	@Column( nullable=false )
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	public User() {}
	
	public User(String name, String category, String description, String email,byte[] photo, String photoUrl, UserRole type,
			long birthday, long lastLogin, String password, UserStatus status) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.email = email;
		this.photo = new byte[10];
		this.photoUrl = photoUrl;
		this.type = type;
		this.birthday = birthday;
		this.lastLogin = lastLogin;
		this.password = password;
		this.status = status;
	}
	
	public User(String name, String email, String password) { 
		this.name = name;
		this.category = "";
		this.description = "admin description";
		this.email = email;
		this.photo=new byte[10];
		this.photoUrl = "...";
		this.type = UserRole.ADMIN;
		this.birthday = 555;
		this.lastLogin = 1999;
		this.password = password;
		this.status = UserStatus.ACTIVE;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
