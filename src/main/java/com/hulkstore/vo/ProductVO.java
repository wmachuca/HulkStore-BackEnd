package com.hulkstore.vo;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
public class ProductVO {

	@XmlElement(name = "id", required = false)
	private String id;
	@XmlElement(name = "name", required = false)
	private String name;
	@XmlElement(name = "brand", required = false)
	private String brand;
	@XmlElement(name = "quantity", required = false)
	private Integer quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductVO() {
		super();
	}

	public ProductVO(String id, String name, String brand, Integer quantity) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.quantity = quantity;
	}

	
}
