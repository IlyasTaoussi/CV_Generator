function OnLoadFunction(){
    let Id_User = JSON.parse(sessionStorage.getItem('userData'))
    fetch('http://localhost:8080/api/user/' + Id_User.userId + '/cv', {
        method: 'GET',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        }
    }).then(response => {
         if(response.status === 200){
            return response.json()
         }
    }).then((data) => {
        const tbody = document.getElementById("my_tbody")
        for(let i = 0; i < data.length; i++){
            tbody.appendChild(createMenuItem(data[i].id))
        }
    }).catch(error => console.error(error))
}

function OnClickDeleteFunction(){
    let userData = JSON.parse(sessionStorage.getItem("userData"))
    let Id_CV = event.target.value
    Delete(userData.userId, Id_CV)
}
function Delete(Id_User, Id_Cv){
    fetch('http://localhost:8080/api/user/' + Id_User + '/cv/delete?cv=' + Id_Cv, {
        method: 'DELETE',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        }
    }).then(response => {
         if(response.status === 200){
            window.location.reload()
         }
    }).catch(error => console.error(error))
}

function OnClickAddFunction(){
    let userData = JSON.parse(sessionStorage.getItem('userData'))
    Add(userData.userId)
}
function Add(Id_User){
    fetch('http://localhost:8080/api/user/' + Id_User + '/cv/new', {
        method: 'POST',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        }
    }).then(response => {
         if(response.status === 200){
            return response.json()
         }
    }).then((data) => {
        const tbody = document.getElementById("my_tbody")
        tbody.appendChild(createMenuItem(data.id))
    }).catch(error => console.error(error))
}

function createMenuItem(cv_id) {
    let tr = document.createElement('tr')
    tr.class = "table-secondary"
    let th = document.createElement('th')
    th.scope = "row"
    th.textContent = "CV name"
    tr.appendChild(th)
    let td = document.createElement('td')
    let a = document.createElement('a')
    a.href = "cv_form.html"
    a.textContent = "Open"
    td.appendChild(a)
    tr.appendChild(td)
    let td2 = document.createElement('td')
    let button = document.createElement('button')
    button.textContent = "Delete"
    button.type = "button"
    button.class = "btn btn-danger text-light float-end mx-2"
    button.value = cv_id
    button.addEventListener('click', function() { OnClickDeleteFunction() })
    button.id = "button_" + cv_id
    td2.appendChild(button)
    tr.appendChild(td2)
    return tr
}

