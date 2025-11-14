const buttons = document.getElementsByTagName("button");
const medicineCardDiv = document.getElementById("medicineCardDiv");
const mainClinicDiv = document.getElementById("mainClinicDiv");
const clinicCardParentDiv = document.getElementById("clinicCardParentDiv");
const clinicCardRemoveBtn = document.getElementById("clinicCardRemoveBtn");
const mainScheduleShowTable = document.querySelector(".mainScheduleShowTable")

window.addEventListener("DOMContentLoaded", function () {
  const clinicCardParentDiv = document.getElementById("clinicCardParentDiv");
  const uploadEditImageBtn = document.getElementById("uploadEditImageBtn");
  const pic = document.getElementById("pic");
  const medicineImageEditForm = document.getElementById(
    "medicineImageEditForm"
  );
  let currentDenominationId = null;
  let denominationImageMap = {};

  fetch("showClinics.do", { method: "POST" })
    .then((res) => res.json())
    .then((data) => {
      let clinics = data.clinics;
      const clinicDays = data.clinicDays;
      const clinicImages = data.clinicImages;

      console.log("CLINICS --", clinics);
      console.log("CLINICDAYS --", clinicDays);
      console.log("CLINICIMAGES --", clinicImages);

      clinics = clinics.map((clinic) => {
        const relatedDays = clinicDays.filter(
          (d) => d.clinic.clinicId === clinic.clinicId
        );
        const relatedImages = clinicImages.filter(
          (img) => img.clinic && img.clinic.clinicId == clinic.clinicId
        );

        return {
          ...clinic,
          clinicDays: relatedDays,
          clinicImages: relatedImages,
        };
      });

      console.log("MERGED CLINICS - ", clinics);

      let cardHTMLs = [];

      for (let obj of clinics) {
        const clinicId = obj.clinicId;
        const clinicName = obj.name;
        const address = obj.address;
        const city = obj.location.city.name;
        const contact = obj.contact;
        const aboutMe = obj.aboutMe;
        const firstVisitCharge = obj.firstVisitCharges;
        const nextVisitCharge = obj.nextVisitCharges;
        const days = obj.clinicDays.map((d) => d.day.day).join(", ");

        const imageFileName = obj.clinicImages[0]?.image;
        console.log(imageFileName);
        let path = imageFileName
          ? "showClinicImage.do?pic_path=" + imageFileName
          : "static/media/images/dummyClinicImage.png";

        // let path = "static/media/images/dummyClinic.png";

        const cardHtml = `
          <div class="col" data-clinic-id="${clinicId}">
            <div class="clinic-card">
              <!-- Image Section with Overlay -->
              <div class="clinic-card-image-wrapper">
                <img src="${path}" class="clinic-card-image denominationImage" alt="Clinic Image" id="image-${clinicId}">
              </div>

              <input hidden class="clinicId" value="${clinicId}">

              <!-- Content Section -->
              <div class="clinic-card-body">
                <div class="clinic-header">
                  <h3 class="clinic-name">${clinicName}</h3>
                  <div class="clinic-status-badge">Active</div>
                </div>

                <div class="clinic-info-grid">
                  <div class="info-item">
                    <i class="bi bi-geo-alt-fill info-icon"></i>
                    <div class="info-content">
                      <span class="info-label">Address</span>
                      <span class="info-value">${address} , ${city}</span>
                    </div>
                  </div>

                  <div class="info-item">
                    <i class="bi bi-telephone-fill info-icon"></i>
                    <div class="info-content">
                      <span class="info-label">Contact</span>
                      <span class="info-value">${contact}</span>
                    </div>
                  </div>

                  <div class="info-item">
                    <i class="bi bi-calendar-day info-icon"></i>
                    <div class="info-content">
                      <span class="info-label">Days Clinic Will Open?</span>
                      <span class="info-value">${days}</span>
                    </div>
                  </div>
                </div>

                <div class="clinic-about">
                  <div class="about-header">
                    <i class="bi bi-info-circle-fill"></i>
                    <span>About Clinic</span>
                  </div>
                  <p class="about-text">${aboutMe}</p>
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

              <!-- Action Buttons -->
              <div class="clinic-card-footer">
              <button class="btn-clinic-action clinicCardSetSchdBtn" data-bs-toggle="modal" data-bs-target="#setScheduleModal">
                <i class="bi bi-calendar-week"></i>
                <span>Edit Schedule</span>
              </button>
              <button class="btn-clinic-action  clinicCardViewSchdBtn ">
                <i class="bi bi-calendar-week"></i>
                <span>View Schedule</span>
              </button>
                <button class="btn-clinic-action btn-edit clinicCardEditBtn " data-bs-toggle="modal" data-bs-target="#medicineEditForm">
                  <i class="bi bi-pencil-square"></i>
                  <span>Edit Details</span>
                </button>
                <button class="btn-clinic-action btn-remove clinicCardRemoveBtn">
                  <i class="bi bi-trash"></i>
                  <span>Remove</span>
                </button>
              </div>
            </div>
          </div>
        `;
        cardHTMLs.push(cardHtml);
      }
      clinicCardParentDiv.innerHTML = cardHTMLs.join("");
    });

  // const searchInput = document.getElementById("searchClinicInput");
  // if (searchInput) {
  //   searchInput.addEventListener("input", applySearchFilter);
  // }

  // function applySearchFilter() {
  //   const searchTerm = searchInput.value.toLowerCase().trim();
  //   let filteredCardHTMLs = [];

  //   for (let denomObj of denominations) {
  //     const denominationId = denomObj.medicineDenominationId;
  //     const imageFileName = denominationImageMap[denominationId];
  //     let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'dummyMedicine.jpg';

  //     const fullMedicineFormat = denomObj.medicineFormat;

  //     let medicineDetails = null;
  //     if (fullMedicineFormat && fullMedicineFormat.medicine) {
  //       medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
  //     }

  //     const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : '';
  //     const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
  //     let unitName = '';
  //     if (unitId !== null) {
  //       const unitObj = unitMap.get(unitId);
  //       unitName = unitObj ? unitObj.unit : '';
  //     }

  //     let includeCard = false;

  //     if (medicineDetails) {
  //       const searchableText = [
  //         medicineDetails.name,
  //         medicineDetails.description,
  //         formatName,
  //         unitName,
  //         String(denomObj.denomination),
  //         String(medicineDetails.sideEffect)
  //       ].join(' ').toLowerCase();

  //       if (searchTerm === '' || searchableText.includes(searchTerm)) {
  //         includeCard = true;
  //       }
  //     }

  //     if (includeCard) {
  //       const cardHtml = `
  //                       <div class="col" data-denomination-id="${denominationId}">
  //                           <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
  //                               <div class="card-body" style="max-height: 180px; overflow-y: auto;">
  //                                   <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${denominationId}">

  //                                   <input hidden class="medicineId" value="${medicineDetails.medicineId}">
  //                                   <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

  //                                   <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>

  //                                   <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
  //                                   <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
  //                                   <hr class="my-1">

  //                                   <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
  //                                   <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
  //                                   <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
  //                                   <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
  //                               </div>
  //                               <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
  //                                   <button class="btn btn-sm btn-outline-primary rounded-pill px-3 clinicCardEditBtn" data-bs-toggle="modal" data-bs-target="#clinicEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
  //                                   <button class="btn btn-sm btn-outline-danger rounded-pill px-3 clinicCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
  //                               </div>
  //                           </div>
  //                       </div>
  //                   `;
  //       filteredCardHTMLs.push(cardHtml);
  //     }
  //   }

  //   if (clinicCardParentDiv) {
  //     clinicCardParentDiv.innerHTML = filteredCardHTMLs.join('');
  //   }
  // }
  // });

  // const confirmingMedicineRemovalModal = new bootstrap.Modal(document.getElementById(
  //   "confirmingMedicineRemovalModal")
  // );
  // const confirmMedicineRemoveBtn = document.querySelector(
  //   "#confirmMedicineRemoveBtn"
  // );

  const confirmingClinicRemoveModal = new bootstrap.Modal(
    document.getElementById("confirmingClinicRemoveModal")
  );
  const setScheduleModal = new bootstrap.Modal(
    document.getElementById("setScheduleModal")
  );
  const setScheduleBtn = document.querySelector(".setScheduleBtn");

  clinicCardParentDiv.addEventListener("click", (e) => {
    if (e.target.classList.contains("clinicCardRemoveBtn")) {
      const cardDiv = e.target.closest(".col");
      const clinicId = cardDiv.querySelector(".clinicId").value;

      confirmingClinicRemoveModal.show();

      const confirmHandler = () => {
        fetch("removeClinic.do", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: "clinicId=" + encodeURIComponent(clinicId),
        })
          .then((res) => res.text())
          .then((msg) => console.log("Clinic Data Deleted", msg));

        cardDiv.remove();
        confirmingClinicRemoveModal.hide();
        confirmClinicRemoveBtn.removeEventListener("click", confirmHandler);
      };

      confirmClinicRemoveBtn.addEventListener("click", confirmHandler);
    }

    const setScheduleButton = e.target.closest(".clinicCardSetSchdBtn");
    if (setScheduleButton) {
      const cardDiv = setScheduleButton.closest(".col");
      const clinicId = cardDiv.querySelector(".clinicId").value;

      const setScheduleModal = new bootstrap.Modal(
        document.getElementById("setScheduleModal")
      ); // Ensure 'setScheduleModal' is initialized if you use Bootstrap's JS methods
      const form = document.getElementById("setScheduleForm");
      const setScheduleSubmitBtn = document.getElementById(
        "setScheduleSubmitBtn"
      );

      console.log(clinicId);

      const paramSubmitHandler = (e) => {
        e.preventDefault();

        const formData = new FormData(form);
        formData.append("clinicId", clinicId);
        const requestBody = new URLSearchParams(formData).toString();

        fetch("setSchedule.do", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: requestBody,
        })
          .then((res) => res.text())
          .then((msg) => {
            setTimeout(() => {
              if (msg.trim() === "Your Have updated a clinic schedule!") {
                alert("Your Schedule is successfully Updated!");
                this.window.location.reload();
              } else {
                alert("Unable to update your schedule");
              }
            }, 150);

            console.log("Server response:", msg);
          })
          .catch((error) => console.error("Error setting schedule:", error))
          .finally(() => {
            setScheduleSubmitBtn.removeEventListener(
              "click",
              paramSubmitHandler
            );
          });
      };

      setScheduleSubmitBtn.addEventListener("click", paramSubmitHandler);
      setScheduleModal.show();
    }
    const clinicCardViewSchdBtn = e.target.closest(".clinicCardViewSchdBtn");
    if (clinicCardViewSchdBtn) {
      const cardDiv = clinicCardViewSchdBtn.closest(".col")
      const clinicId = cardDiv.querySelector(".clinicId").value
      
      
      fetch("show_schedule.do", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "clinicId=" + encodeURIComponent(clinicId)
      })
        .then((res) => res.json())
        .then((data) => {
          console.log(data)
          const scheduleTBodyParent = document.getElementsByClassName("scheduleTBodyParent")
          mainScheduleShowTable.classList.remove("noDisplay")
          
          let cardHTMLs = []
          let i = 0;
          for (obj of data) {
            i++
            let sNo = i
            let startTime = obj.startTime
            let endTime = obj.endTime
            let patientLimit = obj.patientLimit

            let cardHTML = `
            <tr>
                        <td class="checkbox-cell"><input type="checkbox"></td>
                        <td class="serial-number">${i}</td>
                        <td>
                            <div class="time-cell">
                                <span class="time-icon">🕐</span>
                                <span>${startTime}</span>
                            </div>
                        </td>
                        <td>
                            <div class="time-cell">
                                <span class="time-icon">🕐</span>
                                <span>${endTime}</span>
                            </div>
                        </td>
                        <td><span class="patient-limit">${patientLimit}</span></td>
                    </tr>
                    <tr>
          `;

            cardHTMLs.push(cardHTML)
          }
          scheduleTBodyParent.innerHTML = cardHTMLs.join("")
        })
    }


  });//main body parent....
});
