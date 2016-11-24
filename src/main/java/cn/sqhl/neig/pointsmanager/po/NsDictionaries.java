package cn.sqhl.neig.pointsmanager.po;

public class NsDictionaries {
    private Integer id;

    private String type;

    private String code;

    private String name;

    private String parentcode;

    private String remark;

    private String isuse;

    private String isedit;

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

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public String getIsedit() {
        return isedit;
    }

    public void setIsedit(String isedit) {
        this.isedit = isedit == null ? null : isedit.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}