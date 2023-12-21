package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Commit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommitMapper {
    List<Commit> SelectAll();

    Commit SelectById(int taskId);

    Commit SelectFileById(int taskId);

    Commit SelectByTaskIdUId(@Param("taskId") int taskId,
                             @Param("uid") int uid);

    void InsertCommit(@Param("fori_userId") int userId,
                        @Param("fori_taskId") int taskId,
                        @Param("body") String body,
                        @Param("fileName") String fileName,
                        @Param("file") byte[] file);

    List<Commit> selectByStudyTaskId(@Param("studyTaskId") int studyTaskId);

    void updateScoreByCommitId(@Param("commitId") int commitId,
                               @Param("score") int score);
}
