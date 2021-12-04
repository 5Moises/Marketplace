function initMap() {
    var Coordenada = {lat: -16.40544464031007, lng: -71.5430877973082};
    var Mapa = new google.maps.Map(document.getElementById("MapaEmpresa"), {
        zoom: 10,
        center: Coordenada
    });
    var Marcador = new google.maps.Marker({
        map: Mapa,
        draggable: true,
        position: Coordenada
    });
    google.maps.event.addListener(Marcador, "dragend", function (event) {
        document.getElementById("Latitud").value = this.getPosition().lat();
        document.getElementById("Longitud").value = this.getPosition().lng();
    });
}

