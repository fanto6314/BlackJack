package test.dev.anto6314.blackJack2;

public class Players {

    private String playerName;
    private Cards[] playerHand = new Cards[10];
    private int numCardsInHand;


    public Players(String name){

        this.playerName = name;
        this.emptyHand();
    }

    public void emptyHand(){

        for(int hc=0; hc<10;hc++){

            this.playerHand[hc] = null;
        }

        this.numCardsInHand = 0;

    }

    public boolean addCardToPlayersHand(Cards card){

        if(this.numCardsInHand == 10){

            System.err.printf("%s's hand already has 10 cards; cannot add more cards", this.playerName);
            System.exit(1);
        }

        this.playerHand[this.numCardsInHand] = card;
        this.numCardsInHand++;

        return (this.getPlayersHandTotal() <=21);

    }

    public int getPlayersHandTotal(){

        int handTotal = 0;
        int cardNum;
        int numAces = 0;

        //System.out.printf("getPlayersHandTotal :%d\n ", this.numCardsInHand);

        for(int c =0; c<this.numCardsInHand;c++){

            cardNum = this.playerHand[c].getCardNumber();
            //System.out.printf("getPlayersHandTotal: %s\n%d", this.playerHand[c], cardNum);

            if(cardNum == 1){ // Ace

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