package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentMapper {
    void insertComment(Comment comment) throws SQLException;
    List<Comment> selectAllByCount(Integer count) throws SQLException;
}
