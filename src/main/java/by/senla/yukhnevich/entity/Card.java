package by.senla.yukhnevich.entity;

import java.util.Objects;

public class Card {
    private final String cardNumber;
    private int pin;
    private float balance;
    private boolean isBlocked;

    public Card(String cardNumber, int pin, float balance, boolean isBlocked) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return pin == card.pin && Float.compare(card.balance, balance) == 0 && isBlocked == card.isBlocked && Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, pin, balance, isBlocked);
    }
}
