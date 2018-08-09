package com.pan.support;
import org.springframework.beans.factory.annotation.Autowired;

import com.pan.mybatis.BcMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class BaseService<T> implements Service<T> {

    @Autowired
    protected BcMapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        mapper.insertSelective(model);
    }


    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }



    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            //throw new ServiceException(e.getMessage(), e);
        }
		return null;
    }
    
    public List<T> findAll() {
        return mapper.selectAll();
    }
}

