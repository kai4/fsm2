package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Arith;

public class C45<E> {
	private Double infoD = 0d;
	private Double infoA_D = 0d;
	private Double GainA = 0d;
	private Double SplitInfoA_D = 0d;
	private Double GainRatioA = 0d;

	
	private E[][] data;
	private E[] attributes;


	public C45(E[][] data, E[] attributes) {
		this.data = data;
		this.attributes = attributes;
	}

	public void calculate() {
		infoD();
	}
	
	
	public void infoD() {
		Map<E, Map<E, Double>> dataMap = new HashMap<E, Map<E, Double>>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				E attribute = attributes[j];
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

		
		Double dataCount = (double) data.length;
		E model = attributes[attributes.length - 1];
		Map<E, Double> modelClass = dataMap.get(model);
		for (Double value : modelClass.values()) {
			double d = Arith.div(value, dataCount, 3);
			this.infoD = Arith.add(this.infoD, Arith.mul(d, -log(d, 2d)));
		}
	}
	
	public void infoDj() {
		
	}
	
	
	private static double log(Double value, Double base) {
		return Arith.div(Math.log(value), Math.log(base), 3);
	}
	
	public Double getInfoD() {
		return Arith.round(infoD, 3);
	}
}
