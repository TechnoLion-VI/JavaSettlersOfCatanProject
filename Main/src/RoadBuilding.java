public class RoadBuilding extends DevelopmentCard{
    public RoadBuilding() {
        super("RoadBuilding");
    }
    public boolean use(Edge a, Edge b) {
        if (!Board.buildRoad(GameState.currentPlayer, a)) return false;
        if (!Board.buildRoad(GameState.currentPlayer, b)) return false;
        return true;
    }
}
