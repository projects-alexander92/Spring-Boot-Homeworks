$(document).ready(() => {
    const startingCategoryId = 1;
    getAllItemsByCategoryId(startingCategoryId);
    //manage Categories
    $('#sideMenu').click(ev => {
        let eventTareClassName = ev.target.className;
        if (eventTareClassName === 'list-group-item') {
            $('#listGroupSideMenu').find('.list-group-item').each((i, e) => {
                $(e).removeClass('active');
            });
            $(ev.target).addClass('active');
            let id = ev.target.getAttribute('categoryId');
            getAllItemsByCategoryId(id);
        }
    });
    // Search for items
    $('#searchItem').keyup((ev) => {
        ev.preventDefault();
        let itemName = ev.target.value;
        let category = $('#listGroupSideMenu').find('.list-group-item.active').first().text();
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            type: 'GET',
            url: '/items/search',
            data: {itemName: itemName, categoryName: category}
        }).done((objArray) => {
            $('#toDoItemsList').empty();
            objArray.forEach(responseObject => {
                createToDoItem(responseObject);
            })
        })

    });
    //Stop the button from refreshing the page, we don't need it either way
    $('#searchItemButton').click((ev) => {
        ev.preventDefault();
    });
    //Manage add/cancel/save task
    $('#addItemsMenu').click(ev => {
        let eventTargetId = ev.target.id;
        let menu = $('#addNewTaskMenu');
        switch (eventTargetId) {
            case 'addTask':
                ev.preventDefault();
                menu.fadeIn(1000);
                break;
            case 'cancelTask':
                ev.preventDefault();
                menu.fadeOut(1000);
                break;
            case 'saveTask':
                ev.preventDefault();
                let toDoItem = getItemParameters();
                $.ajax({
                    contentType: 'application/json',
                    dataType: 'json',
                    type: 'PUT',
                    url: '/items/save',
                    data: JSON.stringify(toDoItem)
                }).done((responseObject) => {
                    createToDoItem(responseObject)
                });
                menu.fadeOut(1000);
                break;
        }
    });
    //create todoItems dom elements
    let createToDoItem = (toDoItemObject) => {
        let date = new Date(toDoItemObject.deadline);
        date = date.toISOString().slice(0, 10);
        $('#toDoItemsList')
            .append
            (
                $('<div>').attr('toDoItemId', toDoItemObject.id).addClass('row')
                    .change((ev) => {
                        let todoItem = $(ev.target).parent();
                        let id = todoItem.attr('toDoItemId');
                        let name = todoItem.children()[0].value;
                        let date = todoItem.children()[1].value;
                        let item = {
                            name: name,
                            deadline: date,
                            categoryName: $('#listGroupSideMenu').find('.list-group-item.active').first().text()
                        };
                        $.ajax({
                            contentType: 'application/json',
                            dataType: 'json',
                            type: 'POST',
                            url: '/items/update/' + id,
                            data: JSON.stringify(item)
                        })
                    })
                    .keyup((ev) => {
                            if (ev.keyCode === 46) {
                                let todoItem = $(ev.target).parent();
                                let id = todoItem.attr('toDoItemId');
                                todoItem.remove();
                                $.ajax({
                                    contentType: 'application/json',
                                    type: 'DELETE',
                                    url: '/items/delete/' + id,
                                })
                            }
                        }
                    )
                    .append($('<input/>').val(toDoItemObject.name).addClass('col-sm-6'))
                    .append($('<input/>').val(date).addClass('col-sm-6'))
            )
    };
    function getAllItemsByCategoryId(id) {
        $.ajax({
            url: '/items/' + id,
            type: 'GET',
            dataType: 'json'
        }).done((objArray) => {
            $('#toDoItemsList').empty();
            objArray.forEach(responseObject => {
                createToDoItem(responseObject);
            })
        })
    }
    let getItemParameters = () => {
        let toDoItem = {};
        let name = $('#taskName').val();
        let deadLine = $('#taskDeadLine').val();
        let categoryName = $('#listGroupSideMenu').find('.list-group-item.active').first().text();
        toDoItem.name = name;
        toDoItem.deadline = deadLine;
        toDoItem.categoryName = categoryName;
        return toDoItem;
    };
});
