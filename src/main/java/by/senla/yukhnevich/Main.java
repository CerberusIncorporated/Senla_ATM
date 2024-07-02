package by.senla.yukhnevich;

import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.menu.impl.CustomMenuImpl;

/**
 * Class need for start atm
 */
public class Main {
    public static void main(String[] args) throws CustomAtmException {
        CustomMenuImpl menu = new CustomMenuImpl();
        menu.start();
    }
}