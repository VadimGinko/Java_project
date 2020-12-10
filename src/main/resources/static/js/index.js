load = async () => {
    let jwt = localStorage.getItem("jwt");
    if(jwt== null){
        document.location.href = "/login";
    }
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    let length = Object.keys(user_info_json).length;
    document.getElementById("username").innerHTML = user_info_json.username;

    let ulk = document.getElementById("keys");
    let ulv = document.getElementById("values");

    ulk.innerHTML += "<li>username</li>"

    ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.username + "</li>";

    //admin
    if(length == 8)
    {
        ulk.innerHTML += "<li>First Name</li>"
        ulk.innerHTML += "<li>Surname</li>"
        ulk.innerHTML += "<li>Email</li>"
        ulk.innerHTML += "<li>Middle Name</li>"
        ulk.innerHTML += "<li>Faculty</li>"
        ulk.innerHTML += "<li>Course</li>"
        ulk.innerHTML += "<li>Group</li>"

        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.firstName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.lastName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.email + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.middleName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.facultyName.faculty + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.userCourse + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.userGroup + "</li>";
        let ulk2 = document.getElementById("buttt");
        ulk2.innerHTML += "<button type=\"button\" id=\"but\" class=\"btn btn-secondary btn-lg btn-block\" onclick='onStudentPage()' style=\"margin-left: 35px; margin-bottom: 20px; height: 50px; width:280px\"></button>";
        document.getElementById("but").innerHTML = "view ratings";
    }else if(length == 4){
        ulk.innerHTML += "<li>First Name</li>"
        ulk.innerHTML += "<li>Surname</li>"
        ulk.innerHTML += "<li>Middle Name</li>"


        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.firstName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.lastName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.middleName + "</li>";
        let ulk2 = document.getElementById("buttt");
        ulk2.innerHTML += "<button type=\"button\" id=\"but\" class=\"btn btn-secondary btn-lg btn-block\" onclick='onTeacherPage()' style=\"margin-left: 35px; margin-bottom: 20px; height: 50px; width:280px\"></button>";
        document.getElementById("but").innerHTML = "rate students";
        document.getElementById("spisok").style.marginTop = "30px";
    }else
    {
        let ulk2 = document.getElementById("buttt");
        ulk2.innerHTML += "<button type=\"button\" id=\"but\" class=\"btn btn-secondary btn-lg btn-block\" onclick='onAdminPage()' style=\"margin-left: 35px; margin-bottom: 20px; height: 50px; width:280px\"></button>";
        document.getElementById("user_info").style.marginTop = "90px";
        document.getElementById("spisok").style.marginTop = "50px";
        document.getElementById("but").innerHTML = "admin panel";
    }

}

onAdminPage = () => {
    document.location.href = "/register";
}

onStudentPage = () => {
    document.location.href = "/student";
}

onTeacherPage = () => {
    document.location.href = "/teacher";
}

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}
load();