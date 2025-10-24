const buttons = document.getElementsByTagName("button");
const medicineCardDiv = document.getElementById("medicineCardDiv");
const mainStoreDiv = document.getElementById("mainStoreDiv");
const clinicCardParentDiv = document.getElementById("clinicCardParentDiv");
const medicineCardRemoveBtn = document.getElementById("medicineCardRemoveBtn");

window.onload = function () {
  fetch("showClinics.do", { method: "POST" })
    .then((res) => res.json())
    .then((arr) => {
      const clinics = arr.clinics;
    //   const denomination = arr.denominations;

      console.log("Clinics:", clinics);
    //   console.log("Clinics:", denomination);

      for (let obj of clinics) {
        clinicCardParentDiv.innerHTML +=
          '<div class="col">' +
          '<div class="card h-100 shadow-sm border-0 rounded-4 medicineCard">' +
          '<img src="static/media/images/medicineBottle.png" class="card-img-top p-3" alt="Medicine Image" style="border-radius: 20px;">' +
          '<div class="card-body" style="max-height: 180px; overflow-y: auto;">' +
          '<input hidden class="medicineId" hidden value="' +
          obj.medicineId +
          '">' +
          '<h5 class="card-title fw-bold text-danger mb-2">' +
          obj.name +
          "</h5>" +
          '<p class="text-muted mb-1"><strong>Side Effects:</strong> ' +
          obj.sideEffect +
          "</p>" +
          '<p class="text-muted mb-1"><strong>Vegetarian:</strong> ' +
          (obj.veg ? "Yes" : "No") +
          "</p>" +
          '<p class="text-muted mb-1"><strong>Description:</strong> ' +
          obj.description +
          "</p>" +
          '<p class="text-muted mb-1"><strong>Generic Medicine:</strong> ' +
          obj.description +
          "</p>" +
          '<p class="text-muted mb-1"><strong>Format:</strong> ' +
          obj.description +
          "</p>" +
          // '<p class="text-muted mb-1"><strong>Denomination:</strong> ' +
          // denomination.find((d) => d.medicineId === obj.medicineId)?.denomination || "N/A"   +
          // "</p>" +
          "</div>" +
          '<div class="card-footer bg-transparent border-0 d-flex justify-content-between px-3 pb-3 mt-3">' +
          '<button class="btn btn-sm btn-outline-primary rounded-pill px-3 medicineCardEditBtn">Edit</button>' +
          '<button class="btn btn-sm btn-outline-danger rounded-pill px-3 medicineCardRemoveBtn" id="medicineCardRemoveBtn">Remove</button>' +
          "</div>" +
          "</div>" +
          "</div>";
      }
    })};