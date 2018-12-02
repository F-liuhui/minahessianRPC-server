package com.cnpc.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cnpc.test.entity.Person;
import com.cnpc.test.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public List<Person> getNames(String id) {
		List<Person> names=new ArrayList<Person>();
		if(id!=null) {
			names.add(new Person("张三","陕西",23,"dd"));
			names.add(new Person("李四","山西",24,"bb"));
			names.add(new Person("王五","被禁",25,"cc"));
			names.add(new Person("赵柳","上海",26,"dd"));
			names.add(new Person("天气","天津",27,"ee"));
		}
		return names;
	}

}
