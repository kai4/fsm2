package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Arith;

public class C45<E, M extends Enum<?>> {
	private Double infoD = 0d;
	private Double infoA_D = 0d;
	private Double GainA = 0d;
	private Double SplitInfoA_D = 0d;
	private Double GainRatioA = 0d;

	
	private E[][] data;
	private String state;
	private String[] attributes;
	private M[] models;

	private Map<String, Map<E, Double>> dataMap = new HashMap<String, Map<E, Double>>();

	public C45(E[][] data, String[] attributes) {
		this.data = data;
		this.attributes = attributes;
	}

	public void calculate() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				String attribute = attributes[j];
				Map<E, Double> attr = dataMap.get(attribute);
				if (attr == null) {
					attr = new HashMap<E, Double>();
					dataMap.put(attribute, attr);
				}

				E e = data[i][j];
				Double n = attr.get(e);
				if (n == null) {
					attr.put(e, 1d);
				} else {
					attr.put(e, n + 1);
				}
			}
		}

		/*
		 * infoD
		 */
		Double dataCount = (double) data.length;
		String model = attributes[attributes.length - 1];
		Map<E, Double> modelClass = dataMap.get(model);
		for (Double value : modelClass.values()) {
			double d = Arith.div(value, dataCount, 3);
			this.infoD = Arith.add(this.infoD, Arith.mul(d, -log(d, 2d)));
		}
		
		for (int i = 0; i < attributes.length - 1; i ++) {
			String attr = attributes[i];
			double info_j = infoD(attr, dataCount);
			//double Dj_D = Arith.div(v1, v2, scale)
		}
	}
	
	public void calculate2() {
		Map<String, Map<E, List<Double>>> dataMap = new HashMap<String, Map<E, List<Double>>>();
		
		for (int i = 0; i < data.length; i ++) {
			for (int j = 0; j < data[i].length; j ++) {
				String attribute = attributes[j];
				Map<E, List<Double>> attr = dataMap.get(attribute);
				if (attr == null) {
					attr = new HashMap<E, List<Double>>();
					dataMap.put(attribute, attr);
				}

				E e = data[i][j];
				List<Double> l = attr.get(e);
				if (l == null) {
					attr.put(e, new ArrayList());
				}
				
				
			}
		}
	}
	
	private double infoD(String attr, Double dataCount) {
		double info = 0d;
		Map<E, Double> modelClass = dataMap.get(attr);
		for (Double value : modelClass.values()) {
			double d = Arith.div(value, dataCount, 3);
			info = Arith.add(info, Arith.mul(d, -log(d, 2d)));
		}
		return info;
	}
	
	private static double log(Double value, Double base) {
		return Arith.div(Math.log(value), Math.log(base), 3);
	}
	
	public Double getInfoD() {
		return Arith.round(infoD, 3);
	}
}
