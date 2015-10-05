package com.bradbury.dana.homeMonitor.domain;

import java.io.Serializable;

public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7337013470359831417L;
	
	private String description;
    private Double price;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(final Double price) {
        this.price = price;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
        return buffer.toString();
    }
}
