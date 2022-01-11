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

   let login = new LoginDTO(x,y);
   sendLoginData(login);
}

function sendLoginData(lData) {
    fetch("http://localhost:8080/login", {
        method: 'POST',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(lData)
    })
        .then(response => {
            if (response.status === 200) {
                return response.text()
                    .then(text => {
                        alert(text);
                    })
            } else if (response.status === 401) {
                return response.text()
                    .then(text => alert(text))
            } else {
                alert('Unknown Error.')
            }
        })
        .catch((error) => {
            console.error('Error', error)
        });
}


