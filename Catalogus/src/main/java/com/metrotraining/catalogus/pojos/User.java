package com.metrotraining.catalogus.pojos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;

@Entity
@Table(name="useri")
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
	
	private UserRole type;
	private long birthday;
	private long lastLogin;
	private String password;
	private UserStatus status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public UserRole getType() {
		return type;
	}
	public void setType(UserRole type) {
		this.type = type;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	public long getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public User() {
		this.name = "";
		this.email = "";
	}
	public User(String name, String category, String description, String email, byte[] photo, String photoUrl, UserRole type,
			String password, UserStatus status) {
		Calendar cal = Calendar.getInstance() ;
		this.name = name;
		this.category = category;
		this.description = description;
		this.email = email;
		this.photo = photo;
		this.photoUrl = photoUrl;
		this.type = type;
		this.birthday = cal.getTimeInMillis();
		this.lastLogin = cal.getTimeInMillis();
		this.password = password;
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + "]";
	}
	
    public String getImageEncoded() {
        return new String(this.photo);
   }
	
	
}