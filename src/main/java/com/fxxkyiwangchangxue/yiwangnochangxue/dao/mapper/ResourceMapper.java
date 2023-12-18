package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    void insertResource(Resource resource);
    List<Resource> getResource();
}
