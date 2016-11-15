package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;
import java.util.Date;

public class NsOrder {
    private Long id;

    private Long userid;

    private BigDecimal total;

    private Integer counts;

    private String paytype;

    private String outway;

    private String orderstatus;

    private String deliveryNumb;

    private Date createTime;

    private Date deliveryTime;

    private String paynumb;

    private Long positionid;

    private String address;

    private String name;

    private String postcode;

    private String contactnumb;

    private String invoice;

    private String companyname;

    private String content;

    private String remark;

    private BigDecimal commisionCharge;

    private BigDecimal cash;

    private BigDecimal accountAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getOutway() {
        return outway;
    }

    public void setOutway(String outway) {
        this.outway = outway == null ? null : outway.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getDeliveryNumb() {
        return deliveryNumb;
    }

    public void setDeliveryNumb(String deliveryNumb) {
        this.deliveryNumb = deliveryNumb == null ? null : deliveryNumb.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPaynumb() {
        return paynumb;
    }

    public void setPaynumb(String paynumb) {
        this.paynumb = paynumb == null ? null : paynumb.trim();
    }

    public Long getPositionid() {
        return positionid;
    }

    public void setPositionid(Long positionid) {
        this.positionid = positionid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getContactnumb() {
        return contactnumb;
    }

    public void setContactnumb(String contactnumb) {
        this.contactnumb = contactnumb == null ? null : contactnumb.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getCommisionCharge() {
        return commisionCharge;
    }

    public void setCommisionCharge(BigDecimal commisionCharge) {
        this.commisionCharge = commisionCharge;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }
}