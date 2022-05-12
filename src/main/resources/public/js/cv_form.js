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
            let queryString = window.location.search
            let cv = queryString[4]
            for(let i = 0; i < data.length; i++){
                if(data[i].id == cv){
                    //console.log(data)
                    const name = document.getElementById("contact_name")
                    name.innerHTML = data[i].contact.name
                    const phoneNumber = document.getElementById("contact_phoneNumber")
                    phoneNumber.innerHTML = data[i].contact.phoneNumber
                    const address = document.getElementById("contact_address")
                    address.innerHTML = data[i].contact.address
                    const email = document.getElementById("contact_email")
                    email.innerHTML = data[i].contact.mail
                    const links = document.getElementById("contact_links")
                    links.innerHTML = data[i].contact.links

                    const summary = document.getElementById("summary_description")
                    summary.innerHTML = data[i].summary

                    let PE = data[i].professionalExperience
                    for(let i = 0; i < PE.length; i++){ Experience(PE[i]) }
                    let E = data[i].education
                    for(let i = 0; i < E.length; i++){ Education(E[i]) }

                    break
                }
            }

        }).catch(error => console.error(error))
}

function OnClickContactFunction(){
    // Contact
    var Mail = document.getElementById("edit_email").value
    var Name = document.getElementById("edit_name").value
    var Address = document.getElementById("edit_address").value
    var Number = document.getElementById("edit_phoneNumber").value
    var Links = document.getElementById("edit_links").value
    // Ids
    let queryString = window.location.search
    let cv = queryString[4]
    let userData = JSON.parse(sessionStorage.getItem("userData"))
    // API Request
    Contact(userData.userId, cv, Mail, Name, Address, Number, Links)
}
function Contact(Id_User,Id_Cv,Mail,Name,Address,Number,Links){
    fetch('http://localhost:8080/api/user/' + Id_User + '/contact/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
             "mail":Mail,
             "name":Name,
             "address":Address,
             "phone_number":Number,
             "links":Links
        })
    }).then(response => {
         if(response.status === 200){
            window.location.reload()
         }
    }).catch(error => console.error(error))
}

function OnClickSummaryFunction(){
    // Summary
    var summary = document.getElementById("edit_summary").value
    // Ids
    let data = JSON.parse(sessionStorage.getItem("userData"))
    let queryString = window.location.search
    let cv = queryString[4]
    // API request
    Summary(data.userId, cv, summary)
}
function Summary(Id_User, Id_Cv, summary) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/summary/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: summary
    }).then(response => {
         if(response.status === 200){
             window.location.reload()
         }
    }).catch(error => console.error(error))
}

function OnClickPEFunction(){
    // Professional experience
    var Position = document.getElementById("position").value
    var Company = document.getElementById("company").value
    var City = document.getElementById("city").value
    var StartDate = document.getElementById("startDate").value
    var EndDate = document.getElementById("endDate").value
    var JobDescription = document.getElementById("jobDescription").value
    var Techno = document.getElementById("techno").value
    // Ids
    let data = JSON.parse(sessionStorage.getItem("userData"))
    let queryString = window.location.search
    let cv = queryString[4]
    // API request
    ProfessionalExperience(data.userId, cv, Position, Company, City, StartDate, EndDate, JobDescription, Techno)
}
function ProfessionalExperience(Id_User, Id_Cv, Position, Company, City, StartDate, EndDate, JobDescription, Techno) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/profExp/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "position":Position,
            "company_name":Company,
            "localisation":City,
            "startDate":StartDate,
            "endDate":EndDate,
            "description":JobDescription,
            "technos":Techno
        })
    }).then(response => {
         if(response.status === 200){
            return response.json()
         }
    }).then(data => {
        Experience(data)
    }).catch(error => console.error(error))
}
function Experience(data){
    document.getElementById("list_experience").innerHTML += '<li class="list-group-item" id="exp'+ data.id +'"><button class="btn btn-xs text-light float-end" data-bs-toggle="modal" data-bs-target="#modify_experience_popup" id="modify_experience_btn" type="button"><img src="img/edit_icon.png"></button>'+
         '<button type="button" id='+ data.id +' class="btn float-end mx-2" onclick="ProfessionalExperienceDelete(this.id)"><img src="img/delete_icon.png"> </button>'+
         '<div><label>Position : </label><label id="experience_position">'+ data.position +'</label></div>'+
         '<div class="row"><article class="col-md-4"><div><label>Company : </label><label id="experience_company">'+ data.company +'</label></div></article>'+
         '<article class="col-md-4"><div><label>City : </label><label id="experience_city">'+ data.localisation +'</label></div></article>'+
         '</div><div class="row"><article class="col-md-4"><div><label>From : </label><label id="experience_startDate">'+ data.startDate +'</label></div></article>'+
         '<article class="col-md-4"><div><label>To : </label><label id="experience_endDate">'+ data.endDate +'</label></div></article></div>'+
         '<div><label>Certifications description : </label><p id="formation_description">'+ data.description +'</p></div>'+
         '<div><label>Certifications description : </label><p id="formation_technology">'+ data.technos +'</p></div></li>'
}
function ProfessionalExperienceDelete(id){
    let data = JSON.parse(sessionStorage.getItem("userData"))
    let queryString = window.location.search
    let cv = queryString[4]
    fetch('http://localhost:8080/api/user/'+data.userId+'/profExp/delete?cv='+cv+'&profExpId='+id, {
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

function OnClickEducationFunction(){
    // Graduation
    var SchoolingLevel = document.getElementById("schoolingLevel").value
    var Domain = document.getElementById("domain").value
    var School = document.getElementById("school").value
    var SchoolCity = document.getElementById("schoolCity").value
    var FormationStartDate = document.getElementById("formationStartDate").value
    var FormationEndDate = document.getElementById("formationEndDate").value
    // Ids
    let data = JSON.parse(sessionStorage.getItem("userData"))
    let queryString = window.location.search
    let cv = queryString[4]
    // API request
    CallWebAPIEducation(data.userId, cv, SchoolingLevel, Domain, School, SchoolCity, FormationStartDate, FormationEndDate)
}
function CallWebAPIEducation(Id_User, Id_Cv, SchoolingLevel, Domain, School, SchoolCity, FormationStartDate, FormationEndDate) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/education/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "degree":SchoolingLevel,
            "field":Domain,
            "school":School,
            "location":SchoolCity,
            "startDate":FormationStartDate,
            "endDate":FormationEndDate
        })
    }).then(response => {
         if(response.status === 200){
            return response.json()
         }
    }).then(data =>{
        Education(data)
    }).catch(error => console.error(error))
}
function Education(data){
    document.getElementById("list_formation").innerHTML += '<li class="list-group-item" id='+ data.id +'><button class="btn btn-xs text-light float-end" data-bs-toggle="modal" data-bs-target="#modify_formation_popup" id="modify_formation_btn" type="button"><img src="img/edit_icon.png"></button><button type="button" class="btn float-end mx-2" id='+ data.id +' onclick="EducationDelete(this.id)"><img src="img/delete_icon.png"></button>' +
        '<div><label>Schooling level : </label><label id="formation_schoolingLevel">'+ data.degree +'</label></div><div>' +
        '<label>Domain : </label><label id="formation_domain">'+ data.field +'</label></div><div class="row"><article class="col-md-4">' +
        '<div><label>School : </label><label id="formation_school">'+ data.school +'</label></div></article><article class="col-md-4">' +
        '<div><label>City : </label><label id="formation_schoolCity"> '+ data.location +' </label></div></article></div><div class="row">' +
        '<article class="col-md-4"><div><label>From : </label><label id="formation_startDate"> '+ data.startDate +' </label></div></article>' +
        '<article class="col-md-4"><div><label>To : </label><label id="formation_endDate">' + data.endDate + '</label></div></article></div></li>'
}
function EducationDelete(id){
    let data = JSON.parse(sessionStorage.getItem("userData"))
    let queryString = window.location.search
    let cv = queryString[4]
    fetch('http://localhost:8080/api/user/'+data.userId+'/education/delete?cv='+cv+'&eduId='+id, {
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
/*
function OnClickSkillsFunction(){
    // Skills
    var Skill = document.getElementById("skills").value
    var SkillLevel = document.getElementById("skillsLevel").value
    // Ids
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    Skills(user_Data.idUser, id_CV, Skill, SkillLevel)
}
function Skills(Id_User, Id_Cv, Skill, SkillLevel) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/skill/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "name":Skill,
            "level":SkillLevel
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}

function OnClickCertificationsFunction(){
    // Certifications
    var CertifTitle = document.getElementById("certifTitle").value
    var CertifStartDate = document.getElementById("certifStartDate").value
    var CertifEndDate = document.getElementById("certifEndDate").value
    var CertifDescription = document.getElementById("certifDescription").value
    // Ids
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    Certifications(user_Data.idUser, id_CV, CertifTitle, CertifStartDate, CertifEndDate, CertifDescription)
}
function Certifications(Id_User, Id_Cv, CertifTitle, CertifStartDate, CertifEndDate, CertifDescription) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/cert/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "name":CertifTitle,
            "start_date":CertifStartDate,
            "end_date":CertifEndDate,
            "description":CertifDescription
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}

function OnClickLanguagesFunction(){
    // Languages
    var LanguageName = document.getElementById("languageName").value
    var LanguageLevel = document.getElementById("languageLevel").value
    // Ids
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    Language(user_Data.idUser, id_CV, LanguageName, LanguageLevel)
}
function Language(Id_User, Id_Cv, LanguageName, LanguageLevel) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/lang/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "name":LanguageName,
            "level":LanguageLevel
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
*/