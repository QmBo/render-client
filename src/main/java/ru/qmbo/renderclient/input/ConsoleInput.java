package ru.qmbo.renderclient.input;

import org.springframework.stereotype.Component;

import java.util.Scanner;
/**
 * ConsoleInput class.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.06.2018
 */
@Component
public class ConsoleInput implements Input {
    /**
     * Console scanner.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Ask question and wait answer.
     * @param question question.
     * @return answer.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    /**
     * Validate ask.
     * @param question question.
     * @param range valid values.
     * @return answer.
     */
    @Override
    public int ask(String question, int[] range) {
        return Integer.parseInt(this.ask(question));
    }
}
