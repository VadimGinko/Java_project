load = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt + 'a');
    if(jwt== null){
        document.location.href = "/login";
    }
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
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
        console.log("qq");
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
        document.getElementById("but").innerHTML = "view ratings";
    }else if(length == 5){
        console.log("qq222");
        ulk.innerHTML += "<li>First Name</li>"
        ulk.innerHTML += "<li>Surname</li>"
        ulk.innerHTML += "<li>Middle Name</li>"
        ulk.innerHTML += "<li>Subjects</li>"

        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.firstName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.lastName + "</li>";
        ulv.innerHTML += "<li class=\"inf_li\">" + user_info_json.middleName + "</li>";
        var subjects = "";
        user_info_json.subjects.subject.forEach(elem =>{
            subjects += elem + ' '
        })
        ulv.innerHTML += "<li class=\"inf_li\">" + subjects + "</li>";
        document.getElementById("but").innerHTML = "rate students";
        document.getElementById("spisok").style.marginTop = "30px";
    }else
    {
        document.getElementById("user_info").style.marginTop = "90px";
        document.getElementById("spisok").style.marginTop = "50px";
        document.getElementById("but").innerHTML = "admin panel";
    }

}


logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}
load();