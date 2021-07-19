package com.nurturecommunity.Dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ContactUsDetails")
public class ContactUsDetails {
	 private @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 	Long id;
	 	private String name;
	 	private String email;
	 	private String tel;
	 	private String message;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		@Override
		public String toString() {
			return "ContactUsDetails [id=" + id + ", name=" + name + ", email=" + email + ", tel=" + tel + ", message="
					+ message + "]";
		}
		public ContactUsDetails(Long id, String name, String email, String tel, String message) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.tel = tel;
			this.message = message;
		}	

}
