package com.dev.FinalProject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class po {

	@Id
	int i1;
	int i2;
	int i3;
	int i4;
	int i5;

	public po() {
		super();
	}
	public po(int i1, int i2, int i3, int i4, int i5) {
		super();
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.i4 = i4;
		this.i5 = i5;
	}
	public int getI1() {
		return i1;
	}
	public void setI1(int i1) {
		this.i1 = i1;
	}
	public int getI2() {
		return i2;
	}
	public void setI2(int i2) {
		this.i2 = i2;
	}
	public int getI3() {
		return i3;
	}
	public void setI3(int i3) {
		this.i3 = i3;
	}
	public int getI4() {
		return i4;
	}
	public void setI4(int i4) {
		this.i4 = i4;
	}
	public int getI5() {
		return i5;
	}
	public void setI5(int i5) {
		this.i5 = i5;
	}
	
	
	
}
