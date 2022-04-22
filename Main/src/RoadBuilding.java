public class RoadBuilding extends DevelopmentCard{
    public RoadBuilding() {
        super("RoadBuilding");
    }
    public boolean use(Edge a, Edge b) {
        return Board.buildRoad(GameState.currentPlayer, a) && Board.buildRoad(GameState.currentPlayer, b);
    }
}
