package com.todo.controllers;

import com.todo.models.ToDoItemBindingModel;
import com.todo.models.ToDoItemEditModel;
import com.todo.models.ToDoItemViewModel;
import com.todo.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class ToDoListController
{
    private final ToDoItemService toDoItemService;

    @Autowired
    public ToDoListController(ToDoItemService toDoItemService)
    {
        this.toDoItemService = toDoItemService;
    }

    @PutMapping("/items/save")
    public ResponseEntity saveItem(@RequestBody ToDoItemBindingModel toDoItemBindingModel)
    {
        ToDoItemViewModel toDoItemViewModel = this.toDoItemService.saveItem(toDoItemBindingModel);

        return new ResponseEntity(toDoItemViewModel, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity getItemsByCategory(@PathVariable long id)
    {
        List<ToDoItemViewModel> items = this.toDoItemService.getAllItemsByCategoryId(id);
        return new ResponseEntity(items, HttpStatus.OK);
    }

    @PostMapping("/items/update/{id}")
    public ResponseEntity updateItem(@RequestBody ToDoItemEditModel toDoItemBindingModel, @PathVariable long id) throws ParseException
    {
        this.toDoItemService.updateItem(id, toDoItemBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/items/delete/{id}")
    public ResponseEntity deleteItem(@PathVariable long id)
    {
        this.toDoItemService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("items/search")
    public ResponseEntity getItemByCategoryNameAndItemSubstring(@RequestParam String itemName, @RequestParam String categoryName)
    {
        List<ToDoItemViewModel> items = this.toDoItemService.getAllByCategoryAndName(categoryName, itemName);
        return new ResponseEntity(items, HttpStatus.OK);
    }
}
