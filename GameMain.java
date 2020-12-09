package dev.anto6314;

import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String playerName;

        System.out.println("\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t|   BLACKJACK by anto6314   |");
        System.out.println("\t\t\t\t-----------------------------\n");

        System.out.print("Inserisci il tuo nome: ");
        playerName = scanner.nextLine();

        GameCore GameMain = new GameCore(playerName);

        scanner.close();
    }
}
