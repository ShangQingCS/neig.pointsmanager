package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsUserCoupon {
    private Long id;

    private Long userId;

    private String userName;

    private Long couponId;

    private String couponName;

    private BigDecimal couponBlance;

    private BigDecimal couponXfBalance;

    private Date couponCreatdate;

    private Date couponExpirydate;

    private String orderSn;

    private String couponStatus;

    private Date optionTime;

    private Long optionAdminid;

    private String optionRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
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

    public Date getCouponCreatdate() {
        return couponCreatdate;
    }

    public void setCouponCreatdate(Date couponCreatdate) {
        this.couponCreatdate = couponCreatdate;
    }

    public Date getCouponExpirydate() {
        return couponExpirydate;
    }

    public void setCouponExpirydate(Date couponExpirydate) {
        this.couponExpirydate = couponExpirydate;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus == null ? null : couponStatus.trim();
    }

    public Date getOptionTime() {
        return optionTime;
    }

    public void setOptionTime(Date optionTime) {
        this.optionTime = optionTime;
    }

    public Long getOptionAdminid() {
        return optionAdminid;
    }

    public void setOptionAdminid(Long optionAdminid) {
        this.optionAdminid = optionAdminid;
    }

    public String getOptionRemark() {
        return optionRemark;
    }

    public void setOptionRemark(String optionRemark) {
        this.optionRemark = optionRemark == null ? null : optionRemark.trim();
    }
}