package dev.anto6314.blackJack;

import java.util.Scanner;

/**
 * Main
 */
public class GameMain {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String playerName;

        System.out.println("\t\t\t\t-----------------");
        System.out.println("\t\t\t\t|   BLACKJACK   |");
        System.out.println("\t\t\t\t-----------------");
        System.out.print("Inserisci il tuo nome: ");
        playerName = s.nextLine();
        GameCore GameMain = new GameCore(playerName);

        s.close();
    }
}
