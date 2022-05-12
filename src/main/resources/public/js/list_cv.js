function OnLoadFunction(){
    let data = JSON.parse(sessionStorage.getItem('userData'))
    fetch('http://localhost:8080/api/user/' + data.userId + '/cv', {
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
    let th = document.createElement('th')
    th.scope = "row"
    th.textContent = "CV name"
    tr.appendChild(th)
    //------------------------------------------------------------------------------------------------------------------
    let td = document.createElement('td')
    let a = document.createElement('a')
    a.href = "cv_form.html?cv="+cv_id
    let img = document.createElement('img')
    img.src = "img/click_icon.png"
    img.title = "click to add CV"
    a.appendChild(img)
    td.appendChild(a)
    tr.appendChild(td)
    //------------------------------------------------------------------------------------------------------------------
    let td2 = document.createElement('td')
    let a2 = document.createElement('a')
    a2.href = "cv.html?cv="+cv_id
    let img2 = document.createElement('img')
    img2.src = "img/visualize_icon.png"
    img2.title = "click to visualize CV"
    a2.appendChild(img2)
    td2.appendChild(a2)
    tr.appendChild(td2)
    //------------------------------------------------------------------------------------------------------------------
    let td3 = document.createElement('td')
    let a3 = document.createElement('a')
    a3.href = "cv.html?cv="+cv_id
    let img3 = document.createElement('img')
    img3.src = "img/download_icon.png"
    img3.title = "click to download CV"
    a3.appendChild(img3)
    td3.appendChild(a3)
    tr.appendChild(td3)
    //------------------------------------------------------------------------------------------------------------------
    let td4 = document.createElement('td')
    let button = document.createElement('button')
    button.type = "button"
    button.class ="btn text-light float-end mx-2"
    button.value = cv_id
    button.addEventListener('click', function() { OnClickDeleteFunction() })
    button.textContent ="delete"
    /*
    let img4 = document.createElement('img')
    img4.src = "img/delete_icon.png"
    img4.title = "click to delete CV"
    button.appendChild(img4)
    */
    td4.appendChild(button)
    tr.appendChild(td4)
    return tr
}

