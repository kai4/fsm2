package tree;

import java.util.HashMap;
import java.util.Map;

import utils.Arith;

public class C45<E> {
	private Double infoD = 0d;
	private Map<E, Double> infoA_D;
	private Double GainA = 0d;
	private Double SplitInfoA_D = 0d;
	private Double GainRatioA = 0d;

	
	private E[][] data;
	private E[] attributes;
	private E model;

	public C45(E[][] data, E[] attributes) {
		this.data = data;
		this.attributes = attributes;
		infoA_D = new HashMap<E, Double>();
	}

	public void calculate() {
		infoD();
		infoDj();
	}
	
	
	public void infoD() {
		Map<E, Integer> dataMap = new HashMap<E, Integer>();
		int rowNum = data.length;
		int columNum = data[0].length;
		for (int i = 0; i < rowNum; i++) {
			E e = data[i][columNum - 1];
			Integer d = dataMap.get(e);
			if (d == null) {
				dataMap.put(e, 1);
			} else {
				dataMap.put(e, d + 1);
			}
		}

		
		Double dataCount = (double) data.length;
		for (Integer value : dataMap.values()) {
			double d = Arith.div(Double.valueOf(value), dataCount);
			this.infoD = Arith.add(this.infoD, Arith.mul(d, -log(d, 2d)));
		}
	}
	
	public void infoDj() {
		Map<E, Map<E, Integer>> dataMap = new HashMap<E, Map<E, Integer>>();
		int rowNum = data.length;
		int columNum = data[0].length;
		Double dataCount = (double) data.length;
		for (int i = 0; i < columNum; i ++) {
			for (int j = 0; j < rowNum; j ++) {
				E e = data[j][i];
				Map<E, Integer> modelMap = dataMap.get(e);
				if (modelMap == null) {
					modelMap = new HashMap<E, Integer>();
					dataMap.put(e, modelMap);
				}
				E m = data[j][columNum - 1];
				Integer d = modelMap.get(m);
				if (d == null) {
					modelMap.put(m, 1);
				} else {
					modelMap.put(m, d + 1);
				}
			}
			
			for (E m : dataMap.keySet()) {
				Map<E, Integer> model = dataMap.get(m);
				Integer count = 0;
				Double infoDj = 0d;
				for (Integer value : model.values()) {
					count += value;
				}
				
				for (Integer value : model.values()) {
					double d = Arith.div(value, count);
					infoDj = Arith.add(infoDj, Arith.mul(Arith.div(count, dataCount),Arith.mul(d, -log(d, 2d))));
				}
				infoA_D.put(m, infoDj);
			}
		}
	}
	
	
	private static double log(Double value, Double base) {
		return Arith.div(Math.log(value), Math.log(base), 3);
	}
	
	public Double getInfoD() {
		return Arith.round(infoD, 3);
	}

	public Map<E, Double> getInfoA_D() {
		return infoA_D;
	}
}
