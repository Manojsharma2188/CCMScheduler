package com.clientutil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URLEndpoint")
public class ClientEndpoint {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "endpointurl")
	private String endpointurl;

	public int getId() {
		return id;
	}

	public String getEndpointurl() {
		return endpointurl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEndpointurl(String endpointurl) {
		this.endpointurl = endpointurl;
	}

	@Override
	public String toString() {
		return "URLEndpoint [id=" + id + ", endpointurl=" + endpointurl + "]";
	}

	public ClientEndpoint() {

	}

	public ClientEndpoint(int id, String endpointurl) {
		super();
		this.id = id;
		this.endpointurl = endpointurl;
	}


}
