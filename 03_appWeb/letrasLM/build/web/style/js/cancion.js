var cancion = [];
var album = [];
var albumcanc = new Object();
albumcanc.cancion = [];
albumcanc.album = [];
function mostrarformularioCancion() {

    $("#divContenido").html("");
    $.ajax
            ({
                url: "formularioCancion.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);
            });
}

function buscarCancionAlbum()
{
    var nombre = $('#txtBAlbumcanc').val();
    var data = {palabra: nombre};
    if (nombre != "" && nombre != null) {
        $.ajax(
                {
                    type: "GET",
                    url: "rest/cancion/search",
                    async: true,
                    data: data
                }).done(
                function (data) {
                    album = data;
                    var str = '';
                    if (data.length < 1)
                    {
                        str = "";
                        str += "<tr>";
                        str += "<td colspan='10' class='text-center'>";
                        str += "<span class='text-danger font-weight-bold'>Por el momento, no tienes Cantantes registrados con ese nombre.<span>";
                        str += "</td>";
                        str += "<tr>";
                        $("#tbodyAlbumBuscanc").html(str);
                        return;
                    } else {
                        album = data;
                        str = "";
                        for (var i = 0; i < album.length; i++) {
                            var nombre = album[i].nombre;
                            str += "<tr>";
                            str += "<td>" + (i + 1) + "</td>";
                            str += "<td>" + nombre + "</td>";
                            str += "<td>" + album[i].fechaPublicacion + "</td>";
                            str += "<td><img id='imgFoto' width='50' height='40' alt='' " +
                                    "src='data:image/png;base64," + album[i].foto + "' /></td>";
                            str += "<td> <button onclick='seleccionarAlbum(" + i + ")' class='btn btn-success mx-4''><i class='fa fa-plus-circle'></i></button></td>";
                            str += "</tr>";
                        }

                    }
                    $("#tbodyAlbumBuscanc").html(str);
                });
    }
}

function seleccionarAlbum(i) {

    //se asigna el objeto empleado seleccionado a la estructura de venta 
    albumcanc.album = album[i];

    //se obtiene el valor del empleado y se asigna al elemento visual
    var nombre = albumcanc.album.nombre;

    $("#txtElegidoAlbumNuevo").val(nombre);

}


function añadirCancion() {
    Swal.fire({
        title: 'Esta seguro?',
        text: "Quieres guardar esta Cancion mi rey?!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'si, guardar!'
    }).then((result) => {
        if (result.isConfirmed) {
            var nombre = $("#txtNombreCancionNueva").val();
            var letra = $("#txtletra").val();
            var duracion = $("#txtDuracionnu").val();
            var linki = $("#txtDLinkNuevo").val();
            var f = new Date();
            var fa = f.getFullYear() + f.getMonth() + f.getDay() + f.getHours() + f.getMinutes() + "" + f.getSeconds() + f.getMilliseconds();
            var ca = parseInt(fa);
            var idCancion = ca;

            cancion = [
                {
                    idCancion: idCancion,
                    nombre: nombre,
                    letra: letra,
                    duracion: duracion,
                    link: linki
                }];

            albumcanc.cancion = cancion[0];

            var data = {datoCancion: JSON.stringify(albumcanc)};
         

            $.ajax
                    ({
                        type: "POST",
                        url: "rest/cancion/save",
                        async: true,
                        data: data

                    }).done(function (data)
            {

            });
            mostrarformularioCancion();
            if (result.isConfirmed) {
                Swal.fire(
                        'Guardado!',
                        'Este Cancion se guardo correctamente!',
                        'success'
                        )
            }
        }
    })


}
function mostrarCancionesLista(canciones)
{
    $("#divCancionesemb").html("");
    var contenido = "";
    contenido += "<table>";
    contenido += "<tr>";
    contenido += "<td>Cancion </td>";
    contenido += "<td>        Duracion</td>";
    contenido += "<td>Link</td>";
    contenido += "</tr>";
    for (var i = 0; i < cancion.length; i++)
    {
        contenido += "<tr>";
        contenido += "<td>" + cancion[i].nombre + "</td>";
        contenido += "<td><input type='text' disabled id='txduraciondemo" + i + "' value='" + cancion[i].duracion + "' ></td>";
        contenido += "<td>" + cancion[i].link + "</td>";
        contenido += "</tr>";
    }
    contenido += "</table>";
    $("#divCancionesemb").html(contenido);

}


function guardarCancion() {

    Swal.fire({
        title: 'Esta seguro?',
        text: "Quieres guardar esta Cancion mi rey?!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'si, guardar!'
    }).then((result) => {
        if (result.isConfirmed) {
            albumcanc.cancion = cancion;


            var data = {canciones: JSON.stringify(albumcanc)};


            $.ajax
                    ({
                        type: "POST",
                        url: "rest/cancion/save",
                        async: true,
                        data: data

                    }).done(function (data)
            {

            });
            mostrarformularioCancion();
            if (result.isConfirmed) {
                Swal.fire(
                        'Guardado!',
                        'Este Cancion se guardo correctamente!',
                        'success'
                        )
            }
        }
    })
}