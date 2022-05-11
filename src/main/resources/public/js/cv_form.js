function OnClickContactFunction(){
    // Contact
    var Mail = document.getElementById("email").value
    var Name = document.getElementById("name").value
    var Address = document.getElementById("address").value
    var Number = document.getElementById("phoneNumber").value
    var Links = document.getElementById("links").value
    // Ids
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API Request
    Contact(user_Data.idUser, id_CV, Mail, Name, Address, Number, Links)
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
            // Success : reloading current page
            //window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}

function OnClickSummaryFunction(){
    // Summary
    var Summary = document.getElementById("summary").value
    // Ids
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    Summary(user_Data.idUser, id_CV, Summary)
}
function Summary(Id_User,Id_Cv, Summary) {
    fetch('http://localhost:8080/api/user/' + Id_User + '/summary/new?cv=' + Id_Cv, {
        method: 'PUT',
        headers: {
            Accept: 'application/json','Content-Type': 'application/json; charset=utf8'
        },
        body: JSON.stringify({
            "summary":Summary
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
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
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    ProfessionalExperience(user_Data.idUser, id_CV, Position, Company, City, StartDate, EndDate, JobDescription, Techno)
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
            "start_date":startDate,
            "end_date":EndDate,
            "technos":Techno
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
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
    let user_Data = sessionStorage.getItem("userData")
    let id_CV = sessionStorage.getItem("id_CV")
    // API request
    Education(user_Data.idUser, id_CV, SchoolingLevel, Domain, School, SchoolCity, FormationStartDate, FormationEndDate)
}
function Education(Id_User, Id_Cv, SchoolingLevel, Domain, School, SchoolCity, FormationStartDate, FormationEndDate) {
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
            "start_date":FormationStartDate,
            "end_date":FormationEndDate
        })
    }).then(response => {
         if(response.status === 200){
             // Success : reloading current page
             //window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}

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
