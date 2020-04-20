package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.test.entity.Banji;


public interface BanjiDao   extends JpaRepository<Banji,Long>,JpaSpecificationExecutor<Banji> {

}
