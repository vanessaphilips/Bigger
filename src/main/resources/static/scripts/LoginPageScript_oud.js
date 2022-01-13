// "use strict"
//
// class LoginDTO {
//     constructor(email, password) {
//         this.email = email;
//         this.password = password;
//     }
// }
//
// function submitLogin() {
//     let email = document.getElementById('email');
//     let password = document.getElementById('password');
//
//     if  window.alert("Empty Fields");  else {()
//     const loginDTO = new LoginDTO(email, password)
//     sendLoginData(loginDTO);
//     }
// }
//
// async function sendLoginData(lData) {
//     await fetch("http://localhost:8080/login", {
//         method: "POST",
//         headers: acceptHeaders(),
//         body: JSON.stringify(lData)
//     }).then(response => {
//             if (response.status === 200) {
//                 return response.json()
//             }
//             else if (response.status === 401) {
//                 alert(response.text())
//                 return response.text()
//             } else {
//                 alert('Unknown Error.')
//                 return '***Error!!'
//             }
//         }).then((json) => {
//             if (json.authorization !== undefined) {
//                 localStorage.setItem(JWT_KEY, json.authorization)
//                 console.log("login succes" + lData.email);
//             } else {
//                 console.log("login failed");
//             }
//         }).catch((error) => {
//             alert('Error ', error)
//         });
// }
//
//
