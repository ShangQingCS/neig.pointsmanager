package cn.sqhl.neig.pointsmanager.po;

public class NsEventsinfo {
    private Long id;

    private String name;

    private Integer isuse;

    private String minpicture;

    private String picture;

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

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public String getMinpicture() {
        return minpicture;
    }

    public void setMinpicture(String minpicture) {
        this.minpicture = minpicture == null ? null : minpicture.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}