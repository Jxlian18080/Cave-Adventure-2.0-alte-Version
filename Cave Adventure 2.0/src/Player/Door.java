package Player;

import java.util.Random;

import Enemy.Enemy;

public class Door {

    private int[] doorLocation = new int[3];

    private boolean[] playerFoundDoor = new boolean[3];

    public void doorGetLocation(boolean[] positionIsBlocked) {
        int a = 0;

        for (int b = 0; b <= 2; b++) {
            a = 0;
            while (a == 0) {

                Random randomNumber = new Random();
                doorLocation[b] = 1 + randomNumber.nextInt(49);

                if (!(doorLocation[b] == 11 || doorLocation[b] <= 19 && doorLocation[b] >= 17 || doorLocation[b] <= 27
                        && doorLocation[b] >= 23 || doorLocation[b] <= 33 && doorLocation[b] >= 31 || doorLocation[b] == 39
                        || positionIsBlocked[doorLocation[b]] == true)) {
                    a++;
                    positionIsBlocked[doorLocation[b]] = true;
                }
            }
        }
    }

    public void foundDoor(Player player, Item[] items, Enemy enemy) {

        for (int i = 0; i <= 2; i++) {
            if (doorLocation[i] == player.getPlayerLocation() && (enemy.getGuardIsAlive()[0] == true || enemy.getGuardIsAlive()[1] == true
                    || enemy.getGuardIsAlive()[2] == true)) {
                if (playerFoundDoor[i] == false) {
                    playerFoundDoor[i] = true;
                    if (items[0].isPlayerHasItem() == true || items[1].isPlayerHasItem() == true || items[2].isPlayerHasItem() == true) {
                        System.out.println("You are now in the door room and have the key");
                        System.out.println("You win the game!");
                    } else {
                        System.out.println("You are now in the door room");
                    }
                } else if (items[0].isPlayerHasItem() == true || items[1].isPlayerHasItem() == true || items[2].isPlayerHasItem() == true) {
                    System.out.println("You are back in the door room and have the key");
                    System.out.println("You win the game!");
                } else {
                    System.out.println("You are back in the door room");
                }
            }
        }

    }

    public boolean[] getPlayerFoundDoor() {
        return playerFoundDoor;
    }

    public void setPlayerFoundDoor(boolean[] playerFoundDoor) {
        this.playerFoundDoor = playerFoundDoor;
    }

    public int[] getDoorLocation() {
        return doorLocation;
    }

    public void setDoorLocation(int[] doorLocation) {
        this.doorLocation = doorLocation;
    }
}
