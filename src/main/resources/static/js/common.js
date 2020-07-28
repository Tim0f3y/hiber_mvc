
$.ajax({
        type: 'GET',
        url: '/api/all/user',
        success: function (currentUser) {
            $('#logged')
                .append("<tr> \
                               <td>" + currentUser.id + "</td> \
                               <td>" + currentUser.name + "</td> \
                               <td>" + currentUser.last + "</td> \
                               <td>" + currentUser.age + "</td> \
                               <td>" + currentUser.login + "</td> \
                               <td>" + currentUser.role.map(role => role.role).join(' ') + "</td> \
                        </tr>")
            $('#topLogin')
                .append("<span>" + currentUser.login + " with roles: "+ currentUser.role.map(role => role.role).join(' ') + "</span>")

        }
    });