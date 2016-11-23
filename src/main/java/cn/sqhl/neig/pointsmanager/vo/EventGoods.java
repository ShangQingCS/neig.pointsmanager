package cn.sqhl.neig.pointsmanager.vo;

public class EventGoods {
    private Long id;

    private String goodsname;
    
    private String goodsimg;
    
    private Long goodsid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsimg() {
		return goodsimg;
	}

	public void setGoodsimg(String goodsimg) {
		this.goodsimg = goodsimg;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}
}