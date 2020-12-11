async function setStudents() {
    let jwt = localStorage.getItem("jwt");

    let sel2 = document.getElementById("faculties");
    let faculty = sel2.options[sel2.selectedIndex].textContent;
    let sel3 = document.getElementById("subjects");
    let subjectName = sel3.options[sel3.selectedIndex].textContent;
    document.getElementById("subj").innerHTML = subjectName;

    let userCourse = document.getElementById("userCourse").value;
    let userGroup = document.getElementById("userGroup").value;
    let userName = document.getElementById("username").textContent;


    let response = await fetch(`/api/v1/teachers/getStudents?faculty=${faculty}&subjectName=${subjectName}&userCourse=${userCourse}&userGroup=${userGroup}&userName=${userName}`,
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt}
        });
    let data = await response.json();
    var select2 = document.getElementById("students");
    select2.innerHTML = "";
    var i = 1;
    data.forEach(el => {
        select2.innerHTML += "<option value=\"" + el.username + "\">" + el.lastName+ " " + el.firstName +  "</option>";
        i++;
    });
}
async function setStudents2() {
    let jwt = localStorage.getItem("jwt");


    let response = await fetch(`/api/v1/teachers/getStudentsAll`,
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt}
        });
    let data = await response.json();
    var select2 = document.getElementById("students2");
    select2.innerHTML = "";
    var i = 1;
    data.forEach(el => {
        select2.innerHTML += "<option value=\"" + el.username + "\">" + el.lastName+ " " + el.firstName +  "</option>";
        i++;
    });
}
setStudents2()
async function setSubjects() {
    let response = await fetch("api/v1/auth/subjects",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    var select2 = document.getElementById("subjects");
    select2.innerHTML = "";
    var i = 1;
    data.forEach(el => {
        select2.innerHTML += "<option value=\"Choice " + i + "\">" + el.subject + "</option>";
        i++;
    });
}

async function setFaculties() {
    let response = await fetch("api/v1/auth/faculties",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    var select = document.getElementById("faculties");

    var i = 1;
    select.innerHTML = "";
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.faculty + "</option>";
        i++;
    });
}

load = async () => {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
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
    setSubjects();
    setFaculties();
};


load();

    logout = () => {
    localStorage.clear();
    document.location.href = "/login";
};

async function rate(){

    let mark = document.getElementById("mark").value;
    let description = document.getElementById("description").value;
    let sel3 = document.getElementById("students");
    if(sel3.selectedIndex != -1) {
        let username = sel3.options[sel3.selectedIndex].value;
        let subject = document.getElementById("subj").innerText;
        let jwt = localStorage.getItem("jwt");
        if (username != null) {
            await fetch("/api/v1/teachers/rateStudent",
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': 'Bearer_' + jwt
                    },
                    body: JSON.stringify({
                        mark: mark,
                        subject: subject,
                        username: username,
                        description: description
                    })
                });
        }
    }
}
logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}







let page = 0;
getMarks = async () => {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let sel3 = document.getElementById("students2");
    let username = sel3.options[sel3.selectedIndex].value;
    let user_info = await fetch(`/api/v1/teachers/getStudentMarksAll?username=${username}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    page = 0;
    let user_info_json = await user_info.json();
    console.log(user_info_json)
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Предмет: "+ el.subject.subject +"</h5>\n" +
            "        </div>"
    });

    checkDisabled(user_info_json);
};

prev = async () =>{
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let sel3 = document.getElementById("students2");
    let username = sel3.options[sel3.selectedIndex].value;
    page--;
    let user_info = await fetch(`/api/v1/teachers/getStudentMarksAll?username=${username}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Предмет: "+ el.subject.subject +"</h5>\n" +
            "        </div>"
    });
    checkDisabled(user_info_json);
};

next = async () =>{
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
    let sel3 = document.getElementById("students2");
    let username = sel3.options[sel3.selectedIndex].value;
    page++;
    let user_info = await fetch(`/api/v1/teachers/getStudentMarksAll?username=${username}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Предмет: "+ el.subject.subject +"</h5>\n" +
            "        </div>"
    });
    checkDisabled(user_info_json);
};

checkDisabled = (user_info_json) =>  {
    document.getElementById("previous").classList.add("disabled");
    document.getElementById("next").classList.add("disabled");
    if(page == 0) {
        document.getElementById("previous").classList.add("disabled");
    }else {
        document.getElementById("previous").classList.remove("disabled");
    }

    if(page == user_info_json.totalPages -1 ){
        document.getElementById("next").classList.add("disabled");
    }else{
        document.getElementById("next").classList.remove("disabled");
    }
}