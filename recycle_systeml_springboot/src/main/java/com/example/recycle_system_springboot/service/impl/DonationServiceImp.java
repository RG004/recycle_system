package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.dao.DonationDao;
import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.pojo.vo.DonationVo;
import com.example.recycle_system_springboot.service.DonationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DonationServiceImp implements DonationService {
    @Resource
    DonationDao donationDao;
    @Resource
    CollectorDao collectorDao;
    @Override
    public boolean placeAnDonation(Donation order) {
        boolean result=false;
        int i=donationDao.insert(order);
        if(i>0){
            result=true;
        }
        return result;
    }

    @Override
    public PageInfo<DonationVo> selectAllbyuser(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllByuser(userid,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectDoingbyuser(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingByuser(userid,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectAllbycollector(int collectorid, String username, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllBycollector(collectorid,username,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllBycollector(collectorid,username,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectDoingbycollector(int collectorid, String username, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingBycollector(collectorid,username,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingBycollector(collectorid,username,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectAllbyadmin(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectDoingbyadmin(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public boolean placecollector(int donateId, String collectorName) {
        int collectorid=collectorDao.selectBycollectorname(collectorName);
        donationDao.updatecollector(donateId,collectorid);
        return true;
    }
}
