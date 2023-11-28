package bibliotroca.BiblioTroca.strategy;

public enum State {
	Novo("Novo"),
	Seminovo("Seminovo"),
	Usado("Usado");
	
	private final String state;
	
	private State(String state) {
		this.state = state;
	}
	
	public String getState() {
		return this.state;
	}
	
	public static State getByState(String state) {
		return State.valueOf(state);
	}
	
}
