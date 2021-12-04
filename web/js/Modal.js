function MostrarCuadroDialogo() {
    var CuadroDialogo = document.getElementById("CuadroDialogo");
    CuadroDialogo.style.top = "25%";
    var FondoCuadroDialogo = document.getElementById("FondoCuadroDialogo");
    FondoCuadroDialogo.style.display = "block";
}
function CerrarCuadroDialogo() {
    var FondoCuadroDialogo = document.getElementById("FondoCuadroDialogo");
    FondoCuadroDialogo.style.display = "none";
    var CuadroDialogo = document.getElementById("CuadroDialogo");
    CuadroDialogo.style.top = "-50%";
}

function MostrarCuadroMapa() {
    var FondoCuadroMapa = document.getElementById("FondoCuadroMapa");
    FondoCuadroMapa.style.display = "block";
    var CuadroMapa = document.getElementById("CuadroMapa");
    CuadroMapa.style.top = "10%";
}
function CerrarCuadroMapa() {
    var FondoCuadroMapa = document.getElementById("FondoCuadroMapa");
    FondoCuadroMapa.style.display = "none";
    var CuadroMapa = document.getElementById("CuadroMapa"); 
    CuadroMapa.style.top = "-550px";
}