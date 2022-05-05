function OnClickFunction(){
    // Get the fields values
    let username = document.getElementById("email")
    let password = document.getElementById("password")
    // Reinitialize credentials fields
    document.getElementById("email").reset();
    document.getElementById("password").reset();
    // Store credentials in session
    sessionStorage.setItem('username', username)
    sessionStorage.setItem('password', password)
    // API POST request
    CallWebAPI()
});

function CallWebAPI() {
    // Get session's credentials
    let username = sessionStorage.getItem('username')
    let password = sessionStorage.getItem('password')
    // POST request
    fetch('https://test.com/api/user', {
        Method: 'POST',
        Headers: {'Content-Type': 'application/json; charset=UTF-8'},
        Body: JSON.stringify({
            mail:username,
            password:password,
        })
    }).then(function(response) => {
        // Handling the response
        if(response.status === 200){
            // Success : move on to the next page
            window.location.href = "../public/cv_form.html"
        } else {
            // Reload authentication page
            window.location.href = "../public/authentication.html"
        }
    }).catch(error => console.error(error))
}

