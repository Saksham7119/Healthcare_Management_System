const selectDays = document.getElementById("days")
const clinicCity = document.getElementById("clinicCity")

window.addEventListener("load", function () {
  fetch("getDays.do", { method: "POST" })
    .then((res) => res.json())
    .then((days) => {
      console.log(days);
      if (days.length != 0) {
        for (let obj of days) {
          // selectDays.innerHTML += '<option value="'+ obj.dayId +'">'+ obj.day +'</option>'

          selectDays.innerHTML += '<label class="checkbox-item">' +
                                        '<input type="checkbox" name="clinicDays" value="'+ obj.dayId+'">'+
                                        '<span>'+ obj.day +'</span>' +
                                    '</label>'
        }
      }
    });

  fetch("fetchCities.do", { method: "POST" })
    .then((res) => res.json())
    .then((cities) => {
      console.log(cities);
      if (cities.length != 0) {
        for (let obj of cities) {
          clinicCity.innerHTML += '<option value="'+ obj.cityId +'">'+ obj.name +'</option>'
        }
      }
    });
});