package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    /**
     * Character and castle
     */
    private Character character;
    private HeroCastleBuilding heroCastle;
    /**
     * Enemy lists
     */
    private List<BasicEnemy> basicEnemies;

    private Boss boss;
    /**
     * Cards
     */
    private List<Card> cardEntities;

    private CardFactory cardFactory = new CardFactory();
    /**
     * Inventory list
     */
    private List<Entity> unequippedInventoryItems;
    /**
     * Building list. 
     * Different buildings are stored in the same list
     */
    private List<Building> buildingsList;
    /**
     * Game stats
     */
    private Integer Gold;
    private Integer Exp;
    private Integer roundNumber;
    private Integer healthPotionNumber;
    private boolean defeatedAllBosses;
    /**
     * If required in Json
     */
    private Boolean isRing;
    private Boolean isAnduril;
    private Boolean isTreeStump;
    private Random random;
    /**
     * Collected Rare ring during game
     */
    private Boolean respawnWhenDead;
    /**
     * Goal specified in Json
     */
    private Goal Goals;
    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        heroCastle = null;
        basicEnemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingsList = new ArrayList<>();

        this.Gold = 1000;
        this.Exp = 0;
        this.roundNumber = 1;
        defeatedAllBosses = false;
        this.healthPotionNumber = 0;
        this.random = new Random();
        isRing = false;
        isAnduril = false;
        isTreeStump = false;
        respawnWhenDead = false;
        boss = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Fetch list of buildings with specified type name
     * @return resultBuildingList
     */
    public List<Building> getBuildingListByName(String name) {
        List<Building> resultBuildingList = new ArrayList<Building>();
        for (Building building : buildingsList) {
            if (building.getName().equals(name)) {
                resultBuildingList.add(building);
            }
        }
        return resultBuildingList;
    }

    public int getRound() {
        return roundNumber;
    }
    
    public int getHealth() {
        return character.getHealth();
    }

    /**
     * Attatch controller to character
     * Observer
     * @param lc
     */
    public void CharacterAttach(LoopManiaWorldController lc) {
        character.attach(lc);
    }

    public int getGold() {
        return Gold;
    }

    public int getXP() {
        return Exp;
    }

    public int getAlliesNumber() {
        return character.getSizeofAlly();
    }


    public int getHealthPotionNumber() {
        return healthPotionNumber;
    }

    public void setDefeatedAllBosses() {
        this.defeatedAllBosses = true;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param c the character
     */
    public void setCharacter(Character c) {
        this.character = c;
    }

    /**
     * set the Hero Castle. This is necessary because it is loaded as a special entity out of the file
     * @param heroCatle the Hero Castle
     */
    public void setHeroCastle(HeroCastleBuilding heroCatle) {
        this.heroCastle = heroCatle;
    }

    /**
     * Set the world with specified rare item.
     */
    public void setRareItemByName(String name) {
        if (name.equals("THEONERING")) this.isRing = true;
        if (name.equals(Anduril.ANDURIL)) this.isAnduril = true;
        if (name.equals(TreeStump.TREESTUMP)) this.isTreeStump = true;
    }

    /**
     * Store set goals
     * @param goals
     */
    public void setGoals(Goal goals) {
        this.Goals = goals;
    }

    public Boolean isGoalCompleted() {
        if (Goals == null) {
            return false;
        }
        return Goals.isGoalCompleted(Gold, Exp, roundNumber, defeatedAllBosses);
    }

    /**
     * There is a 1/100 change to gain The One Ring
     * If chance made, add ring to game
     * @return gained the ring
     */
    public Boolean rareRingSpawned() {
        if (isRing == true) {
            if (random.nextInt(1000) == 0) {
                this.respawnWhenDead = true;
                this.isRing = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is a chance winning a Anduril sword
     * If can, remove future chance of winning and return true
     * @return whether Anduril sword spawned
     */
    public Boolean andurilSpawned() {
        if (isAnduril == true) {
            if (random.nextInt(1000) == 0) {
                this.isAnduril = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is a chance winning a Tree Stump shield
     * If can, remove future chance of winning and return true
     * @return whether Tree Stump spawned
     */
    public Boolean treeStumpSpawned() {
        if (isTreeStump == true) {
            if (random.nextInt(1000) == 0) {
                this.isTreeStump = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Respawn character
     */
    public void respawnCharacter() {
        character.setFullHealth();
        respawnWhenDead = false;
    }

    /**
     * Update Round Number if Hero Castle reached. This is necessary because it is used to determine
     * 
     */
    public void updateRound() {
        this.roundNumber++;
        // Observer: update vampire list
    }

    /**
     * Add X amount to Exp
     * @param additionExp
     */
    public void addToExp(int additionExp) {
        this.Exp += additionExp;
    }

    /**
     * Add X amount to Gold
     * @param additionGold
     */
    public void addToGold(int additionGold) {
        this.Gold += additionGold;
    }

    /**
     * Add X amount to Health
     * @param additionHealth
     */
    public void addToHealth(int additionHealth) {
        character.addtoHealth(additionHealth);
    }
    
    /**
     * Check if character can revive
     * 
     */
    public Boolean canRespawn() {
        return respawnWhenDead;
    }

    /**
     * Check if character in castle
     * 
     */
    public Boolean isHeroCastle() {
        int indexPositionCharacter = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
        int indexPositionHeroCastle = orderedPath.indexOf(new Pair<Integer, Integer>(heroCastle.getX(), heroCastle.getY()));
        if (indexPositionCharacter == indexPositionHeroCastle) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the given position is a path tile
     * @param x 
     * @param y
     */
    public Boolean isPath(Integer x, Integer y) {
        if (x < 0 || y < 0) {
            return false;
        }
        return orderedPath.contains(new Pair<Integer, Integer>(x, y));
    }

    /**
     * Check if character new position on Barracks
     * @return
     */
    public Building isGiveBuildingType(String name) {
        Building foundBuilding = isOccupied(character.getX(), character.getY());
        if (foundBuilding != null && foundBuilding.getName().equals(name)) {
            return foundBuilding;
        }
        return null;
    }

    /**
     * Check if given position is occupied by a building
     * @param x
     * @param y
     * @return
     */
    public Building isOccupied(Integer x, Integer y) {
        Building possibleBuilding = getBuildingByCoord(x, y);
        if (heroCastle != null && heroCastle.getX() == x && heroCastle.getY() == y) {
            return heroCastle;
        } else if (possibleBuilding != null) {
            return possibleBuilding;
        }
        return null;
    }

    /**
     * Check if there is a builing on this position
     * @param buildinlist
     * @param x
     * @param y
     * @return
     */
    public Building getBuildingByCoord(int x, int y) {
        for (Building building : buildingsList) {
            if (building.getX() == x && building.getY() == y) {
                return building;
            }
        }
        return null;
    }

    /**
     * Determine if the given position is a non-path tiles adjacent to the path
     * @param x 
     * @param y
     */
    public Boolean isAdjacentPath(Integer x, Integer y) {
        if (!isPath(x, y)) {
            if (isPath(x-1, y) || isPath(x+1, y) || isPath(x-1, y-1) || 
                isPath(x-1, y+1) || isPath(x+1, y-1) || isPath(x+1, y+1) || 
                isPath(x, y-1) || isPath(x, y+1)
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if equipped Weapon
     * 
     */
    public StaticEntity getEquippedWeapon() {
        return character.getEquipedWeapon();
    }

    /**
     * Determine if equipped Armour
     * 
     */
    public StaticEntity getEquippedArmour() {
        for (StaticEntity e: character.getEquipedItems()){
            if (e.getName().equals(Armour.ARMOUR)){
                return e;
            }
        }
        return null;
    }

    /**
     * Determine if equipped Shield
     * 
     */
    public StaticEntity getEquippedShield() {
        for (StaticEntity e: character.getEquipedItems()){
            if (e.getName().equals(Shield.SHIELD) || e.getName().equals(TreeStump.TREESTUMP)){
                return e;
            }
        }
        return null;
    }

    /**
     * Determine if equipped Helmet
     * 
     */
    public StaticEntity getEquippedHelmet() {
        for (StaticEntity e: character.getEquipedItems()){
            if (e.getName().equals(Helmet.HELMET)){
                return e;
            }
        }
        return null;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        nonSpecifiedEntities.add(entity);
    }


    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void equipItem(StaticEntity item) {
        character.setEquipedItems(item);
    }


    /**
     * spawns basic enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        
        List<Pair<Integer, Integer>> posSlug = possiblyGetEnemySpawnPosition("SLUG");
        List<Pair<Integer, Integer>> posVampire = possiblyGetEnemySpawnPosition("VAMPIRE");
        List<Pair<Integer, Integer>> posZombie = possiblyGetEnemySpawnPosition("ZOMBIE");
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (posSlug.size() != 0){
            for (Pair<Integer, Integer> ppos : posSlug){
                if (ppos != null) {
                    int indexInPath = orderedPath.indexOf(ppos);
                    Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
                    basicEnemies.add(enemy);
                    spawningEnemies.add(enemy);
                }
            }
        }
        if (posVampire.size() != 0){
            for (Pair<Integer, Integer> ppos : posVampire){
                if (ppos != null) {
                    int indexInPath = orderedPath.indexOf(ppos);
                    Vampire enemy = new Vampire(new PathPosition(indexInPath, orderedPath));
                    basicEnemies.add(enemy);
                    spawningEnemies.add(enemy);
                }
            }
        }
        if (posZombie.size() != 0){
            for (Pair<Integer, Integer> ppos : posZombie){
                if (ppos != null) {
                    int indexInPath = orderedPath.indexOf(ppos);
                    Zombie enemy = new Zombie(new PathPosition(indexInPath, orderedPath));
                    basicEnemies.add(enemy);
                    spawningEnemies.add(enemy);
                }
            }
        }
        return spawningEnemies;
    }


    /**
     * spawns Bosses if the conditions warrant it, adds to world
     * @return list of the bosses to be displayed on screen
     */
    public Boss possiblySpawnBosses() {
        List<Pair<Integer, Integer>> posBasilisk = possiblyGetEnemySpawnPosition("BASILISK");
        List<Pair<Integer, Integer>> posWitch = possiblyGetEnemySpawnPosition("WITCH");
        if (posBasilisk.size() != 0){
            int indexInPath = orderedPath.indexOf(posBasilisk.get(0));
            Basilisk boss = new Basilisk(new PathPosition(indexInPath, orderedPath));
            this.boss = boss;
            return boss;
        }
        if (posWitch.size() != 0){
            int indexInPath = orderedPath.indexOf(posWitch.get(0));
            Witch boss = new Witch(new PathPosition(indexInPath, orderedPath));
            this.boss = boss;
            return boss;
        }
        return null;
    }
    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(Enemy enemy) {
        enemy.destroy();
        basicEnemies.remove(enemy);
    }

    /**
     * Given enemy, check if character in its radius
     * @param e
     * @param radius
     * @return
     */
    private boolean checkRadius(Enemy e, int radius) {
        if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < Math.pow(radius,2)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Given buildind, check if character in its radius
     * @param e
     * @param radius
     * @return
     */
    private boolean checkCampfireRadius() {
        for (Building building : getBuildingListByName(CampfireBuilding.CAMPFIRE)) {
            CampfireBuilding campfire = (CampfireBuilding) building;
            if (Math.pow((character.getX()-building.getX()), 2) +  Math.pow((character.getY()-building.getY()), 2) < Math.pow(campfire.getRadius(),2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if tower should join the battle
     * @return List of tower building that is involved in battle
     */
    private List<Building> checkTower() {
        List<Building> involvedTowers = new ArrayList <Building>();
        List<Building> towerBuildingsList = getBuildingListByName(TowerBuilding.TOWER);
        for(Building tb: towerBuildingsList){
            TowerBuilding thisTower = (TowerBuilding) tb;
            if (Math.pow((tb.getX()-character.getX()), 2) +  Math.pow((tb.getY()-character.getY()), 2) < Math.pow(thisTower.getShootingRadius(),2)){
                involvedTowers.add(tb);
            }
        }
        return involvedTowers;
    }

    /**
     * Run actual battle
     * @param battleEnemy
     * @param supportEnemy
     * @return
     */
    public List<Enemy> actualBattle(List<Enemy> battleEnemy, List<Enemy> supportEnemy) {
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        List<Building> towers = checkTower();

        if(battleEnemy.isEmpty() == true){
            return defeatedEnemies;
        }
        Enemy enemy1 = battleEnemy.get(0);
        int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(enemy1.getX(), enemy1.getY()));

        battleEnemy.addAll(supportEnemy);

        List<Enemy> test = new ArrayList<Enemy>();
        while((character.getHealth() != 0) && (battleEnemy.isEmpty() == false) ){
            for(Enemy e: battleEnemy){
                e.attack(character);
            }
            List<AlliedSoldier> deadSoldier = new ArrayList<AlliedSoldier>();
            for(AlliedSoldier as : character.getAlly()){
                if(as.getIfInfected() == true){
                    test.add(new Zombie(new PathPosition(indexPosition,orderedPath)));
                }
                character.removeListofAlly(deadSoldier);
            }
            battleEnemy.addAll(test);
            //after a battle, all temply tranced enemies removed from alliedsoldier list
            character.BasicAttackEnemy(battleEnemy, checkCampfireRadius());

            //add original ally to list
            List<AlliedSoldier> remainSoldier = new ArrayList<AlliedSoldier>();
            for (AlliedSoldier a: character.getAlly()) {
                if (a.getBecome() == false) {
                    remainSoldier.add(a);
                    //character.getAlly().remove(a);
                }
            }
            character.setAlly(remainSoldier);

            //add back tranced enmey to enmey list
            List<BasicEnemy> rt2Enemy = new ArrayList<BasicEnemy>();
            for (AlliedSoldier a: character.getAlly()) {
                if (a.getBecome() == true) {
                    String enemyType = a.getAllyType();
                    if (enemyType == "SLUG") {
                        rt2Enemy.add(new Slug(new PathPosition(indexPosition,orderedPath)));
                    } else if (enemyType == "VAMPIRE") {
                        rt2Enemy.add(new Vampire(new PathPosition(indexPosition,orderedPath)));
                    } else if (enemyType == "ZOMBIE") {
                        rt2Enemy.add(new Zombie(new PathPosition(indexPosition,orderedPath)));
                    }
                }
            }
            battleEnemy.addAll(rt2Enemy);

            if(towers.isEmpty() == false){
                for(Building tb: towers){
                    TowerBuilding thisTower = (TowerBuilding) tb;
                    thisTower.attack(battleEnemy);
                }
            }

            List<Enemy> ToRemove = new ArrayList<Enemy>();
            for(Enemy e: battleEnemy){
                if(e.getHealth() <= 0){
                    if (e instanceof Basilisk) {
                        defeatedAllBosses = true;
                    }
                    if (e instanceof Witch) {
                        Witch bossW = (Witch) boss;
                        bossW.setBackEnemyMoveBehaviour(basicEnemies, orderedPath);
                    }
                    if (e instanceof Boss) {
                        boss = null;
                    }
                    defeatedEnemies.add(e);
                    ToRemove.add(e);
                }
            }
            battleEnemy.removeAll(ToRemove);
        }
        return defeatedEnemies;
    }

    /**
     * Remove Gold Pile and add value to Gold
     * @param goldPile
     */
    public void collectGoldPile(Building goldPile){
        goldPile.destroy();
        buildingsList.remove(goldPile);
        addToGold(random.nextInt(11) * 10 + 300);
    }

    /**
     * Remove Health Potion and add to collection
     * @param potion
     */
    public void collectHealthPotion(Building potion) {
        potion.destroy();
        buildingsList.remove(potion);
        loadHealthPotion();
    }

    /**
     * Remove village and regain health
     * @param Village
     */
    public void collectVillageHealth(Building village) {
        village.destroy();
        buildingsList.remove(village);
        addToHealth(30);
    }

    /**
     * Remove barracks and spawn allied soldier
     * @param barracks
     */
    public void spawnAlliedSoldier(Building barracks) {
        barracks.destroy();
        buildingsList.remove(barracks);
        character.addAlly(new AlliedSoldier());
    }

    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    public List<Enemy> runBattles() {
        List<Enemy> Enemies = new ArrayList<Enemy>();
        List<Enemy> BattledEnemies = new ArrayList<Enemy>();
        List<Enemy> SupportedEnemies = new ArrayList<Enemy>();
        boolean isBoss = false;
        Enemies.addAll(basicEnemies);

        if (boss != null) {
            Enemies.add(boss);
        }
        for (Enemy e: Enemies){
            if (checkRadius(e,e.getBattleRadius()) == true) {
                // fight...
                if (e instanceof Boss) {
                    isBoss = true;
                }
                BattledEnemies.add(e);

            } else if (checkRadius(e,e.getSupportRadius()) == true) {
                SupportedEnemies.add(e);
            }
        }
        if (isBoss == true && boss instanceof Witch) {
            Witch witch = (Witch) boss;
            witch.setEnemyAttackBehaviour(Enemies);
        }
        List<Enemy> defeatedEnemies = actualBattle(BattledEnemies, SupportedEnemies);

        for (Enemy e: defeatedEnemies){
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        //Popup.start();
        return defeatedEnemies;
    }


    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card loadCardByName(String type) {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            addToGold(500);
            addToExp(200);
            removeCard(0);
        }
        Card returnCard = cardFactory.getCard(type, cardEntities.size());

        if (returnCard != null) {
            cardEntities.add(returnCard);
        }
        return returnCard;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index) {
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * Remove currently equipped weapon
     */
    public void removeReplacedWeapon() {
        StaticEntity item = getEquippedWeapon();
        item.destroy();
        character.removeItem(item);
    }

    /**
     * Remove currently equipped armour
     */
    public void removeReplacedArmour() {
        StaticEntity item = getEquippedArmour();
        item.destroy();
        character.removeItem(item);
    }

    /**
     * Remove currently equipped shield
     */
    public void removeReplacedShield() {
        StaticEntity item = getEquippedShield();
        item.destroy();
        character.removeItem(item);
    }

    /**
     * Remove currently equipped helmet
     */
    public void removeReplacedHelmet() {
        StaticEntity item = getEquippedHelmet();
        item.destroy();
        character.removeItem(item);
    }
    
    
    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        character.moveDownPath();
        move();

    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void move() {
        for (BasicEnemy e: basicEnemies){
            e.move(this);
        }
        if (boss != null) {
            boss.move(this);
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private List<Pair<Integer, Integer>> randomSpawnOnPath(){
        List<Pair<Integer, Integer>> possiblyEnemySpawnPosition = new ArrayList<Pair<Integer, Integer>>();
        Random rand = new Random();
        
        List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
        int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
        // inclusive start and exclusive end of range of positions not allowed
        int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
        int endNotAllowed = (indexPosition + 3)%orderedPath.size();
        // note terminating condition has to be != rather than < since wrap around...
        for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()) {
            orderedPathSpawnCandidates.add(orderedPath.get(i));
        }

        for (Pair<Integer, Integer> sp : orderedPathSpawnCandidates){
                // choose random choice
            sp = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));
            if (possiblyEnemySpawnPosition.size() < 3) {
                possiblyEnemySpawnPosition.add(sp);
            }
        }
        return possiblyEnemySpawnPosition;
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private List<Pair<Integer, Integer>> possiblyGetEnemySpawnPosition(String name){
        List<Pair<Integer, Integer>> possiblyEnemySpawnPosition = new ArrayList<Pair<Integer, Integer>>();
        if (name.equals("SLUG")) {
            possiblyEnemySpawnPosition = randomSpawnOnPath();
        } else if (name.equals("VAMPIRE") && roundNumber % 5 == 0) {
            List<Building> vampireBuilding = getBuildingListByName("VAMPIRECASTLE");
            for (Building b: vampireBuilding){
                VampireCastleBuilding  v = (VampireCastleBuilding) b;
                Pair<Integer, Integer> pos = v.spawnEnemy(orderedPath);
                possiblyEnemySpawnPosition.add(pos);
            }
        } else if (name.equals("ZOMBIE")) {
            List<Building> zombieBuilding = getBuildingListByName("ZOMBIEPIT");
            for (Building b: zombieBuilding) {
                ZombiePit z = (ZombiePit) b;
                Pair<Integer, Integer> pos = z.spawnEnemy(orderedPath);
                possiblyEnemySpawnPosition.add(pos);
            }
        } else if (name.equals("BASILISK") && roundNumber == 40 && Exp >= 10000) {
            possiblyEnemySpawnPosition.add(orderedPath.get(orderedPath.size()/2));
        } else if (name.equals("WITCH") && roundNumber == 20) {
            possiblyEnemySpawnPosition.add(orderedPath.get(orderedPath.size()/3*2));
        }

        return possiblyEnemySpawnPosition;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        if (card == null) {
            return null;
        }
        Building newBuilding = card.getBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        // now spawn building
        //VampireCastleBuilding newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        buildingsList.add(newBuilding);

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }
    /**
     * spawn an item in the world and return the item entity
     * @return an item to be spawned in the controller as a JavaFX node
     */
    public StaticEntity loadItemByName(String type) {       
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
            addToGold(500);
            addToExp(200);
        }
        //todp: change the copied
        StaticEntity returnItem;
        if (type.equals(Sword.SWORD)) {
            returnItem = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Stake.STAKE)) {
            returnItem = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Staff.STAFF)) {
            returnItem = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Shield.SHIELD)) {
            returnItem = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Armour.ARMOUR)) {
            returnItem = new Armour(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Helmet.HELMET)) {
            returnItem = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(Anduril.ANDURIL)) {
            returnItem = new Anduril(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else if (type.equals(TreeStump.TREESTUMP)) {
            returnItem = new TreeStump(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            unequippedInventoryItems.add(returnItem);
        } else {
            returnItem = null;
        }
        return returnItem;
    }

    /**
     * spawn a health potion in the world 
     */
    public void loadHealthPotion() {
        healthPotionNumber++;   
    }

    /**
     * Use a health potion in the world 
     */
    public void consumeHealthPotion() {
        character.addtoHealth((int) (Character.FULLHEALTH * 0.3));
        healthPotionNumber--;   
    }
    /**
     * enable/disable cheat 
     */
    public void worldEnableCheat() {
        character.enableCheat();
    }
    public void worldDisableCheat() {
        character.disableCheat();
    }

    /**
     * If the player has a health lower than 100, 
     * every 3rd round there is a 50% chance a health potion will land in on a a path.
     * @return boolean
     */
    public Boolean needHealthPotion() {
        if (character.getHealth() < 100 && roundNumber % 3 == 0 && random.nextInt(2) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Every round there is a 100% chance a gold pile will land in on a a path.
     * @return boolean
     */
    public Boolean randomGoldPile() {
        if (random.nextInt(100) < 5) {
            return true;
        }
        return false;
    }

    /**
     * Spawn a health potion building if there is free slot
     * @return
     */
    public Building spawnNewHealthPotionBuilding() {
        for (int i=0; i!=orderedPath.size(); i++){
            if (isOccupied(orderedPath.get(i).getValue0(), orderedPath.get(i).getValue1()) == null) {
                HealthPotionBuilding building = new HealthPotionBuilding(new SimpleIntegerProperty(orderedPath.get(i).getValue0()), new SimpleIntegerProperty(orderedPath.get(i).getValue1()));
                buildingsList.add(building);
                return building;
            }
        }
        return null;
    }

    /**
     * Spawn a health potion building if there is free slot
     * @return
     */
    public Building spawnNewGoldPile() {
        for (int i=0; i!=orderedPath.size(); i++){
            if (isOccupied(orderedPath.get(i).getValue0(), orderedPath.get(i).getValue1()) == null) {
                GoldPileBuilding building = new GoldPileBuilding(new SimpleIntegerProperty(orderedPath.get(i).getValue0()), new SimpleIntegerProperty(orderedPath.get(i).getValue1()));
                buildingsList.add(building);
                return building;
            }
        }
        return null;
    }

    /**
     * Apply damage to enemies on trap
     */
    public void trapDamage() {
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        List<Building> removeTrapList = new ArrayList<Building>();
        for (Building trap : getBuildingListByName(TrapBuilding.TRAP)) {
            for (BasicEnemy e: basicEnemies) {
                if (trap.getX() == e.getX() && trap.getY() == e.getY()) {
                    e.loseHealth(20);
                    if (e.getHealth() < 0) {
                        defeatedEnemies.add(e);
                    }
                    if (!removeTrapList.contains(trap)){removeTrapList.add(trap);}
                }
            }
            if (boss != null && trap.getX() == boss.getX() && trap.getY() == boss.getY()) {
                boss.loseHealth(20);
                if (boss.getHealth() < 0) {
                    this.boss = null;
                }
                if (!removeTrapList.contains(trap)){removeTrapList.add(trap);}
            }
        }

        for (Enemy e: defeatedEnemies) {
            killEnemy(e);
        }
        for (Building trap : removeTrapList) {
            trap.destroy();
        }
        buildingsList.removeAll(removeTrapList);
    }

    public boolean isFirstRound() {
        if (roundNumber == 1 && orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY())) == 1) {
            return true;
        }
        return false;
    }

    public void applyPossibleGasAttack() {
        if (boss != null && boss instanceof Basilisk) {
            Basilisk basilisk = (Basilisk) boss;
            if (checkRadius(basilisk, basilisk.getGasRadius())) {
                character.loseHealth(3);
            }
        }
    }

    public void setEnemiesMoveTowardWitch() {
        Witch bossW = (Witch) boss;
        bossW.setEnemyMoveBehaviour(basicEnemies);
    }

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return orderedPath;
    }

    public int getBossPosition() {
        return orderedPath.indexOf(new Pair<>(boss.getX(), boss.getY()));
    }
}
