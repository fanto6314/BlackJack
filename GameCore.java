package dev.anto6314.blackJack;

import java.util.Scanner;


public class GameCore {

    private Deck newDeck;
    private String playerName;
    private boolean youDone;
    private boolean dealerDone;
    private Players dealer;
    private Players you;
    private Scanner sc = new Scanner(System.in);
    private boolean doubleDownAllowed;


    GameCore(String pName) {

        this.newDeck = new Deck(4, true);
        boolean gameOver = false;
        this.playerName = pName;

        /**
         * Creazione giocatore, e banco
         */
        you = new Players(this.playerName);
        dealer = new Players("il banco");


        /**
         * Inizio gioco
         */
        while (!gameOver) {

            System.out.print("\n" + this.playerName + ", Vuoi giocare a BlyackJack? [Y o N] ");
            String gameInit = sc.next();

            if (gameInit.compareToIgnoreCase("Y") == 0) {

                this.dealTheGame();
            } else {

                gameOver = true;
            }
        }

        System.out.println("\n" + this.playerName + ", la tua partita è finita...");

        // To play again
        System.out.println("\n" + this.playerName + ", Vorresti riprovare? [Y o N] ");
        String Y = sc.next();
        if (Y.compareToIgnoreCase("Y") == 0) {
            new GameCore(this.playerName);
        }
        sc.close();
    }

    /**
     * Inizio del gioco
     */
    private void dealTheGame() {

        boolean blackjack = false;
        this.doubleDownAllowed = true;

        you.emptyHand();
        dealer.emptyHand();

        this.youDone = false;
        this.dealerDone = false;

        you.addCardToPlayersHand(newDeck.dealingNextCard());
        dealer.addCardToPlayersHand(newDeck.dealingNextCard());
        you.addCardToPlayersHand(newDeck.dealingNextCard());
        dealer.addCardToPlayersHand(newDeck.dealingNextCard());

        System.out.println("\n---------- CARTE DISTRIBUITE ----------\n");
        you.printCardsInHand(true);
        dealer.printCardsInHand(false);

        System.out.printf("Il tuo punteggio:%d\t", you.getPlayersHandTotal());

        blackjack = this.checkIfBlackJack();

        while (!this.youDone || !this.dealerDone) {
            if (!this.youDone) {
                this.yourPlay();
            }
            else if (!this.dealerDone) {
                this.dealersPlay();
            }
            System.out.println();
        }
        if (!blackjack) {
            this.decideWinner();
        }
    }

    /**
     * Metodo che controlla se le carte distribuite iniziamente formano un BlackJack
     * @return
     */
    private boolean checkIfBlackJack() {

        boolean blackJack = false;

        if (you.getPlayersHandTotal() == 21) {
            this.youDone = true;
            this.dealerDone = true;

            if (you.getPlayersHandTotal() > dealer.getPlayersHandTotal() || dealer.getPlayersHandTotal() > 21) {

                System.out.println("\n\t\t\t\t-----------------------");
                System.out.println("\n\t\t\t\t|      BLACKJACK      |");
                System.out.println("\n\t\t\t\t-----------------------\n");

                dealer.printCardsInHand(true);

                System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
                System.out.print("Hai vinto\t");

                blackJack = true;
            }
            else {

                System.out.println("\tSarebbe potuto essere un tuo BlackJack!\n");
                dealer.printCardsInHand(true);

                System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
                blackJack = false;
            }
        }
        else if (dealer.getPlayersHandTotal() == 21) {
            dealer.printCardsInHand(true);
            System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());

            System.out.println("\t\t\t\t---------------------------");
            System.out.println("\t\t\t\t|   BLACKJACK DEL BANCO   |");
            System.out.println("\t\t\t\t|        HAI PERSO        |");
            System.out.println("\t\t\t\t---------------------------\n");

            this.dealerDone = true;
            blackJack = false;
        }
        return blackJack;
    }

    /**
     * Metodo che fa giocare il giocatore
     */
    private void yourPlay() {

        String answer;

        if (doubleDownAllowed) {
            System.out.print("Prendi un'altra carta, stai o raddoppi? [C - S - R] ");
        }
        else {
            this.doubleDownAllowed = false;
            System.out.print("Prendi un'altra carta, o stai? [C - S] ");
        }

        answer = sc.next();
        System.out.println();

        if (answer.compareToIgnoreCase("C") == 0) {
            this.hit();
            this.doubleDownAllowed = false;
        }
        else if (answer.compareToIgnoreCase("R") == 0 && this.doubleDownAllowed) {
            this.doubleDown();
        }
        else if (answer.compareToIgnoreCase("SS") == 0) {
            this.doubleDownAllowed = false;
        }
        else {
            this.stay();
        }
    }

    /**
     * Metodo che permette al giocatore di prenedre un'altra carta
     */
    private void hit() {
        System.out.println("\tHai scelto di prendere un'altra carta.\n");
        youDone = !you.addCardToPlayersHand(newDeck.dealingNextCard());
        you.printCardsInHand(true);
        System.out.printf("Il tuo punteggio:%d\t", you.getPlayersHandTotal());

        if (you.getPlayersHandTotal() > 21) {

            System.out.println("\n\t\t\t\t-----------------------");
            System.out.println("\n\t\t\t\t|       HAI PERSO     |");
            System.out.println("\n\t\t\t\t-----------------------\n");

            dealer.printCardsInHand(true);
            System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
            youDone = true;
            dealerDone = true;
        }
    }

    /**
     * Metodo che permette al giocatore di non prendere un'altra carta
     */
    private void stay() {

        System.out.println("\tHai scelto di stare, è il turno del banco. \n");
        youDone = true;
    }

    /**
     * Metodo che permette al giocatore di raddoppiare
     */
    private void doubleDown() {

        System.out.println("\tHai scelto di raddoppiare.\n");

        youDone = you.addCardToPlayersHand(newDeck.dealingNextCard());
        youDone = true;
        you.printCardsInHand(true);

        System.out.printf("Il tuo punteggio:%d\t", you.getPlayersHandTotal());

        if (you.getPlayersHandTotal() > 21) {

            System.out.println("\n\t\t\t\t-----------------------");
            System.out.println("\n\t\t\t\t|       HAI PERSO     |");
            System.out.println("\n\t\t\t\t-----------------------\n");

            dealer.printCardsInHand(true);
            System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
            youDone = true;
            dealerDone = true;
        }

        System.out.println("Ora è il turno del banco \n");
    }

    /**
     * Metodo che permette al dealer (computer) di giocare
     */
    private void dealersPlay() {
        if (dealer.getPlayersHandTotal() < 17) {

            dealer.printCardsInHand(true);
            System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
            System.out.println("\tIl banco ha preso un'altra carta \n");
            dealerDone = !dealer.addCardToPlayersHand(newDeck.dealingNextCard());

            if (dealer.getPlayersHandTotal() > 21) {

                dealer.printCardsInHand(true);
                System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
                System.out.println("\t\t\t\t-------------------------");
                System.out.println("\t\t\t\t|   IL BANCO HA PERSO   |");
                System.out.println("\t\t\t\t-------------------------\n");
                dealerDone = true;
            }
        }
        else {
            dealer.printCardsInHand(true);
            System.out.printf("Punteggio del banco:%d\n\n", dealer.getPlayersHandTotal());
            System.out.println("\tIl banco sta.\n");
            dealerDone = true;
        }
    }

    /**
     * Metodo che sceglie chi vince e chi perde, o se il risultato è pari
     */
    private void decideWinner() {

        int youSum = you.getPlayersHandTotal();
        int dealerSum = dealer.getPlayersHandTotal();

        if (youSum > dealerSum && youSum <= 21 || dealerSum > 21) {

            System.out.println("\t\t\t\t-------------------------");
            System.out.println("\t\t\t\t|       HAI VINTO       |");
            System.out.println("\t\t\t\t-------------------------\n");

            System.out.print("Hai vinto\t");
        }
        else if (youSum == dealerSum) {
            System.out.println("\t\t\t\t-------------------------");
            System.out.println("\t\t\t\t|         PARI          |");
            System.out.println("\t\t\t\t-------------------------\n");
        }
        else {
            System.out.println("\t\t\t\t-----------------------");
            System.out.println("\t\t\t\t|       HAI PERSO     |");
            System.out.println("\t\t\t\t-----------------------\n");
            System.out.print("Hai perso\n");
        }
    }
}
