package Enemy;

import java.util.Random;

import Player.Player;

public class Trap {

    private int location;

    private int damage;

    private Random randomNumber = new Random();

    public Trap(int location, int damage) {
        this.damage = damage;
        this.location = location;
    }

    public Trap() {

    }

    public int damage(int damage) {

        damage = (2 + randomNumber.nextInt(4)) * 5;

        return damage;
    }

    public void activateTrap(Player player, Trap[] traps) {

        for (int i = 0; i <= 4; i++) {
            if (player.getPlayerLocation() == traps[i].location) {
                player.setPlayerHP(player.getPlayerHP() - traps[i].damage);
                traps[i].location = 0;
                System.out.println("You run into a trap and lose " + traps[i].damage + " HP");

            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
