package Player;

import Enemy.Enemy;
import Enemy.Trap;

public class Cheats {

    public void showEverything(int playerLocation, boolean[] playerWasThere, int[] doorLocation, Item[] items, Player player, Trap[] traps,
            Enemy[] enemies) {

        for (int field = 1; field <= 49; field++) {

            inner:
            for (int b = 0; b == 0; b++) {
                int calculation = 0;
                if (playerLocation == field) {
                    calculation = field % 7;
                    if (calculation == 0) {
                        System.out.println("P");
                        System.out.println("");
                        break inner;
                    } else {
                        System.out.print("P   ");
                        break inner;
                    }
                }

                if (doorLocation[0] == field || doorLocation[1] == field || doorLocation[2] == field) {
                    calculation = field % 7;
                    if (calculation == 0) {
                        System.out.println("D");
                        System.out.println("");
                        break inner;
                    } else {
                        System.out.print("D   ");
                        break inner;
                    }
                }

                for (int i = 0; i <= 2; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("K");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("K   ");
                            break inner;
                        }
                    }
                }

                for (int i = 3; i <= 9; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("M");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("M   ");
                            break inner;
                        }
                    }
                }

                for (int i = 11; i <= 18; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("sA");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("sA  ");
                            break inner;
                        }
                    }
                }

                for (int i = 19; i <= 25; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("nA");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("nA  ");
                            break inner;
                        }
                    }
                }

                for (int i = 26; i <= 29; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("hA");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("hA  ");
                            break inner;
                        }
                    }
                }

                for (int i = 30; i <= 32; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("rP");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("rP  ");
                            break inner;
                        }
                    }
                }

                for (int i = 33; i <= 35; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("hP");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("hP  ");
                            break inner;
                        }
                    }
                }

                for (int i = 36; i <= 38; i++) {
                    if (items[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("mP");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("mP  ");
                            break inner;
                        }
                    }
                }

                for (int i = 0; i <= 2; i++) {
                    if (player.getBackpackLocation()[i] == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("mB");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("mB  ");
                            break inner;
                        }
                    }
                }

                for (int i = 3; i <= 4; i++) {
                    if (player.getBackpackLocation()[i] == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("lB");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("lB  ");
                            break inner;
                        }
                    }
                }

                for (int i = 0; i <= 4; i++) {
                    if (traps[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("T");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("T   ");
                            break inner;
                        }
                    }
                }

                for (int i = 0; i <= 6; i++) {
                    if (enemies[i].getLocation() == field) {
                        calculation = field % 7;
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

                for (int i = 7; i <= 9; i++) {
                    if (enemies[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("W");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("W   ");
                            break inner;
                        }
                    }
                }

                for (int i = 10; i <= 10; i++) {
                    if (enemies[i].getLocation() == field) {
                        calculation = field % 7;
                        if (calculation == 0) {
                            System.out.println("gO");
                            System.out.println("");
                            break inner;
                        } else {
                            System.out.print("gO  ");
                            break inner;
                        }
                    }
                }

                if (playerWasThere[field] == true && playerLocation != field) {
                    calculation = field % 7;
                    if (calculation == 0) {
                        System.out.println("X");
                        System.out.println("");
                        break inner;
                    } else {
                        System.out.print("X   ");
                        break inner;
                    }

                }
                if (playerLocation != field) {
                    calculation = field % 7;
                    if (calculation == 0) {
                        System.out.println("E");
                        System.out.println("");
                        break inner;
                    } else {
                        System.out.print("E   ");
                        break inner;
                    }
                }
            }
        }
    }
}
