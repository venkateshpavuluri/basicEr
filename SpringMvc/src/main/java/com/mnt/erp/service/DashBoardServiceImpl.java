/**
 * 
 */
package com.mnt.erp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import com.mnt.erp.bean.GetSalesOrderByYear;
import com.mnt.erp.bean.GoodsDeliveryInTheYear;
import com.mnt.erp.bean.InvoiceToCollect;
import com.mnt.erp.bean.InvoiceToPay;
import com.mnt.erp.bean.MatStockBean;
import com.mnt.erp.bean.Material;
import com.mnt.erp.bean.PendingPo;
import com.mnt.erp.bean.StorageLocation;
import com.mnt.erp.bean.ToBeReoredeMaterial;
import com.mnt.erp.bean.Top5OrderItems;
import com.mnt.erp.dao.DashBoardDao;

/**
 * @author venkateshp
 *
 */
public  class DashBoardServiceImpl implements DashBoardService{
	private static Logger logger=Logger.getLogger(DashBoardServiceImpl.class);
List<Object[]> listOfObjcts=null;
private DashBoardDao boardDao;

	public DashBoardDao getBoardDao() {
	return boardDao;
}

public void setBoardDao(DashBoardDao boardDao) {
	this.boardDao = boardDao;
}

	@Override
	public List<Object[]> getTotalStocks(int storageLocId) {
		// TODO Auto-generated method stub
		try
		{
			listOfObjcts=boardDao.getTotalStocks(storageLocId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listOfObjcts;
	}

	@Override
	public List<PendingPo> getPendingPoDetails(String count) {
		// TODO Auto-generated method stub
		List<PendingPo> pendingPos=null;
		try
		{
			pendingPos=boardDao.getPendingPoDetails(count);

		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return pendingPos;
	}

	@Override
	public List<GoodsDeliveryInTheYear> getGoodsinyear(String msg) {
		// TODO Auto-generated method stub
		List<GoodsDeliveryInTheYear> deliveryInTheYears=null; 
		try
		{
			deliveryInTheYears=boardDao.getGoodsinyear(msg);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return deliveryInTheYears;
	}

	@Override
	public List<ToBeReoredeMaterial> getTobeReorederLevels(String msg) {
		// TODO Auto-generated method stub
		List<ToBeReoredeMaterial>  reorederLevels=null;
		try
		{
		
			reorederLevels=boardDao.getTobeReorederLevels(msg);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		return reorederLevels;
	}

	@Override
	public List<InvoiceToCollect> invoiceToCollect(String count) {
		// TODO Auto-generated method stub
		List<InvoiceToCollect>  collects=null;
		try
		{
			collects=boardDao.invoiceToCollect(count);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return collects;
	}

	@Override
	public List<InvoiceToPay> getInvoiceToPays(String count) {
		// TODO Auto-generated method stub
		List<InvoiceToPay> invoiceToPays=null;
		try
		{
			invoiceToPays=boardDao.getInvoiceToPays(count);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return invoiceToPays;
	}

	@Override
	public List<MatStockBean> getMatStockDetails() {
		// TODO Auto-generated method stub
		List<MatStockBean> matStockBeans=null;
		try
		{
			listOfObjcts=boardDao.getMatStockDetails();
			matStockBeans=new ArrayList<MatStockBean>();
			Iterator<Object[]> iterator=listOfObjcts.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				MatStockBean stockBean=new MatStockBean();
				stockBean.setMatStockId((Integer)objects[0]);
				stockBean.setBatchNo((String)objects[1]);
				stockBean.setQtyAval((Float)objects[2]);
				StorageLocation location=(StorageLocation)objects[3];
				Material material=(Material)objects[4];
				stockBean.setMaterialName(material.getMaterialName());
				stockBean.setStorageLocName(location.getStorageLocation());
				matStockBeans.add(stockBean);
		}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
				
		}
		return matStockBeans;
	}

	@Override
	public List<Top5OrderItems> getTop5orderItems() {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		List<Top5OrderItems> top5OrderItems=null;		
		try
		{
			list=boardDao.getTop5orderItems();
			top5OrderItems=new ArrayList<Top5OrderItems>();
			Iterator<Object[]> iterator=list.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				Top5OrderItems orderItems=new Top5OrderItems();
				orderItems.setMaterial((String)objects[0]);
				orderItems.setQuantity(Float.valueOf((String)objects[1]));
				top5OrderItems.add(orderItems);
				
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return top5OrderItems;
	}

	@Override
	public List<GetSalesOrderByYear> getSalesOrdersByYear(int year) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		List<GetSalesOrderByYear> byYears=null;
		try
		{
			list=boardDao.getSalesOrdersByYear(year);
			byYears=new ArrayList<GetSalesOrderByYear>();
			Iterator<Object[]> iterator=list.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				GetSalesOrderByYear salesOrderByYear=new GetSalesOrderByYear();
				salesOrderByYear.setMaterial((String)objects[0]);
				salesOrderByYear.setMatpct(Float.valueOf((String)objects[1]));
				byYears.add(salesOrderByYear);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return byYears;
	}

	@Override
	public List<Top5OrderItems> allorderItems() {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		List<Top5OrderItems> top5OrderItems=null;		
		try
		{
			list=boardDao.allorderItems();
			top5OrderItems=new ArrayList<Top5OrderItems>();
			Iterator<Object[]> iterator=list.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				Top5OrderItems orderItems=new Top5OrderItems();
				orderItems.setMaterial((String)objects[0]);
				orderItems.setQuantity(Float.valueOf((String)objects[1]));
				top5OrderItems.add(orderItems);
				
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return top5OrderItems;
	}

}
