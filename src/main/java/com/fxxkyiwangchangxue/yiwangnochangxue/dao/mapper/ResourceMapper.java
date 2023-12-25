package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    void insertResource(Resource resource);
    List<Resource> getResource();
    Resource selectResourceFile(@Param("resourceId") int resourceId);
}
