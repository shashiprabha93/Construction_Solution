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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Malinda
 */
@Entity 
@Table(name="SiteDetails")
public class Sites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "Site_Id")
    private Integer siteId;
    
    @Column(name="Site_Name")
    private String siteName;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteRef")
    private List<UserLogin> userLoginList;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<UserLogin> getUserLoginList() {
        return userLoginList;
    }

    public void setUserLoginList(List<UserLogin> userLoginList) {
        this.userLoginList = userLoginList;
    }
    
    
    
    
}
