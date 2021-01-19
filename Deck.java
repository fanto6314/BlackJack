package dev.anto6314.blackJack;

import java.util.Random;

public class Deck {

    private Cards[] cardsInDeck;
    private int numOfCardsInDeck;
    private int onePack = 52;

    /**
     * costruttore per il mescolamento di base del mazzo (considerando un pacco da 52 carte)
     */
    public Deck(){
        this(1, true);
    }

    /**
     * Costruttore che definisce il numero di carte in un Deck, e se deve essere mescolato oppure no
     * @param numPacks
     * @param shuffle
     */
    public Deck(int numPacks, boolean shuffle){
        this.numOfCardsInDeck = numPacks*this.onePack;
        this.cardsInDeck = new Cards[this.numOfCardsInDeck];

        int c = 0;

        for(int d=0;d<numPacks;d++){
            for(int s=0; s<4;s++){
                for(int n=1;n<=13;n++){
                    this.cardsInDeck[c] = new Cards(Suits.values()[s], n);
                    c++;
                }
            }
        }

        if(shuffle){
            this.shuffleDeck();
        }
    }
    /**
     * Metodo che mescola le carte tra di loro
     */
    public void shuffleDeck(){
        Random rng = new Random();
        Cards temp;
        int j;
        for(int i=0; i<this.numOfCardsInDeck;i++){
            j = rng.nextInt(this.numOfCardsInDeck);

            temp = this.cardsInDeck[i];
            this.cardsInDeck[i] = this.cardsInDeck[j];
            this.cardsInDeck[j] = temp;
        }
    }

    /**
     * Metodo che distribuisce le carte
     * @return
     */
    public Cards dealingNextCard(){
        Cards topCard = this.cardsInDeck[0];

        for(int c =1; c<this.numOfCardsInDeck;c++){
            this.cardsInDeck[c-1] = this.cardsInDeck[c];
        }

        this.cardsInDeck[this.numOfCardsInDeck - 1] = null;
        this.numOfCardsInDeck--;

        return topCard;
    }

    /**
     * Metodo che stampa le carte
     * @param num
     */
    public void printDeckCards(int num){
        for(int c=0; c<num; c++){
            System.out.printf("% 3d/%d %s\n", c+1, this.numOfCardsInDeck, this.cardsInDeck[c].toString());
        }

        System.out.printf("\t\t[%d other]\n", this.numOfCardsInDeck - num);
    }
}
