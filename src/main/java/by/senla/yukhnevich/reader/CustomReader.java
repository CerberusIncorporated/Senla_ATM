package by.senla.yukhnevich.reader;

import by.senla.yukhnevich.exception.CustomAtmException;

import java.util.List;

/**
 * Interface that describes what reader should have
 */
public interface CustomReader {
    List<String> findCard(String cardNum) throws CustomAtmException;

    int timeForUnblockCard(String cardNum) throws CustomAtmException;

    int atmBalance() throws CustomAtmException;
}
