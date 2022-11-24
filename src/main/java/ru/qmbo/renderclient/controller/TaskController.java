package ru.qmbo.renderclient.controller;

import org.springframework.web.bind.annotation.*;
import ru.qmbo.renderclient.model.Task;
import ru.qmbo.renderclient.service.MenuService;


/**
 * TaskController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final MenuService service;

    public TaskController(MenuService service) {
        this.service = service;
    }

    /**
     * Create task task.
     *
     * @param request the request
     * @return the task
     */
    @PostMapping
    public Task createTask(@RequestBody Task request) {
        return this.service.updateStatus(request);
    }
}
