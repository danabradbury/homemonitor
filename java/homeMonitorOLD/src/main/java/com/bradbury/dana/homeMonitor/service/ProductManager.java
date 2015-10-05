package com.bradbury.dana.homeMonitor.service;

import java.io.Serializable;
import java.util.List;

import com.bradbury.dana.homeMonitor.domain.Product;


public interface ProductManager extends Serializable{

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();
    
}