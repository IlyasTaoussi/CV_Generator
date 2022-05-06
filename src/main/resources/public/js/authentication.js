import { SHA256 } from "webtoolkit.sha256.js"

function OnClickFunction(){
    // Get the fields values and hash the password
    let username = document.getElementById("email")
    let password = document.getElementById("password")
    let hash = SHA256(password)
    // Reinitialize credentials fields
    document.getElementById("email").reset();
    document.getElementById("password").reset()
    // Store credentials in session
    sessionStorage.setItem('username', username)
    sessionStorage.setItem('hashPassword', hash)
    // API POST request
    CallWebAPI()
});

function CallWebAPI() {
    // Get session's credentials
    let username = sessionStorage.getItem('username')
    let hash = sessionStorage.getItem('hashPassword')
    // POST request
    fetch('http://10.4.31.160:8080/api/user', {
        Method: 'POST',
        Headers: {'Content-Type': 'application/json; charset=UTF-8'},
        Body: JSON.stringify({
            mail:username,
            password:hash,
        })
    }).then(response => {
        // Handling the response
        if(response.status === 200){
            // Success : move on to the next page
            window.location.href = "../public/cv_form.html"
        } else {
            // Reload authentication page
            window.location.href = "../public/authentication.html"
            alert("Mauvais mot de passe !")
        }
    }).catch(error => console.error(error))
}

