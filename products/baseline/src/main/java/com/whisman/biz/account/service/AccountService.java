package com.whisman.biz.account.service;

import com.whisman.biz.account.entity.DemoPerson;
import com.whisman.biz.account.entity.User;
import com.whisman.biz.account.repository.jpa.PersonDao;
import com.whisman.biz.account.repository.jpa.UserDao;
import com.whisman.biz.account.repository.mybatis.DemoPersonMybatisDao;
import com.whisman.biz.account.repository.mybatis.UserMybatisDao;
import com.whisman.biz.account.service.ShiroDbRealm.ShiroUser;
import com.whisman.biz.base.service.ServiceException;
import com.whisman.biz.task.repository.jpa.TaskDao;
import com.whisman.modules.page.MybatisPage;
import com.whisman.modules.security.utils.Digests;
import com.whisman.modules.utils.DateProvider;
import com.whisman.modules.utils.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Tim
 */
@Component
@Transactional
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	private UserDao userDao;
	private TaskDao taskDao;
    private PersonDao personDao;
    private UserMybatisDao userMybatisDao;
    private DemoPersonMybatisDao demoPersonMybatisDao;

	private DateProvider dateProvider = DateProvider.DEFAULT;

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public void registerUser(User user) {
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(dateProvider.getDate());

        personDao.save(user.getPerson()) ;
        userDao.save(user);
	}

	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
        personDao.save(user.getPerson()) ;
		userDao.save(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);
		taskDao.deleteByUserId(id);

	}

    public Page<User>  searchUserPage(Pageable pageable){
        MybatisPage<User> page = new MybatisPage<User>(pageable);
        List<User> users = userMybatisDao.searchPage(page);
        page.setContent(users);
        return page;
    }

    public Page<DemoPerson>  searchDemoPersonPage(Pageable pageable){
        MybatisPage<DemoPerson> page = new MybatisPage<DemoPerson>(pageable);
        List<DemoPerson> persons = demoPersonMybatisDao.searchPage(page);
        page.setContent(persons);
        return page;
    }

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroDbRealm.ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public void setUserMybatisDao(UserMybatisDao userMybatisDao) {
        this.userMybatisDao = userMybatisDao;
    }

    @Autowired
    public void setDemoPersonMybatisDao(DemoPersonMybatisDao demoPersonMybatisDao) {
        this.demoPersonMybatisDao = demoPersonMybatisDao;
    }

    public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}
}
