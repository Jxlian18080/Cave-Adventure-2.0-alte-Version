package Enemy;

import java.util.Random;
import java.util.Scanner;

import Main.Main;
import Player.Door;
import Player.Item;
import Player.Player;

public class Enemy {

    private String name;

    private int location;

    private Item weapon;

    private String a;

    private int HP;

    private Random randomNumber = new Random();

    private Scanner scan = new Scanner(System.in);

    private boolean[] weaponIsEquiped = new boolean[11];

    private boolean[] wizardTeleported = new boolean[3];

    private boolean[] guardIsAlive = {
            true, true, true
    }; // 0 = enemies[11]; 1 = enemies[12]; 2 = enemies[13];

    public Enemy(String name, int location, Item weapon, String a, int HP) {
        this.name = name;
        this.location = location;
        this.weapon = weapon;
        this.a = a;
        this.HP = HP;
    }

    public Enemy() {

    }

    public Item getWeapon(Item[] enemyWeapons) {

        boolean repeat = true;

        while (repeat == true) {

            int number = randomNumber.nextInt(10);
            if (weaponIsEquiped[number] == false) {
                weapon = enemyWeapons[number];
                weaponIsEquiped[number] = true;
                repeat = false;
            }
        }
        return weapon;

    }

    public void foundEnemy(Enemy[] enemies, Player player, Item[] items, Door door, Item item, Main main) {
        boolean repeat1 = true;
        boolean repeat = true;
        boolean attackAgain = false;
        boolean doEnemyAttack = true;
        boolean[] newPoisonEffect = item.getPoisonEffect();
        boolean[] wizardRegenerationeffect = item.getWizardRegenerationEffect();
        int number = 0;
        int a = 0;
        int damage = 0;
        loop:
        for (int i = 0; i <= 13; i++) {

            if (player.getPlayerLocation() == enemies[i].location) {
                if (i != 4 && i != 5 && i != 6 && i != 11 && i != 12 && i != 13) {
                    System.out.println(enemies[i].a + " " + enemies[i].name + " with a " + enemies[i].weapon.getName() + " and "
                            + enemies[i].getHP() + " HP stands in this room");
                }

                if (i == 11 || i == 12 || i == 13) {
                    System.out.println("You found a Door but a Guard with a " + enemies[i].weapon.getName() + " and " + enemies[i].getHP()
                            + " HP is defending it");
                }

                while (repeat == true) {

                    String userInput = scan.nextLine();

                    Switch:
                    switch (userInput) {

                    case "attack":

                        doEnemyAttack = player.attack(enemies, items, this, door, item, main);
                        if (doEnemyAttack == true) {
                            if (enemies[i].HP <= 0) {
                                player.timeEffects(item, enemies, player, items, door, main, this);
                                break loop;

                            } else if (!(enemies[i].name.equalsIgnoreCase("wizard"))) {
                                damage = enemies[i].weapon.getAttackDamage();
                                number = 1 + randomNumber.nextInt(10);
                                while (a == 0) {
                                    if (number <= 10 && number >= 8) {
                                        System.out.println("The " + enemies[i].name + " missed his hit");
                                        break;
                                    } else if (number <= enemies[i].weapon.getChanceCriticalHit()) {

                                        damage += 5 * enemies[i].weapon.getChanceCriticalHit();
                                        System.out.println("The " + enemies[i].name + " hit a critical hit and did " + damage + " damage");
                                    } else {
                                        System.out.println("The " + enemies[i].name + " hit his attack and did " + damage + " damage");
                                    }
                                    player.setPlayerHP(player.getPlayerHP() - damage);
                                    player.timeEffects(item, enemies, player, items, door, main, this);
                                    if (main.isEndCode() == true) {
                                        break loop;
                                    }

                                    if (player.getPlayerHP() <= 0) {
                                        break loop;
                                    }
                                    break;
                                }
                            } else if (player.getPlayerLocation() == enemies[i].getLocation()) {

                                attackAgain = false;
                                repeat1 = true;
                                while (repeat1 == true) {
                                    number = randomNumber.nextInt(10);
                                    switch (number) {

                                    case 0:
                                    case 1:
                                        if (attackAgain == false) {
                                            attackAgain = true;
                                            player.setPlayerHP(player.getPlayerHP() - 5);
                                            System.out.println("You lose 5 HP and get stunned. The wizard attacks again");
                                        }

                                        break;

                                    case 2:
                                    case 3:
                                    case 4:

                                        if (item.getPoisonEffect()[3] == false) {
                                            newPoisonEffect[3] = true;
                                            // item.setPoisonEffect(newPoisonEffect);
                                            System.out.println("The wizard give you the poison effect");
                                            repeat1 = false;
                                        }

                                        break;

                                    case 5:
                                    case 6:
                                    case 7:

                                        player.setPlayerHP(player.getPlayerHP() - 15);
                                        System.out.println("The wizard attacks with his magic stick and did 15 damage");
                                        repeat1 = false;

                                        break;

                                    case 8:
                                        if (enemies[i].getHP() < 55) {
                                            enemies[i].setHP(enemies[i].getHP() + 20);

                                            if (enemies[i].getHP() > 55) {
                                                enemies[i].setHP(55);
                                            }
                                            System.out.println("The wizard heals himself and has now " + enemies[i].getHP() + " HP");
                                            repeat1 = false;
                                        }

                                        break;

                                    case 9:
                                        if (enemies[i].getHP() < 55 && item.getWizardRegenerationEffect()[0] == false && item
                                                .getWizardRegenerationEffect()[1] == false && item.getWizardRegenerationEffect()[2] == false
                                                && item.getWizardRegenerationEffect()[3] == false && item
                                                        .getWizardRegenerationEffect()[4] == false && item
                                                                .getWizardRegenerationEffect()[5] == false && item
                                                                        .getWizardRegenerationEffect()[6] == false && item
                                                                                .getWizardRegenerationEffect()[7] == false && item
                                                                                        .getWizardRegenerationEffect()[8] == false) {
                                            System.out.println("The wizard give himself the regeneration effect");
                                            wizardRegenerationeffect[i - 1] = true;
                                            wizardRegenerationeffect[i - 4] = true;
                                            wizardRegenerationeffect[i - 7] = true;
                                            item.setWizardRegenerationEffect(wizardRegenerationeffect);
                                            repeat1 = false;

                                        }

                                        break;
                                    }
                                }
                                player.timeEffects(item, enemies, player, items, door, main, this);
                                if (main.isEndCode() == true) {
                                    break loop;
                                }
                            } else {
                                break loop;
                            }
                        }
                        break;

                    case "runaway":

                        runaway(enemies, i, player);

                        break loop;

                    case "show backpack":

                        player.showBackpack(items);

                        break;

                    case "show hp":

                        System.out.println("You have " + player.getPlayerHP() + " HP");

                        break;

                    case "use potion":

                        item.usePotion(player, items);

                        break;

                    case "give item":

                        boolean repeat2 = true;

                        System.out.println("Which item do you want");

                        while (repeat2 == true) {

                            userInput = scan.nextLine().toLowerCase();

                            for (int j = 0; j <= 39; j++) {
                                if (userInput.equalsIgnoreCase(items[j].getName())) {
                                    items[j].setLocation(player.getPlayerLocation());
                                    items[j].setPlayerHasItem(true);
                                    System.out.println("You get a " + items[j].getName());
                                    break Switch;
                                }
                            }
                            System.out.println("This isn't an item");
                        }

                    case "fill hp":

                        player.setPlayerHP(100);
                        System.out.println("You have full HP");

                        break;

                    default:

                        System.out.println("This isn't a command");

                        break;

                    }
                }

                break;

            }
        }
    }

    public void runaway(Enemy[] enemies, int i, Player player) {
        int number;
        number = randomNumber.nextInt(4);
        if (number != 0) {
            player.setPlayerHP(player.getPlayerHP() - enemies[i].weapon.getAttackDamage());
            System.out.println("While you running away the " + enemies[i].name + " hit you with his " + //
                    enemies[i].getWeapon().getName() + " and do " + enemies[i].weapon.getAttackDamage() + " damage");
        }
        player.setPlayerLocation(player.getPlayerLocationBefore());
        System.out.println("You are now in the room where you came from");
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public boolean[] getWizardTeleported() {
        return wizardTeleported;
    }

    public void setWizardTeleported(boolean[] wizardTeleported) {
        this.wizardTeleported = wizardTeleported;
    }

    public boolean[] getGuardIsAlive() {
        return guardIsAlive;
    }

    public void setGuardIsAlive(boolean[] guardIsAlive) {
        this.guardIsAlive = guardIsAlive;
    }

}
