package fsm;

public class FSMStateMachine<E extends Enum<?>> {
	
	private FSMTransitioningModel<E> model;
	private E currentState;
	
	public FSMStateMachine(FSMTransitioningModel<E> model, E currentState) {
		this.model = model;
		this.currentState = currentState;
	}
	
	public boolean transition(E to) {
		if (model.isValidTransition(this.currentState, to))
			return false;
		this.currentState = to;
		return true;
	}

	public E getCurrentState() {
		return currentState;
	}
}
