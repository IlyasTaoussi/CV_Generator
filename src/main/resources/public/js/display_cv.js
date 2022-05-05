function CallWebAPI() {
    // GET request
    fetch('https://anime-facts-rest-api.herokuapp.com/api/v1')
        .then(response => {
            return response.json()
        }).then(data => {
            appendTo(data)
        }).catch(error => console.error(error))
}

function appendTo(data) {
    console.log(data)
}

CallWebAPI()