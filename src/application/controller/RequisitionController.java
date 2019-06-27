/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.Requisition;
import application.entity.RequisitionDetails;
import constructionsolution.NumberGenerator;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author DELL
 */
public class RequisitionController extends DbCommon{
     
    public boolean saveRequisitionDetais(Requisition requisition) {

        Session session = super.getHibernateUtil().openWritebleDbConnection();
        List<RequisitionDetails> list = null;
        try {

            session.save(requisition);

            String genaretedRequisitionNo = new NumberGenerator().getGenaretedNumber("REQ", requisition.getRequisitionId());
            requisition.setRequisitionNo(genaretedRequisitionNo);
            session.saveOrUpdate(requisition);

            list = requisition.getRequisitionDetailsList();
            Iterator<RequisitionDetails> itr = list.iterator();
            while (itr.hasNext()) {
                RequisitionDetails detail = itr.next();
                detail.setRequisitionDetailRef(requisition);
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
     public void getAllRequisitions(DefaultTableModel dtm) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM Requisition r WHERE status=1 ORDER BY requisitionNo");//status=1 --> active Supplier
            list = query.list();
            if (!list.isEmpty()) {
                Iterator< Requisition> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    Requisition requisition = (Requisition) itr.next();
                    dtm.addRow(new Object[]{requisition.getRequisitionId(), requisition.getRequisitionNo(), requisition.getUserLoginRef().getUserName(),  requisition.isRequisitionSubmitted() == true ? "Submitted" : "Not Submitted"});
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
  public void getRequisitionDetails(DefaultTableModel dtm, int requisitionID) {

        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            SQLQuery query = session.createSQLQuery("SELECT  * FROM   construction_requisition_details rd WHERE  rd.REQUISITION_ID= '" + requisitionID + "'");
            query.addEntity(RequisitionDetails.class);

            list = query.list();
            if (!list.isEmpty()) {
                Iterator< RequisitionDetails> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    RequisitionDetails requisition_Details = itr.next();
                   // System.out.println("product " + rental_Details.getProductRef().getProductName());
                    //System.out.println("QTY " + rental_Details.getQty());
                    dtm.addRow(new Object[]{requisition_Details.getSupplierItemRef().getSupplierItemDescription(), requisition_Details.getSupplierItemRef().getSupplierItemUOM(), requisition_Details.getQty()});
                    
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

    public Requisition findRequisitionById(int id) {
        Requisition requisition = null;;
        List<Requisition> requisitionList = null;
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        try {
            Query query = session.createQuery("SELECT r FROM Requisition r WHERE REQUISITION_ID=:requisitionId").setParameter("requisitionId", id);
            requisitionList = query.list();
            if (!requisitionList.isEmpty()) {
                Iterator<Requisition> itr = requisitionList.iterator();
                while (itr.hasNext()) {
                    requisition = itr.next();
                }
                return requisition;
            }
        } catch (Exception ex) {
            // log.error(ex);
            ex.printStackTrace();
        } finally {
            if (requisitionList != null) {
                requisitionList.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return requisition;
    }

}
