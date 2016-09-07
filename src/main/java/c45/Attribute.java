package c45;

import java.util.HashMap;
import java.util.Map;

public class Attribute<T, E> {
	public T name;
	public Map<Val<E>, Boolean> values = new HashMap<Val<E>, Boolean>();
	
	public Attribute(T t) {
		this.name = t;
	}
	
	public void insertVal(E val, E cls) {
		Val<E> v = new Val<E>(val, cls);
		if (values.get(v) == null) {
			values.put(v, true);
		} else {
			values.get(v);
		}
	}
}
