package ru.qmbo.renderclient.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.qmbo.renderclient.actions.BaseAction;
import ru.qmbo.renderclient.actions.UserAction;
import ru.qmbo.renderclient.input.ConsoleInput;
import ru.qmbo.renderclient.input.Input;
import ru.qmbo.renderclient.input.ValidateInput;
import ru.qmbo.renderclient.model.Stats;
import ru.qmbo.renderclient.model.Task;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MenuService
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.06.2018
 */
@Service
public class MenuService {
    private final RestTemplateService tracker;
    private final Input input;
    private final UserAction[] actions = new UserAction[5];
    private int position = 0;

    public MenuService(ValidateInput input, RestTemplateService tracker) {
        this.input = input;
        this.tracker = tracker;
        this.fillAction();
    }

    private void fillAction() {
        this.actions[this.position] = this.new CreateUser(this.position, "Создать пользователя.");
        this.actions[++this.position] = this.new LoginUser(this.position, "Войти в систему.");
        this.actions[++this.position] = this.new CreatTask(this.position, "Создать заявку.");
        this.actions[++this.position] = this.new ShowListTask(this.position, "Показать все заявки.");
        this.actions[++this.position] = this.new ShowStats(this.position, "История изменения статусов.");
    }

    public int[] getRange() {
        int[] result = new int[this.actions.length];
        int index = 0;
        for (UserAction action : actions) {
            result[index++] = action.key();
        }
        return result;
    }

    public void show() {
        System.out.println("Меню.");
        for (UserAction action: this.actions) {
            if (action == null) {
                break;
            }
            System.out.println(action.info());
        }
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    private void showItem(Task item) {
        System.out.printf("ID: %s, Status: %s%s",
                item.getId(), item.getStatus(), System.lineSeparator());
    }

    private void showStats(Stats item) {
        System.out.printf("ID: %s, Status: %s, Time %s%s",
                item.getId(), item.getStatus(), item.getDate(), System.lineSeparator());
    }

    public Task updateStatus(Task request) {
        showItem(new Task().setId(request.getId()).setStatus(request.getStatus()));
        return request;
    }

    private class CreateUser extends BaseAction {
        protected CreateUser(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, RestTemplateService tracker) {
            System.out.println("------------ Создание нового пользователя --------------");
            String login = input.ask("Введите логин :");
            String pass = input.ask("Введите пароль :");
            String status = tracker.createUser(login, pass);
            if ("ok".equals(status)) {
                System.out.println("------------ Пользователь создан -----------");
            } else {
                System.out.println("------------ Такое имя уже занято -----------");
            }
        }
    }

    private class ShowListTask extends BaseAction {
        protected ShowListTask(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, RestTemplateService tracker) {
            List<Task> show = new ArrayList<>(100);
            Collections.addAll(show, tracker.getAll());
            if (show.isEmpty()) {
                System.out.println("------------ Заявок нет ------------");
            } else {
                System.out.println("------------ Все заявки ------------");
                for (Task item : show) {
                    showItem(item);
                }
            }
        }
    }

    private class ShowStats extends BaseAction {
        protected ShowStats(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, RestTemplateService tracker) {
            List<Stats> show = new ArrayList<>(100);
            Collections.addAll(show, tracker.getStats());
            if (show.isEmpty()) {
                System.out.println("------------ Заявок нет ------------");
            } else {
                System.out.println("------------ Все заявки ------------");
                for (Stats item : show) {
                    showStats(item);
                }
            }        }
    }

    private class CreatTask extends BaseAction {
        protected CreatTask(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, RestTemplateService tracker) {
            Task task = tracker.createTask();
            if (task != null) {
                System.out.println("------------ Заявка принята ------------");
                showItem(task);
            } else {
                System.out.println("------------ Что-то пошло не так ------------");
            }
        }
    }

    private class LoginUser extends BaseAction {
        protected LoginUser(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, RestTemplateService tracker) {
            System.out.println("------------ Авторизация --------------");
            String login = input.ask("Введите логин :");
            String pass = input.ask("Введите пароль :");
            String status = tracker.loginUser(login, pass);
            if ("ok".equals(status)) {
                System.out.println("------------ Удачная авторизация -----------");
            } else {
                System.out.println("------------ Неудачная авторизация -----------");
            }        }
    }
}