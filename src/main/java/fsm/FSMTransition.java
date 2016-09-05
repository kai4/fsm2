package fsm;

public class FSMTransition<E extends Enum<?>> {

	private int from;
	private int to;
	
	public FSMTransition(E from, E to){
		this.from = from.ordinal();
		this.to = to.ordinal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + from;
		result = prime * result + to;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		
		@SuppressWarnings("rawtypes")
		FSMTransition other = (FSMTransition) obj;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		
		return true;
	}
}
