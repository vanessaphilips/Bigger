
class RegistrationDTO{
    constructor(email,password, firstName, insertion, lastName,
    bsn, dateOfBirth, postalCode,street, number, city, country)
{
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.insertion = insertion;
    this.lastName = lastName;
    this.bsn = bsn;
    this.dateOfBirth = dateOfBirth;
    this.postalCode = postalCode;
    this.street = street;
    this.number = number;
    this.city = city;
    this.country = country;
    }
}

function saveRegistration() {
    let registration = new RegistrationDTO(
        document.getElementById('email').value,
        document.getElementById('password').value,
        document.getElementById('firstName').value,
        document.getElementById('insertion').value,
        document.getElementById('lastName').value,
        document.getElementById('bsn').value,
        document.getElementById('dateOfBirth').value,
        document.getElementById('postalCode').value,
        document.getElementById('street').value,
        parseInt(document.getElementById('number').value),
        document.getElementById('city').value,
        document.getElementById('county').value
    );
    alert(JSON.stringify(registration));
}

//voor later
//$.ajax({
//    type: "POST",
//    url: "https://reqbin.com/echo/post/json",
//    data: JSON.stringify({ "userName": userName, "password" : password }),
//    contentType: "application/json",
//    success: function (result) {
//      console.log(result);
//    },
//    error: function (result, status) {
//      console.log(result);
//    }
// });

