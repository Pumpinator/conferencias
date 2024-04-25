var stompClient = null;

var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    console.log(frame);
    stompClient.subscribe('/conferencia/asistentes', function (result) {
        console.log(result);
        let alumno = JSON.parse(result.body);
        alerta(alumno);
    });
});

socket = new SockJS('/websocket');

function alerta(alumno) {
    const alerta = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-dark",
            cancelButton: "btn btn-secondary"
        },
        buttonsStyling: false
    });
    alerta.fire({
        title: `Bienvenido(a) ${alumno.persona.nombres}`,
        text: `${alumno.persona.nombres} ${alumno.persona.apellidos} de la carrera de ${alumno.carrera.nombre}`
    });
}

const boxes = document.querySelectorAll('#box');

window.addEventListener('scroll', checkBoxes);


function checkBoxes() {
    const triggerBottom = window.innerHeight / 5 * 4;
    boxes.forEach(box => {
        const boxTop = box.getBoundingClientRect().top;

        if (boxTop < triggerBottom) {
            box.classList.add('show');

        } else {
            box.classList.remove('show');
        }
    })
}
