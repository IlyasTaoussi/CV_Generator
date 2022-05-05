function CallWebAPI() {
    // GET request
    fetch('https://test.com/api/user_id/user')
        .then(function(response) => {
            return response.json()[0]
        })
    .catch(error => console.error(error))
}