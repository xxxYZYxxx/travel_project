package com.yzy.pojo;

public class Passenger {
    private Integer pid;
    private String name;
    private Integer sex;
    private String birthday;
    private String phone;
    private String idCard;
    //当前报名时间
    private String signDate;
    private Integer tid;
    private Travel travel;


    public Passenger(String name, Integer sex, String birthday, String phone, String idCard, String signDate, Travel travel) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.idCard = idCard;
        this.signDate = signDate;
        this.travel = travel;
    }

    public Passenger(String name, Integer sex, String birthday, String phone, String idCard, Integer tid) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.idCard = idCard;
        this.tid = tid;
    }

    public Passenger(Integer pid, String name, Integer sex, String birthday, String phone, String idCard, String signDate, Integer tid) {
        this.pid = pid;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.idCard = idCard;
        this.signDate = signDate;
        this.tid = tid;
    }

    public Passenger() {
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", signDate='" + signDate + '\'' +
                ", tid=" + tid +
                ", travel=" + travel +
                '}';
    }
}
