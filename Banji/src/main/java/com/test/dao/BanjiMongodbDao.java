package com.test.dao;


import com.test.entity.Banji;

public interface BanjiMongodbDao {
	
	 void saveBanji(Banji banji);
	 
	 Banji findBanjiByBanjiName(String name);
	 
	 void updateBanji(Banji banji);
	 
	 void deleteBanjiById(Long id);
	 
	 

}
