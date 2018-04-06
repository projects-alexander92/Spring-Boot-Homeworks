package com.todo.services;

import com.todo.models.ToDoItemBindingModel;
import com.todo.models.ToDoItemEditModel;
import com.todo.models.ToDoItemViewModel;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

public interface ToDoItemService {

    List<ToDoItemViewModel> getAllItems();

    List<ToDoItemViewModel> getAllItemsByCategoryId(long categoryId);

    ToDoItemViewModel getItemById(long id);

    ToDoItemViewModel saveItem(ToDoItemBindingModel toDoItemBindingModel);

    @Transactional
    void updateItem(long itemId, ToDoItemEditModel toDoItemBindingModel) throws ParseException;

    void deleteById(long id);

    List<ToDoItemViewModel> getAllByCategoryAndName(String category, String itemName);
}
