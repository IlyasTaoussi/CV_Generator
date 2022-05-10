function OnClickFunctionAuth(){
    // Get the fields values and hash the password
    let username = document.getElementById("email").value
    let password = document.getElementById("password").value
    let hash = SHA256(password)
    // API POST request
    CallWebAPIAuth(username, hash)
}

function OnClickFunctionReg(){
    // Get the fields values and hash the password
    let username = document.getElementById("signupEmail").value
    let password = document.getElementById("signupPassword").value
    let hash = SHA256(password)
    // API POST request
    CallWebAPIReg(username, hash)
}

function CallWebAPIReg(username, hash) {
    // POST request
    fetch('http://localhost:8080/api/user/new', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf8'
            },
        body: JSON.stringify({
            "mail":username,
            "password":hash
        })
    }).then(response => {
        // Handling the response
        if(response.status === 200){
            // Success : move on to the next page
            window.location.href = "../authentication.html"
        } else {
            // Reload authentication page
            window.location.href = "../registration.html"
        }
    }).catch(error => console.error(error))
}

function CallWebAPIAuth(username, hash) {
    // POST request
    fetch('http://localhost:8080/api/user/search', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf8'
            },
        body: JSON.stringify({
            "mail":username,
            "password":hash
        })
    }).then(response => {
        // Handling the response
        if(response.status === 200){
            // Store credentials in session
            sessionStorage.setItem('username', username)
            sessionStorage.setItem('hashPassword', hash)
            // Success : move on to the next page
            window.location.href = "../cv_form.html"
        } else {
            // Reload authentication page
            window.location.href = "../authentication.html"
        }
    }).catch(error => console.error(error))
}

