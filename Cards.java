package dev.anto6314.blackJack;

public class Cards {

    private Suits cardSuit;
    private int cardNum;
    private String[] numString = {"Uno", "Due", "Tre", "Quattro", "Cinque", "Sei", "Sette", "Otto", "Nove", "Dieci", "Fante", "Regina", "Re"};

    /**
     * Costruttore della classe Cards
     *
     * @param stype
     * @param snum
     */
    public Cards(Suits stype, int snum) {

        this.cardSuit = stype;

        if (snum >= 1 && snum <= 13)
            this.cardNum = snum;
        else {

            System.err.println(snum + " is not a valid card number\n");
            System.exit(1);
        }
    }

    /**
     * Questo metodo ritorna il numero della carda
     *
     * @return
     */
    public int getCardNumber() {

        return this.cardNum;
    }

    /**
     * Questo metodo ritorna il numero della carta + il tipo di carta
     *
     * @return
     */
    public String toString() {
        return this.numString[this.cardNum - 1] + " di " + this.cardSuit.toString();
    }
}
