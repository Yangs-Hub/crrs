package com.mryoung.action;

import com.mryoung.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * Created by mr_young on 2017/4/18.
 */
public class BaseAction<T> extends ActionSupport implements RequestAware,
        SessionAware, ApplicationAware, ModelDriven<T> {

    // Service
    protected UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 域对象
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    // ModelDriven
    protected T model;

    @Override
    public T getModel() { // 这里通过解析传进来的T来new一个对应的instance
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class clazz = (Class) type.getActualTypeArguments()[0];
        try {
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }

}
