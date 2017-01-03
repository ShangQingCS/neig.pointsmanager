package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsUser {
    private Long id;

    private String userName;

    private String nickName;

    private String trueName;

    private String loginPwd;

    private String payPwd;

    private Long userGrade;

    private String userPhone;

    private String userMail;

    private Integer userSex;

    private String identityCard;

    private Date identityCardValidity;

    private String identityIssuing;

    private Integer identityStatus;

    private BigDecimal userKyBalance;

    private BigDecimal userFxBalance;

    private BigDecimal userDjBalance;

    private Integer tixianStatus;

    private BigDecimal userJfBalance;

    private Date createTime;

    private Date optionTime;

    private Integer userStatus;

    private Long userPid;

    private String optionRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public Long getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Long userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public Date getIdentityCardValidity() {
        return identityCardValidity;
    }

    public void setIdentityCardValidity(Date identityCardValidity) {
        this.identityCardValidity = identityCardValidity;
    }

    public String getIdentityIssuing() {
        return identityIssuing;
    }

    public void setIdentityIssuing(String identityIssuing) {
        this.identityIssuing = identityIssuing == null ? null : identityIssuing.trim();
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public BigDecimal getUserKyBalance() {
        return userKyBalance;
    }

    public void setUserKyBalance(BigDecimal userKyBalance) {
        this.userKyBalance = userKyBalance;
    }

    public BigDecimal getUserFxBalance() {
        return userFxBalance;
    }

    public void setUserFxBalance(BigDecimal userFxBalance) {
        this.userFxBalance = userFxBalance;
    }

    public BigDecimal getUserDjBalance() {
        return userDjBalance;
    }

    public void setUserDjBalance(BigDecimal userDjBalance) {
        this.userDjBalance = userDjBalance;
    }

    public Integer getTixianStatus() {
        return tixianStatus;
    }

    public void setTixianStatus(Integer tixianStatus) {
        this.tixianStatus = tixianStatus;
    }

    public BigDecimal getUserJfBalance() {
        return userJfBalance;
    }

    public void setUserJfBalance(BigDecimal userJfBalance) {
        this.userJfBalance = userJfBalance;
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

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserPid() {
        return userPid;
    }

    public void setUserPid(Long userPid) {
        this.userPid = userPid;
    }

    public String getOptionRemark() {
        return optionRemark;
    }

    public void setOptionRemark(String optionRemark) {
        this.optionRemark = optionRemark == null ? null : optionRemark.trim();
    }
}