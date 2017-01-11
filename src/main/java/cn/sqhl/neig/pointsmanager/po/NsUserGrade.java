package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsUserGrade {
    private Long id;

    private String gradeName;

    private BigDecimal gradeFcLevel;

    private BigDecimal gradeFcLevel1;

    private BigDecimal gradeFcLevel2;

    private BigDecimal gradeFcLevel3;

    private BigDecimal gradeBalance;

    private BigDecimal gradeTxBalance;

    private Date createTime;

    private Long optionAdminid;

    private Date optionTime;

    private String optionRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public BigDecimal getGradeFcLevel() {
        return gradeFcLevel;
    }

    public void setGradeFcLevel(BigDecimal gradeFcLevel) {
        this.gradeFcLevel = gradeFcLevel;
    }

    public BigDecimal getGradeFcLevel1() {
        return gradeFcLevel1;
    }

    public void setGradeFcLevel1(BigDecimal gradeFcLevel1) {
        this.gradeFcLevel1 = gradeFcLevel1;
    }

    public BigDecimal getGradeFcLevel2() {
        return gradeFcLevel2;
    }

    public void setGradeFcLevel2(BigDecimal gradeFcLevel2) {
        this.gradeFcLevel2 = gradeFcLevel2;
    }

    public BigDecimal getGradeFcLevel3() {
        return gradeFcLevel3;
    }

    public void setGradeFcLevel3(BigDecimal gradeFcLevel3) {
        this.gradeFcLevel3 = gradeFcLevel3;
    }

    public BigDecimal getGradeBalance() {
        return gradeBalance;
    }

    public void setGradeBalance(BigDecimal gradeBalance) {
        this.gradeBalance = gradeBalance;
    }

    public BigDecimal getGradeTxBalance() {
        return gradeTxBalance;
    }

    public void setGradeTxBalance(BigDecimal gradeTxBalance) {
        this.gradeTxBalance = gradeTxBalance;
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

    public String getOptionRemark() {
        return optionRemark;
    }

    public void setOptionRemark(String optionRemark) {
        this.optionRemark = optionRemark == null ? null : optionRemark.trim();
    }
}