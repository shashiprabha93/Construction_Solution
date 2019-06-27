/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import constructionsolution.NewHibernateUtil;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Shashiprabha
 */
public class DbCommon {

    NewHibernateUtil hibernateUtil;

    public DbCommon() {
        this.hibernateUtil = NewHibernateUtil.getInstance();
    }

    public boolean delete(Object obj) {

        Session session = hibernateUtil.openWritebleDbConnection();
        try {
            session.update(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            hibernateUtil.closeWritebleDbConnection(session);
        }
//        by using finally block we going to close the session .
// in here difference between writeable and closeable db connection is writeable db connection method 
// use to update and insert and also we have commit after the transaction othervise it not going to save or insert or update 
// in db.but readable db connection is not like that.
    }

    public boolean permdelete(Object obj) {

        Session session = hibernateUtil.openWritebleDbConnection();
        try {
            session.delete(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            hibernateUtil.closeWritebleDbConnection(session);
        }
    }

    public boolean save(Object obj) {

        Session session = hibernateUtil.openWritebleDbConnection();
        try {
            session.save(obj);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {
            hibernateUtil.closeWritebleDbConnection(session);
        }

    }

    public boolean update(Object obj) {

        Session session = hibernateUtil.openWritebleDbConnection();
        try {
            session.update(obj);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {
            hibernateUtil.closeWritebleDbConnection(session);
        }
    }

    public NewHibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }

    public void executeStatement(String statement) {

        Session session = hibernateUtil.openWritebleDbConnection();
        try {
            session.createSQLQuery(statement).executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            hibernateUtil.closeWritebleDbConnection(session);
        }

    }

}
