package com.dao;

import java.util.List;

import org.jvnet.hk2.annotations.Service;

import com.db.HibernateTemplate;
import com.dto.Wiki;


@Service
public class WikiDAO {
	public List<Wiki> getAllData() {
		List<Wiki> depts=(List)HibernateTemplate.getObjectListByQuery("From Wiki");
		return depts;	
	}


}
