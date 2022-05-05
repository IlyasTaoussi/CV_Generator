function OnClickContactFunction(){
    // Contact
    var Mail = document.getElementById("email")
    var Name = document.getElementById("name")
    var Address = document.getElementById("address")
    var Number = document.getElementById("phoneNumber")
    var Links = document.getElementById("links")
    // Reset all inputs
    document.getElementById("contact_form").reset()
    // API request
    CallWebAPIContact()
}
function OnClickSummaryFunction(){
    // Summary
    var Summary = document.getElementById("summary")
    // Reset all inputs
    document.getElementById("summary_form").reset()
    // API request
    CallWebAPISummary()
}
function OnClickPEFunction(){
    // Professional experience
    var Position = document.getElementById("position")
    var Company = document.getElementById("company")
    var City = document.getElementById("city")
    var StartDate = document.getElementById("startDate")
    var EndDate = document.getElementById("endDate")
    var JobDescription = document.getElementById("jobDescription")
    var Techno = document.getElementById("techno")
    // Reset all inputs
    document.getElementById("experience_form").reset()
    // API request
    CallWebAPIPE()
}
function OnClickEducationFunction(){
    // Graduation
    var SchoolingLevel = document.getElementById("schoolingLevel")
    var Domain = document.getElementById("domain")
    var School = document.getElementById("school")
    var SchoolCity = document.getElementById("schoolCity")
    var FormationStartDate = document.getElementById("formationStartDate")
    var FormationEndDate = document.getElementById("formationEndDate")
    // Reset all inputs
    document.getElementById("formation_form").reset()
    // API request
    CallWebAPIEducation()
}
function OnClickSkillsFunction(){
    // Skills
    var Skill = document.getElementById("skills")
    var SkillLevel = document.getElementById("skillsLevel")
    // Reset all inputs
    document.getElementById("skills_form").reset()
    // API request
    CallWebAPISkill()
}
function OnClickCertificationsFunction(){
    // Certifications
    var CertifTitle = document.getElementById("certifTitle")
    var CertifStartDate = document.getElementById("certifStartDate")
    var CertifDescription = document.getElementById("certifDescription")
    // Reset all inputs
    document.getElementById("certification_form").reset()
    // API request
    CallWebAPICertification()
}
function OnClickLanguagesFunction(){
    // Languages
    var LanguageName = document.getElementById("languageName")
    var LanguageLevel = document.getElementById("languageLevel")
    // Reset all inputs
    document.getElementById("language_form").reset()
    // API request
    CallWebAPILanguage()
}

function CallWebAPIContact() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    contact:{
                        mail:Mail,
                        name:Name,
                        address:Address,
                        phone_number:Number,
                        links:Links,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPISummary() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    summary:Summary
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPIPE() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    professional_experience:{
                        position:Position,
                        company_name:Company,
                        localisation:City,
                        start_date:startDate,
                        end_date:EndDate,
                        technos:Techno,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPIEducation() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    education:{
                        degree:SchoolingLevel,
                        field:Domain,
                        school:School,
                        location:SchoolCity,
                        start_date:FormationStartDate,
                        end_date:FormationEndDate,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPISkill() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    skill:{
                        name:Skill,
                        level:SkillLevel,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPICertification() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    certification:{
                        name:CertifTitle,
                        start_date:CertifStartDate,
                        end_date:CertifEndDate,
                        description:CertifDescription,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}
function CallWebAPILanguage() {
    // POST request
    fetch('https://test.com/api/user_id/user', {
        Method: 'POST',
            Headers: {'Content-Type': 'application/json; charset=UTF-8'},
            Body: JSON.stringify({
                cv:{
                    language:{
                        name:LanguageName,
                        level:LanguageLevel,
                    }
                }
            })
    }).then(function(response) => {
         if(response.status === 200){
             // Success : reloading current page
             window.location.href = "../public/cv_form.html"
         }
    }).catch(error => console.error(error))
}