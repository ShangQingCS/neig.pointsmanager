package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsUserPurse {
    private Long id;

    private String tradeType;

    private BigDecimal tradeAmount;

    private String tradeSn;

    private String tradeState;

    private String optionType;

    private Long userId;

    private BigDecimal userAmount;

    private Integer purseType;

    private String payAccount;

    private String payOpenBank;

    private String purseStatus;

    private Date createTime;

    private Date optionTime;

    private Long optionAdminid;

    private String optionAdminname;

    private String optionRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn == null ? null : tradeSn.trim();
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState == null ? null : tradeState.trim();
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType == null ? null : optionType.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(BigDecimal userAmount) {
        this.userAmount = userAmount;
    }

    public Integer getPurseType() {
        return purseType;
    }

    public void setPurseType(Integer purseType) {
        this.purseType = purseType;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public String getPayOpenBank() {
        return payOpenBank;
    }

    public void setPayOpenBank(String payOpenBank) {
        this.payOpenBank = payOpenBank == null ? null : payOpenBank.trim();
    }

    public String getPurseStatus() {
        return purseStatus;
    }

    public void setPurseStatus(String purseStatus) {
        this.purseStatus = purseStatus == null ? null : purseStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getOptionAdminname() {
        return optionAdminname;
    }

    public void setOptionAdminname(String optionAdminname) {
        this.optionAdminname = optionAdminname == null ? null : optionAdminname.trim();
    }

    public String getOptionRemark() {
        return optionRemark;
    }

    public void setOptionRemark(String optionRemark) {
        this.optionRemark = optionRemark == null ? null : optionRemark.trim();
    }
}