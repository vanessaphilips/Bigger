"use strict"

class LoginDTO {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}

function submitLogin(){
   let x = document.getElementById('email');
   let y = document.getElementById('password');

   const loginDTO = new LoginDTO(x,y);
   sendLoginData(loginDTO);
}

async function sendLoginData(lData) {
    await fetch(`${rootURL}login`, {
        method: "POST",
        headers: acceptHeaders(),
        body: JSON.stringify(lData)
    }).then(response => {
            if (response.status === 200) {
                return response.json()
            }
            else if (response.status === 401) {
                return response.text()
                    .then(text => alert(text))
            } else {
                alert('Unknown Error.')
            }
        }).then((json) => {
            if (json.authorization !== undefined) {
                localStorage.setItem(JWT_KEY, json.authorization)
                console.log("login succes" + lData.email);
            } else {
                console.log("login failed");
            }
        }).catch((error) => {
            console.error('Error', error)
        });
}


