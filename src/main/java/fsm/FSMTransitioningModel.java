package fsm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FSMTransitioningModel<E extends Enum<?>> {

	private Map<FSMTransition<E>, Boolean> model;
	
	public FSMTransitioningModel() {
		this.model = new HashMap<FSMTransition<E>, Boolean>();
	}
	
	public void allow(E from, E to) {
		this.model.put(new FSMTransition<E>(from, to), true);
	}
	
	public void allow(E from, Set<E> to) {
		for (E e : to) {
			this.allow(from, e);
		}
	}
	
	public boolean isValidTransition(E from, E to) {
		Boolean b = this.model.get(new FSMTransition<E>(from, to));
		if (b == null)
			return false;
		return b;
	}
}
