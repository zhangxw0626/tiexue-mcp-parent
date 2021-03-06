package com.tiexue.mcp.core.entity;

import java.util.Date;

public class WxChapter {
	//章节Id
    private Integer id;
    //对应的小说Id
    private Integer bookid;
    //简介
    private String intro;
    //章节号
    private Integer sortorder;
    //章节名称
    private String title;
    //章节类型
    private Integer chaptertype;
    //章节价格
    private Integer pirce;
    //章节状态
    private Integer status;
    //章节字节		
    private Integer contentlen;
    //章节号
    private Integer showtype;
    //创建时间
    private Date createtime;
    //修改时间
    private Date updatetime;
    //备注
    private String remark;
    private String uniqueflag;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getChaptertype() {
        return chaptertype;
    }

    public void setChaptertype(Integer chaptertype) {
        this.chaptertype = chaptertype;
    }

    public Integer getPirce() {
        return pirce;
    }

    public void setPirce(Integer pirce) {
        this.pirce = pirce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContentlen() {
        return contentlen;
    }

    public void setContentlen(Integer contentlen) {
        this.contentlen = contentlen;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getUniqueflag() {
		return uniqueflag;
	}

	public void setUniqueflag(String uniqueflag) {
		this.uniqueflag = uniqueflag;
	}

	public Integer getShowtype() {
		return showtype;
	}

	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}
    
}