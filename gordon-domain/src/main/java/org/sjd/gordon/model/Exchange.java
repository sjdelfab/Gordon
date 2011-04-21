package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "EXCHANGE")
@SequenceGenerator(schema="GORDON",name = "exchangeIdSeqGenerator", sequenceName  = "SEQ_EXCHANGE",initialValue=1, allocationSize=1)
public class Exchange implements Serializable {
    
	private static final long serialVersionUID = -9181164934674054648L;

	@Id @GeneratedValue(generator="exchangeIdSeqGenerator")
    private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="active")
    private boolean active;
	@Column(nullable = false,name="code")
    private String code;
	@Column(nullable = false,name="name")
    private String name;
    
    public Exchange() {}

    public Integer getVersion() {
		return version;
	}
    
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}

}
