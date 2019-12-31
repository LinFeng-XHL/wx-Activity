package com.cnrmall.springboot.entity;

import java.util.Date;


public class User {
    /**
     * 主键ID
     * */
    private int id;
    /**
     * 用户姓名
     * */
    private String username;
    /**
     * 手机号
     * */
    private String phone;
    /**
     * 上传图片
     * */
    private String imageUrl;
    /**
     * 上传文件
     * */
    private String fileUrl;
    /**
     * 插入数据时间
     * */
    private Date insertDate;
    /**
     * 备注
     * */
    private String backup;

    /**
     * 微信昵称
     * */
    private String nickName;

    /**
     * 微信头像
     * */
    private String avatarUrl;

    /**
     * 性别
     * */
    private String gender;

    /**
     * 审核是否通过标识
     * */
    private String valid;

    /**
     * 上传文件类型
     * */
    private String urlType;

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    private Integer voteCount;

    public Integer getVoteCount() {
        return voteCount;
    }



    /**
     * 配置的一对一关系字段
     * */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public User() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public User(int id, String username, String phone, String imageUrl, String fileUrl, Date insertDate, String backup, String nickName, String avatarUrl, String gender, String valid, String urlType, Integer voteCount) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
        this.insertDate = insertDate;
        this.backup = backup;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.valid = valid;
        this.urlType = urlType;
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", insertDate=" + insertDate +
                ", backup='" + backup + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", valid='" + valid + '\'' +
                ", urlType='" + urlType + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
