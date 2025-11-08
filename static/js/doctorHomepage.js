window.addEventListener("load" , ()=>{
    const modalElement = document.getElementById('configureDoctorModal');

    if(modalElement){
      const configureDoctorModalvar = new bootstrap.Modal(modalElement);
      configureDoctorModalvar.show()
      const configureDoctorModalCloseBtn = document.querySelector("#configureDoctorModalCloseBtn")
      configureDoctorModalCloseBtn.addEventListener("click" , ()=>{
        configureDoctorModalvar.hide()  
      })
    }
})



//################# HANDLING NOTIFICATIONS #################################

const notificationPanel = document.getElementById("notification-panel");
const deleteNotiBtn = document.getElementById("deleteNotiBtn");

window.addEventListener("DOMContentLoaded", function () {
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
            obj.notificationMessage + " at " + obj.createdAt +
            "</span>" +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            "</div>" 
          )
        }
      } else {
        notificationPanel.innerHTML.insertAdjacentHTML(
            "afterbegin" ,
            '<div class="alert alert-info d-flex justify-content-between align-items-center rounded-pill px-4" role="alert">' +
            "<span>There is no notifications to show...</span>" +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" id="deleteNotiBtn"></button>' +
            "</div>"
        )
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