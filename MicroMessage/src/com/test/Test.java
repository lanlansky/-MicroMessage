package com.test;

import java.util.List;

import com.bean.AllInfo;
import com.service.AllInfoService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		AllInfoService service=new AllInfoService();
		List<AllInfo> infos=service.queryAllInfoList();
		for(AllInfo info:infos){
			System.out.println("得到的数据是:"+info.getAddress()+"  "+info.getRemark()+"  "
					+info.getBankId()+"  "+info.getNum1()+" "+info.getNum2()+"  "+info.getNum3());
		}

	}

}
