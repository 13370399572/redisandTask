package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.dao.BanjiMongodbDao;
import com.test.entity.Banji;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {
    
	@Autowired
	private  BanjiMongodbDao banjiMongodbDao;
	
	@Test
    public void testSaveUser() throws Exception {
        Banji banji=new Banji();
        banji.setId(2l);
        banji.setName("小明");
        banji.setJiazhang("fffooo123");
        banjiMongodbDao.saveBanji(banji);
    }

    @Test
    public void findUserByUserName(){
    	Banji banji = banjiMongodbDao.findBanjiByBanjiName("小明");
       System.out.println("banji is "+banji);
    }

    @Test
    public void updateUser(){
    	Banji banji=new Banji();
    	banji.setId(2l);
    	banji.setName("天空");
    	banji.setJiazhang("fffxxxx");
        banjiMongodbDao.updateBanji(banji);
    }

    @Test
    public void deleteUserById(){
    	banjiMongodbDao.deleteBanjiById(1l);
    }

	
}
