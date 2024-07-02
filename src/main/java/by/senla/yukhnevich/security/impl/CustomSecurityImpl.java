package by.senla.yukhnevich.security.impl;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.reader.impl.CustomReaderImpl;
import by.senla.yukhnevich.security.CustomSecurity;
import by.senla.yukhnevich.writer.impl.CustomWriterImpl;

/**
 * Class for checking the PIN and blocking
 *
 * @see CustomSecurity
 */
public class CustomSecurityImpl implements CustomSecurity {
    private final CustomReaderImpl customReaderImplement = new CustomReaderImpl();
    private final CustomWriterImpl writerImplement = new CustomWriterImpl();


    @Override
    public boolean checkPin(Card card, int inputUserPin) {
        return card.getPin() == inputUserPin;
    }

    @Override
    public boolean checkBlock(Card card) throws CustomAtmException {
        if (card.isBlocked()) {
            if (customReaderImplement.timeForUnblockCard(card.getCardNumber()) >= 1) {
                System.out.println("You're block has passed. Removing block from card.");
                writerImplement.unblock(card);
                return card.isBlocked();
            }
        }
        return card.isBlocked();
    }
}
