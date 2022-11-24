package ru.qmbo.renderclient.service;

import ru.qmbo.renderclient.input.ConsoleInput;
import ru.qmbo.renderclient.input.Input;
import ru.qmbo.renderclient.input.ValidateInput;

/**
 * StarService
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.06.2018
 */
public class StartService {
    private final Input input;
    private final RestTemplateService tracker;

    public StartService(Input input, RestTemplateService tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuService menu = new MenuService((ValidateInput) this.input, this.tracker);
        int[] range = menu.getRange();
        while (true) {
            menu.show();
            int key = this.input.ask("", range);
            menu.select(key);
        }
    }

    public static void start() {
        new StartService(
                new ValidateInput(
                        new ConsoleInput()
                ), new RestTemplateService()
        ).init();
    }
}