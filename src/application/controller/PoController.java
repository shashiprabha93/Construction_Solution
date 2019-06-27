/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.Po;
import application.entity.PoDetail;
import constructionsolution.NumbeFormater;
import constructionsolution.NumberGenerator;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Shashiprabha
 */
public class PoController extends DbCommon{
    
    public boolean savePoDetais(Po po) {

        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<PoDetail> list = null;
        try {

            session.save(po);

            String genaretedRentalNo = new NumberGenerator().getGenaretedNumber("PO", po.getPoId());
            po.setPoNo(genaretedRentalNo);
            session.saveOrUpdate(po);

            list = po.getPoDetails();
            Iterator<PoDetail> itr = list.iterator();
            while (itr.hasNext()) {
                PoDetail detail = itr.next();
                detail.setPodetailsPoRef(po);
                session.saveOrUpdate(detail);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        } finally {
            if (list != null) {
                list.clear();
            }
            session.close();
        }
        return true;
    }

    public void getAllPo(DefaultTableModel dtm) {
       // PoType poType = poTypeController.getPoTypeById(1);
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<Po> list = null;
        try {
            Query query = session.createQuery("FROM Po ");
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
                Iterator<Po> itr = list.iterator();
                while (itr.hasNext()) {
                    Po po = itr.next();
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

                    dtm.addRow(new Object[]{po.getPoId(), po.getPoNo(), NumbeFormater.setDateFormat(po.getCreatedAt()), NumbeFormater.setDateFormat(po.getUpdatedAt())});

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

    public Po findPobyId(long poId) {
        Po po = null;
        List<Po> poList = null;
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        try {
            Query query = session.createQuery("SELECT p FROM Po p WHERE  p.poId= :id");
            //Query query = session.createSQLQuery("SELECT * FROM Po WHERE ID=:id")  WHERE  p.poId= :id");
            //query.setParameter("avilable", true);
            query.setParameter("id", poId);
            poList = query.list();
            if (!poList.isEmpty()) {
                Iterator<Po> itr = poList.iterator();
                while (itr.hasNext()) {
                    po = itr.next();
                }
                return po;
            }
        } catch (Exception ex) {
           // log.error(ex);
            ex.printStackTrace();
        } finally {
            if (!poList.isEmpty()) {
                poList.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return po;
    }

//    public void getPoDetailByPoId(DefaultTableModel dtm, long poId) {
//        Session session = super.getHibernateUtil().openWritebleDbConnection();
//        List<PoDetail> list = null;
//        try {
//            Query query = session.createQuery("SELECT pd\n"
//                    + "FROM PoDetail pd\n"
//                    + "WHERE pd.podetailsPoRef= :Id  ORDER BY pd.createdAt").setBoolean("avilable", true).setLong("Id", poId);
//            list = query.list();
//            if (!list.isEmpty()) {
//                Iterator<PoDetail> itr = list.iterator();
//                dtm.setRowCount(0);
//                while (itr.hasNext()) {
//                    PoDetail poDetial = itr.next();
//                    dtm.addRow(new Object[]{poDetial.getProduct().getProductId(), poDetial.getProduct(), (poDetial.getProduct().getProductDescription() == null) ? " " : poDetial.getProduct().getProductDescription(), poDetial.getProduct().getProductCategory().getProductCategoryName(), poDetial.getProduct().getProductBrand().getProductBrandName(),
//                        poDetial.getProduct().getProductModel().getProductModelName(), NumbeFormater.formatDoubleToDecimal(poDetial.getBuyingPrice() + (poDetial.getBuyingPrice() * vat.getVatPercentage()) / 100), poDetial.getQty(), NumbeFormater.formatDoubleToDecimal((poDetial.getBuyingPrice() + (poDetial.getBuyingPrice() * vat.getVatPercentage()) / 100) * poDetial.getQty()),poDetial.getQty()});
//
//                }
//            }
//
//            System.out.println("this is list" + list);
//        } catch (Exception ex) {
//            //log.error(ex);
//            ex.printStackTrace();
//        } finally {
//            if (list != null) {
//                list.clear();
//            }
//            super.getHibernateUtil().closeWritebleDbConnection(session);
//        }
//    }
//
//    
    
    
        @Override
    public boolean save(Object obj) {
        return super.save(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object obj) {
        return super.update(obj); //To change body of generated methods, choose Tools | Templates.
    }
}
