function clearFields(){
    document.getElementById("smusername").innerHTML = "";
    document.getElementById("smemail").innerHTML = "";
    document.getElementById("smpassword").innerHTML = "";
    document.getElementById("smfirstName").innerHTML = "";
    document.getElementById("smmiddleName").innerHTML = "";
    document.getElementById("smlastName").innerHTML = "";
    document.getElementById("smsubmitPassword").innerHTML = "";
    document.getElementById("res").innerHTML = "";
}


async function Register()
{
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let firstName = document.getElementById("firstName").value;
    let middleName = document.getElementById("middleName").value;
    let lastName = document.getElementById("lastName").value;
    let sel = document.getElementById("select-box1");
    let facultyName = sel.options[sel.selectedIndex].textContent;
    let userCourse = document.getElementById("userCourse").value;
    let userGroup = document.getElementById("userGroup").value;
    clearFields();
    if(password !== document.querySelector("#submitPassword").value)
    {
        document.getElementById("smsubmitPassword").innerHTML = "Passwords are not the same";
        return
    }
    let response = await fetch("/api/v1/auth/registerStudent",
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            username: username,
            email: email,
            password: password,
            firstName: firstName,
            middleName: middleName,
            lastName: lastName,
            facultyName: facultyName,
            userCourse: userCourse,
            userGroup: userGroup
            })
    });
    if(response.status === 201)
    {
        document.getElementById("res").innerHTML = "user registered";
        return;
    }
    else
    {
        let data = await response.json();
        console.log(data);
        data.errors.forEach(err =>
        {
            document.getElementById("sm" + err.field).innerHTML = err.message;
        });
    }
}
async function addSubject(){
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if(jwt== null){
        document.location.href = "/login";
    }
    let subjName = document.getElementById("addSubjectName").value;
    console.log(subjName)
    await fetch("/api/v1/admin/addSubject",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt},
            body: JSON.stringify({
                subject: subjName
            })
        });
    document.getElementById("addSubjectName").value= "";
    setSubjects();
}

async function addFaculty(){
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if(jwt== null){
        document.location.href = "/login";
    }
    let facultyName = document.getElementById("addFacultyName").value;
    console.log(facultyName)
    await fetch("/api/v1/admin/addFaculty",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt},
            body: JSON.stringify({
                faculty: facultyName
            })
        });
    document.getElementById("addFacultyName").value= "";
    setFaculties();
}

async function addTeacherToGroup() {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let sel = document.getElementById("select-box3");
    let facultyName = sel.options[sel.selectedIndex].textContent;
    let sel2 = document.getElementById("teachers");
    let userName = sel2.options[sel2.selectedIndex].textContent;
    let sel3 = document.getElementById("subjects");
    let subjectName = sel3.options[sel3.selectedIndex].textContent;
    let userCourse = document.getElementById("Course").value;
    let userGroup = document.getElementById("Group").value;

    await fetch("/api/v1/admin/addTeacherToGroupRecord",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Bearer_' + jwt
            },
            body: JSON.stringify({
                faculty: facultyName,
                course: userCourse,
                tgroup: userGroup,
                user: userName,
                subject: subjectName
            })
        });
    document.getElementById("Course").value = "";
    document.getElementById("Group").value = "";
}

async function deleteSubject(){
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if(jwt== null){
        document.location.href = "/login";
    }
    let sel = document.getElementById("select-box2");
    let subjectName = sel.options[sel.selectedIndex].textContent;
    console.log(subjectName)
    await fetch("/api/v1/admin/deActivateSubject",
        {
            method: 'PUT',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt},
            body: JSON.stringify({
                subject: subjectName
            })
        });
    document.getElementById("addSubjectName").value= "";
    setSubjects();
}

async function RegisterTeacher()
{
    let username = document.getElementById("usernameTeacher").value;
    let password = document.getElementById("passwordTeacher").value;
    let firstName = document.getElementById("firstNameTeacher").value;
    let middleName = document.getElementById("middleNameTeacher").value;
    let lastName = document.getElementById("lastNameTeacher").value;
    clearFieldsTeacher();
    if(password !== document.querySelector("#submitPasswordTeacher").value)
    {
        document.getElementById("smsubmitPasswordTeacher").innerHTML = "Passwords are not the same";
        return
    }
    let response = await fetch("/api/v1/auth/registerTeacher",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                username: username,
                password: password,
                firstName: firstName,
                middleName: middleName,
                lastName: lastName,
            })
        });
    if(response.status === 201)
    {
        document.getElementById("res").innerHTML = "user registered";
        return;
    }
    else
    {
        let data = await response.json();
        console.log(data);
        data.errors.forEach(err =>
        {
            document.getElementById("sm" + err.field + "Teacher").innerHTML = err.message;
        });
    }
}
function clearFieldsTeacher(){
    document.getElementById("smusernameTeacher").innerHTML = "";
    document.getElementById("smpasswordTeacher").innerHTML = "";
    document.getElementById("smfirstNameTeacher").innerHTML = "";
    document.getElementById("smlastNameTeacher").innerHTML = "";
    document.getElementById("smmiddleNameTeacher").innerHTML = "";
    document.getElementById("smsubmitPasswordTeacher").innerHTML = "";
    document.getElementById("res").innerHTML = "";
}
async function setFaculties() {
    let response = await fetch("api/v1/auth/faculties",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    var select = document.getElementById("select-box1");
    var select2 = document.getElementById("select-box3");

    var i = 1;
    select.innerHTML = "";
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.faculty + "</option>";
        select2.innerHTML += "<option value=\"Choice " + i + "\">" + el.faculty + "</option>";
        i++;
    });
}
async function setSubjects() {
    let response = await fetch("api/v1/auth/subjects",
        {
            method: 'GET', mode: 'no-cors',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    var select = document.getElementById("select-box2");
    var select2 = document.getElementById("subjects");
    select.innerHTML = "";
    select2.innerHTML = "";
    var i = 1;
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el.subject + "</option>";
        select2.innerHTML += "<option value=\"Choice " + i + "\">" + el.subject + "</option>";
        i++;
    });
}
async function setTeachers() {
    let jwt = localStorage.getItem("jwt");
    let response = await fetch("/api/v1/admin/teachers",
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer_' + jwt}
        });
    let data = await response.json();
    var select2 = document.getElementById("teachers");
    select2.innerHTML = "";
    var i = 1;
    console.log(data);
    data.forEach(el => {
        select2.innerHTML += "<option value=\"Choice " + i + "\">" + el.username  + "</option>";
        i++;
    });
}
setTeachers();
setSubjects();

setFaculties();

function changeOnTeacherForm(){
    document.getElementById("student-div").style.display = "none";
    document.getElementById("teacher-div").style.display = "block";
}

function changeOnStudentForm(){
    document.getElementById("student-div").style.display = "block";
    document.getElementById("teacher-div").style.display = "none";
}
hide_result = () =>{
    document.getElementById("res").innerHTML = "";
}
