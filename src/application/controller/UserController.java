/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.entity.UserLogin;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Malinda
 */
public class UserController extends DbCommon {
    public Boolean isUserExists(UserLogin users) {
        List list = null;
        Session session = null;
        try {
            session = super.getHibernateUtil().openWritebleDbConnection();
            Query query = session.createQuery("FROM UserLogin s WHERE User_Password = :userPwd AND User_name = :UserName AND STATUS=1"); //status=1 --> active User
            query.setParameter("userPwd", users.getUserPassword());
            query.setParameter("UserName", users.getUserName());

            list = query.list();

            if (!list.isEmpty()) {
                return true;
            }

        } catch (Exception ex) {
            //log.error(ex);
            ex.printStackTrace();
            return false;
        } finally {
            if (list != null) {
                list.clear();
            }
            
            super.getHibernateUtil().closeWritebleDbConnection(session);
        }
        return false;
    }

}
