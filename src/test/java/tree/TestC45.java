package tree;

import java.util.Map;

import org.junit.Test;

public class TestC45 {

	public String[][] data = {
			{"晴","炎热","高","弱", "否"},
			{"晴","炎热","高","强", "否"},
			{"阴","炎热","高","弱", "是"},
			{"雨","适中","高","弱", "是"},
			{"雨","寒冷","正常","弱", "是"},
			{"雨","寒冷","正常","强", "否"},
			{"阴","寒冷","正常","强", "是"},
			{"晴","适中","高","弱", "否"},
			{"晴","寒冷","正常","弱", "是"},
			{"雨","适中","正常","弱", "是"},
			{"晴","适中","正常","弱", "是"},
			{"阴","适中","高","强", "是"},
			{"阴","炎热","正常","弱", "是"},
			{"雨","适中","高","强", "否"},
	};
	
	
	@Test
	public void testC45() {
		String[] s = {"OUTLOOK", "TEMPERATURE", "HUMIDITY", "WINDY", "STATE"};
		C45<String> c = new C45<String>(data, s);
		c.calculate();
		System.out.println(c.getInfoD());
		
		Map<String, Double> infoA_D = c.getInfoA_D();
		for (String key: infoA_D.keySet()) {
			System.out.println(key + ": " + infoA_D.get(key));
		}
	}
}
