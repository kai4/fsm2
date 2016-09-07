package c45;

public class Val<E> {
	public E val;
	public E cls;
	public Integer count;
	
	public Val(E val, E cls) {
		this.val = val;
		this.cls = cls;
		this.count = 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + val.hashCode();
		result = prime * result + cls.hashCode();
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
		Val other = (Val) obj;
		if (val != other.val)
			return false;
		if (cls != other.cls)
			return false;
		
		return true;
	}
}
