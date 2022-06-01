
var cantante = [];
var album = [];
var albumca = new Object();
albumca.cantante = [];
albumca.album = [];

function buscarCantantesAlbum()
{
    var nombre = $('#txtBCantante').val();
    var data = {nombre: nombre};
    if (nombre != "" && nombre != null) {
        $.ajax(
                {
                    type: "POST",
                    url: "rest/cantante/search",
                    async: true,
                    data: data
                }).done(
                function (data) {
                    cantante = data;
                    var str = '';



                    if (data.length < 1)
                    {
                        str = "";
                        str += "<tr>";
                        str += "<td colspan='10' class='text-center'>";
                        str += "<span class='text-danger font-weight-bold'>Por el momento, no tienes Cantantes registrados con ese nombre.<span>";
                        str += "</td>";
                        str += "<tr>";
                        $("#tbodyCantanteBus").html(str);
                        return;
                    } else {
                        cantantes = data;
                        str = "";
                        for (var i = 0; i < cantante.length; i++) {
                            var nombre = cantante[i].nombre + " " + cantante[i].apellidoPaterno + " " + cantante[i].apellidoMaterno;
                            str += "<tr>";
                            str += "<td>" + (i + 1) + "</td>";
                            str += "<td>" + nombre + "</td>";
                            str += "<td>" + cantante[i].fechaNacimiento + "</td>";
                            str += "<td><img id='imgFoto' width='50' height='40' alt='' " +
                                    "src='data:image/png;base64," + cantante[i].foto + "' /></td>";
                            str += "<td> <button onclick='seleccionarCantante(" + i + ")' class='btn btn-success mx-4''><i class='fa fa-plus-circle'></i></button></td>";
                            str += "</tr>";

                        }

                    }
                    $("#tbodyCantanteBus").html(str);
                });
    }
}

function seleccionarCantante(i) {

    //se asigna el objeto empleado seleccionado a la estructura de venta 
    albumca.cantante = cantante[i];

    //se obtiene el valor del empleado y se asigna al elemento visual
    var nombre = albumca.cantante.nombre + " " +
            albumca.cantante.apellidoPaterno + " " +
            albumca.cantante.apellidoMaterno;
    $("#txtCantanteElegidoAlbumNuevo").val(nombre);

}

function guardarAlbum() {
    Swal.fire({
        title: 'Esta seguro?',
        text: "Quieres guardar este Album mi rey?!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'si, guardar!'
    }).then((result) => {
        if (result.isConfirmed) {
            var nombre = $("#txtNombreAlbumNuevo").val();
            var genero = $("#selectgeneroAlbumNuevo").val();
            var precio = $("#txtPrecioAlbumNuevo").val();
            var fechaPublicacion = $("#txtFecha_nacAlbumNuevo").val();
            var discografia = $("#txtDiscografiaAlbumNuevo").val();
            var foto = $("#txtBase64").val();
            var f = new Date();
            var fa = f.getFullYear() + f.getMonth() + f.getDay() + f.getHours() + f.getMinutes() + "" + f.getSeconds() + f.getMilliseconds();
            var ca = parseInt(fa);
            var idAlbum = ca;
            album = [
                {
                    idAlbum: idAlbum,
                    nombre: nombre,
                    genero: genero,
                    precio: precio,
                    fechaPublicacion: fechaPublicacion,
                    foto: foto,
                    discografica: discografia

                }];

            albumca.album = album[0];

            var data = {datoAlbum: JSON.stringify(albumca)};
          

            $.ajax
                    ({
                        type: "POST",
                        url: "rest/album/save",
                        async: true,
                        data: data

                    }).done(function (data)
            {

            });

            mostrarformularioAlbum();
            if (result.isConfirmed) {
                Swal.fire(
                        'Guardado!',
                        'Este Album se guardo correctamente!',
                        'success'
                 )
            }
        }
    })

}

function mostrarformularioAlbum() {

    $("#divContenido").html("");
    $.ajax
            ({
                url: "formularioAlbum.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);

            });
}

function mostrarAlbum() {
    $.ajax(
            {
                type: "GET",
                url: "rest/album/getAll",
                async: true
            }).done(
            function (data) {
                
                album = data;
                var str = '';
                for (var i = 0; i < album.length; i++) {
                    str +=
                            '<tr id="empleado' + i + '">' +
                            '<td id="idEmpleado' + i + '">' + album[i].idAlbum + '</td>' + //las variables deben ser iguales al modelo de persona y                           
                            '<td id="nombre' + i + '">' + album[i].nombre + '</td>' +
                            '<td id="aPaterno' + i + '">' + album[i].genero + '</td>' +
                            '<td id="aMaterno' + i + '">' + album[i].precio + '</td>' +
                            '<td id="fechaNac' + i + '">' + album[i].fechaPublicacion + '</td>' +
                            '<td id="correo' + i + '">' + album[i].discografica + '</td>' +
                            '<td id="telefono' + i + '">' + album[i].cantante.nombre + '</td>' +
                            '</td>' +
                            '</tr>';
                }
                $('#tbodyDtAlbum').html(str);
            });

}