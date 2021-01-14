package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.EvaluationDao;
import com.example.recycle_system_springboot.pojo.entity.Evaluation;
import com.example.recycle_system_springboot.service.EvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    EvaluationDao evaluationDao;
    //根据主键查询出某一个评价记录的信息
    @Override
    public List<Evaluation> findEvaluation(int id) {
        Evaluation e=evaluationDao.selectByPrimaryKey(id);
        List<Evaluation> result=new ArrayList<>();
        result.add(e);
        return result;
    }
}
