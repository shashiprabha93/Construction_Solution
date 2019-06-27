/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Malinda
 */
@Entity
@Table(name = "GRN_Details)")
public class GRNDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRN_ID")
    private Integer GRNId;

    @Column(name = "PO_ID", nullable = false)
    private Integer poId;

    @Column(name = "PO_GRN", nullable = false)
    private String PoGrn;

    @Column(name = "Approved_By", nullable = false)
    private String approvedBy;

    @Column(name = "Approved_Date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ApprovedDate;
    
    @Column(name = "PRICE", nullable = false)
    private Double buyingPrice;
    
    @Column(name = "Comments", nullable = false)
    private String comments;

    public Integer getGRNId() {
        return GRNId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setGRNId(Integer GRNId) {
        this.GRNId = GRNId;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getPoGrn() {
        return PoGrn;
    }

    public void setPoGrn(String PoGrn) {
        this.PoGrn = PoGrn;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return ApprovedDate;
    }

    public void setApprovedDate(Date ApprovedDate) {
        this.ApprovedDate = ApprovedDate;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
    
    
}
