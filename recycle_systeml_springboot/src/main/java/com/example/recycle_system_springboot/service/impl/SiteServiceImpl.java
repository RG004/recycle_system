package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleSiteDao;
import com.example.recycle_system_springboot.pojo.entity.RecycleSite;
import com.example.recycle_system_springboot.service.SiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class SiteServiceImpl implements SiteService {
    @Resource
    RecycleSiteDao recycleSiteDao;
    @Resource
    RecycleSite recycleSite;
    @Override
    public boolean updatesite(RecycleSite site) {
        recycleSiteDao.updateByPrimaryKey(site);
        return true;
    }

    @Override
    public int siteid(String siteName, Double longitude, Double latitude) {
        recycleSite.setSiteName(siteName);
        recycleSite.setLatitude(latitude);
        recycleSite.setLongitude(longitude);
        recycleSiteDao.insert(recycleSite);
        return recycleSite.getSiteId();
    }

}
