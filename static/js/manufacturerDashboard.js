const configureManufacturerModal = document.querySelector(
  "#configureManufacturerModal"
);
window.addEventListener("load", function () {
  const myModal = new bootstrap.Modal(configureManufacturerModal);
  myModal.show();
});

const addMedicineForm = document.getElementById("addMedicineForm");

const addMedicineDenominationForm = document.getElementById(
  "addMedicineDenominationForm"
);
const addMedicineDenominationSubmitBtn = document.getElementById(
  "MedicineDenominationSubmitBtn"
);

const genericMedicineTypeSelect = document.getElementById(
  "genericMedicineType"
);
const generateInputForDenominationBtn = document.getElementById(
  "generateInputForDenomination"
);

const addMedicineSubmitBtn = document.getElementById("addMedicineSubmitBtn");
const addMedicineFormatBtn = document.getElementById(
  "addMedicineFormatSubmitBtn"
);
const addMedicineCloseBtn = document.getElementById("addMedicineCloseBtn");
const addMedicineDiv = document.querySelector(".addMedicineDiv");
const addDenominationDiv = document.querySelector(".addDenominationDiv");

addMedicineForm.addEventListener("submit", async (e) => {
  console.log("Medicine added successfully");
  addMedicineDiv.style.display = "none";
  addMedicineSubmitBtn.style.display = "none";
  addMedicineCloseBtn.style.display = "none";
  addDenominationDiv.style.display = "block";
  addMedicineFormatBtn.style.display = "block";
});

generateInputForDenominationBtn.addEventListener("click", (e) => {
  const noOfDenominationInput = document.getElementById("noOfDenomination");
  const denominationContainer = document.getElementById(
    "denominationContainer"
  );
  const getNoOfDenominationDiv = document.getElementById(
    "getNoOfDenominationDiv"
  );

  e.preventDefault();
  generateInputForDenominationBtn.innerText = "Change";

  noOfDenominationInputValue = noOfDenominationInput.value;
  console.log("---- " + noOfDenominationInputValue);

  denominationContainer.innerHTML = "";

  console.log("----LINE 1 Run hui");

  console.log("Fetching Medicine Units");
  fetch("getMedicineUnits", { method: "POST" })
    .then((res) => res.json())
    .then((units) => {
      console.log("Units fetched:", units);

      console.log("Fetching Medicine Formats");
      fetch("getMedicineFormat", { method: "POST" })
        .then((res) => res.json())
        .then((format) => {
          console.log("----LINE 2 Run hui");

          console.log("Format fetched:", format);
          console.log("Format fetched:", format);

          generateDenominationFields(format, units);
          console.log("Fields Generated");
        })
        .catch((err) => console.error("Error", err));
    })
    .catch((err) => console.error("Error", err));
});

//################# HANDLING NOTIFICATIONS #################################

const notificationPanel = document.getElementById("notification-panel");
const deleteNotiBtn = document.getElementById("deleteNotiBtn");

window.addEventListener("load", function () {
  fetch("showNotification.do", { method: "POST" })
    .then((res) => res.json())
    .then((arr) => {
      console.log(arr);
      if (arr.length != 0) {
        for (let obj of arr) {
          notificationPanel.insertAdjacentHTML("afterbegin" , 
            '<div class="alert alert-info d-flex justify-content-between align-items-center rounded-pill px-4" role="alert">' +
            '<input class="notificationId" id="notificationID" hidden value="' +
            obj.notificationID +
            '">' +
            "<span>" +
            obj.notificationMessage + " at " + new Date().toDateString() +
            "</span>" +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            "</div>" 
          )
        }
      } else {
        notificationPanel.insertAdjacentHTML("afterbegin" , 
          '<div class="alert alert-info d-flex justify-content-between align-items-center rounded-pill px-4" role="alert">' +
          "<span>There is no notifications to show...</span>" +
          '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" id="deleteNotiBtn"></button>' +
          "</div>")
          
      }
    });
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

// generateInputForDenominationBtn.addEventListener("click", async (e) => {
//   e.preventDefault();
//   const noOfDenominationInput = document.getElementById("noOfDenomination");
//   const denominationContainer = document.getElementById("denominationContainer");

//   generateInputForDenominationBtn.innerText = "Change";
//   noOfDenominationInputValue = noOfDenominationInput.value;
//   denominationContainer.innerHTML = "";

//   try {
//     const unitsRes = await fetch("getMedicineUnits", { method: "POST" });
//     const units = await unitsRes.json();

//     const formatRes = await fetch("getMedicineFormat", { method: "POST" });
//     const format = await formatRes.json();

//     generateDenominationFields(format, units);
//     console.log("✅ Fields generated");

//     // ✅ Unhide the submit button only after fields are ready
//     addMedicineFormatBtn.disabled = false;

//   } catch (err) {
//     console.error("Error fetching data", err);
//   }
// })

//-----------JS FUNCTIONS DECLARATION AND DEFINATIONS
function generateDenominationFields(format, units) {
  for (let i = 0; i < noOfDenominationInputValue; i++) {
    denominationContainer.appendChild(
      createFloatingSelect(
        "selectMedicineFormat" + (i + 1),
        "selectMedicineFormat",
        "Format for Denomination " + (i + 1),
        format.map((u) => ({ value: u.formatId, text: u.name }))
      )
    );

    denominationContainer.appendChild(
      createFloatingInput(
        "denominationValue" + (i + 1),
        "denominationValue",
        "Denomination " + (i + 1),
        "number"
      )
    );

    denominationContainer.appendChild(
      createFloatingSelect(
        "medicineUnitSelect" + (i + 1),
        "medicineUnitSelect",
        "Medicine Unit " + (i + 1),
        units.map((u) => ({ value: u.medicineUnitId, text: u.unit }))
      )
    );

    denominationContainer.appendChild(
      createFloatingInput(
        "medicineDenominationImg" + (i + 1),
        "medicineDenominationImg",
        "Image for Denomination " + (i + 1),
        "file"
      )
    );
  }
}

function createFloatingInput(id, name, placeholder, type = "text") {
  const div = document.createElement("div");
  div.classList.add("form-floating", "mb-3");

  const input = document.createElement("input");
  input.type = type;
  input.id = id;
  input.name = name;
  input.classList.add("form-control");
  input.placeholder = placeholder;

  const label = document.createElement("label");
  label.htmlFor = id;
  label.textContent = placeholder;

  div.appendChild(input);
  div.appendChild(label);
  return div;
}

function createFloatingSelect(id, name, labelText, options = []) {
  const div = document.createElement("div");
  div.classList.add("form-floating", "mb-3");

  const select = document.createElement("select");
  select.id = id;
  select.name = name;
  select.classList.add("form-select");

  options.forEach((opt) => {
    const option = document.createElement("option");
    option.value = opt.value;
    option.textContent = opt.text;
    select.appendChild(option);
  });

  const label = document.createElement("label");
  label.htmlFor = id;
  label.textContent = labelText;

  div.appendChild(select);
  div.appendChild(label);
  return div;
}

console.log("----LINE 3 Run hui");
