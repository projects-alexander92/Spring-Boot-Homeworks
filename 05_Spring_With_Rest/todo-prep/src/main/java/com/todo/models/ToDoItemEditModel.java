package com.todo.models;

public class ToDoItemEditModel
{

    private String name;

    private String deadline;
    private String categoryName;

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeadline()
    {
        return this.deadline;
    }

    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }
}
