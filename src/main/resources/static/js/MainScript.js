//import fetchWithAuth from './FectWithJwt';
ShowAllfoods();
GetUsernameByToken();


async function ShowAllfoods()
{
    let response = await fetch("api/foods",
    {
        method: 'GET', mode: 'no-cors',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
    });
    let data = await response.json();
    data.forEach(el =>
    {
        let div = document.createElement('div');
        div.setAttribute('class', 'content__element');

        let img = document.createElement('img');
        img.src = el.image;

        let innerDiv = document.createElement('div');
        innerDiv.setAttribute('class', 'element__title');

        let h3 = document.createElement('h3');
        h3.innerHTML = el.title;

        let label = document.createElement('label');
        label.innerHTML = el.weight + 'г';

        innerDiv.appendChild(h3);
        innerDiv.appendChild(label);

        let h2 = document.createElement('h2');
        h2.innerHTML = el.price + ' BYN';

        div.appendChild(img);
        div.appendChild(innerDiv);
        div.appendChild(h2);

        document.querySelector('#content').appendChild(div);
    });
}

async function GetUsernameByToken() {

    let token = GetCookie('jwt');
    if(token === false)
    {
        SetNotAuthorizationMenu();
        return;
    }

    let response = await fetch('api/auth/username',
        {
            headers: {'Authorization': 'Bearer_' + token, 'Accept': 'application/json'}
        });


    if(response.status === 200)
    {
        let data = await response.json();
        SetUserMenu(data.username);
    }
    else
    {
        SetNotAuthorizationMenu();
        DeleteCookie('jwt');
    }

}

function LogOut()
{
    DeleteCookie('jwt');
    SetNotAuthorizationMenu();
}

function GetCookie(a)
{
    let b = new RegExp(a+'=([^;]){1,}');
    let c = b.exec(document.cookie);
    if(c) c = c[0].split('=');
    else return false;
    return c[1] ? c[1] : false;
}

function DeleteCookie ( cookieName )
{
    var cookieDate = new Date ( );  // Текущая дата и время
    cookieDate.setTime ( cookieDate.getTime() - 1 );
    document.cookie = cookieName += "=; expires=" + cookieDate.toGMTString();
}

function SetNotAuthorizationMenu()
{
    let menu = document.querySelector('#header_menu');
    menu.innerHTML = "";

    let ul = document.createElement('ul');
    let li1 = document.createElement('li');
    let li2 = document.createElement('li');
    let a1 = document.createElement('a');
    a1.href = "/login";
    a1.innerHTML = "Sign in";
    let a2 = document.createElement('a');
    a2.href = "/register";
    a2.innerHTML = "Sign up";

    li1.appendChild(a1);
    li2.appendChild(a2);
    ul.appendChild(li1);
    ul.appendChild(li2);
    menu.appendChild(ul);

}

function SetUserMenu(username)
{
    let menu = document.querySelector('#header_menu');
    menu.innerHTML = "";

    let ul = document.createElement('ul');
    let li1 = document.createElement('li');
    let li2 = document.createElement('li');
    let a = document.createElement('a');
    a.innerHTML = "Log out";
    a.addEventListener("click", LogOut);
    li1.innerHTML = username;


    li2.appendChild(a);
    ul.appendChild(li1);
    ul.appendChild(li2);
    menu.appendChild(ul);
}
