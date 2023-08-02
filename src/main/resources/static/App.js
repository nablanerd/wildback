// CONNECTION API


document.getElementById('getUsers').addEventListener('click', e => getAllUser())

function getAllUser() {
    // console.log("FIRST")
    fetch('/user')
        .then(response => response.json()
            .then(payload => console.log(payload, 'Payload'))
            .catch(error => console.log(error))
        ).catch(error => console.error(error));
    // console.log("SECOND")
}

function getUserByLogin(login) {

}

function addUser(username, email, password) {
    fetch("/player", {
        method: "post",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //make sure to serialize your JSON body
        body: JSON.stringify({
            username: login,
            email: email,
            password: password
        })
    }).then( response => {
            response.json().then(payload => {
                console.log(payload, 'BODY')
                console.log(response.status, 'STATUS')
            })
    });

}

function updateUser(update) {

}


function deleteUser(login) {

}