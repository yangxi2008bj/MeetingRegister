/*
 * @ClassName Account
 * @Description 
 * @version 1.0
 * @Date 2018-02-07 13:30:38
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Account extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="name", value="")
    private String name;

    @ApiModelProperty(name="mobile", value="")
    private String mobile;

    @ApiModelProperty(name="verification", value="")
    private String verification;

    @ApiModelProperty(name="update_time", value="", example="2017-01-16T15:30:19.000+0800")
    private Date updateTime;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification == null ? null : verification.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}