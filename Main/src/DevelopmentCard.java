public class DevelopmentCard {
    private String type;
    public DevelopmentCard(String s) {
        type=s;
    }
    public String getType() {
        return type;
    }
    public boolean use(Player p) {
        return false;
    }
}
