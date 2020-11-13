async function Login()
{
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let response = await fetch("/api/v1/auth/login",
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            username: username,
            password: password
        })
    });

    let data = await response.json();

    if(response.status === 200)
    {
        localStorage.setItem("jwt", data.token);

        console.log(localStorage.getItem("jwt"));
        document.location.href = "/";
    }
    else
    {
        document.querySelector("#result").style.color = "#e74c3c";
        document.querySelector("#result").innerHTML = data.error;
    }
}

