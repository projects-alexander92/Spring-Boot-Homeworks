package com.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ToDoItemBindingModel {

    private String name;

    private Date deadline;


    private String categoryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
