package by.senla.yukhnevich.reader.impl;

import by.senla.yukhnevich.exception.CustomAtmException;
import by.senla.yukhnevich.reader.CustomReader;
import by.senla.yukhnevich.utils.FileNameValidation;
import by.senla.yukhnevich.utils.RegexAtmValidation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomReaderImpl implements CustomReader {
    private static final String PATH = "src\\main\\resources\\data\\cards.txt";
    private static final String PATH_ATM = "src\\main\\resources\\data\\atm_balance.txt";
    private static final String PATH_BLOCK = "src\\main\\resources\\data\\cards_block.txt";

    @Override
    public List<String> findCard(String cardNumber) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH)) {
            throw new CustomAtmException("Reading from the file impossible");
        }

        try {
            if (RegexAtmValidation.validateRegex(cardNumber)) {

                for (String line : Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8)) {
                    if (line.contains(cardNumber)) {
                        String[] parts = line.split(" ");
                        String card = parts[0];
                        String pin = parts[1];
                        String balance = parts[2];
                        String isBlock = parts[3];
                        System.out.println(card);
                        return new ArrayList<>(Arrays.asList(card, pin, balance, isBlock));
                    }
                }
            }
            System.err.println("Card is not exist");
        } catch (IOException e) {
            throw new CustomAtmException(e);
        }
        return Collections.emptyList();
    }

    @Override
    public int atmBalance() throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH_ATM)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        try {
            return Integer.parseInt(Files.readString(Paths.get(PATH_ATM), StandardCharsets.UTF_8).replaceAll("\\s+", ""));
        } catch (IOException e) {
            throw new CustomAtmException(e);
        }
    }

    @Override
    public int timeForUnblockCard(String cardNumber) throws CustomAtmException {
        if (!FileNameValidation.validateFile(PATH_BLOCK)) {
            throw new CustomAtmException("Reading from the file impossible");
        }
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateTimeToString = dateTime.format(formatter);

        try {
            for (String line : Files.readAllLines(Paths.get(PATH_BLOCK), StandardCharsets.UTF_8)) {
                if (line.contains(cardNumber)) {
                    String[] parts = line.split(" ");
                    String date = parts[1];
                    String time = parts[2];
                    String oldDateTime = date + " " + time;
                    System.out.println("Your card was blocked at: " + oldDateTime);
                    LocalDateTime oldDateTimeToDate = LocalDateTime.parse(oldDateTime, formatter);
                    LocalDateTime nowDateTimeToDate = LocalDateTime.parse(dateTimeToString, formatter);
                    Duration duration = Duration.between(oldDateTimeToDate, nowDateTimeToDate);
                    return (int) duration.toDays();
                }
            }
        } catch (IOException e) {
            throw new CustomAtmException(e);
        }
        return 0;
    }
}
