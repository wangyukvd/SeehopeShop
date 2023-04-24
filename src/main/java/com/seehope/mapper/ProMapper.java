package com.seehope.mapper;

import com.seehope.entity.Msoxq;
import com.seehope.entity.Pro;

import java.util.List;
import java.util.Map;

public interface ProMapper {
    public List<Pro> findPros(Map<String,String> map);  //根据条件动态查询商品

    public List findPname(int cid);     //查询某类别的品牌

    public List findpSn(String pSn);//模糊查询某关键字的商品名称

    public Pro findId(int proid);//根据商品编号查询商品信息

    //修改商品库数量(减少)，销量（增加）
    public void updateProNum(Msoxq msoxq);//订单订购了商品表明商品的库存的减少和销量的增加

}
