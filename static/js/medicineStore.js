const buttons = document.getElementsByTagName("button");
const medicineCardDiv = document.getElementById("medicineCardDiv");
const mainStoreDiv = document.getElementById("mainStoreDiv");
const medicineCardParentDiv = document.getElementById("medicineCardParentDiv");
const medicineCardRemoveBtn = document.getElementById("medicineCardRemoveBtn");
const uploadEditImageBtn = document.getElementById("uploadEditImageBtn");
const medicineImageEditForm = "medicineImageEditForm";

// window.addEventListener("DOMContentLoaded", function () {
//     const medicineCardParentDiv = document.getElementById("medicineCardParentDiv");

//     fetch("showMedicines.do", { method: "POST" })
//         .then((res) => res.json())
//         .then((arr) => {
//             const medicines = arr.medicines;
//             const formats = arr.formats;
//             const denominations = arr.denominations;
//             const units = arr.units;

//             console.log("Medicines:", medicines);
//             console.log("Denominations:", denominations);
//             console.log("Formats:", formats);
//             console.log("Units:", units);

//             const medicineMap = new Map();
//             for (const med of medicines) {
//                 medicineMap.set(med.medicineId, med);
//             }

//             const unitMap = new Map();
//             if (units) {
//                 for (const unit of units) {
//                     unitMap.set(unit.medicineUnitId, unit);
//                 }
//             }

//             let cardHTMLs = [];

//             for (let denomObj of denominations) {
//                 const denominationId = denomObj.medicineDenominationId;
//                 const imageFileName = denominationImageMap[denominationId];

//                 let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'dummyMedicine.jpg';

//                 const fullMedicineFormat = denomObj.medicineFormat;

//                 let medicineDetails = null;
//                 if (fullMedicineFormat && fullMedicineFormat.medicine) {
//                     medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
//                 }

//                 const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : 'N/A';

//                 const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
//                 let unitName = 'N/A';

//                 if (unitId !== null) {
//                     const unitObj = unitMap.get(unitId);
//                     unitName = unitObj ? unitObj.unit : 'N/A (Map Miss)';

//                 }
//                 if (medicineDetails && fullMedicineFormat) {

//                     const cardHtml = `
//                         <div class="col">
//                             <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
//                                 <div class="card-body" style="max-height: 180px; overflow-y: auto;">
//                                     <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="dummyMedicineImage">
                                    
//                                     <input hidden class="medicineId" value="${medicineDetails.medicineId}">
//                                     <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

//                                     <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>
                                    
//                                     <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
//                                     <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
//                                     <hr class="my-1">

//                                     <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
//                                     <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
//                                     <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
//                                     <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
//                                 </div>
//                                 <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
//                                     <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn" data-bs-toggle="modal" data-bs-target="#medicineEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
//                                     <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn medicineImageEditBtn" data-bs-toggle="modal" data-bs-target="#medicineImageEditForm"><i class="bi bi-image me-1"></i>Edit Image</button>
//                                     <button class="btn btn-sm btn-outline-danger rounded-pill px-3 medicineCardRemoveBtn" id="medicineCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
//                                 </div>
//                             </div>
//                         </div>
//                     `;
//                     cardHTMLs.push(cardHtml);
//                 }

//                 if (medicineCardParentDiv) {
//                     medicineCardParentDiv.innerHTML = cardHTMLs.join("");
//                 }

//                 if (medicineCardParentDiv) {
//                     medicineCardParentDiv.innerHTML = cardHTMLs.join("");
//                 }

//                 const searchInput = document.getElementById("searchStoreMedicineInput");

//                 if (searchInput) {
//                     searchInput.addEventListener("input", applySearchFilter);
//                 }

//                 function applySearchFilter() {
//                     const searchTerm = searchInput.value.toLowerCase().trim();

//                     let filteredCardHTMLs = [];

//                     for (let denomObj of denominations) {
//                         let path = denomObj.image != null ? 'showPic.do?pic_path=' + denomObj.image : 'dummyMedicine.jpg';
//                         const fullMedicineFormat = denomObj.medicineFormat;

//                         let medicineDetails = null;
//                         if (fullMedicineFormat && fullMedicineFormat.medicine) {
//                             medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
//                         }

//                         const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : '';
//                         const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
//                         let unitName = '';
//                         if (unitId !== null) {
//                             const unitObj = unitMap.get(unitId);
//                             unitName = unitObj ? unitObj.unit : '';
//                         }

//                         let includeCard = false;

//                         if (medicineDetails) {

//                             const searchableText = [
//                                 medicineDetails.name,
//                                 medicineDetails.description,
//                                 formatName,
//                                 unitName,
//                                 String(denomObj.denomination),
//                                 String(medicineDetails.sideEffect)
//                             ].join(' ').toLowerCase();

//                             if (searchTerm === '' || searchableText.includes(searchTerm)) {
//                                 includeCard = true;
//                             }
//                         }

//                         if (includeCard) {
//                             const cardHtml = `
//                 <div class="col">
//                     <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
//                         <div class="card-body" style="max-height: 180px; overflow-y: auto;">
//                             <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="dummyMedicineImage">
                            
//                             <input hidden class="medicineId" value="${medicineDetails.medicineId}">
//                             <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

//                             <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>
                            
//                             <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
//                             <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
//                             <hr class="my-1">

//                             <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
//                             <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
//                             <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
//                             <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
//                         </div>
//                         <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
//                             <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn" data-bs-toggle="modal" data-bs-target="#medicineEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
//                             <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardImageEditBtn v" id="medicineImageEditBtn" data-bs-toggle="modal" data-bs-target="#medicineImageEditForm"><i class="bi bi-image me-1"></i>Edit Image</button>
//                             <button class="btn btn-sm btn-outline-danger rounded-pill px-3 medicineCardRemoveBtn" id="medicineCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
//                         </div>
//                     </div>
//                 </div>
//             `;
//                             filteredCardHTMLs.push(cardHtml);
//                         }
//                     }

//                     if (medicineCardParentDiv) {
//                         medicineCardParentDiv.innerHTML = filteredCardHTMLs.join('');
//                     }
//                 }
//             }
//         })

//     const confirmingMedicineRemovalModal = new bootstrap.Modal(document.getElementById(
//         "confirmingMedicineRemovalModal")
//     );
//     const rejectMedicineRemoveBtn = document.querySelector(
//         "#rejectMedicineRemoveBtn"
//     );
//     const confirmMedicineRemoveBtn = document.querySelector(
//         "#confirmMedicineRemoveBtn"
//     );
//     medicineCardParentDiv.addEventListener("click", (e) => {

//         // ---------Handling Remove Logic--------------------
//         if (e.target.classList.contains("medicineCardRemoveBtn")) {
//             const denominationId = e.target
//                 .closest(".col")
//                 .querySelector(".denominationId").value;
//             console.log("DenomID " + denominationId)
//             const cardDiv = e.target.closest(".col");
//             confirmingMedicineRemovalModal.show();
//             confirmMedicineRemoveBtn.addEventListener("click", () => {

//                 fetch("deleteMedicine.do", {
//                     method: "POST",
//                     headers: { "Content-Type": "application/x-www-form-urlencoded" },
//                     body: "denominationId=" + encodeURIComponent(denominationId)
//                 })
//                     .then((res) => res.text())
//                     .then((msg) => console.log("Medicine Denomination Deleted", msg))
//                 cardDiv.remove();
//                 confirmingMedicineRemovalModal.hide()
//             })

//         }

//         // ---------Handling Image Logic--------------------
//         // ---------Handling Image Logic--------------------
//         // Assuming your overall code structure is a big event listener (e.g., on 'DOMContentLoaded' or similar)

//         // Define variables that are needed inside the click listener's scope
//         const uploadEditImageBtn = document.getElementById("uploadEditImageBtn"); // Get the button by ID
//         const pic = document.getElementById("pic"); // Get the file input by ID
//         const medicineImageEditForm = document.getElementById("form"); // Assuming the <form id="form"> is the one to reset

//         // Variable to store the denominationId clicked (needs to be available for the upload logic)
//         let currentDenominationId = null;

//         document.addEventListener('click', (e) => {
//             if (e.target.classList.contains("medicineImageEditBtn")) {
//                 // 1. Store the denomination ID when the edit button is clicked
//                 currentDenominationId = e.target
//                     .closest(".col")
//                     .querySelector(".denominationId").value;
//                 console.log("DenomID " + currentDenominationId);

//                 // You might want to show the modal here, but the code provided doesn't show that logic
//                 // Example: new bootstrap.Modal(document.getElementById('medicineImageEditForm')).show();
//             }
//         });


//         // 2. Add the event listener to the actual upload button
//         uploadEditImageBtn.addEventListener("click", (event) => {
//             // PREVENT DEFAULT FORM SUBMISSION (This is the fix for the page refresh!)
//             event.preventDefault();

//             // Check if a file is selected and a denomination ID is set
//             if (!pic.files.length || !currentDenominationId) {
//                 console.error("No file selected or Denomination ID missing.");
//                 return;
//             }

//             const denominationImage = document.querySelector(".denominationImage"); // Select this dynamically

//             const uploadPic = async () => {
//                 let formData = new FormData()
//                 formData.append('pic', pic.files[0])
//                 formData.append('denominationId', currentDenominationId)

//                 // Make the fetch request
//                 let response = await fetch('uploadPic.do', { method: 'post', body: formData })

//                 // We need the response text to check the message
//                 let result = await response.text()
//                 return result;
//             }

//             uploadPic().then((data) => {
//                 console.log("Server Response:", data);

//                 // Your Java Servlet returns "Pic Upload Ho Gaya!!" or "Pic Upload Nahi Hua!!"
//                 if (data.indexOf("Nahi") == -1) {
//                     // SUCCESS

//                     // 3. Fix: Get the filename from the input and use it as the source path
//                     const uploadedFileName = pic.files[0].name;

//                     // IMPORTANT: Set the image source to the *publicly accessible* path.
//                     // Assuming your server is set up to serve images from a public 'uploads' folder,
//                     // the path should look something like: /uploads/filename.jpg
//                     // Since your servlet is saving it to /WEB-INF/uploads, which is secure, 
//                     // you must adjust the path to a public one or change your servlet logic 
//                     // to save it to a public folder like /uploads.
//                     // For now, I'll assume you save to a public `/uploads/` folder and use the filename.

//                     // To be accurate based on the server code: The server saves the original filename (`pic`). 
//                     // Let's assume the images are publicly accessible at `/uploads/`.

//                     // This line needs to be updated based on where the image is actually served from
//                     // For a quick fix, if you serve from a public `/uploads` folder:
//                     denominationImage.src = `/uploads/${uploadedFileName}`;

//                     // If the server returns the *full URL* of the saved image, use that instead:
//                     // denominationImage.src = data; // if data was the URL

//                     // 4. Reset the form (clears the file input) and hide the modal (if it was a modal form)
//                     if (document.getElementById('medicineImageEditForm')) {
//                         // Assuming you are using Bootstrap modal, this closes the modal
//                         const modal = bootstrap.Modal.getInstance(document.getElementById('medicineImageEditForm'));
//                         if (modal) modal.hide();
//                     }
//                     medicineImageEditForm.reset();
//                     console.log('Pic uploaded and image source updated.');
//                 } else {
//                     console.error("Image upload failed on server.");
//                 }
//             })
//                 .catch(error => {
//                     console.error("Fetch error:", error);
//                 });
//         })


//     });
// })


window.addEventListener("DOMContentLoaded", function () {
    const medicineCardParentDiv = document.getElementById("medicineCardParentDiv");
    const uploadEditImageBtn = document.getElementById("uploadEditImageBtn"); 
    const pic = document.getElementById("pic"); 
    const medicineImageEditForm = document.getElementById("medicineImageEditForm"); // Use the modal ID, assuming it is the form container/modal
    let currentDenominationId = null;
    let denominationImageMap = {};

    fetch("showMedicines.do", { method: "POST" })
        .then((res) => res.json())
        .then((data) => { 
            const medicines = data.medicines;
            const formats = data.formats;
            const denominations = data.denominations;
            const units = data.units;         
            denominationImageMap = data.denominationImages; 

            console.log("Medicines:", medicines);
            console.log("Denominations:", denominations);
            console.log("Formats:", formats);
            console.log("Units:", units);
            console.log("Image Map:", denominationImageMap);

            const medicineMap = new Map();
            for (const med of medicines) {
                medicineMap.set(med.medicineId, med);
            }

            const unitMap = new Map();
            for (const unit of units) {
                unitMap.set(unit.medicineUnitId, unit);
            }

            let cardHTMLs = [];

            for (let denomObj of denominations) {
                const denominationId = denomObj.medicineDenominationId;
                const imageFileName = denominationImageMap[denominationId]; 
                const fullMedicineFormat = denomObj.medicineFormat;
                const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : 'N/A';
                const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
                let unitName = 'N/A';

                let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'static/media/images/dummyMedicine.jpg';

                let medicineDetails = null;
                if (fullMedicineFormat && fullMedicineFormat.medicine) {
                    medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
                }
    
                if (unitId !== null) {
                    const unitObj = unitMap.get(unitId);
                    unitName = unitObj ? unitObj.unit : 'N/A (Map Miss)';
                }

                if (medicineDetails && fullMedicineFormat) {

                    const cardHtml = `
                        <div class="col" data-denomination-id="${denominationId}"> <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
                                <div class="card-body" style="max-height: 180px; overflow-y: auto;">
                                    <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${denominationId}">
                                    
                                    <input hidden class="medicineId" value="${medicineDetails.medicineId}">
                                    <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

                                    <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>
                                    
                                    <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
                                    <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
                                    <hr class="my-1">

                                    <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
                                    <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
                                    <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
                                    <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
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
            }
            
            medicineCardParentDiv.innerHTML = cardHTMLs.join("");

            const searchInput = document.getElementById("searchStoreMedicineInput");
            if (searchInput) {
                searchInput.addEventListener("input", applySearchFilter);
            }

            function applySearchFilter() {
                const searchTerm = searchInput.value.toLowerCase().trim();
                let filteredCardHTMLs = [];

                for (let denomObj of denominations) {
                    const denominationId = denomObj.medicineDenominationId;
                    const imageFileName = denominationImageMap[denominationId]; 
                    let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'dummyMedicine.jpg';
                    
                    const fullMedicineFormat = denomObj.medicineFormat;

                    let medicineDetails = null;
                    if (fullMedicineFormat && fullMedicineFormat.medicine) {
                        medicineDetails = medicineMap.get(fullMedicineFormat.medicine.medicineId);
                    }

                    const formatName = fullMedicineFormat && fullMedicineFormat.format ? fullMedicineFormat.format.name : '';
                    const unitId = denomObj.medicineUnit ? denomObj.medicineUnit.medicineUnitId : null;
                    let unitName = '';
                    if (unitId !== null) {
                        const unitObj = unitMap.get(unitId);
                        unitName = unitObj ? unitObj.unit : '';
                    }

                    let includeCard = false;

                    if (medicineDetails) {
                        const searchableText = [
                            medicineDetails.name,
                            medicineDetails.description,
                            formatName,
                            unitName,
                            String(denomObj.denomination),
                            String(medicineDetails.sideEffect)
                        ].join(' ').toLowerCase();

                        if (searchTerm === '' || searchableText.includes(searchTerm)) {
                            includeCard = true;
                        }
                    }

                    if (includeCard) {
                        const cardHtml = `
                            <div class="col" data-denomination-id="${denominationId}">
                                <div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">
                                    <div class="card-body" style="max-height: 180px; overflow-y: auto;">
                                        <img src="${path}" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${denominationId}">
                                        
                                        <input hidden class="medicineId" value="${medicineDetails.medicineId}">
                                        <input hidden class="denominationId" value="${denomObj.medicineDenominationId}">

                                        <h5 class="card-title fw-bold text-danger mb-2">${medicineDetails.name}</h5>
                                        
                                        <p class="text-muted mb-1"><strong>Denomination:</strong> ${denomObj.denomination} ${unitName}</p>
                                        <p class="text-muted mb-1"><strong>Format:</strong> ${formatName}</p>
                                        <hr class="my-1">

                                        <p class="text-muted mb-1"><strong>Side Effects:</strong> ${medicineDetails.sideEffect}</p>
                                        <p class="text-muted mb-1"><strong>Description:</strong> ${medicineDetails.description}</p>
                                        <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${medicineDetails.veg ? "Yes" : "No"}</p>
                                        <p class="text-muted mb-1"><strong>Generic Medicine:</strong></p>
                                    </div>
                                    <div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">
                                        <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn" data-bs-toggle="modal" data-bs-target="#medicineEditForm"><i class="bi bi-pencil-square me-1"></i>Edit Details</button>
                                        <button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineImageEditBtn" data-bs-toggle="modal" data-bs-target="#medicineImageEditForm"><i class="bi bi-image me-1"></i>Edit Image</button>
                                        <button class="btn btn-sm btn-outline-danger rounded-pill px-3 medicineCardRemoveBtn"><i class="bi bi-trash me-1"></i>Remove</button>
                                    </div>
                                </div>
                            </div>
                        `;
                        filteredCardHTMLs.push(cardHtml);
                    }
                }

                if (medicineCardParentDiv) {
                    medicineCardParentDiv.innerHTML = filteredCardHTMLs.join('');
                }
            }
        });

    const confirmingMedicineRemovalModal = new bootstrap.Modal(document.getElementById(
        "confirmingMedicineRemovalModal")
    );
    const confirmMedicineRemoveBtn = document.querySelector(
        "#confirmMedicineRemoveBtn"
    );
    
    medicineCardParentDiv.addEventListener("click", (e) => {
        if (e.target.classList.contains("medicineCardRemoveBtn")) {
            const cardDiv = e.target.closest(".col");
            const denominationId = cardDiv.querySelector(".denominationId").value;

            confirmingMedicineRemovalModal.show();

            const confirmHandler = () => {
                 fetch("deleteMedicine.do", {
                     method: "POST",
                     headers: { "Content-Type": "application/x-www-form-urlencoded" },
                     body: "denominationId=" + encodeURIComponent(denominationId)
                 })
                 .then((res) => res.text())
                 .then((msg) => console.log("Medicine Denomination Deleted", msg))
                 
                 cardDiv.remove();
                 confirmingMedicineRemovalModal.hide();
                 confirmMedicineRemoveBtn.removeEventListener('click', confirmHandler);
            };

            confirmMedicineRemoveBtn.addEventListener("click", confirmHandler);
        }
        
        // ---------Handling Image Edit Button Click --------------------
        if (e.target.classList.contains("medicineImageEditBtn")) {
            currentDenominationId = e.target.closest(".col").querySelector(".denominationId").value;
            console.log("DenomID for image upload:", currentDenominationId);

            // Hide/reset form logic might go here 
        }
    });


    // -------------- Handling Image Upload --------------------
    if (uploadEditImageBtn) {
        uploadEditImageBtn.addEventListener("click", (event) => {
            event.preventDefault();

            if (!pic.files.length || !currentDenominationId) {
                console.error("No file selected or Denomination ID missing.");
                return;
            }

            const uploadPic = async () => {
                let formData = new FormData()
                formData.append('pic', pic.files[0])
                formData.append('denominationId', currentDenominationId)

                let response = await fetch('uploadPic.do', { method: 'post', body: formData })
                let result = await response.text()
                return result;
            }

            uploadPic().then((data) => {
                console.log("Server Response:", data);

                if (data.indexOf("Nahi") === -1) {
                    const imageElement = document.getElementById(`image-${currentDenominationId}`);
                    
                    // The server saves the file and returns a success message.
                    // To show the new image, we must construct the URL using the filename stored in the map.
                    // Since the server side must be saving a UNIQUE FILENAME and updating the DB,
                    // we need to re-fetch the data or, more simply, **reload the image source**.
                    
                    // The easiest way to force a browser to reload an image is to append a timestamp.
                    if (imageElement) {
                        const newSource = `showPic.do?pic_path=${pic.files[0].name}&t=${new Date().getTime()}`;
                        imageElement.src = newSource;
                    }

                    // Reset the form and hide the modal
                    if (document.getElementById('medicineImageEditForm')) {
                        const modal = bootstrap.Modal.getInstance(document.getElementById('medicineImageEditForm'));
                        if (modal) modal.hide();
                    }
                    // Assuming 'pic' is the file input element:
                    if (pic.closest('form')) {
                        pic.closest('form').reset();
                    }
                    console.log('Pic uploaded and image source updated.');
                } else {
                    alert("Image upload failed on server.");
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
            });
        });
    }
});