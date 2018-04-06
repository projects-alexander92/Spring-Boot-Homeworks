package com.todo.serviceImpl;

import com.todo.entities.Category;
import com.todo.entities.ToDoItem;
import com.todo.models.ToDoItemBindingModel;
import com.todo.models.ToDoItemEditModel;
import com.todo.models.ToDoItemViewModel;
import com.todo.repositories.CategoryRepository;
import com.todo.repositories.ToDoItemRepository;
import com.todo.services.ToDoItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoItemServiceImpl implements ToDoItemService
{

    private final ToDoItemRepository toDoItemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository, CategoryRepository categoryRepository, ModelMapper modelMapper)
    {
        this.toDoItemRepository = toDoItemRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ToDoItemViewModel> getAllItems()
    {
        List<ToDoItem> categories = this.toDoItemRepository.findAll();
        List<ToDoItemViewModel> categoryViewModels = new ArrayList<>();
        for (ToDoItem toDoItem : categories)
        {
            ToDoItemViewModel model = this.modelMapper.map(toDoItem, ToDoItemViewModel.class);
            categoryViewModels.add(model);
        }

        return categoryViewModels;
    }

    @Override
    public List<ToDoItemViewModel> getAllItemsByCategoryId(long categoryId)
    {
        List<ToDoItem> categories = this.toDoItemRepository.findAllByCategoryId(categoryId);
        List<ToDoItemViewModel> categoryViewModels = new ArrayList<>();
        for (ToDoItem toDoItem : categories)
        {
            ToDoItemViewModel model = this.modelMapper.map(toDoItem, ToDoItemViewModel.class);
            categoryViewModels.add(model);
        }

        return categoryViewModels;
    }

    @Override
    public ToDoItemViewModel getItemById(long id)
    {
        ToDoItem toDoItem = this.toDoItemRepository.findOne(id);
        ToDoItemViewModel model = this.modelMapper.map(toDoItem, ToDoItemViewModel.class);
        return model;
    }

    @Override
    public ToDoItemViewModel saveItem(ToDoItemBindingModel toDoItemBindingModel)
    {
        ToDoItem toDoItem = this.modelMapper.map(toDoItemBindingModel, ToDoItem.class);
        Category category = this.categoryRepository.findByName(toDoItemBindingModel.getCategoryName());
        toDoItem.setCategory(category);
        this.toDoItemRepository.save(toDoItem);
        return this.modelMapper.map(toDoItem, ToDoItemViewModel.class);
    }

    @Override
    @Transactional
    public void updateItem(long itemId, ToDoItemEditModel toDoItemEditModel) throws ParseException
    {
        ToDoItem item = new ToDoItem();
        item.setName(toDoItemEditModel.getName());
        item.setId(itemId);
        Category category = this.categoryRepository.findByName(toDoItemEditModel.getCategoryName());
        item.setCategory(category);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(toDoItemEditModel.getDeadline());
        item.setDeadline(date);
        this.toDoItemRepository.save(item);

    }

    @Override
    public void deleteById(long id)
    {
        this.toDoItemRepository.delete(id);
    }

    @Override
    public List<ToDoItemViewModel> getAllByCategoryAndName(String category, String itemName)
    {
        Category byName = this.categoryRepository.findByName(category);
        List<ToDoItem> allItems = this.toDoItemRepository.findAllByNameContainingAndCategory(itemName, byName);
        List<ToDoItemViewModel> items = allItems.stream().map(e -> this.modelMapper.map(e, ToDoItemViewModel.class)).collect(Collectors.toList());
        return items;
    }
}
