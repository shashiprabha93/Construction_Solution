/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author Malinda
 */

@Entity
@Table (name = "User_Login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "User_ID")
    
    private Integer userId;
    
    @Column(name = "User_name")
    private String userName;
    
    @Column(name = "User_Password")
    private String userPassword;

    @Column(name = "User_Catergory")
    private String userCatergory;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Site_Id")
    private Sites siteRef;
    
     /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requisitionDetailRef")
    private List<Requisition> requisitionList;
*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userLoginRef")
    private List<Requisition> requisitionList;
     
    public List<Requisition> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<Requisition> requisitionList) {
        this.requisitionList = requisitionList;
    }


    public Sites getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(Sites siteRef) {
        this.siteRef = siteRef;
    }


    @Column(name = "STATUS", columnDefinition = "TINYINT(1)", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;
    
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
       
     
    public String getUserCatergory() {
        return userCatergory;
    }

    public void setUserCatergory(String userCatergory) {
        this.userCatergory = userCatergory;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
        
    
}

