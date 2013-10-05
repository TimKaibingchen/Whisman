package com.whisman.web.account;

import com.whisman.biz.account.entity.DemoPerson;
import com.whisman.biz.account.entity.User;
import com.whisman.biz.account.service.AccountService;
import com.whisman.modules.page.DataTable;
import com.whisman.modules.page.DataTablePage;
import com.whisman.modules.page.DataTablePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin/person")
public class PersonController {

	@Autowired
	private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "account/personList";
	}

    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public DataTable<DemoPerson> searchPage(DataTablePageable pageable) {
        Page<DemoPerson> userPage = accountService.searchDemoPersonPage(pageable);
        DataTablePage<DemoPerson> page = new DataTablePage<DemoPerson>(userPage);
        page.setsEcho(pageable.getsEcho());
        return page;
    }
}
