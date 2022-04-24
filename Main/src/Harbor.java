public class Harbor {
    private Intersection[] ints;
    private String resource;

    public Harbor(Intersection i1, Intersection i2, String r){
        ints = new Intersection[]{i1, i2};
        resource = r;
    }

    public Player getOwner() {
        for (Intersection i:ints) {
            if (i.getOwner() != null) return i.getOwner();
        }
        return null;
    }

}
