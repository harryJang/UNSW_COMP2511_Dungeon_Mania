package unsw.loopmania;

public class Basilisk extends Boss {
    private String basiliskImage;
    private static final int GASRADIUS = 5;
    public Basilisk(PathPosition pathPosition){
        super(pathPosition);
        setHealth(300);
        setAttackBehaviour(new BasiliskAttack());
        setMoveBehaviour(new BasiliskMove(pathPosition));
        setName("BASILISK");
        this.basiliskImage = "src/images/basilisk.png";
    }

    @Override
    public String getImage() {
        return this.basiliskImage;
    }

    public int getGasRadius() {
        return GASRADIUS;
    }
}