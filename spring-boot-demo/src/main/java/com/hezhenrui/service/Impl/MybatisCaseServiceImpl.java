package com.hezhenrui.service.Impl;

import com.hezhenrui.domain.Person;
import com.hezhenrui.mapper.PersonMapper;
import com.hezhenrui.service.MybatisCaseService;
import org.springframework.stereotype.Service;

/**
 * @author hzr
 * @date 2021-12-24
 */
@Service
public class MybatisCaseServiceImpl implements MybatisCaseService {

    private final PersonMapper personMapper;

    public MybatisCaseServiceImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public void mybatisFlushCase() {
        Person person =personMapper.selectByPrimaryKey(4);
        System.out.println(1);
        personMapper.deleteByPrimaryKey(4);
        personMapper.insert(person);
    }
}
