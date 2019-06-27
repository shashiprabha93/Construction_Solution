/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.Supplier;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DELL
 */
public class SupplierController extends DbCommon{
    
    public int isSupplierExists(Supplier supplier) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM Supplier s WHERE SUPPLIER_ID != :supplierId AND SUPPLIER_NAME= :supplierName  AND STATUS=1"); //status=1 --> active Supplier
            query.setParameter("supplierId", supplier.getSupplierId() == null ? 0 : supplier.getSupplierId());
            query.setParameter("supplierName", supplier.getSupplierName());
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

    public boolean checkSupplierFax(Supplier supplier) {
        Session session = null;
        boolean numberAvailable = false;
        try {
            session = super.getHibernateUtil().openReadableDbConnection();
//            Query query = session.createSQLQuery("SELECT * FROM orange_supplier s1 where s1.SUPPLIER_CONTACTNO= :mobile");//status=1 --> active Supplier

            Query query = session.createQuery("FROM Supplier s WHERE SUPPLIER_ID != :supplierId AND supplierFax = :fax"); //status=1 --> active Supplier
            query.setParameter("supplierId", supplier.getSupplierId() == null ? 0 : supplier.getSupplierId());
            query.setParameter("fax", supplier.getSupplierFax());

            List list = query.list();
            if (!list.isEmpty()) {
                numberAvailable = true;
            }

        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return numberAvailable;
    }

    public Supplier findSupplierById(int id) {
        Supplier supplier = null;
        List<Supplier> supplierList = null;
        Session session = super.getHibernateUtil().openWritebleDbConnection();
        try {
            Query query = session.createQuery("SELECT s FROM Supplier s WHERE SUPPLIER_ID=:supId").setParameter("supId", id);
            supplierList = query.list();
            if (!supplierList.isEmpty()) {
                Iterator<Supplier> itr = supplierList.iterator();
                while (itr.hasNext()) {
                    supplier = itr.next();
                }
                return supplier;
            }
        } catch (Exception ex) {
            // log.error(ex);
            ex.printStackTrace();
        } finally {
            if (supplierList != null) {
                supplierList.clear();
            }
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return supplier;
    }

    public void getAllSupplier(DefaultTableModel dtm) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM Supplier s WHERE status=1 ORDER BY supplierName");//status=1 --> active Supplier
            list = query.list();
            if (!list.isEmpty()) {
                Iterator< Supplier> itr = list.iterator();
                dtm.setRowCount(0);
                while (itr.hasNext()) {
                    Supplier supplier = (Supplier) itr.next();
                    dtm.addRow(new Object[]{supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierZip(), supplier.getSupplierCurrnecy(), supplier.getSupplierCountry(), supplier.getSupplierFax()});
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
        public boolean getAllSuppliersForCombo(DefaultComboBoxModel cbm) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openReadableDbConnection();
            Query query = session.createQuery("FROM Supplier s WHERE STATUS=1");//status=1 --> active Customer
            list = query.list();
            cbm.removeAllElements();
            cbm.addElement("- Select a Supplier -");
            if (!list.isEmpty()) {
                Iterator< Supplier> itr = list.iterator();
                while (itr.hasNext()) {
                    Supplier supplier = (Supplier) itr.next();
                    cbm.addElement(supplier.getSupplierName());
                }
            }
            return true;
        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
            return false;
        } finally {
            if (list != null) {
                list.clear();
            }
            session.close();
        }
    }

}

    
