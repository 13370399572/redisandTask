package com.test.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.test.dao.BanjiMongodbDao;

@Component
public class BanjiDaoImpl implements BanjiMongodbDao{
    
	 @Autowired
	 private MongoTemplate mongoTemplate;
	/**
     * 创建对象
     * @param user
     */
	@Override
	public void saveBanji(Banji banji) {
		// TODO Auto-generated method stub
		mongoTemplate.save(banji);
		
	}
    
	/**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
	@Override
	public Banji findBanjiByBanjiName(String name) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("name").is(name));
	    Banji banji = mongoTemplate.findOne(query, Banji.class);
		return banji;
	}
    
	/**
     * 更新对象
     * @param user
     */
	@Override
	public void updateBanji(Banji banji) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(banji.getId()));
		Update update = new Update().set("name", banji.getName()).set("banji", banji.getBanji());
		//更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Banji.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
		
	}

	/**
     * 删除对象
     * @param id
     */
	@Override
	public void deleteBanjiById(Long id) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query , Banji.class);
		
	}
}
