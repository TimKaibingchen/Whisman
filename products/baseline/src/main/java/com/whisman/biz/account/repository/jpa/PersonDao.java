package com.whisman.biz.account.repository.jpa;

import com.whisman.biz.account.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonDao extends PagingAndSortingRepository<Person, Long> {
}
