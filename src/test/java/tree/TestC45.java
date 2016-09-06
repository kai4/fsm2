package tree;

import java.util.Map;

import org.junit.Test;

public class TestC45 {

	public String[][] data = { { "晴", "炎热", "高", "弱", "否" }, { "晴", "炎热", "高", "强", "否" }, { "阴", "炎热", "高", "弱", "是" },
			{ "雨", "适中", "高", "弱", "是" }, { "雨", "寒冷", "正常", "弱", "是" }, { "雨", "寒冷", "正常", "强", "否" },
			{ "阴", "寒冷", "正常", "强", "是" }, { "晴", "适中", "高", "弱", "否" }, { "晴", "寒冷", "正常", "弱", "是" },
			{ "雨", "适中", "正常", "弱", "是" }, { "晴", "适中", "正常", "强", "是" }, { "阴", "适中", "高", "强", "是" },
			{ "阴", "炎热", "正常", "弱", "是" }, { "雨", "适中", "高", "强", "否" }, };

	@Test
	public void testC45() {
		String[] s = { "OUTLOOK", "TEMPERATURE", "HUMIDITY", "WINDY", "STATE" };
		C45<String, String> c = new C45<String, String>(data, s);
		c.calculate();
		System.out.println(">>>>>>>>>>>>>>>>InfoD<<<<<<<<<<<<<<<<<<<<");
		System.out.println(c.getInfoD());

		System.out.println("\n>>>>>>>>>>>>>>>>InfoA_D<<<<<<<<<<<<<<<<<<");
		for (int i = 0; i < s.length - 1; i++) {
			String attr = s[i];
			Map<String, Double> infoA_D = c.getInfoA_D();
			System.out.println(attr + ": " + infoA_D.get(attr));
		}
		
		System.out.println("\n>>>>>>>>>>>>>>>>GainA<<<<<<<<<<<<<<<<<<");
		for (int i = 0; i < s.length - 1; i++) {
			String attr = s[i];
			Map<String, Double> gainA = c.getGainA();
			System.out.println(attr + " : " + gainA.get(attr));
		}

		System.out.println("\n>>>>>>>>>>>>>>>>SplitInfoA_D<<<<<<<<<<<<<<<<<<");
		for (int i = 0; i < s.length - 1; i++) {
			String attr = s[i];
			Map<String, Double> splitInfo = c.getSplitInfoA_D();
			System.out.println(attr + ": " + splitInfo.get(attr));
		}
		
		System.out.println("\n>>>>>>>>>>>>>>>>IGR<<<<<<<<<<<<<<<<<<");
		for (int i = 0; i < s.length - 1; i++) {
			String attr = s[i];
			Map<String, Double> igr = c.getIGR();
			System.out.println(attr + ": " + igr.get(attr));
		}
	}
}
