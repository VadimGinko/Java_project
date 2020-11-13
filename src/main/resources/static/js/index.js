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

    console.log(user_info_json)
    //admin
    if(length == 8)
    {
        ulk.innerHTML += "<li>firstName</li>"
        ulk.innerHTML += "<li>lastName</li>"
        ulk.innerHTML += "<li>email</li>"
        ulk.innerHTML += "<li>middleName</li>"
        ulk.innerHTML += "<li>facultyName</li>"
        ulk.innerHTML += "<li>userCourse</li>"
        ulk.innerHTML += "<li>userGroup</li>"

        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.firstName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.lastName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.email + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.middleName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.facultyName.faculty + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.userCourse + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.userGroup + "</li>";
    }else if(length == 4){
        ulk.innerHTML += "<li>firstName</li>"
        ulk.innerHTML += "<li>lastName</li>"
        ulk.innerHTML += "<li>middleName</li>"
        ulk.innerHTML += "<li>subjects</li>"

        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.firstName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.lastName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.middleName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.subjects + "</li>";
    }
}


logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}
load();