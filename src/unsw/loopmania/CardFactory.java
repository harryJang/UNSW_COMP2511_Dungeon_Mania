package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class CardFactory {
    public Card getCard(String cardType, int cardEntitySize) {
        if (cardType.equals(VampireCastleCard.VAMPIRECASTLECARD)) {
            return new VampireCastleCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(TowerCard.TOWERCARD)) {
            return new TowerCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(BarracksCard.BARRACKSCARD)) {
            return new BarracksCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(TrapCard.TRAPCARD)) {
            return new TrapCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(CampfireCard.CAMPFIRECARD)) {
            return new CampfireCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(ZombiePitCard.ZOMBIEPITCARD)) {
            return new ZombiePitCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else if (cardType.equals(VillageCard.VILLAGECARD)) {
            return new VillageCard(new SimpleIntegerProperty(cardEntitySize), new SimpleIntegerProperty(0));
        } else {
            return null;
        }
    }
}
