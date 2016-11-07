package cn.sqhl.neig.pointsmanager.vo;

import java.util.Date;

public class Comment {
    private Long id;

    private Long goodid;

    private String comment;

    private Date createTime;

    private Integer score;

    private Long userid;
    
    private Integer avgscore;

	private String ishidden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodid() {
        return goodid;
    }

    public void setGoodid(Long goodid) {
        this.goodid = goodid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getIshidden() {
        return ishidden;
    }

    public void setIshidden(String ishidden) {
        this.ishidden = ishidden;
    }

	public Integer getAvgscore() {
		return avgscore;
	}

	public void setAvgscore(Integer avgscore) {
		this.avgscore = avgscore;
	}
}