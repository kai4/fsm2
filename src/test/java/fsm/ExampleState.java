package fsm;

public enum ExampleState {
	PLANNING, BUILDING, WORKING, DECOMMISONING, DEFECTIVE;
	
	public static void main(String[] args) {
		for (ExampleState s : ExampleState.values()) {
			System.out.println(s.toString());
		}
	}
}
