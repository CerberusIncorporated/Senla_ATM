package by.senla.yukhnevich.writer;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;

/**
 * Interface that describes what writer should have
 */
public interface CustomWriter {

    void saveBalance(Card card, float oldMoney) throws CustomAtmException;

    void saveAtmBalance(int money) throws CustomAtmException;

    void saveBlock(Card card) throws CustomAtmException;

    void unblock(Card card) throws CustomAtmException;
}
