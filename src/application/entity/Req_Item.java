/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "construction_reqItem")

public class Req_Item implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQ_ITEM_ID")
    private Integer reqItemId;

    @Column(name = "REQ_ITEM_MANUFACTURER", nullable = false)
    private String reqItemManufacturer;

    @Column(name = "REQ_ITEM_STATUS")
    private Boolean reqItemstatus;

    @Column(name = "REQ_ITEM_DESCRIPTION")
    private String reqItemDescription;

    @Column(name = "REQ_ITEM_U_O_M")
    private String reqItemUom;

    @Column(name = "REQ_ITEM_S_K_U")
    private String reqItemSku;

    @Column(name = "REQ_ITEM_QTY")
    private Integer reqItemQty;
    
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
  

}
