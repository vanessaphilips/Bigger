let token;

class LoginDTO {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}
const loginDTO = new LoginDTO("henk@unicom.nl", "password1234345")
export const getToken = () => {

    fetch("http://localhost:8080/login",
        {
            method: "POST",
            headers: {
                Accept: 'Application/json',
                "Content-type": "application/json",
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                //"Access-Control-Expose-Headers": "authorization"
            },
            body: JSON.stringify(loginDTO)
        })
        .then(response => {
            if (response.ok) {
                console.log("login succes");
                // for (const header of response.headers) {
                //     console.log(header);
                // }
                return response.text()

            } else {

                console.log("login failed");
            }
        }).then(json => {

        token = json;

        console.log("hier moet de token staan " + token)
        return token
    }).catch()
}


