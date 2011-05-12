package org.sjd.gordon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
@SequenceGenerator(schema="GORDON",name = "groupIdSeqGenerator", sequenceName  = "SEQ_GROUP",initialValue=1, allocationSize=1)
public class Group {

	@Id @GeneratedValue(generator="groupIdSeqGenerator")
	private Integer id;
	@Column(nullable = false,name="name")
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
