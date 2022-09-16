package unsw.loopmania;

/**
 * Slug
 */
public class Slug extends BasicEnemy {
    private String slugImage;
    public Slug(PathPosition position){
        super(position);
        setHealth(50);
        setAttackBehaviour(new SlugAttack());
        setMoveBehaviour(new SlugMove(position));
        setName("SLUG");
        this.slugImage = "src/images/slug.png";
    }
    @Override
    public String getImage() {
        return this.slugImage;
    }

    @Override
    public void setOriginalMoveBehaviour() {
        setMoveBehaviour(new SlugMove(super.getPathPosition()));
    }
}
