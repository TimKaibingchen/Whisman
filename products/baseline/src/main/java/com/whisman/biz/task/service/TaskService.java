package com.whisman.biz.task.service;

import java.util.List;
import java.util.Map;

import com.whisman.biz.task.repository.mybatis.TaskMybatisDao;
import com.whisman.modules.page.MybatisPage;
import com.whisman.modules.persistence.DynamicSpecifications;
import com.whisman.modules.persistence.SearchFilter;
import com.whisman.biz.task.entity.Task;
import com.whisman.biz.task.repository.jpa.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.whisman.modules.persistence.SearchFilter.Operator;

//Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional
public class TaskService {

	private TaskDao taskDao;

    private TaskMybatisDao taskMybatisDao;

	public Task getTask(Long id) {
		return taskDao.findOne(id);
	}

	public void saveTask(Task entity) {
		taskDao.save(entity);
	}

	public void deleteTask(Long id) {
		taskDao.delete(id);
	}

	public List<Task> getAllTask() {
		return (List<Task>) taskDao.findAll();
	}

	public Page<Task> getUserTask(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Task> spec = buildSpecification(userId, searchParams);

		return taskDao.findAll(spec, pageRequest);
	}

    public  Page<Task> getUserTask(Pageable pageable){
        MybatisPage<Task> page = new MybatisPage<Task>(pageable);
        List<Task> taskList = taskMybatisDao.searchPage(page);
        page.setContent(taskList);
        return  page;
    }

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Task> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Task> spec = DynamicSpecifications.bySearchFilter(filters.values(), Task.class);
		return spec;
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

    @Autowired
    public void setTaskMybatisDao(TaskMybatisDao taskMybatisDao) {
        this.taskMybatisDao = taskMybatisDao;
    }
}
