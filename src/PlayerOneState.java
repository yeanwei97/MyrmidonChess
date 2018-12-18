
public class PlayerOneState implements State{
    public void changePlayer(PlayerContext context) {
        context.setState(new PlayerTwoState());
    }
    
    public String toString() {
        return "Player 1";
    }
}
