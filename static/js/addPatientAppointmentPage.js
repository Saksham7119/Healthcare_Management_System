window.addEventListener("DOMContentLoaded", () => {
  const showClinicDetailsDiv = document.getElementById("showClinicDetailsDiv");
  const params = new URLSearchParams(window.location.search);
  const clinicId = params.get("clinicId");
  console.log("Clinic ID:", clinicId);

  fetch("collectClinicForPatientAppointment.do", {
    method: "POST",
    headers: { "Content-type": "application/x-www-form-urlencoded" },
    body: `clinicId=${clinicId}`,
  })
    .then((res) => res.json())
    .then((data) => {
      console.log("Clinic Data:", data);
      let cardHTMLs = [];
      let globalClinicDays = "";
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
        const clinicDays = obj.clinicDay
          .map((dayName) => dayName.day.day)
          .join(", ");
        globalClinicDays = clinicDays;
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
        if (doctorGenderFromObj === "1") {
          doctorGender = "Male";
        } else if (doctorGenderFromObj === "2") {
          doctorGender = "Female";
        } else {
          doctorGender = "Not Specified";
        }

        const practiceStartDateOfDoctorFromObj = new Date(
          practiceStartDateOfDoctor
        );
        const todaysDate = new Date();

        const doctorExpirience =
          todaysDate.getFullYear() -
          practiceStartDateOfDoctorFromObj.getFullYear();

        const dobOfDoctorFromObj = new Date(doctorDob);
        let doctorAge =
          todaysDate.getFullYear() - dobOfDoctorFromObj.getFullYear();
        if (todaysDate.getMonth() < dobOfDoctorFromObj.getMonth()) {
          doctorAge--;
        } else if (
          todaysDate.getMonth() === dobOfDoctorFromObj.getMonth() &&
          todaysDate.getDate() < dobOfDoctorFromObj.getDate()
        ) {
          doctorAge--;
        }

        //---Schedule Details
        const schedules = obj.schedule;
        let i = 1;
        for (let schedule of schedules) {
          const scheduleId = schedule?.scheduleId;
          const startTime = schedule?.startTime;
          const endTime = schedule?.endTime;
          const patientSlot = schedule?.patientLimit;
          let listItemHTML = `
                            <tr>

                            <td><input type=hidden value="${scheduleId}">${i}</td>
                            <td>${startTime}</td>
                            <td>${endTime}</td>
                            <td>${patientSlot}</td>
                            <td><button class="bookAppointmentSlotBtn">Book Appointment</button></td>
                            </tr>
                    `;
          scheduleListHTML.push(listItemHTML);
          i++;
        }

        const cardHtml = `

                <div class="clinic-card">

                <!-- LEFT SIDE -->
                <div class="clinic-card-left">

                    <input type="hidden" class="clinicId" value="${clinicId}">
                     <!--   <div class="clinic-schedule">
                            <select class="clinic-slot-select" id="slotSelect-${clinicId}">
                                <option value="" disabled selected>View Clinic Timings</option>
                                ${scheduleListHTML.join("")}
                            </select>
                        </div> -->
                  

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
                        <p class="about-text"><strong>Experience:</strong> ${doctorExpirience} yrs</p>
                        <p class="about-text"><strong>Age:</strong> ${doctorAge} yrs</p>
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

                     <div class="appointmentDatePicker">
                        <div class="date-icon">
                            <i class="bi bi-calendar-date"></i>
                        </div>
                        <div class="date-details">
                            <div>
                            <span class="date-label">Select Date To Check Slot</span><br>
                            <input type="date" name="appointmentDate" id="appointmentDate"> <button class="checkSlotBtn">Check Slots</button>
                            </div>
                        </div>
                    </div>

                    </div>
                   <div class="selectAppointmentSlot" style="display:none;">
                <div class="appointment-header">
                    <i class="bi bi-clock-history"></i>
                    <span>Available Appointment Slots</span>
                </div>
                <div class="table-container">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>S.no</th>
                                <th>Start Time</th>
                                <th>End Time</th>
                                <th>Patient Slots</th>
                                <th>Book</th>
                            </tr>
                        </thead>
                        <tbody>
                        ${scheduleListHTML.join("")}
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
    </div>`;
        cardHTMLs.push(cardHtml);
      }
      showClinicDetailsDiv.innerHTML = cardHTMLs.join("");

      const checkSlotBtn = document.querySelector(".checkSlotBtn");
      const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      const showAppointmentFormParentDiv = document.querySelector(".showAppointmentFormParentDiv");
      const selectAppointmentSlot = document.querySelector(".selectAppointmentSlot");
      let globalAppointmentDate = '';
      checkSlotBtn.addEventListener("click", () => {
        const appointmentDateInput = document.getElementById("appointmentDate").value;

        if (!appointmentDateInput) {
          alert("Please select a date!");
          return;
        }

        const appointmentDateInputValue = new Date(appointmentDateInput);
        globalAppointmentDate = appointmentDateInputValue.toISOString().split("T")[0];
        const appointmentDayIndex = appointmentDateInputValue.getDay();

        const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        const appointmentDayName = days[appointmentDayIndex];

        const clinicDaysArray = globalClinicDays.split(",").map(d => d.trim());

        if (clinicDaysArray.includes(appointmentDayName)) {
          selectAppointmentSlot.style.display = "block";
        } else {
          alert("Clinic is CLOSED on this selected date. Please check 'days clinic will open' section.");
        }
      });

      const appointmentForm = document.getElementById("appointment-form");
      const submitAppointmentFormBtn = document.querySelector(".submitAppointmentFormBtn");
      let selectedScheduleId = null;

      selectAppointmentSlot.addEventListener("click", (e) => {
        if (e.target.classList.contains("bookAppointmentSlotBtn")) {

          selectedScheduleId = e.target.closest("tr").querySelector("input[type='hidden']").value;

          console.log("Selected Schedule ID:", selectedScheduleId);
          console.log("Selected Appointment Date:", globalAppointmentDate);

          showAppointmentFormParentDiv.style.display = "block";
        }
      });
      submitAppointmentFormBtn.addEventListener("click", (e) => {
        e.preventDefault();

        const formData = new FormData(appointmentForm);
        console.log("Selected Schedule ID before appending to FormData:", selectedScheduleId);
        console.log("Global Appointment Date before appending to FormData:", globalAppointmentDate);
        formData.append("scheduleId", selectedScheduleId);
        formData.append("appointmentDate", globalAppointmentDate);

        console.log("FormData:");
        for (const [k, v] of formData.entries()) {
          console.log(k, v);
        }

        fetch("bookPatientAppointment.do", {
          method: "POST",
          body: new URLSearchParams([...formData])
        })
          .then(res => res.text())
          .then(data => {
            let response = data.toString();
            if(response.includes("successfully")) {
              alert("Appointment booked successfully!");
              appointmentForm.reset();
              showAppointmentFormParentDiv.style.display = "none";
            }
            console.log("Appointment Booking Response:", data);
          });
      });

     

      //----
    });
});
