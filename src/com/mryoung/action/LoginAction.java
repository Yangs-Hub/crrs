package com.mryoung.action;

import com.mryoung.model.User;
import com.mryoung.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by mr_young on 2017/4/17.
 */
@Controller("login")
@Scope("prototype")
public class LoginAction extends BaseAction<User> {
    public String login(User user) {
        System.out.println(user.getUsername());
        return "index";
    }
}
