package com.mryoung.service;

import java.util.List;

/**
 * Created by mr_young on 2017/4/18.
 */
public interface BaseService<T> {
    public void save(T t);

    public void update(T t);

    public void delete(int id);

    public T getById(int id);

    public List<T> querry();
}
