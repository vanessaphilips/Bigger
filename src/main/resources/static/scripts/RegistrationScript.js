
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

document.getElementById('postalCode').addEventListener('focusout', checkAddress);

document.getElementById('number').addEventListener('focusout', checkAddress);

document.getElementById('submitForm').addEventListener('click', prepareRegistration);


function checkAddress(){
    let regex = new RegExp(/^[1-9][0-9]{3}[\s]?[A-Za-z]{2}$/i);

    let postcode = document.getElementById('postalCode').value
    let huisnummer = document.getElementById('number').value

    console.log('pc is valide: ' + regex.test(postcode))

    if(regex.test(postcode) && huisnummer){
        let formData = `postcode=${postcode}&number=${huisnummer}`

        fetch("https://postcode.tech/api/v1/postcode?" + formData , {
            headers: {
                'Authorization': 'Bearer 9565619a-9760-47d8-8f6d-e789d63b60ca',
            },
        })
            .then(response => response.json())//kan hier ook checken wat de response is.
            .then(json => {
                processAddress(json)
            })
            .catch((error) => { console.error('Error', error) });
    }
}

function processAddress(data) {
    console.log(data)
    let addressPart = data;
    if (addressPart.hasOwnProperty('message')) {
        document.getElementById('postalError').style.display = 'block';
        document.getElementById('postalCode').classList.add('error');
        document.getElementById('number').classList.add('error');
    } else {
        document.getElementById('postalError').style.display = 'none';
        document.getElementById('city').value = addressPart.city; // zonder validatie
        document.getElementById('street').value = addressPart.street; // zonder validatie

        document.getElementById('postalCode').classList.remove('error');
        document.getElementById('number').classList.remove('error');
        }
}

function prepareRegistration() {
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
        document.getElementById('country').value
    );
    alert(JSON.stringify(registration));
    sendRegistrationData(registration);
}

function sendRegistrationData(rData){
    fetch("http://localhost:8080/register", {
        method: 'POST',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(rData)
    })
        .then(response => {
            if(response.ok){
                alert("ok");
            }
            else{
                alert("niet ok");
            }
        })
        .catch((error) => {console.error('Error', error)});
}

//fetch("https://postcode.tech/api/v1/postcode?" + formData , {
//             headers: {
//                 'Authorization': 'Bearer 9565619a-9760-47d8-8f6d-e789d63b60ca',
//             },
//         })
//             .then(response => response.json())//kan hier ook checken wat de response is.
//             .then(json => {
//                 processAddress(json)
//             })
//             .catch((error) => { console.error('Error', error) });
//     }


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

