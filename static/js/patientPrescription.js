window.addEventListener("DOMContentLoaded" , ()=>{
    const appointmentId = new URLSearchParams(window.location.search).get("appointmentId")
    fetch("/getPatientPrescription.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `appointmentId=${appointmentId}`
    })
    .then(res => res.json())
    .then(data =>{
        console.log(data)
    })
})