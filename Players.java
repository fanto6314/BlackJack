package dev.anto6314.blackJack;

public class Players {

    private String playerName;
    private Cards[] playerHand = new Cards[10];
    private int numCardsInHand;

    /**
     * Costruttore della classe Players
     * @param name
     */
    public Players(String name){

        this.playerName = name;
        this.emptyHand();
    }

    /**
     * Questo metodo serve per svuotare la mano del giocatore
     */
    public void emptyHand(){
        for(int hc=0; hc<10;hc++){
            this.playerHand[hc] = null;
        }
        this.numCardsInHand = 0;
    }

    /**
     * Questo metodo serve per aggiungere delle carte al mazzo del giocatore
     * @param card
     * @return
     */
    public boolean addCardToPlayersHand(Cards card){
        if(this.numCardsInHand == 10){
            System.err.printf("%s's hand already has 10 cards; cannot add more cards", this.playerName);
            System.exit(1);
        }

        this.playerHand[this.numCardsInHand] = card;
        this.numCardsInHand++;

        return (this.getPlayersHandTotal() <=21);
    }

    /**
     * Questo metodo controlla le carte del giocatore e ritorna la somma delle carte
     * @return
     */
    public int getPlayersHandTotal(){
        int handTotal = 0;
        int cardNum;
        int numAces = 0;

        for(int c =0; c<this.numCardsInHand;c++){

            cardNum = this.playerHand[c].getCardNumber();

            if(cardNum == 1){
                numAces++;
                handTotal += 11;
            }
            else if(cardNum >= 10){
                handTotal += 10;
            }
            else{
                handTotal += cardNum;
            }
        }
        while(handTotal > 21 && numAces > 0){
            handTotal -= 10;
            numAces--;
        }
        return handTotal;
    }

    /**
     * Questo metodo stampa le carte che il giocatore ha in mano
     * @param showFirstCard
     */
    public void printCardsInHand(boolean showFirstCard){

        System.out.println("Carte che "+this.playerName+" ha in mano:");
        for(int c=0; c<this.numCardsInHand;c++){

            if(!showFirstCard && c==0){
                System.out.printf("\t[hidden]\n");
            }
            else{
                System.out.printf("\t%s\n\n", this.playerHand[c]);
            }
        }
    }

    public boolean splitPossible(){
        if(this.numCardsInHand == 2 && (this.playerHand[0].getCardNumber() == this.playerHand[1].getCardNumber())){
            return true;
        }
        else{
            return false;
        }
    }
}
