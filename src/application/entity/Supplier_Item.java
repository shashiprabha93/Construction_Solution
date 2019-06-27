/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "construction_supplierItem")

public class Supplier_Item implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_ITEM_ID")
    private Integer supplierItemId;

    @Column(name = "SUPPLIER_ITEM_MANUFACTURER")
    private String supplierItemManufacturer;

    @Column(name = "SUPPLIER_ITEM_DESCRIPTION")
    private String supplierItemDescription;

    @Column(name = "SUPPLIER_ITEM_U_O_M")
    private String supplierItemUOM;

    @Column(name = "SUPPLIER_ITEM_S_K_U")
    private String supplierItemSKU;
    
    //One to many mapping 
    @Column(name = "SUPPLIER_ITEM_SUPPLIER")
    private String supplier;

    @Column(name = "CREATED_AT", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "DELETED_AT", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "STATUS", columnDefinition = "TINYINT(1)", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;

    public Integer getSupplierItemId() {
        return supplierItemId;
    }

    public void setSupplierItemId(Integer supplierItemId) {
        this.supplierItemId = supplierItemId;
    }

    public String getSupplierItemManufacturer() {
        return supplierItemManufacturer;
    }

    public void setSupplierItemManufacturer(String supplierItemManufacturer) {
        this.supplierItemManufacturer = supplierItemManufacturer;
    }

    public String getSupplierItemDescription() {
        return supplierItemDescription;
    }

    public void setSupplierItemDescription(String supplierItemDescription) {
        this.supplierItemDescription = supplierItemDescription;
    }

    public String getSupplierItemUOM() {
        return supplierItemUOM;
    }

    public void setSupplierItemUOM(String supplierItemUOM) {
        this.supplierItemUOM = supplierItemUOM;
    }

    public String getSupplierItemSKU() {
        return supplierItemSKU;
    }

    public void setSupplierItemSKU(String supplierItemSKU) {
        this.supplierItemSKU = supplierItemSKU;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<PoDetail> getPoDetails() {
        return poDetails;
    }

    public void setPoDetails(List<PoDetail> poDetails) {
        this.poDetails = poDetails;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<PoDetail> poDetails;
  
}

