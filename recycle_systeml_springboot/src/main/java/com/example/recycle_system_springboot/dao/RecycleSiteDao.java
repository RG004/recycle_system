package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleSite;
import org.apache.ibatis.annotations.Param;

public interface RecycleSiteDao {
    int deleteByPrimaryKey(Integer siteId);

    int insert(RecycleSite record);

    int insertSelective(RecycleSite record);

    RecycleSite selectByPrimaryKey(Integer siteId);

    int updateByPrimaryKeySelective(RecycleSite record);

    int updateByPrimaryKey(RecycleSite record);

}
