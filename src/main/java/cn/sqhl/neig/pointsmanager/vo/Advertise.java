package cn.sqhl.neig.pointsmanager.vo;

public class Advertise {
    private Long id;

    private String name;

    private String imgurl;

    private Long linkkind;

    private String imglink;

    private Integer ordernumb;

    private Long kind;
    
    private String memo;

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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Long getLinkkind() {
        return linkkind;
    }

    public void setLinkkind(Long linkkind) {
        this.linkkind = linkkind;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink == null ? null : imglink.trim();
    }

    public Integer getOrdernumb() {
        return ordernumb;
    }

    public void setOrdernumb(Integer ordernumb) {
        this.ordernumb = ordernumb;
    }

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    
}