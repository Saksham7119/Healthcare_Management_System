window.addEventListener("DOMContentLoaded" , () => {
  const appointmentsCardParentDiv = document.getElementById("appointmentsCardParentDiv")
     fetch("collect_appointments.do", {method:"POST"})
      .then(res => res.json())
      .then((data) => {
        let cardHTMLs = [];

        for(let obj of data){
          const appointmentDate = obj.appointmentDate;
          const appointmentId = obj.appointmentId;
          const diagnosed = obj.diagnosed;
          const bloodGroup = obj?.patient?.bloodGroup?.name;
          const bp =  obj?.patient?.bp;
          const height =  obj?.patient?.height;
          const history =  obj?.patient?.history;
          const patientId =  obj?.patient?.patientId;
          const patientName = obj?.patient?.user?.name;
          const endTime = obj?.schedule?.endTime;
          const startTime = obj?.schedule?.startTime;
          const clinicName = obj?.schedule?.clinic?.name;
          const clinicId = obj?.schedule?.clinic?.clinicId;
          const clinicContact = obj?.schedule?.clinic?.contact;
          const clinicFirstCharge = obj?.schedule?.clinic?.firstVisitCharges;
          const clinicNextCharge = obj?.schedule?.clinic?.nextVisitCharges;
          const clinicImage = obj?.schedule?.clinic?.clinicImage?.[0]?.image;
          const doctorName = obj?.schedule?.clinic?.doctor?.user?.name;
          const doctorContact = obj?.schedule?.clinic?.doctor?.user?.contact

          let path = clinicImage ? "showClinicImage.do?pic_path="+clinicImage :"static/media/images/dummyClinicImage.png";

          let cardHtml = `
          <div class="appointment-card">
        <div class="card-container">
            <div class="clinic-section">
                    <div class="clinic-info">
                        <img class="clinic-logo" src="${path}" alt="Clinic Logo">
                        <div class="clinic-name">${clinicName}</div>
                        <div class="clinic-id>${clinicId}</div>
                        <div class="doctor-name">Dr. ${doctorName}</div>
                        <div class="contact-info">Contact: ${doctorContact}</div>
                    </div>
                    <div class="clinic-actions">
                        <button class="viewPrescriptionBtn" data-appointment-id=${appointmentId}>View Prescription</button>
                        <button class="cancelAppointmentBtn">Cancel Appointment</button>
                    </div>
                </div>

            <div class="details-section">
                <div class="header-row">
                    <div class="header-title">Appointment Details</div>
                    <div class="appointment-badge">${appointmentId}</div>
                </div>

                <div class="info-grid">
                    <div class="info-box">
                        <div class="info-label">Date</div>
                        <div class="info-value">${appointmentDate}</div>
                    </div>
                    <div class="info-box">
                        <div class="info-label">Time Slot</div>
                        <div class="info-value">${startTime} - ${endTime}</div>
                    </div>
                    <div class="info-box">
                        <div class="info-label">Diagnosis Status</div>
                        <div class="info-value status-completed">${diagnosed}</div>
                    </div>
                </div>

                <div class="section-title">Patient Information</div>
                <div class="patient-details">
                    <div class="patient-header">${patientName}</div>
                    <div class="vitals-grid">
                        <div class="vital-item">
                            <div class="vital-label">Blood Group</div>
                            <div class="vital-value">${bloodGroup}</div>
                        </div>
                        <div class="vital-item">
                            <div class="vital-label">Blood Pressure</div>
                            <div class="vital-value">${bp}</div>
                        </div>
                        <div class="vital-item">
                            <div class="vital-label">Height (in cm)</div>
                            <div class="vital-value">${height}</div>
                        </div>
                        <div class="vital-item">
                            <div class="vital-label">History</div>
                            <div class="vital-value">${history}</div>
                        </div>
                    </div>
                </div>

                <!-- Billing -->
                <div class="section-title">Billing Information</div>
                <div class="billing-section">
                    <div class="billing-item">
                        <span class="billing-label">First Visit Charge:</span>
                        <span class="billing-amount">Rs ${clinicFirstCharge}</span>
                    </div>
                    <div class="billing-item">
                        <span class="billing-label">Next Visit Charge:</span>
                        <span class="billing-amount">Rs ${clinicNextCharge}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;

    cardHTMLs.push(cardHtml)
  }
        appointmentsCardParentDiv.innerHTML = cardHTMLs.join("")
        appointmentsCardParentDiv.addEventListener("click",(e)=>{

          if(e.target.classList.contains("viewPrescriptionBtn")){
            const appointmentId = e.target.dataset.appointmentId;
            console.log(appointmentId)
            window.location.href = `showPrescriptionToPatient.do?appointmentId=${appointmentId}`
          }

          if(e.target.classList.contains("cancelAppointmentBtn")){
            const appointmentCard = e.target.closest(".appointment-card")
            if(appointmentCard){
              alert("Cancel Appointment Feature is still under developement!")
            }
          }

        })

        const searchAppointmentInput = document.querySelector(".searchAppointmentInput")
        searchAppointmentInput.addEventListener("input" , ()=>{
          alert("Search Appointments Feature is still in development!")
        })

      })
    })
