//import {rootURL} from "./URLs.js";

class LoginDTO {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}


const loginDTO = new LoginDTO("henk@unicom.nl", "password1234345")

 async function getToken() {

    await fetch(`${rootURL}login`,
        {
            method: "POST",
            headers: {
                Accept: 'Application/json',
                "Content-type": "application/json",
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                'X-Content-Type-Options': '*'
            },
            body: JSON.stringify(loginDTO)
        })
        .then(response => {
            if (response.ok) {
                console.log("login succes" + loginDTO.email);
                return response.json()
            } else {
                console.log("login failed");
            }
        }).then((json) => {
                localStorage.setItem("jwtToken", json.authorization)
            }
        )


}



