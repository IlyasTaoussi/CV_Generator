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
    sessionStorage.getItem('token');

    if(<..........>){
        // Success : move on to the next page
        window.location.href = "../public/<NOM_PAGE>.html";
    } else {
        // Reload authentication page
        window.location.href = "../public/authentication.html";
    }
});

function CallWebAPI() {
    fetch("https:///.json")
        .then(response => {
            // response.ok : http error or not
            if (!response.ok) {
                throw new Error(`Request failed with status ${response.status}`)
            } else {
                return response.json()
            }
        })
        .then(data => {
            console.log(data.<TEST>)
        })
    .catch(error => console.log(error))
}