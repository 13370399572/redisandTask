package com.ypc.solrdemo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ypc.solrdemo.dao.UserDao;
import com.ypc.solrdemo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("core");
	
	@Autowired
	private UserDao userMapper;
	
	@Autowired
	private SolrClient solrClient;
	
	/*
	 * public User queryById(int id) { // 先通过solr查询，查询不到查数据库 SolrQuery query = new
	 * SolrQuery(); query.setQuery("id:" + id); User user = null; try {
	 * QueryResponse response = solrClient.query(query); SolrDocumentList
	 * documentList = response.getResults(); if (!documentList.isEmpty()) { for
	 * (SolrDocument document:documentList) { user = new User(); user.setId(id);
	 * user.setAddress((String) document.get("address")); user.setMobile((String)
	 * document.get("mobile")); user.setUserName((String) document.get("userName"));
	 * user.setAge((Integer) document.get("age")); user.setDescription((String)
	 * document.get("description"));
	 * LOGGER.info(">>>> query user from solr success <<<<"); } } else { // 从数据库查询
	 * user = userMapper.queryById(id); if (user != null) {
	 * solrClient.addBean(user,1000); }
	 * LOGGER.info(">>>> query user from database <<<<"); }
	 * 
	 * } catch (SolrServerException e) { LOGGER.error(e.getMessage(),e); } catch
	 * (IOException e) { LOGGER.error(e.getMessage(),e); } return user; }
	 */
	
	public User queryById(int id) {
        User user = null;
        try {
            SolrDocument solrDocument = solrClient.getById(String.valueOf(id));
            Gson gson = new Gson();
            // 方法1
			/*
			 * String solrString = gson.toJson(solrDocument); user =
			 * gson.fromJson(solrString,User.class);
			 */
            // 方法2
            Map<String,Object> map = solrDocument.getFieldValueMap();
            user = gson.fromJson(map.toString(),User.class);
            
            if (null == user) {
                user = userMapper.queryById(id);
                solrClient.addBean(user,1000);
            }

        } catch (SolrServerException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return user;
    }
	
    public List<User> queryAll() {
        List<User> list = null;
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<User>>() {}.getType());
//              list = convertToModel(documentList);
                LOGGER.info(">>>> query user from solr success <<<<");
            } else {
                list = userMapper.queryAll();
                solrClient.addBeans(list);
                LOGGER.info(">>>> query user from database <<<<");
            }

        } catch (SolrServerException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return list;
    }
    
 // service
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> insertAndUpdate(User user) {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("success",false);
        // 返回结果表示受影响的数据条数，而不是id值
        int insert = userMapper.insertUser(user);
        if (insert != 1) {
            throw new RuntimeException(" >>>> insert user to database failed,the return value should be 1,but result is:" + insert + " <<<<");
        }
        // 插入或者更新solr数据
        try {
            UpdateResponse response = solrClient.addBean(user,1000);
            int staus = response.getStatus();
            if (staus != 0) {
                LOGGER.error(">>>> update solr document failed <<<<");
                solrClient.rollback();
                result.put("message","insert user to solr failed");
                return result;
            }
        } catch (SolrServerException e) {
            LOGGER.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        }

        result.put("message","insert user to solr success");
        result.put("success",true);
        return result;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteUserById(int id) {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("success",false);
        // 先删除数据库，再更新solr
        int delete = userMapper.deleteById(id);
        if (delete != 1) {
            throw new RuntimeException(">>>> delete user failed ,user id=" + id + " <<<<");
        }

        try {
            UpdateResponse response = solrClient.deleteById(String.valueOf(id),1000);
            int status = response.getStatus();
            if (status != 0) {
                LOGGER.error(">>>> delete user from solr failed ,user id=" + id + " <<<<");
                solrClient.rollback();
                result.put("message","delete user to solr failed");
                return result;
            }
        } catch (SolrServerException e) {
            LOGGER.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        }

        result.put("success",true);
        result.put("message","delete user success");
        return result;
    }
}
