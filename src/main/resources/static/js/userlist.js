const ROLES = ['ROLE_ADMIN', 'ROLE_USER'];
$(function () {
    $('#delete').on('click', event => deleteUser(event));
    $('#editmodaluser').on('click', event => editUser(event));
    $('#save').on('click', saveUser);
    showUserList();
})
function saveUser(e) {
    e.preventDefault();
    let newUser = {
        name: $('#add-name').val(),
        last: $('#add-last').val(),
        age: $('#add-age').val(),
        login: $('#add-login').val(),
        password: $('#add-password').val(),
        role: []
    };
    $('#add-roles').find('option:selected').each((i, option) => {
        newUser['role'].push({'role': $(option).val()});

    });
    console.log("user:");
    console.log(newUser);
    $.ajax({
        url: '/api/save',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(newUser),
        complete: function (data) {
            $('#admin-home').tab('show')
            showUserList();
        }
    });
}

function showUserList() {
    $('#list').empty()
    $.ajax({
        type: 'GET',
        url: '/api/list',
        success: function (data) {
            data.forEach(el => {
                $('#list')
                    .append("<tr> \
                               <td>" + el.id + "</td> \
                               <td>" + el.name + "</td> \
                               <td>" + el.last + "</td> \
                               <td>" + el.age + "</td> \
                               <td>" + el.login + "</td> \
                               <td>" + el.role.map(role => role.role).join(' ') + "</td> \
                               <td><button type='button' class='btn btn-info' onclick='editForm(" + el.id + ")'>Edit</button></td> \
                                    <td><button type='button' class='btn btn-danger' onclick='deleteForm(" + el.id + ")'>Delete</button></td> \
                        </tr>")
            })
        }
    });
}

function editForm(id) {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/api/getUserById?id=' + id,
        contentType: 'application/json',
        success: function (user) {
            console.log("user:");
            console.log(user);
            $('#edit-id').val(user.id);
            $('#edit-name').val(user.name);
            $('#edit-last').val(user.last);
            $('#edit-age').val(user.age);
            $('#edit-login').val(user.login);
            $('#edit-password').val(user.password);
            const editRoles = $('#edit-roles');
            const userRoles = user.role.map(r => r.role);
            console.log("userRoles:");
            console.log(userRoles);
            editRoles.empty();
            ROLES.forEach(role => editRoles.append("<option value='" + role + "'"
                + (userRoles.includes(role) ? " selected" : "") + ">"
                + role + "</option>"));
            $('#edit-modal').modal('show');
        }
    })
}

function editUser(e) {
    e.preventDefault();
    let editUser = {
        id: $('#edit-id').val(),
        name: $('#edit-name').val(),
        last: $('#edit-last').val(),
        age: $('#edit-age').val(),
        login: $('#edit-login').val(),
        password: $('#edit-password').val(),
        role: []
    };

    $('#edit-roles').find('option:selected').each((i, option) => {
        editUser['role'].push({'role': $(option).val()});
    });
    console.log("user:");
    console.log(editUser);
    $.ajax({
        url: '/api/edit',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(editUser),
        complete: function (data) {
            $('#edit-modal').modal('hide');
            showUserList();
        }
    });
}

function deleteForm(id) {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: '/api/getUserById?id=' + id,
        contentType: 'application/json',
        success: function (user) {
            console.log("user:");
            console.log(user);
            $('#delete-id').val(user.id);
            $('#delete-name').val(user.name);
            $('#delete-last').val(user.last);
            $('#delete-age').val(user.age);
            $('#delete-login').val(user.login);
            const editRoles = $('#delete-roles');
            const userRoles = user.role.map(r => r.role);
            console.log("userRoles:");
            console.log(userRoles);
            editRoles.empty();
            ROLES.forEach(role => editRoles.append("<option value='" + role + "'"
                + (userRoles.includes(role) ? " selected" : "") + ">"
                + role + "</option>"));
            $('#delete-modal').modal('show');
        }
    })
}



function deleteUser(e) {
    e.preventDefault();
    const id = $('#delete-id').val();
    $.ajax({
        url: "/api/delete",
        data: {"id": id},
        contentType: "application/json",
        dataType: 'json',
        complete: function (data) {
            $('#delete-modal').modal('hide');
            showUserList();
        }
    });
}

