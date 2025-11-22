window.addEventListener("DOMContentLoaded", () => {
    const doctorsCardParentDiv = document.getElementById("doctorsCardParentDiv");
    fetch("showClinicsForPatient.do", { method: "GET" })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let cardHTMLs = [];
            for (let obj of data) {
                let scheduleListHTML = [];
                //--- Clinic Details ---
                const aboutMe = obj.aboutMe;
                const clinicAddress = obj.address;
                const clinicId = obj.clinicId;
                const clinicContact = obj.contact;
                const clinicName = obj.name;
                const firstVisitCharge = obj.firstVisitCharges;
                const nextVisitCharge = obj.nextVisitCharges;
                const clinicCity = obj.location.city.name;
                const clinicDays = obj.clinicDay.map(dayName => dayName.day.day).join(', ');
                const imageFileName = obj.clinicImage?.[0]?.image;
                let path = imageFileName
                    ? "showClinicImage.do?pic_path=" + imageFileName
                    : "static/media/images/dummyClinicImage.png";

                //--- Doctor Details ---
                const aboutDoctor = obj.doctor?.aboutMe;
                const practiceStartDateOfDoctor = obj.doctor?.practiceStartDate;
                const doctorAdress = obj.doctor?.user?.address;
                const doctorContact = obj.doctor?.user?.contact;
                const doctorEmail = obj.doctor?.user?.email;
                const doctorName = obj.doctor?.user?.name;
                const doctorGenderFromObj = obj.doctor?.user?.gender;
                const doctorDob = obj.doctor?.user?.dob;


                let doctorGender = null;
                if (doctorGenderFromObj === '1') {
                    doctorGender = "Male";
                } else if (doctorGenderFromObj === '2') {
                    doctorGender = "Female";
                } else {
                    doctorGender = "Not Specified";
                }

                const practiceStartDateOfDoctorFromObj = new Date(practiceStartDateOfDoctor);
                const todaysDate = new Date();

                const doctorExpirience = todaysDate.getFullYear() - practiceStartDateOfDoctorFromObj.getFullYear();

                const dobOfDoctorFromObj = new Date(doctorDob);
                let doctorAge = todaysDate.getFullYear() - dobOfDoctorFromObj.getFullYear();
                if (todaysDate.getMonth() < dobOfDoctorFromObj.getMonth()) {
                    doctorAge--;
                }
                else if (todaysDate.getMonth() === dobOfDoctorFromObj.getMonth() && todaysDate.getDate() < dobOfDoctorFromObj.getDate()) {
                    doctorAge--;
                }

                //---Schedule Details
                const schedules = obj.schedule;
                let scheduleCounter = 1;
                for (let schedule of schedules) {
                    const scheduleId = schedule?.scheduleId;
                    const startTime = schedule?.startTime;
                    const endTime = schedule?.endTime;
                    const patientSlot = schedule?.patientLimit;
                    let listItemHTML = `
    <option value="${scheduleId}" data-slots="${patientSlot}">
        ${startTime} - ${endTime} (Slots: ${patientSlot})
    </option>
`;
                    scheduleListHTML.push(listItemHTML);
                }

                const cardHtml = `

    <div class="clinic-card">

        <!-- LEFT SIDE -->
        <div class="clinic-card-left">

            <div class="clinic-card-image-wrapper">
                <img src="${path}" class="clinic-card-image denominationImage" alt="Clinic Image" id="image-${clinicId}">
            </div>

            <input type="hidden" class="clinicId" value="${clinicId}">

            <div class="clinic-card-footer">
                <button class="action-button btn-primary">Book Appointment</button>

                <div class="clinic-schedule">
                    <select class="clinic-slot-select" id="slotSelect-${clinicId}">
                        <option value="" disabled selected>View Clinic Timings</option>
                        ${scheduleListHTML.join("")}
                    </select>
                </div>
            </div>

        </div> <!-- END clinic-card-left -->

        <!-- RIGHT SIDE -->
        

            <div class="clinic-header">
                <h3 class="clinic-name">${clinicName}</h3>
                <div class="clinic-status-badge">Active</div>
            </div>
<div class="clinic-card-body">
            <div class="clinic-info-grid">
                <div class="info-item">
                    <i class="bi bi-geo-alt-fill info-icon"></i>
                    <div class="info-content">
                        <span class="info-label">Address</span>
                        <span class="info-value">${clinicAddress}</span>
                    </div>
                </div>

                <div class="info-item">
                    <i class="bi bi-telephone-fill info-icon"></i>
                    <div class="info-content">
                        <span class="info-label">Contact</span>
                        <span class="info-value">${clinicContact}</span>
                    </div>
                </div>

                <div class="info-item">
                    <i class="bi bi-calendar-day info-icon"></i>
                    <div class="info-content">
                        <span class="info-label">Days Clinic Will Open</span>
                        <span class="info-value">${clinicDays}</span>
                    </div>
                </div>
            </div>

            <div class="section-divider">
                <div class="clinic-about">
                    <div class="about-header">
                        <i class="bi bi-info-circle-fill"></i>
                        <span>About Clinic</span>
                    </div>
                    <p class="about-text">${aboutMe}</p>
                </div>

                <div class="doctor-about">
                    <div class="about-header">
                        <i class="bi bi-person-circle"></i>
                        <span>About Doctor</span>
                    </div>
                    <p class="about-text"><strong>Name:</strong> ${doctorName}</p>
                    <p class="about-text"><strong>Experience:</strong> ${doctorExpirience}</p>
                    <p class="about-text"><strong>Age:</strong> ${doctorAge}</p>
                    <p class="about-text"><strong>Gender:</strong> ${doctorGender}</p>
                    <p class="about-text"><strong>Bio:</strong> ${aboutDoctor}</p>
                    <p class="about-text"><strong>Contact:</strong> ${doctorContact} | <strong>Email:</strong> ${doctorEmail}</p>
                    <p class="about-text"><strong>Address:</strong> ${doctorAdress}</p>
                </div>
            </div>

            <div class="clinic-pricing">
                <div class="pricing-card">
                    <div class="pricing-icon">
                        <i class="bi bi-calendar-check"></i>
                    </div>
                    <div class="pricing-details">
                        <span class="pricing-label">First Visit</span>
                        <span class="pricing-amount">Rs ${firstVisitCharge}</span>
                    </div>
                </div>

                <div class="pricing-card">
                    <div class="pricing-icon">
                        <i class="bi bi-arrow-repeat"></i>
                    </div>
                    <div class="pricing-details">
                        <span class="pricing-label">Follow-up</span>
                        <span class="pricing-amount">Rs ${nextVisitCharge}</span>
                    </div>
                </div>
            </div>

        </div>

</div>`;
                cardHTMLs.push(cardHtml);
            }
            doctorsCardParentDiv.innerHTML = cardHTMLs.join("");
        })
});
