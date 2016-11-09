package cn.sqhl.neig.pointsmanager.vo;


import java.util.List;

public class GoodsCategory {
    private Integer id;

    private String cateName;

    private Integer level;
    
    private String url;
    
    private Integer parentId;

    private Integer cateOrder; 

    public Integer getCateOrder() {
		return cateOrder;
	}

	public void setCateOrder(Integer cateOrder) {
		this.cateOrder = cateOrder;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName == null ? null : cateName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}