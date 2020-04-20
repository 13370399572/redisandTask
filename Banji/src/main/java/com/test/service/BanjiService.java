package com.test.service;

import java.util.List;

import com.test.entity.Banji;
import org.springframework.data.domain.Page;
public interface BanjiService {

	void save(Banji banji);
	
	List<Banji> findAll();
	
	Page<Banji> ListPage(Banji search, int num, int size);
	
    void delete (Long id);
    
    List<Banji> findByName(String name);
    
    Banji findByIdBanji(Long id);
    
    void update(Banji banji);

}
