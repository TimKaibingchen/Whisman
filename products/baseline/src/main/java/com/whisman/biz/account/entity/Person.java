
package com.whisman.biz.account.entity;

import com.whisman.biz.base.entity.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ac_person")
public class Person extends IdEntity {
	private static final long serialVersionUID = 5454155825314635342L;

	private String firstName;
	private String lastName;
	private String middleName;
	private Integer gender;
	private String personProfileImg;
	private String email;
	private String signature;
	private User user;
	private Date modDate;

	public Person(){
	}

	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	public void setMiddleName(String value) {
		this.middleName = value;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	public void setGender(Integer value) {
		this.gender = value;
	}
	
	public Integer getGender() {
		return this.gender;
	}
	public void setPersonProfileImg(String value) {
		this.personProfileImg = value;
	}
	
	public String getPersonProfileImg() {
		return this.personProfileImg;
	}
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setSignature(String value) {
		this.signature = value;
	}
	
	public String getSignature() {
		return this.signature;
	}

	public void setModDate(Date value) {
		this.modDate = value;
	}
	
	public Date getModDate() {
		return this.modDate;
	}

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}

