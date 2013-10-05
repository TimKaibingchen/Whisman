package com.whisman.biz.account.repository.mybatis;

import com.whisman.biz.account.entity.DemoPerson;
import com.whisman.biz.base.repository.MyBatisRepository;
import org.springframework.data.domain.Page;

import java.util.List;

@MyBatisRepository
public interface DemoPersonMybatisDao {

    List<DemoPerson> searchPage(Page<DemoPerson> page);
}