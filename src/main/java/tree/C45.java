package tree;

import java.util.HashMap;
import java.util.Map;

import utils.Arith;

public class C45<E> {
	private Double InfoD = 0d;
	private Map<E, Double> InfoA_D;
	private Map<E, Double> GainA;
	private Map<E, Double> SplitInfoA_D;
	private Double GainRatioA = 0d;

	
	private E[][] data;
	private E[] attrs;
	private E model;

	public C45(E[][] data, E[] attrs) {
		this.data = data;
		this.attrs = attrs;
		InfoA_D = new HashMap<E, Double>();
		GainA = new HashMap<E, Double>();
		SplitInfoA_D = new HashMap<E, Double>();
	}

	public void calculate() {
		infoD();
		infoDj();
		gainA();
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
			this.InfoD += value/dataCount*(-log(value/dataCount, 2d));
		}
		InfoD = Arith.round(InfoD, 3);
	}
	
	public void infoDj() {
		int rowNum = data.length;
		int columNum = data[0].length;
		Double dataCount = (double) data.length;
		for (int i = 0; i < columNum - 1; i ++) {
			Map<E, Map<E, Integer>> dataMap = new HashMap<E, Map<E, Integer>>();
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
			
			Double infoDj = 0d;
			for (E m : dataMap.keySet()) {
				Double count = 0d;
				Double infoJ = 0d;
				Map<E, Integer> model = dataMap.get(m);
				
				for (Integer value : model.values()) {
					count += value;
				}
				
				for (Integer value : model.values()) {
					Double d = Double.valueOf(value)/count;
					infoJ += -d * log(d, 2d);
				}
				infoDj += count / dataCount * infoJ;
			}
			InfoA_D.put(attrs[i], Arith.round(infoDj, 3));
		}
	}
	
	public void gainA() {
		for (int i = 0; i < attrs.length - 1; i ++) {
			E e = attrs[i];
			GainA.put(e, Arith.round(InfoD - InfoA_D.get(e), 3));
		}
	}
	
	public void splitInfo() {
		
	}
	
	
	private static double log(Double value, Double base) {
		return Arith.div(Math.log(value), Math.log(base), 3);
	}

	public Double getInfoD() {
		return InfoD;
	}

	public Map<E, Double> getInfoA_D() {
		return InfoA_D;
	}

	public Map<E, Double> getGainA() {
		return GainA;
	}
}
