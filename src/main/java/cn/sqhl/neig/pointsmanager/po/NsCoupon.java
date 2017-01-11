package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsCoupon {
    private Long id;

    private String couponName;

    private Integer couponType;

    private BigDecimal couponZsBalance;

    private BigDecimal couponBlance;

    private BigDecimal couponXfBalance;

    private Integer couponExpiryDate;

    private String couponRemark;

    private Integer couponStatus;

    private Date createTime;

    private Long optionAdminid;

    private Date optionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponZsBalance() {
        return couponZsBalance;
    }

    public void setCouponZsBalance(BigDecimal couponZsBalance) {
        this.couponZsBalance = couponZsBalance;
    }

    public BigDecimal getCouponBlance() {
        return couponBlance;
    }

    public void setCouponBlance(BigDecimal couponBlance) {
        this.couponBlance = couponBlance;
    }

    public BigDecimal getCouponXfBalance() {
        return couponXfBalance;
    }

    public void setCouponXfBalance(BigDecimal couponXfBalance) {
        this.couponXfBalance = couponXfBalance;
    }

    public Integer getCouponExpiryDate() {
        return couponExpiryDate;
    }

    public void setCouponExpiryDate(Integer couponExpiryDate) {
        this.couponExpiryDate = couponExpiryDate;
    }

    public String getCouponRemark() {
        return couponRemark;
    }

    public void setCouponRemark(String couponRemark) {
        this.couponRemark = couponRemark == null ? null : couponRemark.trim();
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOptionAdminid() {
        return optionAdminid;
    }

    public void setOptionAdminid(Long optionAdminid) {
        this.optionAdminid = optionAdminid;
    }

    public Date getOptionTime() {
        return optionTime;
    }

    public void setOptionTime(Date optionTime) {
        this.optionTime = optionTime;
    }
}