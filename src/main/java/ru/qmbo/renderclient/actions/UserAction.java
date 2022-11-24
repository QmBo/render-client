package ru.qmbo.renderclient.actions;

import ru.qmbo.renderclient.input.Input;
import ru.qmbo.renderclient.service.RestTemplateService;

/**
 * UserAction
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.06.2018
 */
public interface UserAction {
    /**
     * Key of Actiom.
     * @return kye.
     */
    int key();

    /**
     * Main Action.
     * @param input input.
     * @param tracker tracker.
     */
    void execute(Input input, RestTemplateService tracker);

    /**
     * Menu information of Action.
     * @return menu string.
     */
    String info();
}
