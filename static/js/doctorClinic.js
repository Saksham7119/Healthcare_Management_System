const buttons = document.getElementsByTagName("button");
const medicineCardDiv = document.getElementById("medicineCardDiv");
const mainClinicDiv = document.getElementById("mainClinicDiv");
const clinicCardParentDiv = document.getElementById("clinicCardParentDiv");
const clinicCardRemoveBtn = document.getElementById("clinicCardRemoveBtn");



window.addEventListener("DOMContentLoaded", function () {
  const clinicCardParentDiv = document.getElementById("clinicCardParentDiv");
  const uploadEditImageBtn = document.getElementById("uploadEditImageBtn");
  const pic = document.getElementById("pic");
  const medicineImageEditForm = document.getElementById("medicineImageEditForm"); // Use the modal ID, assuming it is the form container/modal
  let currentDenominationId = null;
  let denominationImageMap = {};

  fetch("showClinics.do", { method: "POST" })
    .then((res) => res.json())
    .then((data) => {
      const clinics = data.clinics;
      const clinicDays = data.clinicDays;
      const clinicImages = data.clinicImages;

      console.log("CLINICS --", clinics)
      console.log("CLINICDAYS --", clinicDays)
      console.log("CLINICIMAGES --", clinicImages)
      let cardHTMLs = [];

      for (let obj of clinics ){
        const clinicId = obj.clinicId;
        const clinicName = obj.name;
        const address = obj.address;
        const contact = obj.contact;
        const aboutMe = obj.aboutMe;
        const firstVisitCharge = obj.firstVisitCharges;
        const nextVisitCharge = obj.nextVisitCharges;

                // const imageFileName = denominationImageMap[denominationId]; 
                // const fullMedicineFormat = denomObj.medicineFormat;
                // const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : 'N/A';
                // const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
                // let unitName = 'N/A';

                // let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'static/media/images/dummyMedicine.jpg';
                let path = 'static/media/images/dummyClinic.png';

                    const cardHtml = `
                        <div class="col" data-clinic-id="${clinicId}"> <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
                                <div class="card-body" style="max-height: 180px; overflow-y: auto;">
                                    <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${clinicId}">
                                    
                                    <input hidden class="clinicId" value="${clinicId}">

                                    <h5 class="card-title fw-bold text-danger mb-2">${clinicName}</h5>
                                    
                                    <p class="text-muted mb-1"><strong>Address:</strong> ${address}</p>
                                    <p class="text-muted mb-1"><strong>Contact:</strong> ${contact}</p>
                                    <hr class="my-1">

                                    <p class="text-muted mb-1"><strong>About:</strong> ${aboutMe}</p>
                                    <p class="text-muted mb-1"><strong>First Visit Charges:</strong> ${firstVisitCharge}</p>
                                    <p class="text-muted mb-1"><strong>Charges From Second Visit:</strong> ${nextVisitCharge}</p>
                                </div>
                                <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
                                    <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn" data-bs-toggle="modal" data-bs-target="#medicineEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
                                    <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineImageEditBtn" data-bs-toggle="modal" data-bs-target="#medicineImageEditForm"><i class="bi bi-image me-1"></i>Edit Image</button>
                                    <button class="btn btn-sm btn-outline-danger rounded-pill px-3 medicineCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
                                </div>
                            </div>
                        </div>
                    `;
                    cardHTMLs.push(cardHtml);
      }
      clinicCardParentDiv.innerHTML = cardHTMLs.join("");
            }
            
          )})

  //     const searchInput = document.getElementById("searchStoreMedicineInput");
  //     if (searchInput) {
  //       searchInput.addEventListener("input", applySearchFilter);
  //     }

  //     function applySearchFilter() {
  //       const searchTerm = searchInput.value.toLowerCase().trim();
  //       let filteredCardHTMLs = [];

  //       for (let denomObj of denominations) {
  //         const denominationId = denomObj.medicineDenominationId;
  //         const imageFileName = denominationImageMap[denominationId];
  //         let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'dummyMedicine.jpg';

  //         const fullMedicineFormat = denomObj.medicineFormat;

  //         let medicineDetails = null;
  //         if (fullMedicineFormat && fullMedicineFormat.medicine) {
  //           medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
  //         }

  //         const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : '';
  //         const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
  //         let unitName = '';
  //         if (unitId !== null) {
  //           const unitObj = unitMap.get(unitId);
  //           unitName = unitObj ? unitObj.unit : '';
  //         }

  //         let includeCard = false;

  //         if (medicineDetails) {
  //           const searchableText = [
  //             medicineDetails.name,
  //             medicineDetails.description,
  //             formatName,
  //             unitName,
  //             String(denomObj.denomination),
  //             String(medicineDetails.sideEffect)
  //           ].join(' ').toLowerCase();

  //           if (searchTerm === '' || searchableText.includes(searchTerm)) {
  //             includeCard = true;
  //           }
  //         }

  //         if (includeCard) {
  //           const cardHtml = `
  //                           <div class="col" data-denomination-id="${denominationId}">
  //                               <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
  //                                   <div class="card-body" style="max-height: 180px; overflow-y: auto;">
  //                                       <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${denominationId}">
                                        
  //                                       <input hidden class="medicineId" value="${medicineDetails.medicineId}">
  //                                       <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

  //                                       <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>
                                        
  //                                       <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
  //                                       <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
  //                                       <hr class="my-1">

  //                                       <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
  //                                       <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
  //                                       <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
  //                                       <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
  //                                   </div>
  //                                   <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
  //                                       <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn" data-bs-toggle="modal" data-bs-target="#clinicEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
  //                                       <button class="btn btn-sm btn-outline-danger rounded-pill px-3 clinicCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
  //                                   </div>
  //                               </div>
  //                           </div>
  //                       `;
  //           filteredCardHTMLs.push(cardHtml);
  //         }
  //       }

  //       if (clinicCardParentDiv) {
  //         clinicCardParentDiv.innerHTML = filteredCardHTMLs.join('');
  //       }
  //     }
  //   });

  // const confirmingMedicineRemovalModal = new bootstrap.Modal(document.getElementById(
  //   "confirmingMedicineRemovalModal")
  // );
  // const confirmMedicineRemoveBtn = document.querySelector(
  //   "#confirmMedicineRemoveBtn"
  // );

  // clinicCardParentDiv.addEventListener("click", (e) => {
  //   if (e.target.classList.contains("clinicCardRemoveBtn")) {
  //     const cardDiv = e.target.closest(".col");
  //     const denominationId = cardDiv.querySelector(".denominationId").value;

  //     confirmingMedicineRemovalModal.show();

  //     const confirmHandler = () => {
  //       fetch("deleteMedicine.do", {
  //         method: "POST",
  //         headers: { "Content-Type": "application/x-www-form-urlencoded" },
  //         body: "denominationId=" + encodeURIComponent(denominationId)
  //       })
  //         .then((res) => res.text())
  //         .then((msg) => console.log("Medicine Denomination Deleted", msg))

  //       cardDiv.remove();
  //       confirmingMedicineRemovalModal.hide();
  //       confirmMedicineRemoveBtn.removeEventListener('click', confirmHandler);
  //     };

  //     confirmMedicineRemoveBtn.addEventListener("click", confirmHandler);
  //   }

  //   // ---------Handling Image Edit Button Click --------------------
  //   if (e.target.classList.contains("medicineImageEditBtn")) {
  //     currentDenominationId = e.target.closest(".col").querySelector(".denominationId").value;
  //     console.log("DenomID for image upload:", currentDenominationId);

  //     // Hide/reset form logic might go here 
  //   }
  // });


  // // -------------- Handling Image Upload --------------------
  // if (uploadEditImageBtn) {
  //   uploadEditImageBtn.addEventListener("click", (event) => {
  //     event.preventDefault();

  //     if (!pic.files.length || !currentDenominationId) {
  //       console.error("No file selected or Denomination ID missing.");
  //       return;
  //     }

  //     const uploadPic = async () => {
  //       let formData = new FormData()
  //       formData.append('pic', pic.files[0])
  //       formData.append('denominationId', currentDenominationId)

  //       let response = await fetch('uploadPic.do', { method: 'post', body: formData })
  //       let result = await response.text()
  //       return result;
  //     }

  //     uploadPic().then((data) => {
  //       console.log("Server Response:", data);

  //       if (data.indexOf("Nahi") === -1) {
  //         const imageElement = document.getElementById(`image-${currentDenominationId}`);

  //         // The server saves the file and returns a success message.
  //         // To show the new image, we must construct the URL using the filename stored in the map.
  //         // Since the server side must be saving a UNIQUE FILENAME and updating the DB,
  //         // we need to re-fetch the data or, more simply, **reload the image source**.

  //         // The easiest way to force a browser to reload an image is to append a timestamp.
  //         if (imageElement) {
  //           const newSource = `showPic.do?pic_path=${pic.files[0].name}&t=${new Date().getTime()}`;
  //           imageElement.src = newSource;
  //         }

  //         // Reset the form and hide the modal
  //         if (document.getElementById('medicineImageEditForm')) {
  //           const modal = bootstrap.Modal.getInstance(document.getElementById('medicineImageEditForm'));
  //           if (modal) modal.hide();
  //         }
  //         // Assuming 'pic' is the file input element:
  //         if (pic.closest('form')) {
  //           pic.closest('form').reset();
  //         }
  //         console.log('Pic uploaded and image source updated.');
  //       } else {
  //         alert("Image upload failed on server.");
  //       }
  //     })
  //       .catch(error => {
  //         console.error("Fetch error:", error);
  //       });
//     });
//   }
// });

// window.addEventListener("DOMContentLoaded" , function(){
//   fetch("showClinics.do" , {method:"POST"})
// .then((res) => res.json())
// .then((data)=>{
//   const clinics = data.clinics;
//   const clinicDays = data.clinicDays;
//   const clinicImages = data.clinicImages;

//   console.log("CLINICS --" , clinics)
//   console.log("CLINICDAYS --" , clinicDays)
//   console.log("CLINICIMAGES --" , clinicImages)
// })
// })