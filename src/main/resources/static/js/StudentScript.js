

async function setFaculties() {
    let jwt = localStorage.getItem("jwt");
    let userName = document.getElementById("username").textContent;
    let response = await fetch(`/api/v1/students/getUserSubjects?username=${userName}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt,'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    console.log(data)
    var select = document.getElementById("subjects");

    var i = 1;
    select.innerHTML = "";
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el + "</option>";
        i++;
    });
}

load = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
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
    setFaculties();
};
let page = 0;
getMarks = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    page = 0;
    let user_info_json = await user_info.json();

    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
                            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
                            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
                            "        </div>"
    });

    checkDisabled(user_info_json);
};

prev = async () =>{
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    page--;
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
            "        </div>"
    });
    checkDisabled(user_info_json);
};

next = async () =>{
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    page++;
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += "        <div style=\"display: flex;justify-content: center\">\n" +
            "            <h5>Оценка: "+ el.mark +"</h5>\n" +
            "            <h5 style=\"margin-left: 40px\">Описание: "+ el.discription +"</h5>\n" +
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

load();