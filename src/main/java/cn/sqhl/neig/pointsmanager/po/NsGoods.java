package cn.sqhl.neig.pointsmanager.po;

import java.math.BigDecimal;

public class NsGoods {
    private Long id;

    private String gname;

    private BigDecimal price;

    private Long category;

    private Long kind;

    private Long brand;

    private String goodimglist;

    private Integer isuser;

    private String gfullname;

    private Integer storenumb;

    private String goodimg;

    private Integer freazes;

    private BigDecimal costprice;

    private Integer sellnumb;

    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public String getGoodimglist() {
        return goodimglist;
    }

    public void setGoodimglist(String goodimglist) {
        this.goodimglist = goodimglist == null ? null : goodimglist.trim();
    }

    public Integer getIsuser() {
        return isuser;
    }

    public void setIsuser(Integer isuser) {
        this.isuser = isuser;
    }

    public String getGfullname() {
        return gfullname;
    }

    public void setGfullname(String gfullname) {
        this.gfullname = gfullname == null ? null : gfullname.trim();
    }

    public Integer getStorenumb() {
        return storenumb;
    }

    public void setStorenumb(Integer storenumb) {
        this.storenumb = storenumb;
    }

    public String getGoodimg() {
        return goodimg;
    }

    public void setGoodimg(String goodimg) {
        this.goodimg = goodimg == null ? null : goodimg.trim();
    }

    public Integer getFreazes() {
        return freazes;
    }

    public void setFreazes(Integer freazes) {
        this.freazes = freazes;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public Integer getSellnumb() {
        return sellnumb;
    }

    public void setSellnumb(Integer sellnumb) {
        this.sellnumb = sellnumb;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}