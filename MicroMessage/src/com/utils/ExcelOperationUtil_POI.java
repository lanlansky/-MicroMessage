package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExcelOperationUtil_POI {
	private List title=new ArrayList();
	private List field=new ArrayList();

	boolean readInfoToExcel(String titleField, String infos, String beginDate, String endDate) {

		titleField = "[{'field':'remark','title':'Ͷ�ݷ־�'},{'field':'address','title':'Ͷ�ݾ�'},{'field':'szyh','title':'szyh'},{'field':'10000','title':'10000'},{'field':'95598','title':'95598'},{'field':'hyc','title':'hyc'},{'field':'95588','title':'95588'},{'field':'95591','title':'95591'},{'field':'total','title':'�ܼ�'}]";

		infos = "{'total':25,'rows':[{'total':702,'szyh':0,'8816':0,'10000':126,'95598':0,"
				+ "'remark':'��Ͷ��','address':'������·֧��','hyc':0,'95588':23,'95591':553},"
				+ "{'total':13,'szyh':0,'8816':0,'10000':13,'95598':0,'remark':'���з־�',"
				+ "'address':'����֧��','hyc':0,'95588':0,'95591':0},{'total':37,'szyh':0,"
				+ "'8816':0,'10000':26,'95598':0,'remark':'԰���־�','address':'б��֧��',"
				+ "'hyc':0,'95588':6,'95591':5},{'total':601,'szyh':0,'8816':0,'10000':11,"
				+ "'95598':588,'remark':'�����־�','address':'䰹�֧��','hyc':0,'95588':2,"
				+ "'95591':0},{'total':54,'szyh':0,'8816':0,'10000':8,'95598':0,'remark':'��Ƿ־�',"
				+ "'address':'��ܤ֧��','hyc':0,'95588':2,'95591':44},{'total':8,'szyh':0,'8816':2,"
				+ "'10000':0,'95598':0,'remark':'','address':'','hyc':6,'95588':0,'95591':0},"
				+ "{'total':15,'szyh':0,'8816':0,'10000':0,'95598':0,'remark':'���з־�','address':"
				+ "'ԽϪ֧��','hyc':0,'95588':15,'95591':0},{'total':102,'szyh':0,'8816':0,'10000':0,"
				+ "'95598':0,'remark':'���з־�','address':'���֧��','hyc':0,'95588':0,'95591':102},"
				+ "{'total':20,'szyh':0,'8816':0,'10000':0,'95598':0,'remark':'�����־�','address':'���֧��',"
				+ "'hyc':0,'95588':0,'95591':20},{'total':6,'szyh':0,'8816':0,'10000':0,'95598':0,"
				+ "'remark':'�����˺�','address':'������','hyc':0,'95588':0,'95591':6},{'total':5603,"
				+ "'szyh':0,'8816':0,'10000':0,'95598':5603,'remark':'��Ͷ��  ','address':'����֧��',"
				+ "'hyc':0,'95588':0,'95591':0},{'total':1474,'szyh':0,'8816':0,'10000':0,'95598':1474,"
				+ "'remark':'���з־�','address':'��ֱ֧��','hyc':0,'95588':0,'95591':0},{'total':435,"
				+ "'szyh':0,'8816':0,'10000':0,'95598':435,'remark':'԰���־�','address':'ʤ��֧��',"
				+ "'hyc':0,'95588':0,'95591':0},{'total':4,'szyh':0,'8816':0,'10000':0,'95598':4,"
				+ "'remark':'�����˺�','address':'����Դ��','hyc':0,'95588':0,'95591':0},{'total':161,"
				+ "'szyh':0,'8816':0,'10000':0,'95598':161,'remark':'��Ƿ־�','address':'����֧��',"
				+ "'hyc':0,'95588':0,'95591':0},{'total':4,'szyh':4,'8816':0,'10000':0,'95598':0,"
				+ "'remark':'','address':'','hyc':0,'95588':0,'95591':0}]}";

		JSONArray array = new JSONArray(titleField);
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			title.add(object.getString("title"));
			if(i>1){
				field.add(object.getString("field"));
			}
			System.out.println("�õ���������:" + object.getString("title"));
		}
		// ����Excel������
		HSSFWorkbook workBook = new HSSFWorkbook();
		// ����һ��������sheet
		HSSFSheet sheet = workBook.createSheet();		
		//����һ����ʽ
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);// ˮƽ����
		cellStyle.setVerticalAlignment(cellStyle.VERTICAL_CENTER);// ��ֱ����		
		 //���ñ߿���ʽ
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);		
	     //���ñ߿���ɫ
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		
		
		// ������һ��,���������
		HSSFRow row = sheet.createRow(0);
		HSSFRow secondRow = sheet.createRow(1);
        //��Ԫ��ϲ�,�ĸ������ֱ��ǣ���ʼ�У������У���ʼ��,������
		sheet.addMergedRegion(new  CellRangeAddress(0,(short)1,0,(short)(title.size()-1)));
		HSSFCell titleCell = row.createCell(0);
		HSSFCell titleSecondCell =secondRow.createCell(0);
		titleCell.setCellStyle(cellStyle);
		titleCell.setCellValue("ʵʱ��֤Ͷ��ͳ�Ʊ� ");
		for(int i=1;i<title.size();i++){
			HSSFCell emptyCell = row.createCell(i);
			emptyCell.setCellStyle(cellStyle);
			HSSFCell emptySecondCell =secondRow.createCell(i);
			emptySecondCell.setCellStyle(cellStyle);
		}
		
		
		
		//�����ڶ���,���������
		HSSFRow timeRow = sheet.createRow(2);
		sheet.addMergedRegion(new  CellRangeAddress(2,(short)2,0,(short)(title.size()-1)));
		HSSFCell timeCell = timeRow.createCell(0);
		timeCell.setCellValue("����:"+beginDate+"~"+endDate);
		timeCell.setCellStyle(cellStyle);
		for(int i=1;i<title.size();i++){
			HSSFCell emptyCell = timeRow.createCell(i);
			emptyCell.setCellStyle(cellStyle);
		}
		
		//���������У���ͷ��
		HSSFRow rowTitle=sheet.createRow(3);
		HSSFCell cell=null;
		//�����ͷ����
		for(int i=0;i<title.size();i++){
			cell=rowTitle.createCell(i);
			cell.setCellValue(title.get(i).toString());
			cell.setCellStyle(cellStyle);
		}
		//������������
		//׷����������
		JSONObject infoObject=new JSONObject(infos);
		int totalNum=infoObject.getInt("total");
		JSONArray infoArray=new JSONArray(infoObject.getJSONArray("rows").toString());
		for(int i=0;i<infoArray.length();i++){
			JSONObject rowObject=infoArray.getJSONObject(i);
			HSSFRow infoRow=sheet.createRow(i+4);
			HSSFCell cell0= infoRow.createCell(0);
			cell0.setCellValue(rowObject.getString("remark"));	
			HSSFCell cell1 = infoRow.createCell(1);
			cell1.setCellValue(rowObject.getString("address"));	
			cell0.setCellStyle(cellStyle);
			cell1.setCellStyle(cellStyle);
			for(int j=0;j<field.size();j++){
				HSSFCell cell3= infoRow.createCell(j+2);
				cell3.setCellValue(rowObject.getInt(field.get(j).toString()));	
				cell3.setCellStyle(cellStyle);
			}
		}	
		
		
		
		 //����洢·��    ����+��ǰ���ڣ��Դﵽ���ֵ�Ŀ��
        String path_title="D:\\ʵʱ��֤Ͷ��ͳ�Ʊ�";
      //��õ�ǰʱ�䣬����ָ����ʽת��Ϊ�ַ���
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
        String time = dateFormat.format(date);
        String path_mid=path_title.concat(time);
        String path=path_mid.concat(".xls");//���յ��ļ���
	
		File xlsFile=new File(path);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(xlsFile);
			workBook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		return true;

	}

	public static void main(String[] args) {
		new ExcelOperationUtil_POI().readInfoToExcel(null, null,"2015-07-08","2016-07-29");
	}

}
