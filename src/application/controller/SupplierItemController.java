/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.SupplierItem;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DELL
 */
public class SupplierItemController extends DbCommon{
    
    public int isSupplierItemExists(SupplierItem supplierItem) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM SupplierItem i WHERE SUPPLIER_ITEM_ID != :supplierItemId AND SUPPLIER_ITEM_DESCRIPTION= :supplierItemDescription  AND STATUS=1"); //status=1 --> active Supplier
            query.setParameter("supplierItemId", supplierItem.getSupplierItemId() == null ? 0 : supplierItem.getSupplierItemId());
            query.setParameter("supplierItemDescription", supplierItem.getSupplierItemDescription());
            //query.setParameter("supplierType", supplier.getSupplerType());
            list = query.list();
            if (list.isEmpty()) {
                return 1;
            }
        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
            return -1;
        } finally {
            if (list != null) {
                list.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return 0;
    }
    

    public SupplierItem findSupplierItemById(int id) {
        SupplierItem supplierItem = null;
        List<SupplierItem> supplierItemList = null;
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        try {
            Query query = session.createQuery("SELECT p FROM SupplierItem p WHERE SUPPLIER_ITEM_ID=:supplierItemId").setParameter("supplierItemId", id);
            supplierItemList = query.list();
            if (!supplierItemList.isEmpty()) {
                Iterator<SupplierItem> itr = supplierItemList.iterator();
                while (itr.hasNext()) {
                    supplierItem = itr.next();
                }
                return supplierItem;
            }
        } catch (Exception ex) {
            // log.error(ex);
            ex.printStackTrace();
        } finally {
            if (supplierItemList != null) {
                supplierItemList.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return supplierItem;
    }
    public void getAllSupplierItems(DefaultTableModel dtm) {
        
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM SupplierItem WHERE status= 1 ORDER BY supplierItemDescription ");//status=1 --> active Supplier
            list = query.list();
            if (!list.isEmpty()) {
                Iterator< SupplierItem> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    SupplierItem supplierItem = (SupplierItem) itr.next();
                    System.out.println(supplierItem.getSupplierItemDescription());
                    dtm.addRow(new Object[]{supplierItem.getSupplierItemId(), supplierItem.getSupplierItemManufacturer(), supplierItem.getSupplierItemDescription(), supplierItem.getSupplierItemUOM(),supplierItem.getSupplierRef().getSupplierName()});
               //, supplierItem.getSupplierRef().getSupplierName()
                }
            }
        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
        } finally {
            if (list != null) {
                list.clear();
            }
            session.close();
        }
    }


    
    public void getAllProducts(DefaultTableModel dtm) {
        
//        List list = null;
//        Session session = null;
//        try {
//            session = super.getHibernateUtil().openWritebleDbConnection();
//            Query query = session.createQuery("FROM Product WHERE status= 1 ORDER BY productName ");//status=1 --> active Supplier
//            list = query.list();
//            if (!list.isEmpty()) {
//                Iterator< Product> itr = list.iterator();
//                dtm.setRowCount(0);
//                while (itr.hasNext()) {
//                    Product product = (Product) itr.next();
//                    System.out.println(product.getProductName());
//                    dtm.addRow(new Object[]{product.getProductId(), product.getProductName(), product.getProductType(), product.getProductPrice()});
//                }
//            }
//        } catch (Exception ex) {
//            //log.error(ex);
//            ex.printStackTrace();
//        } finally {
//            if (list != null) {
//                list.clear();
//            }
//            session.close();
//        }
  }
//    

}
