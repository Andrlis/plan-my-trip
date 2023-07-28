navigator.geolocation.getCurrentPosition(function (position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;

        inicialize_map(lat, lng)

    },

    function (positionError) {
        inicialize_map(52.519, 13.407)
    });


function inicialize_map(lat, lng){

    var cp_map = L.map('mapid').setView([lat, lng], 12);
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
            minZoom: 3,
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
                '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            id: 'mapbox.streets',
            accessToken: access_key
        }).addTo(cp_map);

    var objectIcon = L.icon({
    iconUrl: '/static/citypoints/img/map-pin.png',
    iconSize: [35, 35], // size of the icon
    //shadowSize:   [50, 64], // size of the shadow
    iconAnchor: [17, 35], // point of the icon which will correspond to marker's location
    //shadowAnchor: [4, 62],  // the same for the shadow
    popupAnchor: [0, -30] // point from which the popup should open relative to the iconAnchor
});

var markersLayer = L.markerClusterGroup();

cp_map.addLayer(markersLayer);

var controlSearch = new L.Control.Search({
    position: 'topright',
    layer: markersLayer,
    initial: false,
    zoom: 12,
    marker: false
});

cp_map.addControl(controlSearch);

function populateMap(data) {
    for (i in data) {
        var name = data[i].name,	//value searched
            coordinates = data[i].coordinates,		//position found
            description = data[i].description,
            url = data[i].url,
            marker = new L.Marker(coordinates, {title: name, icon: objectIcon});//se property searched
        marker.bindPopup('<p><a href="' + url + '">' + name + '</a><p style="text-align: justify">' + description + '</p></p>');
        markersLayer.addLayer(marker);
    }
}

// use context variable instead of making AJAX call
var objects = JSON.parse(map_objects);

populateMap(objects);
}