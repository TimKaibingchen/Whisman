package com.whisman.biz.account.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.whisman.biz.account.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
