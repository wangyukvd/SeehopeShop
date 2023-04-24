package com.seehope.entity;

public class Msoxq {
    private int xqid;   //订单明细顶id
    private String msoid;//订单编号
    private int count;//订单项内商品购买数量
    private Mso mso;//所属订单,对于数据库的msoid列
    private Pro pro;//订购的商品，对应数据库proid列
    private int proid;//订购的商品编号

    @Override
    public String toString() {
        return "Msoxq{" +
                "xqid=" + xqid +
                ", msoid='" + msoid + '\'' +
                ", count=" + count +
                ", mso=" + mso +
                ", pro=" + pro +
                ", proid=" + proid +
                '}';
    }

    public Msoxq() {
    }

    public Msoxq(int xqid, String msoid, int count, Mso mso, Pro pro, int proid) {
        this.xqid = xqid;
        this.msoid = msoid;
        this.count = count;
        this.mso = mso;
        this.pro = pro;
        this.proid = proid;
    }

    public int getXqid() {
        return xqid;
    }

    public void setXqid(int xqid) {
        this.xqid = xqid;
    }

    public String getMsoid() {
        return msoid;
    }

    public void setMsoid(String msoid) {
        this.msoid = msoid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Mso getMso() {
        return mso;
    }

    public void setMso(Mso mso) {
        this.mso = mso;
    }

    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }
}
