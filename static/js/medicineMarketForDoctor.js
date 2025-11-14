const buttons = document.getElementsByTagName("button");
const medicineCardDiv = document.getElementById("medicineCardDiv");
const mainStoreDiv = document.getElementById("mainStoreDiv");
const medicineCardParentDiv = document.getElementById("medicineCardParentDiv");
const medicineCardRemoveBtn = document.getElementById("medicineCardRemoveBtn");
const uploadEditImageBtn = document.getElementById("uploadEditImageBtn");
const medicineImageEditForm = "medicineImageEditForm";

window.addEventListener("DOMContentLoaded", function () {
    const medicineCardParentDiv = document.getElementById("medicineCardParentDiv");
    const uploadEditImageBtn = document.getElementById("uploadEditImageBtn");
    const pic = document.getElementById("pic");
    const medicineImageEditForm = document.getElementById("medicineImageEditForm"); // Use the modal ID, assuming it is the form container/modal
    let currentDenominationId = null;
    let denominationImageMap = {};

    fetch("show_medicine_inventory_for_doctor.do", { method: "GET" })
        .then((res) => {

            if (!res.ok) {
                throw new Error(`HTTP error! Status: ${res.status}`);
            }
            return res.json();
        })
        .then((medicines) => {
            console.log(medicines)

            let cardHTMLs = []
            for (const obj of medicines) {
                const medicineId = obj.medicineId;
                const name = obj.name;
                const description = obj.description;
                const sideEffect = obj.sideEffect;
                const veg = obj.veg;
                const genericMedicine = obj.medicineComposition?.genericMedicine?.name || 'N/A';
                const medicineFormats = obj.medicineFormats;

                for (const formatObj of medicineFormats) {
                    const medicineFormatName = formatObj.format.name;
                    const medicineFormatId = formatObj.medicineFormatId;
                    const denominationObj = formatObj.medicineDenomination;

                    if (!denominationObj) continue;

                    const denominationId = denominationObj.medicineDenominationId;
                    const unitName = denominationObj.medicineUnit.unit;
                    const denominationValue = denominationObj.denomination;
                    // const imageFileName = denominationObj.medicineDenominationImage.image; 
                    // let path = imageFileName ? 'showPic.do?pic_path=' + imageFileName : 'static/media/images/dummyMedicine.jpg';

                    const cardHtml = `
            <div class="col" data-medicine-id="${medicineId}" data-denomination-id="${denominationId} border-card" "> 
                <div class="card h-70 shadow-sm border-0 rounded-4 medicineCard" >
                    <div class="card-body" style="max-height: 180px; overflow-y: auto;">
                        <img src="static/media/images/dummyMedicine.jpg" class="card-img-top p-3 denominationImage" alt="Medicine Image" style="border-radius: 20px;" id="image-${denominationId}">
                        
                        <input hidden class="medicineId" value="${medicineId}">
                        <input hidden class="denominationId" value="${denominationId}">

                        <h5 class="card-title fw-bold text-danger mb-2">${name}</h5>
                        
                        <p class="text-muted mb-1"><strong>Denomination:</strong> ${denominationValue} ${unitName}</p>
                        <p class="text-muted mb-1"><strong>Format:</strong> ${medicineFormatName}</p>
                        <hr class="my-1">

                        <p class="text-muted mb-1"><strong>Side Effects:</strong> ${sideEffect}</p>
                        <p class="text-muted mb-1"><strong>Description:</strong> ${description}</p>
                        <p class="text-muted mb-1"><strong>Vegetarian:</strong> ${veg ? "Yes" : "No"}</p>
                        <p class="text-muted mb-1"><strong>Generic Medicine:</strong> ${genericMedicine}</p>
                        <p class="text-muted mb-1"><strong>Manufacturer Name:</strong> Dummy Name </p>
                    </div>
                </div>
            </div>
        `;
                    cardHTMLs.push(cardHtml);
                }
                medicineCardParentDiv.innerHTML = cardHTMLs.join("");
            }
        })


    // const searchInput = document.getElementById("searchStoreMedicineInput");
    // if (searchInput) {
    //     searchInput.addEventListener("input", handleSearchInput);
    // }
    // const searchTerm = searchInput.value.toLowerCase().trim();

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
            console.log(denominationId)

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
                // this.window.location.reload()
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