package cn.sqhl.neig.pointsmanager.vo;

import java.math.BigDecimal;

public class Goods {
    private Long id;

    private String gname;

    private BigDecimal price;

    private Long brand;

    private String goodimglist;

    private String gfullname;

    private Integer storenumb;

    private String goodimg;

    private String detail;
    
    public Goods(Long id,String gname,BigDecimal price,Long brand,String goodimglist,String gfullname,Integer storenumb,String goodimg,String detail) {
		this.id=id;
		this.gname=gname;
		this.price=price;
		this.brand=brand;
		this.goodimglist=goodimglist;
		this.gfullname=gfullname;
		this.storenumb=storenumb;
		this.goodimg=goodimg;
		this.detail=detail;
	}

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}