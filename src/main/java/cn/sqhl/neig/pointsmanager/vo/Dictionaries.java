package cn.sqhl.neig.pointsmanager.vo;

public class Dictionaries {
	 	private Integer id;

	    private String type;

	    private String code;

	    private String name;

	    private String parentcode;

	    private String remark;

	    private Integer order;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type == null ? null : type.trim();
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code == null ? null : code.trim();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public String getParentcode() {
	        return parentcode;
	    }

	    public void setParentcode(String parentcode) {
	        this.parentcode = parentcode == null ? null : parentcode.trim();
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }

	    public Integer getOrder() {
	        return order;
	    }

	    public void setOrder(Integer order) {
	        this.order = order;
	    }
}