function CallWebAPI() {
    // Clear select
    document.getElementById("<SELECT_ID>").length = 0;
    fetch('https://anime-facts-rest-api.herokuapp.com/api/v1')
  		.then(response => {
		    return response.json()
    	}).then(data => {
      		for(let i in data.<NAME>){
          	    //console.log(data.data[i].<SOMETHING>)
          	    let newOption = new Option(data.<NAME>[i].<SOMETHING>, data.<NAME>[i].<SOMETHING>)
  				document.getElementById("<SELECT_ID>").append(newOption)
            }
    	}).catch(error => console.error(error))
}

function delete_button() {
    let element = document.getElementById("<SELECT_ID>")
    let str = e.options[e.selectedIndex].text
    console.log(str)
}