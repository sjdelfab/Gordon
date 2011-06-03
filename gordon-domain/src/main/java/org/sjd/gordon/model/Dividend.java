package org.sjd.gordon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "dividend")
@SequenceGenerator(schema="GORDON",name = "dividendIdSeqGenerator", sequenceName  = "SEQ_DIVIDEND",initialValue=1, allocationSize=1)
public class Dividend implements Serializable {

	private static final long serialVersionUID = -3172193867088179468L;
	
	@Id @GeneratedValue(generator="dividendIdSeqGenerator")
    private Long id;	
	@Version 
    private Integer version;
	@Column(nullable = false,name="date")
	@Temporal(TemporalType.DATE)
    private Date date;
	@Column(nullable = false,name="announcement_date")
	@Temporal(TemporalType.DATE)
    private Date announcementDate;
	@Column(nullable = false,name="amount")
    private BigDecimal amount;
	
	public Dividend() { }
	
	public Dividend(Dividend data) {
		if (data != null) {
		    id = data.id;
		    version = data.version;
		    date = data.date;
		    announcementDate = data.announcementDate;
		    amount = data.amount;
		}
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getAnnouncementDate() {
		return announcementDate;
	}
	
	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void mergeTo(Dividend dividend) {
		announcementDate = new Date(dividend.announcementDate.getTime());
		date = new Date(dividend.date.getTime());
		amount = dividend.amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dividend other = (Dividend) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
