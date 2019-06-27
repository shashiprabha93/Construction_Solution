/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.Po;
import application.entity.PoDetail;
import constructionsolution.NumbeFormater;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Shashiprabha
 */
public class PoDetailController extends DbCommon{
    
    public boolean savePoDetailDirectAccept(Po p) {
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<PoDetail> list = null;
        try {
            session.beginTransaction();
            session.save(p);
            list = p.getPoDetails();

            Iterator<PoDetail> itr = list.iterator();
            while (itr.hasNext()) {
                PoDetail detail = itr.next();
                Query query = session.createSQLQuery("INSERT INTO po_detail(`CREATED_AT`,`DELETED_AT`,`PO_DETAIL_ID`,`STATUS`,`UPDATED_AT`,`PO_ID`,`SUPPLIER_ITEM_ID`) VALUES(:CREATED_AT,:DELETED_AT,:PO_DETAIL_ID,:STATUS,:UPDATED_AT,:PO_ID,:SUPPLIER_ITEM_ID)");
                //query.setParameter("PRICE", detail.getBuyingPrice());
                query.setParameter("CREATED_AT", detail.getCreatedAt());
                //query.setParameter("CREATED_DATE", detail.getCreatedDate());
                query.setParameter("DELETED_AT", detail.getDeletedAt());
                query.setParameter("PO_DETAIL_ID", detail.getPoDetailId());
                //query.setParameter("QTY", detail.getQty());
                query.setParameter("STATUS", 1);
                query.setParameter("UPDATED_AT", detail.getUpdatedAt());
                query.setParameter("CREATED_DATE", detail.getCreatedDate());
                //query.setParameter("version", detail.getVersion());
                query.setParameter("PO_ID", p.getPoId());
                query.setParameter("PRODUCT_ID", detail.getProduct().getSupplierItemId());
                query.executeUpdate();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            //log.error(ex);
            return false;
        } finally {
            if (list != null) {
                list.clear();
            }
            session.close();
        }
        return true;
    }

    
    public void getDirectPoDetailAndVatByPoId(DefaultTableModel dtm, long poId) {
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<PoDetail> list = null;
        try {
            Query query = session.createQuery("SELECT pd\n"
                    + "FROM PoDetail pd\n"
                    + "WHERE pd.podetailsPoRef= :Id  ORDER BY pd.createdAt").setLong("Id", poId);
            list = query.list();
            if (!list.isEmpty()) {
                Iterator<PoDetail> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    PoDetail poDetial = itr.next();
                    dtm.addRow(new Object[]{poDetial.getProduct().getSupplierItemId(), poDetial.getProduct(), (poDetial.getProduct().getSupplierItemDescription()), poDetial.getProduct().getSupplierItemManufacturer(), poDetial.getProduct().getSupplierItemUOM(), poDetial.getQty()});

                }
            }

            System.out.println("this is list" + list);
        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
        } finally {
            if (list != null) {
                list.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
    }

    
    public void getAllPoDetail(DefaultTableModel dtm) {
       // PoType poType = poTypeController.getPoTypeById(1);
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<PoDetail> list = null;
        try {
            Query query = session.createQuery("FROM PoDetail ");
            //query.setParameter("Type", "DIMO");
            //query.setParameter("avilable", true);
            // query.setParameter("approveStatus", "1");
            //query.setParameter("ReferenceId", -1);
            //query.setParameter("poType", poType);

            System.out.println(query.getQueryString());
            String sync;
            list = query.list();
            dtm.setRowCount(0);
            String poNo;
            if (!list.isEmpty()) {
                Iterator<PoDetail> itr = list.iterator();
                while (itr.hasNext()) {
                    PoDetail po = itr.next();
//                    if (po.getSyncStatus() == 1) {
//                        sync = "YES";
//
//                    } else {
//                        sync = "NO";
//
//                    }
//
//                    if (po.getOrderId()== null) {
//
//                        poNo = po.getPoNo();
//
//                    } else {
//
//                        poNo = po.getOrderId();
//
//                    }

                    dtm.addRow(new Object[]{po.getPoId(), po.getProduct().getSupplierItemDescription(),po.getProduct().getSupplierItemManufacturer(),po.getProduct().getSupplierItemUOM(),po.getProduct().getSupplierRef().getSupplierName(),NumbeFormater.setDateFormat(po.getCreatedAt()), NumbeFormater.setDateFormat(po.getUpdatedAt())});

                }
            }
        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
        } finally {
            if (list != null) {
                list.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
    }

    
}
