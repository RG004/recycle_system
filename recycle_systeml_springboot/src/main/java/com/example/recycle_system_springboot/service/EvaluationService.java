package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Evaluation;
import org.springframework.stereotype.Service;


public interface EvaluationService {
    Evaluation findEvaluation(int id);
}
