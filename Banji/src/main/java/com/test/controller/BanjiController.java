package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.entity.PageQueryBean;
import com.test.dao.BanjiMongodbDao;
import com.test.entity.Banji;
import com.test.service.BanjiService;

@RestController
@RequestMapping("/banji")
public class BanjiController {

	@Autowired
	private BanjiService banjiService;

	@Autowired
	private BanjiMongodbDao banjiMongodbDao;

	@PostMapping("/save")
	public String save(@RequestBody Banji banji) {
		banjiService.save(banji);
		return "添加成功";
	}

	@PostMapping("/update")
	public String update(@RequestBody Banji banji) {
		banjiService.update(banji);
		return "添加成功";
	}

	@GetMapping(value = "/myStudentList")
	public PageQueryBean PageQueryBean (HttpServletRequest request ,String name) {
		List<Banji> list = banjiService.findByName(name);   //直接把所有用户都查出来了
		PageQueryBean pageQueryBean = new PageQueryBean();     //自己创建的一个分页类，直接返回List集合会有问题。
		pageQueryBean.setItems(list);
		pageQueryBean.setTotalRows(list.size());
		return pageQueryBean;
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long  id) {
		banjiService.delete(id);
		return "删除成功";
	}

	@PostMapping("/select/{id}")
	public Banji select(@PathVariable Long  id) {
		return	banjiService.findByIdBanji(id);
	}
    
	@PostMapping("/svaetest")
	public void saveBanji() {
		Banji banji=new Banji();
		banji.setId(2l);
		banji.setName("小明");
		banji.setJiazhang("fffooo123");
		banjiMongodbDao.saveBanji(banji);
	}
}
