package cn.sqhl.neig.pointsmanager.vo;

import java.math.BigDecimal;

public class Cart {
    private Long id;

    private Long userid;

    private String username;
    
    private Long goodsid;

    private String goodsname;

	private Integer count;

    private BigDecimal price;
    
    private Integer storenumb;

    private String goodsuse;

    public String getGoodsuse() {
		return goodsuse;
	}

	public void setGoodsuse(String goodsuse) {
		this.goodsuse = goodsuse;
	}

	public Integer getStorenumb() {
		return storenumb;
	}

	public void setStorenumb(Integer storenumb) {
		this.storenumb = storenumb;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
    
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

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}