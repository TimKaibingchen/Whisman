package com.whisman.biz.task.repository.mybatis;

import com.whisman.biz.base.repository.MyBatisRepository;
import com.whisman.biz.task.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

@MyBatisRepository
public interface TaskMybatisDao {

    List<Task> searchPage(Page<Task> page);

}