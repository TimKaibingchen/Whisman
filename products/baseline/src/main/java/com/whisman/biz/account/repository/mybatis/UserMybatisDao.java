package com.whisman.biz.account.repository.mybatis;

import com.whisman.biz.account.entity.User;
import com.whisman.biz.base.repository.MyBatisRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepository
public interface UserMybatisDao {

	User get(Long id);

	List<User> search(Map<String, Object> parameters);

	void save(User user);

	void delete(Long id);

    List<User> searchPage(Page<User> page);
}
