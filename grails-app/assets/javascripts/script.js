function preventDefaults (e) {
    e.preventDefault()
    e.stopPropagation()
}

function handleDrop(e) {
    let dt = e.dataTransfer
    let files = dt.files
    uploadFile(files[0])
}

function uploadFile(file) {

    let form = document.getElementById('the-form');
    console.log(form)
    let formData = new FormData();
    console.log(file)
    formData.append('userImageFile', file);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            document.getElementById('theImage').src = document.getElementById('theImage').src + "?"+new Date().getTime();
        }
    };
    // Add any event handlers here...
    xhr.open('PUT', form.getAttribute('action'), true);
    xhr.send(formData);
}

document.addEventListener('DOMContentLoaded', function() {
    let dropArea = document.getElementById('drop-area');

    ;['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false)
    })

    ;['dragenter', 'dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, highlight, false)
    })

    ;['dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, unhighlight, false)
    });

    function highlight(e) {
        dropArea.classList.add('highlight')
    }

    function unhighlight(e) {
        dropArea.classList.remove('highlight')
    }

    dropArea.addEventListener('drop', handleDrop, false)
    // your code here

    }, false);

    var b = true;

    function messageShow(id) {
        if(b){
            document.getElementById(id).className = "aMessageNotHidden";
            b = false;
        }else{
            document.getElementById(id).className = "aMessage";
            b = true;
        }

    }