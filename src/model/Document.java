package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author hanxi
 * 
 */

public class Document {
	private int id;
	private String name;
	private String remark;
	private BooleanProperty select = new SimpleBooleanProperty();
	private List<Double> charts = new ArrayList<Double>();;

	public Document(int id, String name, String remark) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			charts.add(random.nextDouble() * 100);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}

	public boolean getSelect() {
		return select.get();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSelect(boolean select) {
		this.select.set(select);
	}

	public BooleanProperty selectProperty() {
		return select;
	}

	public List<Double> getCharts() {
		return charts;
	}

	public void setCharts(List<Double> charts) {
		this.charts = charts;
	}

}
