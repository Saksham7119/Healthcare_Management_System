window.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search)
    const patientId = params.get("patientId")
    const appointmentId = params.get("appointmentId")
    const clinicId = params.get("clinicId")
    fetch("getPatientDetailsForPrescription.do", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `clinicId=${clinicId}&patientId=${patientId}&appointmentId=${appointmentId}`
    })
        .then(res => res.json())
        .then((data) => {

            console.log(data)

            const appointmentIdInput = document.getElementById("appointmentIdInput")
            const patientNameInput = document.getElementById("patient_name")
            const ageInput = document.getElementById("age")
            const heightInput = document.getElementById("height")
            const weightInput = document.getElementById("weight")
            const bpInput = document.getElementById("bp")
            const genderInput = document.getElementById("gender")
            const diagnosedInput = document.getElementById("disease")

            for (let obj of data) {

                const patientName = obj?.patient?.user?.name;
                const dob = obj?.patient?.user?.dob;
                const genderFromObj = obj?.patient?.user?.gender;
                const height = obj?.patient?.height;
                const weight = obj?.patient?.weight;
                const bp = obj?.patient?.bp;
                const diagnosed = obj?.diagnosed;
                const appointmentId = obj?.appointmentId

                let gender = null;
                if (genderFromObj == "1") {
                    gender = "Male";
                } else if (genderFromObj == "2") {
                    gender = "Female";
                } else {
                    gender = "Not Specified";
                }

                const todaysDate = new Date();
                const genderObj = new Date(dob);
                let patientAge =
                    todaysDate.getFullYear() - genderObj.getFullYear();
                if (todaysDate.getMonth() < genderObj.getMonth()) {
                    patientAge--;
                } else if (
                    todaysDate.getMonth() === genderObj.getMonth() &&
                    todaysDate.getDate() < genderObj.getDate()
                ) {
                    patientAge--;
                }

                patientNameInput.value = patientName;
                heightInput.value = height;
                weightInput.value = weight;
                bpInput.value = bp;
                genderInput.value = gender
                ageInput.value = patientAge;
                diagnosedInput.value = diagnosed;               
                appointmentIdInput.value = appointmentId
            }

            const savePrescriptionBtn = document.getElementById("savePrescriptionBtn")
            const prescriptionForm = document.getElementById("prescriptionForm")
            savePrescriptionBtn.addEventListener("click" , (e)=>{
                e.preventDefault();
                const formData = new FormData(prescriptionForm)

                fetch("savePrescription.do" ,{
                    method:"POST",
                    body: new URLSearchParams([...formData])
                })
                .then(res => res.text())
                .then(data => {
                    console.log("Backend Response: " + data)
                    if(data.includes("successfully"))alert("Prescription Has been saved!")
                    
                })
            })


        })
})