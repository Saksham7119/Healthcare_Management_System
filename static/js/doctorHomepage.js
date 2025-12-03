window.addEventListener("load", () => {
  const modalElement = document.getElementById('configureDoctorModal');

  if (modalElement) {
    const configureDoctorModalvar = new bootstrap.Modal(modalElement);
    configureDoctorModalvar.show()

    const configureDoctorModalCloseBtn = document.querySelector("#configureDoctorModalCloseBtn")

    // **ADD THIS NULL CHECK** to prevent the error
    //   if (configureDoctorModalCloseBtn) { 
    //       configureDoctorModalCloseBtn.addEventListener("click" , ()=>{
    //         configureDoctorModalvar.hide()  
    //       })
    //   } else {
    //       // Optional: Log an error to help with debugging the HTML
    //       console.error("Error: Could not find the close button with ID #configureDoctorModalCloseBtn"); 
    //   }
    // }
  }
})



//################# HANDLING NOTIFICATIONS #################################

const notificationPanel = document.getElementById("notification-panel");
const deleteNotiBtn = document.getElementById("deleteNotiBtn");

window.addEventListener("DOMContentLoaded", function () {
  fetch("showNotification.do", { method: "POST" })
    .then((res) => res.json())
    .then((arr) => {
      if (arr.length != 0) {
        for (let obj of arr) {
          notificationPanel.insertAdjacentHTML("afterbegin",
            '<div class="alert alert-info d-flex justify-content-between align-items-center rounded-pill px-4" role="alert">' +
            '<input class="notificationId" id="notificationID" hidden value="' +
            obj.notificationID +
            '">' +
            "<span>" +
            obj.notificationMessage + " at " + obj.createdAt +
            "</span>" +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            "</div>"
          )
        }
      } else {
        notificationPanel.insertAdjacentHTML(
          "afterbegin",
          '<div class="alert alert-info d-flex justify-content-between align-items-center rounded-pill px-4" role="alert">' +
          "<span>There is no notifications to show...</span>" +
          '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" id="deleteNotiBtn"></button>' +
          "</div>"
        )
      }
    });

  fetch("showClinicAppointment.do", { method: "POST" })
    .then(res => res.json())
    .then((data) => {
      console.log(data)

      const appointmentsContainer = document.getElementById("appointments-container")

      let cardHTMLs = []
      for (let obj of data) {
        const clinicName = obj.name;
        const clinicId = obj.clinicId;
        const schedules = obj?.schedule;

        for (let s of schedules) {
          const endTime = s?.endTime;
          const startTime = s?.startTime;
          const appointments = s?.appointment;

          for (let a of appointments) {
            const appointmentDate = a?.appointmentDate;
            const appointmentId = a?.appointmentId;
            const diagnosed = a?.diagnosed;
            const patientId = a?.patient?.patientId
            const name = a?.patient?.user?.name;
            const contact = a?.patient?.user?.contact;

            const patientAgeFromDB = a?.patient?.user?.dob;
            const todaysDate = new Date();
            const patientAgeFromObj = new Date(patientAgeFromDB);
            let patientAge = todaysDate.getFullYear() - patientAgeFromObj.getFullYear();

            if (todaysDate.getMonth() < patientAgeFromObj.getMonth()) {
              patientAge--;
            } else if (
              todaysDate.getMonth() === patientAgeFromObj.getMonth() &&
              todaysDate.getDate() < patientAgeFromObj.getDate()
            ) {
              patientAge--;
            }

            const genderFromObj = a?.patient?.user?.gender;
            let patientGender = null;
            if (genderFromObj == "1") {
              patientGender = "Male";
            } else if (genderFromObj == "2") {
              patientGender = "Female";
            } else {
              patientGender = "Not Specified";
            }


            console.log({
              clinicName,
              clinicId,
              endTime,
              startTime,
              appointmentDate,
              appointmentId,
              diagnosed,
              name,
              patientId,
              contact,
              patientAge,
              patientGender
            })

            const cardHTML = `
                <div class="appointment-card-wrapper">
                    <input type="hidden" id="clinicId" value="${clinicId}">
                    <input type="hidden" id="patientId" value="${patientId}">
                    <input type="hidden" id="appointmentId" value="${appointmentId}">
                    <div class="appointment-info-grid">
                        <div class="info-field">
                            <div class="field-label">Clinic Name</div>
                            <div class="field-value">${clinicName}</div>
                        </div>
                        
                        <div class="info-field">
                            <div class="field-label">Patient Name</div>
                            <div class="field-value">${name}</div>
                        </div>
                        
                        <div class="info-field">
                            <div class="field-label">Patient Age</div>
                            <div class="field-value">${patientAge}yrs</div>
                        </div>
                        
                        <div class="info-field">
                            <div class="field-label">Gender</div>
                            <div class="field-value">${patientGender}</div>
                        </div>
                        
                        <div class="info-field">
                            <div class="field-label">Appointment Date</div>
                            <div class="field-value">${appointmentDate}</div>
                        </div>
                    
                        <div class="info-field">
                            <div class="field-label">Clinic Time</div>
                            <div class="field-value">${startTime} - ${endTime}</div>
                        </div>
                        
                        <div class="info-field">
                            <div class="field-label">Diagnosed With</div>
                            <div class="field-value">${diagnosed}</div>
                        </div>
                    </div>
                    <div class="appointment-actions">
                        <button class="action-button primary-action generatePrescriptionBtn">Generate Prescription</button>
                        <button class="action-button secondary-action">Request to Reschedule</button>
                    </div>
                </div>
            `;
            cardHTMLs.push(cardHTML)
            //-----
          }
        }
        appointmentsContainer.innerHTML = cardHTMLs.join("")

        appointmentsContainer.addEventListener("click", (e) => {
          if (e.target.classList.contains("generatePrescriptionBtn")) {
            const appointmentId = document.getElementById("appointmentId").value
            const patientId = document.getElementById("patientId").value
            const clinicId = document.getElementById("clinicId").value
            console.log(appointmentId)
            this.window.location.href = `doctorPrescription.do?appointmentId=${appointmentId}&patientId=${patientId}&clinicId=${clinicId}`
          }

          if (e.target.classList.contains("secondary-action")) {
            alert("Clicked Reschedule Request!");
            return;
          }
        })
      }
    })
});

// const notiID = document.getElementById("notificationID")

notificationPanel.addEventListener("click", (e) => {
  if (e.target.classList.contains("btn-close")) {
    const alertDiv = e.target.closest(".alert");
    const notiId = alertDiv.querySelector(".notificationId").value;

    fetch("deleteNotification.do", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: "notificationId=" + encodeURIComponent(notiId),
    })
      .then((res) => res.text())
      .then((msg) => {
        console.log("Notification deleted:", msg);
      });
  }
});

//################# HANDLING NOTIFICATIONS #################################