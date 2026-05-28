package com.work.ProductRest.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dimensions {
	
	private double width;
    private double height;
    private double depth;
    
	public Dimensions() {
		super();
		
	}

	public Dimensions(double width, double height, double depth) {
		super();
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Dimensions [width=" + width + ", height=" + height + ", depth=" + depth + "]";
	} 

}
