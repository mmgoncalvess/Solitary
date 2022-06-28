package com.example.model;

import com.example.enums.Rank;

public class FinalStack extends Stack {

    @Override
    public boolean setCard(Card card) {
        if (card == null) return false;
        if (getCard(getSize()-1) == null) {
            if (card.getRank() == Rank.ACE) {
                arrayList.add(card);
                return true;
            }
        } else {
            if (getCard(getSize()-1).getSuit() == card.getSuit()) {
                if (getCard(getSize()-1).getRank().compareTo(card.getRank()) == -1) {
                    arrayList.add(card);
                    return true;
                }
            }
        }
        return false;
    }
}
