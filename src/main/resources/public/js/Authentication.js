// Redirection
$("li:eq(0)").click(function(){ window.location.href = "../public/authentication.html"; });
$("li:eq(1)").click(function(){ window.location.href = "../public/<NOM_PAGE>.html"; });

$("#<BUTTON_NAME>").click(function(){
    // Get the fields values
    let username = $('#<....>').val();
    let password = $('#<....>').val();
    // Reinitialize credentials fields
    $('#username').val('');
    $('#password').val('');
    // Create token and store credentials in session
    let token = username + ":" + password;
    sessionStorage.setItem('token', token);
});

$("li:eq(1)").click(function(){
    // API POST request
    response = CallWebAPI();
    if(response === 200){
        // Success : move on to the next page
        window.location.href = "../public/<NOM_PAGE>.html";
    } else {
        // Reload authentication page
        window.location.href = "../public/authentication.html";
    }
});

function CallWebAPI() {
    let username = sessionStorage.getItem('username');
    let password = sessionStorage.getItem('password');

    fetch('https://test.com/User', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                mail:username,
                password:password,
            })
    }).then(function(response){
        return response.json()
        }).then(function(data){
            console.log(data)
            }).catch(error => console.error('Error:', error));
    });
}
