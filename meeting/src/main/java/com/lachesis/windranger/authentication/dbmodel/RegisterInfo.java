/*
 * @ClassName RegisterInfo
 * @Description 
 * @version 1.0
 * @Date 2018-02-07 13:30:38
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;

public class RegisterInfo extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="register_id", value="")
    private Long registerId;

    @ApiModelProperty(name="register_meeting", value="")
    private String registerMeeting;

    @ApiModelProperty(name="name", value="")
    private String name;

    @ApiModelProperty(name="age", value="")
    private String age;

    @ApiModelProperty(name="province", value="")
    private String province;

    @ApiModelProperty(name="city", value="")
    private String city;

    @ApiModelProperty(name="duty", value="")
    private String duty;

    @ApiModelProperty(name="title", value="")
    private String title;

    @ApiModelProperty(name="organization", value="")
    private String organization;

    @ApiModelProperty(name="department", value="")
    private String department;

    @ApiModelProperty(name="mobile", value="")
    private String mobile;

    @ApiModelProperty(name="phone", value="")
    private String phone;

    @ApiModelProperty(name="email", value="")
    private String email;

    @ApiModelProperty(name="fee_origin", value="")
    private String feeOrigin;

    @ApiModelProperty(name="hotel", value="")
    private String hotel;

    @ApiModelProperty(name="lunch", value="")
    private String lunch;

    @ApiModelProperty(name="agree", value="")
    private String agree;

    @ApiModelProperty(name="invoice_title", value="")
    private String invoiceTitle;

    @ApiModelProperty(name="tax_no", value="")
    private String taxNo;

    @ApiModelProperty(name="hospital_level", value="")
    private String hospitalLevel;

    @ApiModelProperty(name="hospital_type", value="")
    private String hospitalType;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getRegisterMeeting() {
        return registerMeeting;
    }

    public void setRegisterMeeting(String registerMeeting) {
        this.registerMeeting = registerMeeting == null ? null : registerMeeting.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFeeOrigin() {
        return feeOrigin;
    }

    public void setFeeOrigin(String feeOrigin) {
        this.feeOrigin = feeOrigin == null ? null : feeOrigin.trim();
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel == null ? null : hotel.trim();
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch == null ? null : lunch.trim();
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree == null ? null : agree.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo == null ? null : taxNo.trim();
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel == null ? null : hospitalLevel.trim();
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType == null ? null : hospitalType.trim();
    }
}