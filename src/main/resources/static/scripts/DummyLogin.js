import {rootURL} from "./Root.js";

class LoginDTO {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}


const loginDTO = new LoginDTO("henk@unicom.nl", "password1234345")

export async function getToken() {
let token;
    await fetch(`${rootURL}login`,
        {
            method: "POST",
            headers: {
                Accept: 'Application/json',
                "Content-type": "application/json",
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                'X-Content-Type-Options': '*',
                //"Access-Control-Expose-Headers": "authorization"
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
            token = json.authorization
        }
    )
    console.log("fetchToken : " + token)
    return token
}



