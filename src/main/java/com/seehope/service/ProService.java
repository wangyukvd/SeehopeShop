package com.seehope.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seehope.entity.Comment;
import com.seehope.entity.Pro;
import com.seehope.mapper.CommentMapper;
import com.seehope.mapper.ProMapper;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProService {

    SqlSession sqlSession= MyBatisUtil.getSession();
    ProMapper proMapper=sqlSession.getMapper(ProMapper.class);

    //多条件查询
    public PageInfo<Pro>findPros(int pageno, int pageSize, Map<String,String> map){
        PageHelper.startPage(pageno,pageSize);
        List<Pro> proList=proMapper.findPros(map);
        PageInfo<Pro>pageInfo=new PageInfo(proList);
        return pageInfo;
    }

    // 查询某类别的品牌
    public List findPname(int cid){
        return proMapper.findPname(cid);
    }

     //模糊查询某个关键字的商品信息，封装成List集合,只封装10条，名字太长的只截取20个字符:
    //模糊查询某关键字的商品名称，并截取前20个字符
    public List findpSn(String pSn){
        List list1=proMapper.findpSn(pSn);
        List list2=new ArrayList();

        int num=0;
        for(Object obj:list1){
            String str=obj.toString();
            if(str.length()>20){
                str = str.substring(0,20);
            }
            list2.add(str);
            num++;
            if(num==10){
                break;
            }
        }
        return list2;
    }



    public Pro findId(int proid){
        return proMapper.findId(proid);
    }

}
