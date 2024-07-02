package by.senla.yukhnevich.service;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;
/**
 * Interface that describes what Atm should have
 */
public interface CustomService {
    float checkBalance(Card card);
    void withdraw(Card card, int amount) throws CustomAtmException;
    void replenishment(Card card, float amount) throws CustomAtmException;
}
