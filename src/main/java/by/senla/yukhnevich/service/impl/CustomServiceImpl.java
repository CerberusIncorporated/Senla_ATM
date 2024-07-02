package by.senla.yukhnevich.service.impl;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.reader.impl.CustomReaderImpl;
import by.senla.yukhnevich.service.CustomService;
import by.senla.yukhnevich.writer.impl.CustomWriterImpl;

public class CustomServiceImpl implements CustomService {
    private final CustomWriterImpl customWriter = new CustomWriterImpl();
    private final CustomReaderImpl customReader = new CustomReaderImpl();

    @Override
    public float checkBalance(Card card) {
        return card.getBalance();
    }

    @Override
    public void withdraw(Card card, int amount) throws CustomAtmException {
        int atmMoney = customReader.atmBalance();
        if (amount <= 0) {
            System.err.println("Type number more than zero.");
        } else if (card.getBalance() >= amount && atmMoney >= amount) {
            float oldMoney = card.getBalance();
            float money = card.getBalance() - amount;
            card.setBalance(money);
            atmMoney -= amount;
            customWriter.saveAtmBalance(atmMoney);
            customWriter.saveBalance(card, oldMoney);
            System.out.println("Withdraw complete successful!");
        } else {
            System.out.println("Not enough money on the card or at the ATM.");
        }
    }

    @Override
    public void replenishment(Card card, float amount) throws CustomAtmException {
        int atmMoney = customReader.atmBalance();
        if (amount <= 0) {
            System.err.println("Type number more than zero.");
        } else if (amount < 1000000) {
            float oldMoney = card.getBalance();
            float money = card.getBalance() + amount;
            card.setBalance(money);
            atmMoney += amount;
            customWriter.saveAtmBalance(atmMoney);
            customWriter.saveBalance(card, oldMoney);
            System.out.println("Replenishment complete successful!");
        } else {
            System.out.println("Replenishment should be no more than 1 000 000");
        }

    }
}
