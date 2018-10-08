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

    var form = document.getElementById('the-form');
    console.log(form)
    var formData = new FormData();
    console.log(file)
    formData.append('userImageFile', file);
    var xhr = new XMLHttpRequest();
    // Add any event handlers here...
    xhr.open('PUT', form.getAttribute('action'), true);
    xhr.send(formData);
    return false; // To avoid actual submission of the form
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
    })

    function highlight(e) {
        dropArea.classList.add('highlight')
    }

    function unhighlight(e) {
        dropArea.classList.remove('highlight')
    }

    dropArea.addEventListener('drop', handleDrop, false)
    // your code here

    }, false);