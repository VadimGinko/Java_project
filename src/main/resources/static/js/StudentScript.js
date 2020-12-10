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
};


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

load();