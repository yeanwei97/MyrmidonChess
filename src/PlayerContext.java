
public class PlayerContext {
    private State state;
    
    public PlayerContext() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return state;
    }
    
    public String getStateString() {
        return state.toString();
    }
}
