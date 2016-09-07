package c45;

import java.util.HashMap;
import java.util.Map;

import utils.Arith;

public class C45<T, E> {
	private Double InfoD = 0d;
	private Map<T, Double> InfoA_D;
	private Map<T, Double> GainA;
	private Map<T, Double> SplitInfoA_D;
	private Map<T, Double> IGR;

	private E[][] data;
	private T[] attrs;

	public C45(E[][] data, T[] attrs) {
		this.data = data;
		this.attrs = attrs;
		InfoA_D = new HashMap<T, Double>();
		GainA = new HashMap<T, Double>();
		SplitInfoA_D = new HashMap<T, Double>();
		IGR = new HashMap<T, Double>();
	}

	public void calculate() {
		infoD();
		infoDj();
		gainA();
		splitInfo();
		igr();
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
			InfoA_D.put(attrs[i], infoDj);
		}
	}
	
	public void gainA() {
		for (int i = 0; i < attrs.length - 1; i ++) {
			T e = attrs[i];
			GainA.put(e, InfoD - InfoA_D.get(e));
		}
	}
	
	public void splitInfo() {
		int rowNum = data.length;
		int columNum = data[0].length;
		Double dataCount = (double) data.length;
		for (int i = 0; i < columNum - 1; i ++) {
			Map<E, Integer> dataMap = new HashMap<E, Integer>();
			for (int j = 0; j < rowNum; j ++) {
				E e = data[j][i];
				
				Integer d = dataMap.get(e);
				if (d == null) {
					dataMap.put(e, 1);
				} else {
					dataMap.put(e, d + 1);
				}
			}
			
			Double splitInfoA = 0d;
			for (Integer value : dataMap.values()) {
				splitInfoA += value/dataCount*(-log(value/dataCount, 2d));
			}
			this.SplitInfoA_D.put(attrs[i], splitInfoA);
		}
	}
	
	public void igr() {
		for (int i = 0; i < attrs.length - 1; i ++) {
			T t = attrs[i];
			this.IGR.put(t, this.GainA.get(t)/this.SplitInfoA_D.get(t));
		}
	}
	
	private static double log(Double value, Double base) {
		return Arith.div(Math.log(value), Math.log(base), 3);
	}

	public Double getInfoD() {
		return InfoD;
	}

	public Map<T, Double> getInfoA_D() {
		return InfoA_D;
	}

	public Map<T, Double> getGainA() {
		return GainA;
	}

	public Map<T, Double> getSplitInfoA_D() {
		return SplitInfoA_D;
	}

	public Map<T, Double> getIGR() {
		return IGR;
	}
}
