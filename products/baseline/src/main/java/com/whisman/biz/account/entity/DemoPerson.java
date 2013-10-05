package com.whisman.biz.account.entity;

import java.util.Date;

public class DemoPerson {
    private Integer id;

    private Integer personId;

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer addressId;

    private Integer timeZoneId;

    private Integer localeId;

    private Byte isPublic;

    private Integer invitedByUserId;

    private Date activationDate;

    private String personalCompanyName;

    private Byte neIsNeUser;

    private String personProfileImg;

    private Byte neIsDemoNeUser;

    private String emailAddress;

    private String emailDesc;

    private Date etlTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName == null ? null : middleName.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(Integer timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public Integer getLocaleId() {
        return localeId;
    }

    public void setLocaleId(Integer localeId) {
        this.localeId = localeId;
    }

    public Byte getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getInvitedByUserId() {
        return invitedByUserId;
    }

    public void setInvitedByUserId(Integer invitedByUserId) {
        this.invitedByUserId = invitedByUserId;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getPersonalCompanyName() {
        return personalCompanyName;
    }

    public void setPersonalCompanyName(String personalCompanyName) {
        this.personalCompanyName = personalCompanyName == null ? null : personalCompanyName.trim();
    }

    public Byte getNeIsNeUser() {
        return neIsNeUser;
    }

    public void setNeIsNeUser(Byte neIsNeUser) {
        this.neIsNeUser = neIsNeUser;
    }

    public String getPersonProfileImg() {
        return personProfileImg;
    }

    public void setPersonProfileImg(String personProfileImg) {
        this.personProfileImg = personProfileImg == null ? null : personProfileImg.trim();
    }

    public Byte getNeIsDemoNeUser() {
        return neIsDemoNeUser;
    }

    public void setNeIsDemoNeUser(Byte neIsDemoNeUser) {
        this.neIsDemoNeUser = neIsDemoNeUser;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public String getEmailDesc() {
        return emailDesc;
    }

    public void setEmailDesc(String emailDesc) {
        this.emailDesc = emailDesc == null ? null : emailDesc.trim();
    }

    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
}