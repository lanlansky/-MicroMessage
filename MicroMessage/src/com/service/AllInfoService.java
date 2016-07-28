package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.AllInfo;
import com.dao.impl.DBAccess;

public class AllInfoService {
	
	public List<AllInfo> queryAllInfoList(){
		
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		List<AllInfo> infos=new ArrayList<AllInfo>();
		try {
			sqlSession=dbAccess.getSqlSession();
			//Í¨¹ýsqlSessionÖ´ÐÐSQLÓï¾ä
			infos=sqlSession.selectList("AllInfo.queryAllInfoList");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
			
		}
		return infos;
		
	}

}
