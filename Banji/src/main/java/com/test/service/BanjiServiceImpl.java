package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.test.dao.BanjiDao;
import com.test.entity.Banji;


import org.springframework.stereotype.Service;

@Service
public class BanjiServiceImpl implements BanjiService {
    
	@Autowired
	private  BanjiDao banjiDao;
	
	
	@Override
	public void save(Banji banji) {
		// TODO Auto-generated method stub
		banjiDao.save(banji);
		
		
	}

	@Override
	public List<Banji> findAll() {
		// TODO Auto-generated method stub
		return banjiDao.findAll();
	}

	@Override
	public Page<Banji> ListPage(Banji search, int num, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		banjiDao.deleteById(id);
		/*
		 * Example example = new Example(Banji.class);
		 * example.createCriteria().andLike("name", "%"+Name+"%");
		 */

	}

	@Override
	public List<Banji> findByName(String name) {
	return	 banjiDao.findAll(new Specification<Banji>() {
			@Override
			public Predicate toPredicate(Root<Banji> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				 List<Predicate> list = new ArrayList<Predicate>();
				 if(StringUtils.isNotBlank(name)||name==""){
					list.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
					}if (StringUtils.isEmpty(name)) {
					list.add(criteriaBuilder.notLike(root.get("name"), "%"+name+"%"));
					}
				 return criteriaBuilder.or(list.toArray(new Predicate[list.size()]));
			}
			
		});
		
	}

	@Override
	public Banji findByIdBanji(Long id) {
		// TODO Auto-generated method stub
		 Optional<Banji>  apOptional=	banjiDao.findById(id);
		 return apOptional.get();
	}
	
	@Override
	public void update(Banji banji) {
		// TODO Auto-generated method stub
		banjiDao.save(banji);
	}

	
}
