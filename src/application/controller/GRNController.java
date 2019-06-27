/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.Requisition;
import java.util.Iterator;
import java.util.List;
import application.entity.Po;
import application.entity.PoDetail;
import application.entity.Supplier;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Malinda
 */
public class GRNController extends DbCommon {

    public Po findPO_IDById(long id) {

        Po po = null;
        List<Po> poList = null;
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        try {
            Query query = session.createQuery("SELECT s FROM Po s WHERE po_id=:PoId");
            query.setParameter("PoId", id);

            poList = query.list();
            if (!poList.isEmpty()) {
                Iterator<Po> itr = poList.iterator();
                while (itr.hasNext()) {
                    po = itr.next();
                }

                return po;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Id");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (poList != null) {
                poList.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        JOptionPane.showMessageDialog(null, po);
        return po;
    }

    public void getAllPODetails(DefaultTableModel dtm, int id) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            //session.save(po);
            Query query = session.createQuery("FROM PoDetail s WHERE STATUS=1");
            //query.setParameter("PoId",id);
            list = query.list();
            if (!list.isEmpty()) {
                Iterator< PoDetail> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    PoDetail poDetail = (PoDetail) itr.next();
                    
                    dtm.addRow(new Object[]{poDetail.getQty(), poDetail.getProduct().getSupplierItemId(), poDetail.getProduct().getSupplierItemDescription(),poDetail.getItemStatus()});
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

}
