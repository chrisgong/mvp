package com.shuzijiayuan.myapplication.data.bean.profile;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 账户信息关系列表的信息内容.
 */
public class ProfileInfo extends RealmObject{

    //当前登录用户的id
    public int accountId;

    //年龄
    public int age;

    //出生日期
    public String birthday;

    //头像
    public String face;

    //身份证号
    @PrimaryKey
    public String iid;

    //姓名
    public String name;

    //当前登录用户个人信息的id
    public int profileId;

    //性别
    public int sex;

    //身高
    public int stature;

    //体重
    public float weight;

    //开启健康计划时间
    public String time;

    //过敏药物
    public String allergicDrug;

    //个人病史
    public String diseaseRecordDis;

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "accountId=" + accountId +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", face='" + face + '\'' +
                ", iid='" + iid + '\'' +
                ", name='" + name + '\'' +
                ", profileId=" + profileId +
                ", sex=" + sex +
                ", stature=" + stature +
                ", weight=" + weight +
                ", time='" + time + '\'' +
                ", allergicDrug='" + allergicDrug + '\'' +
                ", diseaseRecordDis='" + diseaseRecordDis + '\'' +
                '}';
    }
}
