package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;

import java.util.Collections;

public class Deck extends Stack {


    public Deck() {
        initialize();
    }

    public void next() {
        if (arrayList.size() > 1) {
            Card card = arrayList.remove(arrayList.size() - 1);
            card.setCovered(true);
            arrayList.add(0, card);
        }
    }

    private void initialize() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                arrayList.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(arrayList);
    }
}
