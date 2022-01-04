class LoginDTO {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}

const loginDTO = new LoginDTO("henk@unicom.nl", "password1234345")

async function getToken() {
 fetch(`${rootURL}login`,{
            method: "POST",
            headers: acceptHeaders(),
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
                if (json.authorization === true) {
                    localStorage.setItem("jwtToken", json.authorization)
                }
            })
}



