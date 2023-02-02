package Player;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import Enemy.Enemy;
import Enemy.Trap;
import Main.Main;

public class Player {

    Scanner scan = new Scanner(System.in);

    Random randomNumber = new Random();

    private int playerLocation = 25;

    private int playerLocationBefore = 25;

    private int playerHP = 100;

    private double backpackMaxWeight = 2.05f;

    private double backpackWeight = 0.2f;

    private int[] backpackLocation = new int[5];

    private boolean[] playerHasBackpack = new boolean[5];

    private boolean playerWasThere[] = new boolean[50];

    private boolean positionIsBlocked[] = new boolean[50];

    List<Integer> possibleLocations = new ArrayList<>();

    public void backpackGetLocation(Item item) {

        for (int i = 0; i <= 4; i++) {
            backpackLocation[i] = item.itemGetLocation(positionIsBlocked);
        }
    }

    public void playerAction(Item item, Door door, Cheats cheats, Item[] items, Trap[] traps, Enemy[] enemies, Enemy enemy, Main main) {

        int calculation;
        String userInput = scan.nextLine().toLowerCase();

        Switch:
        switch (userInput) {

        case "go north":

            if (playerLocation <= 7 && playerLocation >= 1) {

                System.out.println("You can't go into the wall");
            } else {
                playerLocationBefore = playerLocation;
                playerLocation -= 7;
                System.out.println("You enter the room over you");
                methods(item, items, enemy, enemies, door, main);
            }

            break;

        case "go east":

            calculation = playerLocation % 7;
            if (calculation == 0) {

                System.out.println("You can't go into the wall");
            } else {
                playerLocationBefore = playerLocation;
                playerLocation += 1;
                System.out.println("You enter the room right to you");
                methods(item, items, enemy, enemies, door, main);
            }

            break;

        case "go south":

            if (playerLocation <= 49 && playerLocation >= 43) {

                System.out.println("You can't go into the wall");
            } else {
                playerLocationBefore = playerLocation;
                playerLocation += 7;
                System.out.println("You enter the room under you");
                methods(item, items, enemy, enemies, door, main);
            }

            break;

        case "go west":

            calculation = playerLocation % 7;
            if (calculation == 1) {

                System.out.println("You can't go into the wall");
            } else {
                playerLocationBefore = playerLocation;
                playerLocation -= 1;
                System.out.println("You enter the room left to you");
                methods(item, items, enemy, enemies, door, main);
            }

            break;

        case "pick up":

            item.pickUpItem(this, items);
            break;

        case "put down":
            item.putDown(this, items);
            break;

        case "show backpack":

            showBackpack(items);

            break;

        case "show map":

            item.showMap(playerLocation, playerWasThere, door.getDoorLocation(), door.getPlayerFoundDoor(), door, items, this);
            break;

        case "show hp":

            System.out.println("You have " + playerHP + " HP");

            break;

        case "use potion":

            item.usePotion(this, items);

            break;

        case "attack":
            System.out.println("There is no enemy you can attack");
            break;

        case "show everything":

            cheats.showEverything(playerLocation, playerWasThere, door.getDoorLocation(), items, this, traps, enemies);

            break;

        case "give item":
            boolean repeat = true;

            System.out.println("Which item do you want");

            while (repeat == true) {

                userInput = scan.nextLine().toLowerCase();

                for (int i = 0; i <= 39; i++) {
                    if (userInput.equalsIgnoreCase(items[i].getName())) {
                        items[i].setLocation(playerLocation);
                        items[i].setPlayerHasItem(true);
                        System.out.println("You get a " + items[i].getName());
                        break Switch;
                    }
                }
                System.out.println("This isn't an item");
                break Switch;
            }
        case "fill hp":

            playerHP = 100;
            System.out.println("You have full HP");

            break;

        case "show wizard":

            System.out.println("The wizard locations are " + enemies[7].getLocation() + " " + enemies[8].getLocation() + " " + enemies[9]
                    .getLocation());

            break;

        default:
            System.out.println("This isn't a command");
        }

    }

    public void methods(Item item, Item[] items, Enemy enemy, Enemy[] enemies, Door door, Main main) {
        timeEffects(item, enemies, this, items, door, main, enemy);
        enemy.foundEnemy(enemies, this, items, door, item, main);
        door.foundDoor(this, items, enemy);
        playerMove(item, items);
    }

    public void timeEffects(Item item, Enemy[] enemies, Player player, Item[] items, Door door, Main main, Enemy enemy) {
        item.doRegeneration(this);
        item.doPoison(this);
        item.wizardRegeneration(this, enemies);
        player.endGame(items, door, main, enemy);
    }

    public void playerMove(Item item, Item[] items) {

        item.foundItem(this, items);

        loop:
        for (int i = 0; i <= 4; i++) {
            if (playerLocation == backpackLocation[i] && playerHasBackpack[0] == false && playerHasBackpack[1] == false
                    && playerHasBackpack[2] == false && playerHasBackpack[3] == false && playerHasBackpack[4] == false) {
                playerHasBackpack[i] = true;
                if (i >= 0 && i <= 2) {
                    backpackMaxWeight = 3;
                } else {
                    backpackMaxWeight = 4;
                }
                System.out.println("You found another backpack. Your backpack can now weight " + backpackMaxWeight + "kg");
            } else {
                if (playerLocation == backpackLocation[3] && playerHasBackpack[3] == false && playerHasBackpack[4] == false
                        || playerLocation == backpackLocation[4] && playerHasBackpack[3] == false && playerHasBackpack[4] == false) {
                    playerHasBackpack[4] = true;
                    backpackMaxWeight = 4;

                    System.out.println("You found another backpack. Your backpack can now weight " + backpackMaxWeight + "kg");
                    break loop;
                }
            }
        }

        for (int i = 0; i <= 38; i++) {
            if (items[i].isPlayerHasItem() == true) {

                items[i].setLocation(playerLocation);
            }
        }
    }

    public boolean attack(Enemy[] enemies, Item[] items, Enemy enemy, Door door, Item item, Main main) {
        boolean enemyIsThere = false;
        int quantityWeapons = 0;
        int indexEnemy = 0;
        boolean repeat = true;
        boolean playerHasWeapons = true;
        loop:
        for (int i = 0; i <= 13; i++) {
            if (playerLocation == enemies[i].getLocation()) {
                enemyIsThere = true;
                indexEnemy = i;
                break loop;
            }
        }

        for (int i = 10; i <= 29; i++) {
            if (items[i].isPlayerHasItem() == true) {
                quantityWeapons++;
            }
        }

        if (enemyIsThere == true) {
            switch (quantityWeapons) {

            case 0:
                System.out.println("You don't have any weapons to attack. Choose another command!");
                playerHasWeapons = false;

                break;

            default:

                System.out.println("With which weapon do you want attack?");
                while (repeat == true) {
                    String userInput = scan.nextLine().toLowerCase();
                    Switch:
                    switch (userInput) {

                    case "stick":
                        if (items[10].isPlayerHasItem() == true) {

                            dealDamage(10, indexEnemy, items, enemy, enemies, userInput, door, item, main);
                            repeat = false;
                        } else {
                            System.out.println("You don't have this weapon. Choose a weapon you have in your backpack.");
                        }
                        break;

                    case "small axe":

                        for (int i = 11; i <= 18; i++) {
                            if (items[i].isPlayerHasItem() == true) {

                                dealDamage(i, indexEnemy, items, enemy, enemies, userInput, door, item, main);
                                repeat = false;
                                break Switch;
                            }
                        }
                        System.out.println("You don't have this weapon. Choose a weapon you have in your backpack.");

                        break;

                    case "normal axe":
                        for (int i = 19; i <= 25; i++) {
                            if (items[i].isPlayerHasItem() == true) {

                                dealDamage(i, indexEnemy, items, enemy, enemies, userInput, door, item, main);
                                repeat = false;
                                break Switch;
                            }
                        }
                        System.out.println("You don't have this weapon. Choose a weapon you have in your backpack.");

                        break;

                    case "heavy axe":
                        for (int i = 26; i <= 29; i++) {
                            if (items[i].isPlayerHasItem() == true) {

                                dealDamage(i, indexEnemy, items, enemy, enemies, userInput, door, item, main);
                                repeat = false;
                                break Switch;
                            }
                        }
                        System.out.println("You don't have this weapon. Choose a weapon you have in your backpack.");

                        break;

                    case "legendary sword":
                        if (items[39].isPlayerHasItem() == true) {

                            dealDamage(39, indexEnemy, items, enemy, enemies, userInput, door, item, main);
                            repeat = false;
                            break Switch;
                        }

                        System.out.println("You don't have this weapon. Choose a weapon you have in your backpack.");

                        break;

                    case "show backpack":

                        showBackpack(items);
                        break;

                    default:
                        System.out.println("This isn't a weapon");

                        break;

                    }
                }
                break;
            }
        }
        return playerHasWeapons;
    }

    public void dealDamage(int indexItem, int indexEnemy, Item[] items, Enemy enemy, Enemy[] enemies, String name, Door door, Item item,
            Main main) {

        Enemy activeEnemy = enemies[indexEnemy];
        int a = 0;
        int damage = items[indexItem].getAttackDamage();
        int number = 1 + randomNumber.nextInt(10);
        int number2 = 0;// randomNumber.nextInt(10);
        int enemyHP;
        String weaponBroke = ".";
        String weaponBroke2 = ".";
        boolean[] newGuardIsAlive = enemy.getGuardIsAlive();

        if (number2 == 0) {
            items[indexItem].setPlayerHasItem(false);
            items[indexItem].setLocation(0);
            backpackWeight -= items[indexItem].getWeight();
            weaponBroke = " but your weapon broke.";
            weaponBroke2 = " and your weapon broke";
        }

        while (a == 0) {
            if (number <= 10 && number >= 9) {
                System.out.println("You missed your hit" + weaponBroke2);
                break;

            } else if (number <= items[indexItem].getChanceCriticalHit()) {

                damage += 5 * items[indexItem].getChanceCriticalHit();
            }

            enemyHP = enemies[indexEnemy].getHP() - damage;

            if (!(enemies[indexEnemy].getName().equalsIgnoreCase("wizard")) || !(enemyHP <= 0) || //
                    enemy.getWizardTeleported()[indexEnemy - 7] == true) {

                if (number <= items[indexItem].getChanceCriticalHit()) {
                    if (enemyHP <= 0) {
                        System.out.println("You hit a critical hit and did " + damage + " damage" + weaponBroke);
                    } else {
                        System.out.println("You hit a critical hit and did " + damage + " damage" + weaponBroke + " The " + //
                                enemies[indexEnemy].getName() + " has " + enemyHP + " HP left");
                    }
                } else {
                    if (enemyHP <= 0) {
                        System.out.println("You hit your attack and did " + damage + " damage" + weaponBroke);
                    } else {
                        System.out.println("You hit your attack and did " + damage + " damage" + weaponBroke + " The " + //
                                enemies[indexEnemy].getName() + " has " + enemyHP + " HP left");
                    }
                }

                enemies[indexEnemy].setHP(enemies[indexEnemy].getHP() - damage);

                if (enemies[indexEnemy].getHP() <= 0) {

                    if (!(enemies[indexEnemy].getName().equalsIgnoreCase("wizard")) && !(enemies[indexEnemy].getName()
                            .equalsIgnoreCase("guard"))) {
                        enemies[indexEnemy].getWeapon().setLocation(playerLocation);
                        enemies[indexEnemy].setLocation(0);

                        System.out.println("The " + enemies[indexEnemy].getName() + " is dead and dropped his " + //
                                enemies[indexEnemy].getWeapon().getName());

                    } else if (enemies[indexEnemy].getName().equalsIgnoreCase("wizard")) {
                        items[indexEnemy - 7].setLocation(enemies[indexEnemy].getLocation());
                        enemies[indexEnemy].setLocation(0);

                        System.out.println("The " + enemies[indexEnemy].getName() + " is dead and dropped a key");

                    } else {
                        enemies[indexEnemy].setLocation(0);
                        System.out.println("The " + enemies[indexEnemy].getName() + " is dead");
                        newGuardIsAlive[indexEnemy - 11] = false;
                        enemy.setGuardIsAlive(newGuardIsAlive);
                    }
                }
            } else {
                boolean[] wizardTeleported = enemy.getWizardTeleported();
                wizardTeleported[indexEnemy - 7] = true;
                enemy.setWizardTeleported(wizardTeleported);

                int enemyLocation = activeEnemy.getLocation();

                addColumnToList(enemyLocation, enemies, door);
                addRowToList(enemyLocation, enemies, door);
                number = randomNumber.nextInt(possibleLocations.size());
                enemies[indexEnemy].setLocation(possibleLocations.get(number));

                enemies[indexEnemy - 3].setLocation(playerLocation);
                System.out.println("The wizard teleports 1 or 2 rooms away and spawns an ork with a " + enemies[indexEnemy - 3].getWeapon()
                        .getName() + " in your room");

                if (number2 == 0) {
                    items[indexItem].setPlayerHasItem(true);
                    items[indexItem].setLocation(playerLocation);
                    backpackWeight += items[indexItem].getWeight();
                }

                enemy.foundEnemy(enemies, this, items, door, item, main);

            }

            break;
        }
    }

    public void addColumnToList(int enemyLocation, Enemy[] enemies, Door door) {

        boolean enemyIsThere;
        boolean doorIsThere;
        int calculation;

        for (int i = -2; i <= 2; i++) {

            calculation = (enemyLocation - 1) / 7 + i;

            enemyIsThere = false;
            doorIsThere = false;

            for (int j = 0; j <= 10; j++) {
                if (enemyLocation + i * 7 == enemies[j].getLocation()) {
                    enemyIsThere = true;
                }
            }

            for (int j = 0; j <= 2; j++) {
                if (enemyLocation + i * 7 == door.getDoorLocation()[j]) {
                    doorIsThere = true;
                }
            }

            if (enemyIsThere == false && doorIsThere == false && calculation >= 0 && calculation <= 6 && i != 0 && enemyLocation + 7
                    * i != playerLocationBefore) {
                possibleLocations.add(enemyLocation + 7 * i);
            }
        }
    }

    public void addRowToList(int enemyLocation, Enemy[] enemies, Door door) {

        boolean enemyIsThere = false;
        boolean doorIsThere = false;
        int calculation;

        for (int i = -2; i <= 2; i++) {

            calculation = (enemyLocation - 1) % 7 + i;

            enemyIsThere = false;
            doorIsThere = false;

            for (int j = 0; j <= 10; j++) {
                if (enemyLocation + i == enemies[j].getLocation()) {
                    enemyIsThere = true;
                }
            }

            for (int j = 0; j <= 2; j++) {
                if (enemyLocation + i == door.getDoorLocation()[j]) {
                    doorIsThere = true;
                }
            }

            if (enemyIsThere == false && doorIsThere == false && calculation >= 0 && calculation <= 6 && i != 0 && enemyLocation
                    + i != playerLocationBefore) {
                possibleLocations.add(enemyLocation + i);
            }
        }
    }

    public void showBackpack(Item[] items) {
        boolean backpackIsEmpty = true;
        DecimalFormat decimalFormat = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.US));

        System.out.println("Your backpack weights: " + decimalFormat.format(backpackWeight) + "/" + decimalFormat.format(backpackMaxWeight)
                + "kg");

        for (int i = 0; i <= 38; i++) {
            if (items[i].isPlayerHasItem() == true) {
                if (backpackIsEmpty == true) {
                    backpackIsEmpty = false;
                    System.out.println("You have in your backpack:");
                }
                System.out.println(items[i].getName());
            }
        }
        if (backpackIsEmpty == true) {
            System.out.println("Your backpack is empty");
        }
    }

    public void endGame(Item[] items, Door door, Main main, Enemy enemy) {
        boolean playerHasKey = false;
        if (main.isEndCode() == false) {

            if (playerHP <= 0) {
                System.out.println("You don't have any more HP and lose the game!");
                main.setEndCode(true);
            }

            for (int i = 0; i <= 2; i++) {

                if (items[i].isPlayerHasItem() == true) {
                    playerHasKey = true;
                }
            }

            if (playerHasKey == true && (enemy.getGuardIsAlive()[0] == false && door.getDoorLocation()[0] == playerLocation || enemy
                    .getGuardIsAlive()[1] == false && door.getDoorLocation()[1] == playerLocation || enemy.getGuardIsAlive()[2] == false
                            && door.getDoorLocation()[2] == playerLocation)) {
                main.setEndCode(true);
            }
        }
    }

    public int getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }

    public boolean[] getPlayerWasThere() {
        return playerWasThere;
    }

    public void setPlayerWasThere(boolean[] playerWasThere) {
        this.playerWasThere = playerWasThere;
    }

    public boolean[] getPositionIsBlocked() {
        return positionIsBlocked;
    }

    public void setPositionIsBlocked(boolean[] positionIsBlocked) {
        this.positionIsBlocked = positionIsBlocked;
    }

    public double getBackpackMaxWeight() {
        return backpackMaxWeight;
    }

    public void setBackpackMaxWeight(double backpackMaxWeight) {
        this.backpackMaxWeight = backpackMaxWeight;
    }

    public double getBackpackWeight() {
        return backpackWeight;
    }

    public void setBackpackWeight(double backpackWeight) {
        this.backpackWeight = backpackWeight;
    }

    public int[] getBackpackLocation() {
        return backpackLocation;
    }

    public void setBackpackLocation(int[] backpackLocation) {
        this.backpackLocation = backpackLocation;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerLocationBefore() {
        return playerLocationBefore;
    }

    public void setPlayerLocationBefore(int playerLocationBefore) {
        this.playerLocationBefore = playerLocationBefore;
    }

}
