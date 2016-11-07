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

    private String isuse;

    private String gfullname;

    private Integer storenumb;

    private String goodimg;

    private Integer freazes;

    private Long costprice;

    private Integer sellnumb;

    private String img1;

    private String img2;

    private String img3;

    private String img4;

    private String img5;

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

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
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

    public Long getCostprice() {
        return costprice;
    }

    public void setCostprice(Long costprice) {
        this.costprice = costprice;
    }

    public Integer getSellnumb() {
        return sellnumb;
    }

    public void setSellnumb(Integer sellnumb) {
        this.sellnumb = sellnumb;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2 == null ? null : img2.trim();
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3 == null ? null : img3.trim();
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4 == null ? null : img4.trim();
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5 == null ? null : img5.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}