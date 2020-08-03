package com.spring.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.spring.rest.model.Course;

public class CourseDto {
	private List<Course> list = new ArrayList<>();
	private double discountPrice;
	/**
	 * @return the list
	 */
	public List<Course> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Course> list) {
		this.list = list;
	}
	/**
	 * @return the discountPrice
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

}
