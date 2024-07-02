package by.senla.yukhnevich.menu.impl;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.menu.CustomMenu;
import by.senla.yukhnevich.reader.impl.CustomReaderImpl;
import by.senla.yukhnevich.security.impl.CustomSecurityImpl;
import by.senla.yukhnevich.service.impl.CustomServiceImpl;
import by.senla.yukhnevich.writer.impl.CustomWriterImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomMenuImpl implements CustomMenu {
    private final CustomReaderImpl customReaderImplement = new CustomReaderImpl();
    private final CustomServiceImpl serviceImplement = new CustomServiceImpl();
    private final CustomSecurityImpl securityAtm = new CustomSecurityImpl();
    private final CustomWriterImpl writerImplement = new CustomWriterImpl();

    /**
     * Method check card number, PIN  and is the card blocked.
     * If everything is fine then call controlPanel.
     */
    @Override
    public void start() throws CustomAtmException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type card number: ХХХХ-ХХХХ-ХХХХ-ХХХХ");
            List<String> cardInfo = customReaderImplement.findCard(scanner.nextLine());
            if (cardInfo != null) {
                System.out.println("Card is exist.");
                Card card = new Card(cardInfo.get(0), Integer.parseInt(cardInfo.get(1)), Float.parseFloat(cardInfo.get(2)), Boolean.parseBoolean(cardInfo.get(3)));
                if (securityAtm.checkBlock(card)) {
                    System.err.println("Your card is block! Contact support service!");
                    break;
                }
                System.out.println("Type pin: XXXX");
                try {
                    int i;
                    for (i = 0; i < 3; i++) {
                        String inputUser = scanner.nextLine();
                        if (inputUser != null && inputUser.matches("[0-9]+")) {
                            if (securityAtm.checkPin(card, Integer.parseInt(inputUser))) {
                                System.out.println("Welcome!");
                                controlPanel(card);
                                break;
                            } else {
                                System.err.println("Pin is wrong! Try again: ");
                            }
                        } else {
                            System.err.println("Pin is wrong! Try again: ");
                        }
                    }
                    if (i == 3) {
                        writerImplement.saveBlock(card);
                        System.err.println("Too many attempts. The card is blocked!");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Pin is wrong! Try again.");
                }
                System.out.println("End session");
                break;
            }
        }
    }

    @Override
    public void controlPanel(Card card) throws CustomAtmException {
        System.out.println("Choose operation: ");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("1 - Check balance");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Replenishment");
            System.out.println("0 - End session");
            String operation = scanner.nextLine();
            String amount;
            try {
                switch (operation) {
                    case ("1"):
                        System.out.println("Your balance: " + serviceImplement.checkBalance(card));
                        break;
                    case ("2"):
                        System.out.println("Type amount to withdraw: ");
                        amount = scanner.nextLine();
                        serviceImplement.withdraw(card, Integer.parseInt(amount));
                        break;
                    case ("3"):
                        System.out.println("Type amount to replenishment: ");
                        amount = scanner.nextLine();
                        serviceImplement.replenishment(card, Integer.parseInt(amount));
                        break;
                    case ("0"):
                        System.out.println("Exit...");
                        flag = false;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Type integer numbers!");
            }
        }
    }
}
