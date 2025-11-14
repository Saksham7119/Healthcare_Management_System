let medicineBox = document.querySelector('#medicine_box');
let userId = document.querySelector("#userId");
// console.log(userId.value);
let collectAllMedicineRequest = async ()=>{
    let request = await fetch("view_medicine.do?userId="+userId.value);
    let response = await request.json();
    return response;
};

let collectAllMedicine = ()=>{
    collectAllMedicineRequest().then((data)=>{
        let divHtml = '';
        let medHtml = '';
        for(let obj of data){
             medHtml = '';
            for(let next of obj.medicineFormats){
               
                // console.log(next.format.name,'-+++--')
                for(let deno of next.medicineDenominations){
                    // console.log(deno.medicineDenominationImage,'********');
                    
                     medHtml += `
                     
                     <div class="col-xxl-2 col-sm-10 col-md-5 rounded-2 m-1 bg-light p-1 me-3">
                                    <div class="row">
                                        <div class="col-4">
                                            <div class="row">
                                                <div class="col-10 text-primary">${next.format.name}</div>
                                                <div class="col-10 text-body-secondary">${deno.denomination} ${deno.medicineUnit.unit}</div>
                                            </div>
                                        </div>
                                        <div class="col-8 pt-1">
                                            <button type="button" class="btn btn-outline-primary btn-sm m-1"
                                                data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                    class="bi bi-card-image"></i>&nbsp; Image</button>
                                        </div>
                                    </div>
                                </div>`;
                    
                }
            }
            console.log(obj);
            // let div = document.createElement('div');
            divHtml += `<div class="row mb-4">
                        <div class="col-12 ms-2 me-3 shadow border rounded-2 ps-4">
                            <div class="row mt-2">
                                <div class="col-8"><span class="fs-4 text-primary"> ${obj.name}</span></div>
                                <div class="col-8 fs-6 text-body-secondary"><span
                                        class=" ">Generic:</span><span>${obj.medicineCompositions.genericMedicine.name}
                                        </span></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-xxl-4 col-lg-5 col-md-10 mb-2 fs-6">
                                    <div class="row">
                                        <div class="col-11 text-body-secondary">DESCRIPTION</div>
                                        <div class="col-11 fw-light text-body-emphasis">${obj.description}</div>
                                    </div>
                                </div>
                                <div class="col-xxl-4 col-lg-5 col-md-10 mb-2 fs-6">
                                    <div class="row">
                                        <div class="col-11 text-body-secondary"><i
                                                class="bi bi-exclamation-triangle text-danger"></i>&nbsp; SIDE EFFECT
                                        </div>
                                        <div class="col-11 fw-light text-danger ">${obj.sideEffect}</div>
                                    </div>
                                </div>
                                <div class="col-xxl-4 col-lg-5 col-md-10 mb-2 fs-6">
                                    <div class="row">
                                        <div class="col-11 text-body-secondary"><i class="bi bi-buildings"></i>&nbsp;
                                            MANUFACTURER</div>
                                        <div class="col-11 fw-light text-body-emphasis">${obj.manufacturer.name} </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row fs-6 mt-3">
                                <div class="col-8 text-body-secondary"><span>AVAILABLE VARIANT</span></div>
                            </div>
                            <div class="row fs-6 m-1 mb-3 d-flex justify-content-start">
                            ${medHtml}
                               
                            </div>
                        </div>
                    </div>
                   
                    `;
                    
                }
                medicineBox.innerHTML = divHtml;
    }).catch((err)=>{
        console.log(err);
    });
};

collectAllMedicine();

