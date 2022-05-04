$("#signin").click(function(){
    // Get the fields values
    let username = $('#<....>').val()
    let password = $('#<....>').val()
    // Reinitialize credentials fields
    $('#username').val('')
    $('#password').val('')
    // Store credentials in session
    sessionStorage.setItem('username', username)
    sessionStorage.setItem('password', password)
    // API POST request
    let response = CallWebAPI()
    // Handling the response
    if(response === 200){
        // Success : move on to the next page
        window.location.href = "../public/<NOM_PAGE>.html"
    } else {
        // Reload authentication page
        window.location.href = "../public/authentication.html"
    }
});

function CallWebAPI() {
    // Get session's credentials
    let username = sessionStorage.getItem('username')
    let password = sessionStorage.getItem('password')
    // POST request
    fetch('https://test.com/User', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                mail:username,
                password:password,
            })
    }).then(function(response) => {
        console.log(response.status)
        return response.status
    }).catch(error => console.error(error))
}

