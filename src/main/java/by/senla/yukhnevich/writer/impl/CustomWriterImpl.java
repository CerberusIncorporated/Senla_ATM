package by.senla.yukhnevich.writer.impl;

import by.senla.yukhnevich.entity.Card;
import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.utils.FileNameValidation;
import by.senla.yukhnevich.writer.CustomWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class implements CustomWriter. Class save changes in file
 *
 * @see CustomWriter
 */
public class CustomWriterImpl implements CustomWriter {
    private static final String PATH = "src\\main\\resources\\data\\cards.txt";
    private static final String PATH_ATM = "src\\main\\resources\\data\\atm_balance.txt";
    private static final String PATH_BLOCK = "src\\main\\resources\\data\\cards_block.txt";

    @Override
    public void saveBalance(Card card, float oldMoney) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        List<String> newLines = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                if (line.contains(card.getCardNumber())) {
                    newLines.add(line.replace(Float.toString(oldMoney), Float.toString(card.getBalance())));
                } else {
                    newLines.add(line);
                }
            }
            Files.write(Paths.get(PATH), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CustomAtmException("Reading from the file impossible");
        }
    }

    @Override
    public void saveBlock(Card card) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH) && !FileNameValidation.validateFile(PATH_BLOCK)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        List<String> newLines = new ArrayList<>();
        List<String> newLinesBlock = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                if (line.contains(card.getCardNumber())) {
                    card.setBlocked(true);
                    newLines.add(line.replace("false", Boolean.toString(card.isBlocked())));
                } else {
                    newLines.add(line);
                }
            }

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);

            newLinesBlock.add(card.getCardNumber() + " " + formattedDateTime);

            Files.write(Paths.get(PATH), newLines, StandardCharsets.UTF_8);
            Files.write(Paths.get(PATH_BLOCK), newLinesBlock, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CustomAtmException("Reading from the file impossible");
        }

    }

    @Override
    public void unblock(Card card) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH) && !FileNameValidation.validateFile(PATH_BLOCK)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        List<String> newLines = new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                if (line.contains(card.getCardNumber())) {
                    card.setBlocked(false);
                    newLines.add(line.replace("true", Boolean.toString(card.isBlocked())));
                } else {
                    newLines.add(line);
                }
            }
            Files.write(Paths.get(PATH), newLines, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new CustomAtmException("Reading from the file impossible");
        }

    }


    @Override
    public void saveAtmBalance(int money) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH_ATM)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        try {
            Files.write(Paths.get(PATH_ATM), Collections.singleton(Integer.toString(money)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CustomAtmException(e);
        }
    }
}
