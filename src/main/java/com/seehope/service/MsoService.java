package com.seehope.service;

import com.seehope.entity.Mso;
import com.seehope.entity.Msoxq;
import com.seehope.mapper.MsoMapper;
import com.seehope.mapper.ProMapper;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MsoService {
    SqlSession sqlSession= MyBatisUtil.getSession();
    MsoMapper msoMapper=sqlSession.getMapper(MsoMapper.class);
    ProMapper proMapper=sqlSession.getMapper(ProMapper.class);

    //提交订单
    public void submitMso(Mso mso){
        //存储订单
        msoMapper.addMso(mso);
        //存储订单明细  修改库存与销量
        for (Msoxq xq : mso.getMsoxqs()){
            msoMapper.addMsoxq(xq);
            proMapper.updateProNum(xq);
            sqlSession.commit();
        }
    }
    public int updateMsoPayState(String msoid){
        int num =msoMapper.updateMsoPayState(msoid);
        sqlSession.commit();
        return num;
    }

    // 根据用户名查询所有定单(不含订单明细)
    public List<Mso>findMsosByUserId(int userid){
        return msoMapper.findMsosByUserId(userid);
    }

    //查询指定编号的订单信息（含订单明细）
    public  Mso findMsoByMsoid(String msoid){
        System.out.println(msoMapper.findMsoByMsoid(msoid));
        return msoMapper.findMsoByMsoid(msoid);
    }

    //删除订单
    public void delMso(String msoid){

        //先删除明细
        msoMapper.delMsoxq(msoid);
        //再删除订单
        msoMapper.delMso(msoid);
        sqlSession.commit();
    }

}
