
public class PlayerTwoState implements State{
    public void changePlayer(PlayerContext context) {
        context.setState(new PlayerOneState());
    }
    
    public String toString() {
        return "Player 2";
    }
}
