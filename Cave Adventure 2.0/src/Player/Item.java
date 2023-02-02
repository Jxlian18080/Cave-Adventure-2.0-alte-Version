package Player;

import java.util.Random;
import java.util.Scanner;

import Enemy.Enemy;

public class Item {

    private String name;

    private int location;

    private double weight;

    private boolean playerHasItem;

    private int attackDamage;

    private int chanceCriticalHit;

    private Scanner scan = new Scanner(System.in);

    private Random randomNumber = new Random();

    private StringBuffer sb = new StringBuffer();

    private int counter;

    private int counter2;

    private boolean[] regenerationEffect = new boolean[18];

    private boolean[] wizardRegenerationEffect = new boolean[9];

    private boolean[] poisonEffect = new boolean[4];

    public Item(String name, int location, double weight, boolean playerHasItem, int attackDamage, int chanceCriticalHit) {
        this.name = name;
        this.location = location;
        this.weight = weight;
        this.playerHasItem = playerHasItem;
        this.attackDamage = attackDamage;
        this.chanceCriticalHit = chanceCriticalHit;
    }

    public Item() {

    }

    public int itemGetLocation(boolean[] positionIsBlocked) {
        int a = 0;

        while (a == 0) {

            location = 1 + randomNumber.nextInt(49);

            if (!(location == 25 || positionIsBlocked[location] == true)) {
                a++;
                positionIsBlocked[location] = true;

            }
        }
        return location;
    }

    public void foundItem(Player player, Item[] items) {

        int quantityItems = 0;
        int currentString = 0;

        for (int i = 0; i <= 38; i++) {

            if (player.getPlayerLocation() == items[i].location && items[i].playerHasItem == false) {

                quantityItems++;

            }
        }

        switch (quantityItems) {

        case 0:

            break;

        case 1:

            for (int i = 0; i <= 38; i++) {

                if (player.getPlayerLocation() == items[i].location && items[i].playerHasItem == false) {
                    System.out.println("There is a " + items[i].name + " on the floor");
                }
            }
            break;

        default:

            for (int i = 0; i <= 38; i++) {

                if (player.getPlayerLocation() == items[i].location && items[i].playerHasItem == false) {

                    if (currentString == 0) {
                        sb.append(items[i].name);
                        currentString++;
                    } else if (currentString + 1 == quantityItems) {
                        sb.append(" and a " + items[i].name);
                        currentString++;
                    } else {
                        sb.append(", a " + items[i].name);
                        currentString++;
                    }
                }
            }
            System.out.println("There is a " + sb + " on the floor");
            break;
        }

    }

    public void pickUpItem(Player player, Item[] items) {

        counter = 0;
        counter2 = 0;

        boolean[] itemOnTheFloor = new boolean[40];

        for (int i = 0; i <= 39; i++) {

            if (player.getPlayerLocation() == items[i].location && items[i].playerHasItem == false) {
                counter++;
                itemOnTheFloor[i] = true;
            }
        }
        switch (counter) {

        case 0:
            System.out.println("There is nothing you can pick up");
            break;

        case 1:
            for (int i = 0; i <= 39; i++) {
                if (itemOnTheFloor[i] == true) {
                    if (player.getBackpackMaxWeight() >= player.getBackpackWeight() + items[i].weight) {
                        items[i].playerHasItem = true;
                        System.out.println("You picked up the " + items[i].name);
                        player.setBackpackWeight(player.getBackpackWeight() + items[i].weight);
                        break;
                    } else {
                        System.out.println("The item weighs too much. Put an other item down to pick up this item");
                        break;
                    }
                }
            }
            break;

        default:
            System.out.println("Which item do you want to pick up?");

            boolean endMethod = false;
            while (endMethod == false) {
                String userInput = scan.nextLine().toLowerCase();

                Switch:
                switch (userInput) {

                case "key":
                    for (int i = 0; i <= 2; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 3);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "map":
                    for (int i = 3; i <= 9; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 7);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "stick":
                    endMethod = checkItemIsOnTheFloor(player, 10, items, 1);
                    if (endMethod == true) {
                        break;
                    }
                    break;
                case "small axe":
                    for (int i = 11; i <= 18; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 8);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "normal axe":
                    for (int i = 19; i <= 25; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 7);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "heavy axe":
                    for (int i = 25; i <= 29; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 4);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "regeneration potion":
                    for (int i = 30; i <= 32; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 3);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "heal potion":
                    for (int i = 33; i <= 35; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 3);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "mystery potion":
                    for (int i = 36; i <= 38; i++) {
                        endMethod = checkItemIsOnTheFloor(player, i, items, 3);
                        if (endMethod == true) {
                            break;
                        }
                    }
                    break;
                case "legendary sword":
                    endMethod = checkItemIsOnTheFloor(player, 39, items, 1);
                    if (endMethod == true) {
                        break;
                    }
                    break;

                case "show backpack":
                    player.showBackpack(items);
                    break Switch;

                case "nothing":
                    System.out.println("You picked nothing up");
                    endMethod = true;
                    break Switch;

                default:
                    System.out.println("This isn't an item");
                    break Switch;

                }
            }
            break;
        }
    }

    public boolean checkItemIsOnTheFloor(Player player, int itemNumber, Item[] items, int itemQuantity) {
        boolean endMethod = false;

        if (items[itemNumber].location == player.getPlayerLocation() && items[itemNumber].playerHasItem == false) {
            if (player.getBackpackMaxWeight() >= player.getBackpackWeight() + items[itemNumber].weight) {
                System.out.println("You picked up the " + items[itemNumber].name);
                endMethod = true;
                items[itemNumber].playerHasItem = true;
                player.setBackpackWeight(player.getBackpackWeight() + items[itemNumber].weight);
            } else {
                System.out.println("The item weighs too much. Put an other item down to pick up this item");
                endMethod = true;
            }

        } else {
            counter2++;
            if (counter2 == itemQuantity) {
                System.out.println("This item isn't on the floor");
            }
        }
        return endMethod;

    }

    public void putDown(Player player, Item[] items) {

        counter = 0;
        boolean endMethod = false;

        System.out.println("Which item do you want to put down?");
        while (endMethod == false) {
            String userInput = scan.nextLine().toLowerCase();

            switch (userInput) {

            case "key":
                for (int i = 0; i <= 2; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 3);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "map":
                for (int i = 3; i <= 9; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 7);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "stick":
                for (int i = 10; i == 10; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 1);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;

            case "small axe":
                for (int i = 11; i <= 18; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 8);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "normal axe":
                for (int i = 19; i <= 25; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 7);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "heavy axe":
                for (int i = 26; i <= 29; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 4);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "regeneration potion":
                for (int i = 30; i <= 32; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 3);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "heal potion":
                for (int i = 33; i <= 35; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 3);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "mystery potion":
                for (int i = 36; i <= 38; i++) {
                    endMethod = checkPlayerHasItem(player, i, items, 3);
                    if (endMethod == true) {
                        break;
                    }
                }
                break;
            case "show backpack":
                player.showBackpack(items);
                break;

            default:
                System.out.println("This isn't an item");
                break;

            }
        }
    }

    public boolean checkPlayerHasItem(Player player, int itemNumber, Item[] items, int itemQuantity) {
        boolean endMethod = false;

        if (items[itemNumber].location == player.getPlayerLocation() && items[itemNumber].playerHasItem == true) {
            System.out.println("You put the " + items[itemNumber].name + " down");
            endMethod = true;
            items[itemNumber].playerHasItem = false;

            player.setBackpackWeight(player.getBackpackWeight() - items[itemNumber].weight);

        } else {
            counter++;
            if (counter == itemQuantity) {
                System.out.println("This item isn't in your backpack. Choose another item");
            }
        }
        return endMethod;

    }

    public void showMap(int playerLocation, boolean[] playerWasThere, int[] doorLocation, boolean playerFoundDoor[], Door door,
            Item[] items, Player player) {

        boolean doOutput = true;
        int number = randomNumber.nextInt(5);
        int i;

        outer:
        for (i = 3; i <= 9; i++) {

            if (items[i].playerHasItem == true) {

                for (int a = 1; a <= 49; a++) {

                    inner:
                    for (int b = 0; b == 0; b++) {
                        int calculation = 0;
                        if (playerLocation == a) {
                            calculation = a % 7;
                            if (calculation == 0) {
                                System.out.println("P");
                                System.out.println("");
                                break inner;
                            } else {
                                System.out.print("P   ");
                                break inner;
                            }
                        }
                        if (doorLocation[0] == a && door.getPlayerFoundDoor()[0] == true || doorLocation[1] == a && door
                                .getPlayerFoundDoor()[1] == true || doorLocation[2] == a && door.getPlayerFoundDoor()[2] == true) {
                            calculation = a % 7;
                            if (calculation == 0) {
                                System.out.println("D");
                                System.out.println("");
                                break inner;
                            } else {
                                System.out.print("D   ");
                                break inner;
                            }
                        }

                        if (playerWasThere[a] == true && playerLocation != a) {
                            calculation = a % 7;
                            if (calculation == 0) {
                                System.out.println("X");
                                System.out.println("");
                                break inner;
                            } else {
                                System.out.print("X   ");
                                break inner;
                            }

                        }
                        if (playerLocation != a) {
                            calculation = a % 7;
                            if (calculation == 0) {
                                System.out.println("O");
                                System.out.println("");
                                break inner;
                            } else {
                                System.out.print("O   ");
                                break inner;
                            }
                        }
                    }
                }
                doOutput = false;
                break outer;
            }
        }
        if (doOutput == true) {
            System.out.println("You don't have a map");
        } else if (number == 0) {
            items[i].playerHasItem = false;
            items[i].location = 0;
            player.setBackpackWeight(player.getBackpackWeight() - items[i].weight);
            System.out.println("The map broke while you using it");
        }
    }

    public void usePotion(Player player, Item[] items) {
        counter = 0;
        int effect;
        boolean potionUse = false;
        boolean playerHasPotion = false;

        for (int i = 30; i <= 38; i++) {
            if (items[i].playerHasItem == true) {
                playerHasPotion = true;
            }
        }

        if (playerHasPotion == true) {

            System.out.println("Which potion do you want to use?");

            while (potionUse == false) {
                String userInput = scan.nextLine().toLowerCase();
                switch (userInput) {

                case "regeneration potion":
                    loop:
                    for (int i = 30; i <= 32; i++) {

                        potionUse = checkPlayerHasPotion(player, items, i, potionUse);
                        if (potionUse == true) {
                            regenerationEffect[i - 30] = true;
                            regenerationEffect[i - 24] = true;
                            regenerationEffect[i - 18] = true;

                            break loop;
                        }
                    }
                    break;

                case "heal potion":
                    loop:
                    for (int i = 33; i <= 35; i++) {

                        potionUse = checkPlayerHasPotion(player, items, i, potionUse);
                        if (potionUse == true) {
                            player.setPlayerHP(player.getPlayerHP() + 20);
                            if (player.getPlayerHP() >= 100) {
                                player.setPlayerHP(100);
                            }
                            break loop;
                        }
                    }
                    break;

                case "mystery potion":
                    loop:
                    for (int i = 36; i <= 38; i++) {

                        potionUse = checkPlayerHasPotion(player, items, i, potionUse);
                        if (potionUse == true) {

                            effect = 4; // + randomNumber.nextInt(4); // 1 = heal, 2 = regeneration, 3 = damage, 4 = poison
                            switch (effect) {

                            case 1:

                                player.setPlayerHP(player.getPlayerHP() + 20);
                                System.out.println("You got 20 hp");
                                if (player.getPlayerHP() >= 100) {
                                    player.setPlayerHP(100);
                                }
                                break;

                            case 2:

                                regenerationEffect[i - 30] = true;
                                regenerationEffect[i - 24] = true;
                                regenerationEffect[i - 18] = true;
                                System.out.println("You got the regeneration effect");
                                break;

                            case 3:

                                player.setPlayerHP(player.getPlayerHP() - 15);
                                System.out.println("You lose 15 hp");
                                break;

                            case 4:

                                poisonEffect[i - 36] = true;
                                System.out.println("You got the poison effect");
                                break;

                            }

                            break loop;
                        }
                    }
                    break;

                case "show backpack":
                    player.showBackpack(items);
                    break;

                case "no one":
                    System.out.println("You didn't use a potion");
                    potionUse = true;

                    break;

                default:

                    System.out.println("This isn't a potion");

                    break;
                }
            }
        } else {
            System.out.println("You don't have any potions");
        }
    }

    public boolean checkPlayerHasPotion(Player player, Item[] items, int i, boolean potionUse) {

        if (items[i].playerHasItem == true) {

            potionUse = true;
            items[i].playerHasItem = false;
            items[i].location = 0;
            System.out.println("You use a " + items[i].getName());
            player.setBackpackWeight(player.getBackpackWeight() - items[i].getWeight());
        } else {
            counter++;
        }
        if (counter == 3) {
            System.out.println("You don't have a " + items[i].getName());
            counter = 0;
        }
        return potionUse;

    }

    public void doRegeneration(Player player) {
        counter = 0;

        for (int i = 0; i <= 5; i++) {

            if (regenerationEffect[i] == true) {
                if (regenerationEffect[i + 6] == true) {
                    if (regenerationEffect[i + 12] == true) {
                        player.setPlayerHP(player.getPlayerHP() + 10);
                        if (player.getPlayerHP() >= 100) {
                            player.setPlayerHP(100);
                        }
                        regenerationEffect[i + 12] = false;
                        counter++;
                    } else {
                        player.setPlayerHP(player.getPlayerHP() + 10);
                        if (player.getPlayerHP() >= 100) {
                            player.setPlayerHP(100);
                        }
                        regenerationEffect[i + 6] = false;
                        counter++;
                    }

                } else {
                    player.setPlayerHP(player.getPlayerHP() + 10);
                    if (player.getPlayerHP() >= 100) {
                        player.setPlayerHP(100);
                    }
                    regenerationEffect[i] = false;
                    counter++;
                }
            }
        }

        if (counter >= 1) {
            System.out.println("You got " + counter * 10 + " HP because of the regeneration effect");
        }
    }

    public void wizardRegeneration(Player player, Enemy[] enemies) {
        counter = 0;

        for (int i = 0; i <= 2; i++) {

            if (wizardRegenerationEffect[i] == true) {
                if (wizardRegenerationEffect[i + 3] == true) {
                    if (wizardRegenerationEffect[i + 6] == true) {
                        enemies[i + 7].setHP(enemies[i + 7].getHP() + 10);
                        if (enemies[i + 7].getHP() >= 55) {
                            enemies[i + 7].setHP(55);
                        }
                        wizardRegenerationEffect[i + 6] = false;
                        counter++;
                    } else {
                        enemies[i + 7].setHP(enemies[i + 7].getHP() + 10);
                        if (enemies[i + 7].getHP() >= 55) {
                            enemies[i + 7].setHP(55);
                        }
                        wizardRegenerationEffect[i + 3] = false;
                        counter++;
                    }
                } else {
                    enemies[i + 7].setHP(enemies[i + 7].getHP() + 10);
                    if (enemies[i + 7].getHP() >= 55) {
                        enemies[i + 7].setHP(55);
                    }
                    wizardRegenerationEffect[i] = false;
                    counter++;
                }
            }
        }

        if (counter >= 1 && (enemies[7].getLocation() == player.getPlayerLocation() || enemies[8].getLocation() == player
                .getPlayerLocation() || enemies[9].getLocation() == player.getPlayerLocation())) {
            System.out.println("The wizard get " + counter * 10 + " HP because of the regeneration effect");
        }
    }

    public void doPoison(Player player) {
        counter = 0;
        int number;
        int j = 0;

        for (int i = 0; i <= 3; i++) {
            if (poisonEffect[i] == true) {
                player.setPlayerHP(player.getPlayerHP() - 5);
                counter++;
                j = i;
            }

        }
        number = randomNumber.nextInt(4);
        if (number == 0) {
            poisonEffect[j] = false;
        }

        if (counter >= 1) {
            System.out.println("You lose " + counter * 5 + " HP because of the poison effect");
        }
    }

    public void mapUpdate(boolean[] playerWasThere, int playerLocation) {

        playerWasThere[playerLocation] = true;

    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isPlayerHasItem() {
        return playerHasItem;
    }

    public void setPlayerHasItem(boolean playerHasItem) {
        this.playerHasItem = playerHasItem;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getChanceCriticalHit() {
        return chanceCriticalHit;
    }

    public void setChanceCriticalHit(int chanceCriticalHit) {
        this.chanceCriticalHit = chanceCriticalHit;
    }

    public boolean[] getPoisonEffect() {
        return poisonEffect;
    }

    public void setPoisonEffect(boolean[] poisonEffect) {
        this.poisonEffect = poisonEffect;
    }

    public boolean[] getWizardRegenerationEffect() {
        return wizardRegenerationEffect;
    }

    public void setWizardRegenerationEffect(boolean[] wizardRegenerationEffect) {
        this.wizardRegenerationEffect = wizardRegenerationEffect;
    }

}
