package com.example.noussa.services.interfaces;

import com.example.noussa.models.PerformanceEmployee;

import java.util.List;

public interface ISerivcePerformance {
    public List<PerformanceEmployee> getAllPerformances();
    public PerformanceEmployee getPerformanceById(Long id);
    public PerformanceEmployee savePerformance(PerformanceEmployee performance,Long id);
    public void deletePerformance(Long id);
}
