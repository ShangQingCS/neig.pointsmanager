package cn.sqhl.neig.pointsmanager.vo;

import java.util.List;

public class Eventsinfo {
    private Long id;

    private String name;
    
    private String minpicture;

    private String picture;
    
    private String memo;
    
    private List<EventGoods> goodslist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

	public List<EventGoods> getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(List<EventGoods> goodslist) {
		this.goodslist = goodslist;
	}

	public String getMinpicture() {
		return minpicture;
	}

	public void setMinpicture(String minpicture) {
		this.minpicture = minpicture;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
}