package by.senla.yukhnevich.menu;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;

/**
 * Interface that describes what an ATM menu should have
 */
public interface CustomMenu {
    void start() throws CustomAtmException;

    void controlPanel(Card card) throws CustomAtmException;
}
