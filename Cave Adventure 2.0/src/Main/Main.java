package Main;

import Enemy.Enemy;
import Enemy.Trap;
import Player.Cheats;
import Player.Door;
import Player.Item;
import Player.Player;

public class Main {

    private boolean endCode = false;

    public boolean isEndCode() {
        return endCode;
    }

    public void setEndCode(boolean endCode) {
        this.endCode = endCode;
    }

    public static void main(String[] args) {

        Main main = new Main();
        Cheats cheats = new Cheats();
        Player player = new Player();
        Door door = new Door();
        Item item = new Item();
        Trap trap = new Trap();
        Enemy enemy = new Enemy();
        Item key1 = new Item("key", 0, 0.2f, false, //
                0, 0);
        Item key2 = new Item("key", 0, 0.2f, false, //
                0, 0);
        Item key3 = new Item("key", 0, 0.2f, false, //
                0, 0);
        Item map1 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map2 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map3 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map4 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map5 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map6 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item map7 = new Item("map", item.itemGetLocation(player.getPositionIsBlocked()), 0.3f, false, //
                0, 0);
        Item stick = new Item("stick", player.getPlayerLocation(), 0.2f, true, 5, //
                1);
        Item smallAxe1 = new Item("small axe", item.itemGetLocation(player.getPositionIsBlocked()), 0.5f, //
                false, 10, 2);
        Item smallAxe2 = new Item("small axe", item.itemGetLocation(player.getPositionIsBlocked()), 0.5f, //
                false, 10, 2);
        Item smallAxe3 = new Item("small axe", item.itemGetLocation(player.getPositionIsBlocked()), 0.5f, //
                false, 10, 2);
        Item smallAxe4 = new Item("small axe", item.itemGetLocation(player.getPositionIsBlocked()), 0.5f, //
                false, 10, 2);
        Item normalAxe1 = new Item("normal axe", item.itemGetLocation(player.getPositionIsBlocked()), 1f, //
                false, 15, 3);
        Item normalAxe2 = new Item("normal axe", item.itemGetLocation(player.getPositionIsBlocked()), 1f, //
                false, 15, 3);
        Item normalAxe3 = new Item("normal axe", item.itemGetLocation(player.getPositionIsBlocked()), 1f, //
                false, 15, 3);
        Item heavyAxe1 = new Item("heavy axe", item.itemGetLocation(player.getPositionIsBlocked()), 1.5f, //
                false, 25, 3);
        Item heavyAxe2 = new Item("heavy axe", item.itemGetLocation(player.getPositionIsBlocked()), 1.5f, //
                false, 25, 3);
        Item regenerationPotion1 = new Item("regeneration potion", item.itemGetLocation(player.getPositionIsBlocked()), //
                0.6f, false, 0, 0);
        Item regenerationPotion2 = new Item("regeneration potion", item.itemGetLocation(player.getPositionIsBlocked()), //
                0.6f, false, 0, 0);
        Item regenerationPotion3 = new Item("regeneration potion", item.itemGetLocation(player.getPositionIsBlocked()), //
                0.6f, false, 0, 0);
        Item healPotion1 = new Item("heal potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);
        Item healPotion2 = new Item("heal potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);
        Item healPotion3 = new Item("heal potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);
        Item mysteryPotion1 = new Item("mystery potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);
        Item mysteryPotion2 = new Item("mystery potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);
        Item mysteryPotion3 = new Item("mystery potion", item.itemGetLocation(player.getPositionIsBlocked()), 0.6f, //
                false, 0, 0);

        door.doorGetLocation(player.getPositionIsBlocked());

        Trap trap1 = new Trap(item.itemGetLocation(player.getPositionIsBlocked()), trap.damage(trap.getDamage()));
        Trap trap2 = new Trap(item.itemGetLocation(player.getPositionIsBlocked()), trap.damage(trap.getDamage()));
        Trap trap3 = new Trap(item.itemGetLocation(player.getPositionIsBlocked()), trap.damage(trap.getDamage()));
        Trap trap4 = new Trap(item.itemGetLocation(player.getPositionIsBlocked()), trap.damage(trap.getDamage()));
        Trap trap5 = new Trap(item.itemGetLocation(player.getPositionIsBlocked()), trap.damage(trap.getDamage()));

        Trap[] traps = {
                trap1, trap2, trap3, trap4, trap5
        };

        Item enemySmallAxe1 = new Item("small axe", 0, 0.5f, //
                false, 10, 1);
        Item enemySmallAxe2 = new Item("small axe", 0, 0.5f, //
                false, 10, 1);
        Item enemySmallAxe3 = new Item("small axe", 0, 0.5f, //
                false, 10, 1);
        Item enemySmallAxe4 = new Item("small axe", 0, 0.5f, //
                false, 10, 1);
        Item enemyNormalAxe1 = new Item("normal axe", 0, 1f, //
                false, 15, 2);
        Item enemyNormalAxe2 = new Item("normal axe", 0, 1f, //
                false, 15, 2);
        Item enemyNormalAxe3 = new Item("normal axe", 0, 1f, //
                false, 15, 2);
        Item enemyNormalAxe4 = new Item("normal axe", 0, 1f, //
                false, 15, 2);
        Item enemyHeavyAxe1 = new Item("heavy axe", 0, 1.5f, //
                false, 25, 2);
        Item enemyHeavyAxe2 = new Item("heavy axe", 0, 1.5f, //
                false, 25, 2);
        Item magicStick = new Item("magic stick", 0, 0f, //
                false, 15, 0);
        Item legendarySword = new Item("legendary sword", 0, 4f, //
                false, 40, 0);

        Item[] items = {
                key1, key2, key3, map1, map2, map3, map4, map5, map6, map7, stick, smallAxe1, smallAxe2, smallAxe3, smallAxe4,
                enemySmallAxe1, enemySmallAxe2, enemySmallAxe3, enemySmallAxe4, normalAxe1, normalAxe2, normalAxe3, enemyNormalAxe1,
                enemyNormalAxe2, enemyNormalAxe3, enemyNormalAxe4, heavyAxe1, heavyAxe2, enemyHeavyAxe1, enemyHeavyAxe2,
                regenerationPotion1, regenerationPotion2, regenerationPotion3, healPotion1, healPotion2, healPotion3, mysteryPotion1,
                mysteryPotion2, mysteryPotion3, legendarySword
        };

        Item[] enemyWeapons = {
                enemySmallAxe1, enemySmallAxe2, enemySmallAxe3, enemySmallAxe4, enemyNormalAxe1, enemyNormalAxe2, enemyNormalAxe3,
                enemyNormalAxe4, enemyHeavyAxe1, enemyHeavyAxe2, legendarySword
        };

        Enemy org1 = new Enemy("ork", item.itemGetLocation(player.getPositionIsBlocked()), enemy.getWeapon(enemyWeapons), "an", //
                30);
        Enemy org2 = new Enemy("ork", item.itemGetLocation(player.getPositionIsBlocked()), enemy.getWeapon(enemyWeapons), "an", //
                30);
        Enemy org3 = new Enemy("ork", item.itemGetLocation(player.getPositionIsBlocked()), enemy.getWeapon(enemyWeapons), "an", //
                30);
        Enemy org4 = new Enemy("ork", item.itemGetLocation(player.getPositionIsBlocked()), enemy.getWeapon(enemyWeapons), "an", //
                30);
        Enemy magicOrg1 = new Enemy("ork", 0, enemy.getWeapon(enemyWeapons), "an", 30);
        Enemy magicOrg2 = new Enemy("ork", 0, enemy.getWeapon(enemyWeapons), "an", 30);
        Enemy magicOrg3 = new Enemy("ork", 0, enemy.getWeapon(enemyWeapons), "an", 30);
        Enemy wizard1 = new Enemy("wizard", item.itemGetLocation(player.getPositionIsBlocked()), magicStick, "a", //
                55);
        Enemy wizard2 = new Enemy("wizard", item.itemGetLocation(player.getPositionIsBlocked()), magicStick, "a", //
                55);
        Enemy wizard3 = new Enemy("wizard", item.itemGetLocation(player.getPositionIsBlocked()), magicStick, "a", //
                55);
        Enemy giantOrg = new Enemy("giant org", item.itemGetLocation(player.getPositionIsBlocked()), legendarySword, "a", //
                120);
        Enemy guard1 = new Enemy("guard", door.getDoorLocation()[0], enemy.getWeapon(enemyWeapons), "a", 70);
        Enemy guard2 = new Enemy("guard", door.getDoorLocation()[1], enemy.getWeapon(enemyWeapons), "a", 70);
        Enemy guard3 = new Enemy("guard", door.getDoorLocation()[2], enemy.getWeapon(enemyWeapons), "a", 70);

        Enemy[] enemies = {
                org1, org2, org3, org4, magicOrg1, magicOrg2, magicOrg3, wizard1, wizard2, wizard3, giantOrg, guard1, guard2, guard3
        };

        player.backpackGetLocation(item);

        System.out.println(
                "You are caught in a dark cave with many rooms\nFind the key and the door to escape\nWrite 'help' if you need help");

        while (main.endCode == false) {
            item.mapUpdate(player.getPlayerWasThere(), player.getPlayerLocation());
            player.playerAction(item, door, cheats, items, traps, enemies, enemy, main);
            trap.activateTrap(player, traps);
            player.endGame(items, door, main, enemy);
        }

    }

}
