package by.senla.yukhnevich.security;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;

/**
 * Interface for checking the PIN and blocking
 */
public interface CustomSecurity {
    boolean checkPin(Card card, int inputUserPin);

    boolean checkBlock(Card card) throws CustomAtmException;
}
