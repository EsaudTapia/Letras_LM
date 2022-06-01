var cantantes = [];

function buscarCantantes() {

    var nombre = $('#txtsearchCantante').val();
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
                    cantantes = data;
                    var str = '';
                    var contenido = '<div class="row border border-dark mt-3 mx-2">' +
                            '<h3 id="idtitulo" class="py-2 bg-success border-bottom border-dark text-center text-light w-100">Resultado de la busqueda</h3>' +
                            '<div id="divcantantes" class="row py-2 mx-2">' +
                            '</div>' +
                            '</div>';

                    $("#divContenido").html("");

                    $("#divContenido").html(contenido);


                    if (data.length < 1)
                    {
                        //Para no dejar vacía la tabla se comoca una imagen y un mensaje de que o hay información 
                        str += "<tr>";
                        str += "<td colspan='10' class='text-center'>";
                        str += "<span class='text-danger font-weight-bold'>Por el momento, no tenemos un cantante con ese nombre.<span>";
                        str += "</td>";
                        str += "<tr>";
                        $("#divcantantes").html(str);
                        return;
                    } else if (data.length == 1) {
                        cantantes = data;
                        for (var i = 0; i < cantantes.length; i++) {
                            str +=
                                    '<div  class="card col-sm-3" id="Cantante' + i + '">' +
                                    '<div class="card border-dark">' +
                                    '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                    ' <h5 id="nomproductoMas" class="text-center" >' + cantantes[i].nombre + '</h5>' +
                                    '</div>' +
                                    '<div id="dtsmas" class="card-body text-center">' +
                                    "<img id='imgFoto' width='180' height='200' alt='' " +
                                    "src='data:image/png;base64," + cantantes[i].foto + "' />" +
                                    ' <div class="modal-footer"> ' +
                                    '<span id="contDinamicomasc">' + '</span>' +
                                    '</div>' +
                                    '<button class="btn btn-sm btn-primary" onclick="detalleCantante(' + cantantes[i].idCantante + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>';
                        }

                    } else if (data.length > 1) {
                        cantantes = data;
                        for (var i = 0; i < cantantes.length; i++) {
                            str +=
                                    '<div  class="card col-3 " id="Cantante' + i + '">' +
                                    '<div class="card border-dark">' +
                                    '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                    ' <h5 id="nomproductoCan" class="text-center" >' + cantantes[i].nombre + '</h5>' +
                                    '</div>' +
                                    '<div id="dtsCan" class="card-body text-center">' +
                                    "<img id='imgFoto' width='180' height='200' alt='' " +
                                    "src='data:image/png;base64," + cantantes[i].foto + "' />" +
                                    ' <div class="modal-footer"> ' +
                                    '<span id="contDinamicoCan">' + '</span>' +
                                    '</div>' +
                                    '<button class="btn btn-sm btn-primary" onclick="detalleCantante(' + cantantes[i].idCantante + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '<br>';
                        }

                    }
                    $("#divcantantes").html(str);
                });
    }



}

function mostrarCantante() {
    $.ajax(
            {
                type: "GET",
                url: "rest/cantante/getAll",
                async: true
            }).done(
            function (data) {
                cantante = data;
                var str = '';
                for (var i = 0; i < cantante.length; i++) {
                    str +=
                            '<tr id="empleado' + i + '">' +
                            '<td id="idEmpleado' + i + '">' + cantante[i].idCantante + '</td>' + //las variables deben ser iguales al modelo de persona y                           
                            '<td id="nombre' + i + '">' + cantante[i].nombre + '</td>' +
                            '<td id="aPaterno' + i + '">' + cantante[i].apellidoPaterno + '</td>' +
                            '<td id="aMaterno' + i + '">' + cantante[i].apellidoMaterno + '</td>' +
                            '<td id="fechaNac' + i + '">' + cantante[i].fechaNacimiento + '</td>' +
                            '<td id="correo' + i + '">' + cantante[i].nacionalidad + '</td>' +
                            '<td id="telefono' + i + '">' + cantante[i].sexo + '</td>' +
                            '<td id="estatus' + i + '">' + (cantante[i].estatus = 1 ? 'Activo' : 'inactivo') + '</td>' +
                            '</td>' +
                            '</tr>';
                }
                $('#tbodycantante').html(str);
            });

}
function mostrarformulario() {

    $("#divContenido").html("");
    $.ajax
            ({
                url: "formularioCantante.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);

            });
}

function cargarFotografia(objetoInput) {
    if (objetoInput.files && objetoInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e)
        {
            var fotoB64 = e.target.result;
            $("#espacioimg").attr('src', fotoB64);
            $("#txtBase64").val(fotoB64.substring(22, fotoB64.length));
            $("#descImg").hide();
        };

        reader.readAsDataURL(objetoInput.files[0]);
    }
}


function confirmarNuevoCliente() {
    Swal.fire({
        title: 'Esta seguro?',
        text: "Quieres guardar este Cantante mi rey?!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'si, guardar!'
    }).then((result) => {
        if (result.isConfirmed) {
    var f = new Date();
    var fa = f.getFullYear() + f.getMonth() + f.getDay() + f.getHours() + f.getMinutes() + "" + f.getSeconds() + f.getMilliseconds();
    var ca = parseInt(fa);
    var idCantante = ca;
    var nombre = $("#txtnombre").val();
    var apellidoPaterno = $("#txtappellido_pat").val();
    var apellidoMaterno = $("#txtappellido_mat").val();
    var fechaNacimiento = $("#txtFecha_nac").val();

    var sexo = $("#selectSexo").val();
    var nacionalidad = $("#txtNacionalidad").val();
    var foto = $("#txtBase64").val();

    var data = {idCantante: idCantante, nombre: nombre,
        apellidoPaterno: apellidoPaterno,
        apellidoMaterno: apellidoMaterno,
        fechaNacimiento: fechaNacimiento,
        sexo: sexo,
        nacionalidad: nacionalidad,
        foto: foto
    };
   
    $.ajax
            ({
                type: "POST",
                url: "rest/cantante/save",
                async: true,
                data: data

            }).done(function (data)
    {
       
    }
    );
   
   mostrarformulario();
     if (result.isConfirmed) {
                Swal.fire(
                        'Guardado!',
                        'Este Cantante se guardo correctamente!',
                        'success'
                        )
            }
        }
    })

}

function obtenerFecha(e)
{

    var fecha = moment(e.value);
    console.log("Fecha original:" + e.value);
    console.log("Fecha formateada es: " + fecha.format("DD/MM/YYYY"));
}


function detalleCantante(i) {
    var pos = i;
   
    $("#divContenido").html("");
    $.ajax
            ({
                url: "detalleCantante.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);
            });

    var id = pos;
    var data = {
        palabra: id};
    $.ajax(
            {
                type: "GET",
                url: "rest/cantante/searchdetalle",
                async: true,
                data: data
            }).done(
            function (data) {
                
                album = data;
                var str = '';
                if (data.length < 1)
                {
                    str = "";
                    str = '<h1>NO PUEDE SER</h1>'
                    $("#divdetalleCantante").html(str);
                    return;
                } else {
                    detalle = data;
                    str = "";
                    str = '<div id="divimgCantante" class="col-4">' +
                            "<img id='imgFoto' width='180' height='200' alt='' " +
                            "src='data:image/png;base64," + detalle[0].cantante.foto + "' />" +
                            '</div>' +
                            '<div id="divinfoCantante" class="col-6">' +
                            '<label class="text-dark" for="txtNombredtCa">' + "Nombre: " + detalle[0].cantante.nombre + " " + detalle[0].cantante.apellidoPaterno + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtFecNactCa">' + "Nacimiento: " + detalle[0].cantante.fechaNacimiento + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtSexodtCa">' + "Sexo: " + detalle[0].cantante.sexo + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtNacdtCa">' + "Nacionalidad: " + detalle[0].cantante.nacionalidad + '</label>' +
                            '</div>';
                    $("#divdetalleCantante").html(str);
                    var contenido = "";
                    for (var i = 0; i < detalle.length; i++) {

                        contenido += '<div  class="card col-12  mt-3" id="Cantante">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                '<h5 id="nomproductoMas" class="text-center" >' + detalle[i].nombre + ' </h5>' +
                                ' </div>' +
                                '<div id="dtsmas" class="card-body text-center">' +
                                '<div id="divimgCantante" class="col-12">' +
                                "<img id='imgFoto' width='180' height='200' alt='' " +
                                "src='data:image/png;base64," + detalle[i].foto + "' />" +
                                '</div>' +
                                ' <div class="modal-footer">' +
                                ' <span id="contDinamicomasc">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleAlbum(' + detalle[i].idAlbum + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                ' </div>' +
                                ' </div>' +
                                ' </div>';
                    }
                    $("#divConteAlbuCD").html(contenido);


                }
            });

}

function buscarporprecio() {
    var id = $("#txtPrecioDC").val();
    var data = {palabra: id};
    $.ajax(
            {
                type: "GET",
                url: "rest/cantante/searchdprecio",
                async: true,
                data: data
            }).done(
            function (data) {
                
                album = data;
                var str = '';
                if (data.length < 1)
                {
                    str = "";
                    str = '<h3>No hay tan baratos</h3>'
                    $("#divConteAlbuCD").html(str);
                    return;
                } else {
                    detalle = data;

                    $("#divConteAlbuCD").html("");

                    var contenido = "";
                    for (var i = 0; i < detalle.length; i++) {

                        contenido += '<div  class="card col-12  mt-3" id="Cantante">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                '<h5 id="nomproductoMas" class="text-center" >' + detalle[i].nombre + ' </h5>' +
                                ' </div>' +
                                '<div id="dtsmas" class="card-body text-center">' +
                                '<div id="divimgCantante" class="col-12">' +
                                "<img id='imgFoto' width='180' height='200' alt='' " +
                                "src='data:image/png;base64," + detalle[i].foto + "' />" +
                                '</div>' +
                                ' <div class="modal-footer">' +
                                ' <span id="contDinamicomasc">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleAlbum(' + detalle[i].idAlbum + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                ' </div>' +
                                ' </div>' +
                                ' </div>';

                    }
                    $("#divConteAlbuCD").html(contenido);


                }
            });

}


function detalleAlbum(i) {
    var pos = i;
    
    $("#divContenido").html("");
    $.ajax
            ({
                url: "detalleAlbum.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);
            });

    var id = pos;
    var data = {
        palabra: id};
    $.ajax(
            {
                type: "GET",
                url: "rest/album/searchdetalle",
                async: true,
                data: data
            }).done(
            function (data) {
              
                album = data;
                var str = '';
                if (data.length < 1)
                {
                    str = "";
                    str = '<h1>NO PUEDE SER</h1>'
                    $("#divdetalleCantante").html(str);
                    return;
                } else {
                    detalle = data;
                    str = "";

                    str = '<div id="divimgCantante" class="col-4">' +
                            "<img id='imgFoto' width='180' height='200' alt='' " +
                            "src='data:image/png;base64," + detalle[0].album.foto + "' />" +
                            '</div>' +
                            '<div id="divinfoCantante" class="col-6">' +
                            '<label class="text-dark" for="txtNombredtCa">' + "Nombre: " + detalle[0].album.nombre + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtSexodtCa">' + "Precio: $" + detalle[0].album.precio + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtFecNactCa">' + "Publicacion: " + detalle[0].album.fechaPublicacion + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtSexodtCa">' + "Genero: " + detalle[0].album.genero + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtNacdtCa">' + "Discografica: " + detalle[0].album.discografica + '</label>' +
                            '</div>';
                    $("#divdetalleCantante").html(str);


                    var contenido = "";
                    for (var i = 0; i < detalle.length; i++) {

                        contenido += '<div  class="card col-12  mt-3" id="Cantante">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                '<h5 id="nomproductoMas" class="text-center" >' + detalle[i].nombre + ' </h5>' +
                                ' </div>' +
                                '<div id="dtsmas" class="card-body text-center">' +
                                '<div id="divimgCantante" class="col-12">' +
                                "<img id='imgFoto' width='70' height='70' alt='' " +
                                "src='data:image/png;base64," + detalle[i].album.foto + "' />" +
                                '</div>' +
                                ' <div class="modal-footer">' +
                                ' <span id="contDinamicomasc">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleCancion(' + detalle[i].IdCancion + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                ' </div>' +
                                ' </div>' +
                                ' </div>';

                    }
                    $("#divConteAlbuCD").html(contenido);


                }
            });

}

function buscarporNombre() {
    var id = $("#txtbNombreDC").val();
    var data = {palabra: id};
    $.ajax(
            {
                type: "GET",
                url: "rest/album/searchdNombre",
                async: true,
                data: data
            }).done(
            function (data) {
               
                album = data;
                var str = '';
                if (data.length < 1)
                {
                    str = "";
                    str = '<h3>No existe</h3>'
                    $("#divConteAlbuCD").html(str);
                    return;
                } else {
                    detalle = data;

                    $("#divConteAlbuCD").html("");

                    var contenido = "";
                    for (var i = 0; i < detalle.length; i++) {

                        contenido += '<div  class="card col-12  mt-3" id="Cantante">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                '<h5 id="nomproductoMas" class="text-center" >' + detalle[i].nombre + ' </h5>' +
                                ' </div>' +
                                '<div id="dtsmas" class="card-body text-center">' +
                                '<div id="divimgCantante" class="col-12">' +
                                "<img id='imgFoto' width='70' height='70' alt='' " +
                                "src='data:image/png;base64," + detalle[i].foto + "' />" +
                                '</div>' +
                                ' <div class="modal-footer">' +
                                ' <span id="contDinamicomasc">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleCancion(' + detalle[i].IdCancion + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                ' </div>' +
                                ' </div>' +
                                ' </div>';

                    }
                    $("#divConteAlbuCD").html(contenido);


                }
            });
}

function detalleCancion(i) {
    var pos = i;
   
    $("#divContenido").html("");
    $.ajax
            ({
                url: "detalleCanciones.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenido").html(data);
            });

    var id = pos;
    var data = {
        palabra: id};

    $.ajax(
            {
                type: "GET",
                url: "rest/cancion/searchdetallecan",
                async: true,
                data: data
            }).done(
            function (data) {
               
                album = data;
                var str = '';
                if (data.length < 1)
                {
                    str = "";
                    str = '<h1>NO PUEDE SER</h1>'
                    $("#divdetalleCantante").html(str);
                    return;
                } else {
                    detalle = data;
                    str = "";

                    str = '<div id="divimgCantante" class="col-4">' +
                            "<img id='imgFoto' width='180' height='200' alt='' " +
                            "src='data:image/png;base64," + detalle[0].album.foto + "' />" +
                            '</div>' +
                            '<div id="divinfoCantante" class="col-6">' +
                            '<label class="text-dark" for="txtNombredtCa">' + "Nombre: " + detalle[0].nombre + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtSexodtCa">' + "Duracion: " + detalle[0].duracion + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtFecNactCa">' + "Publicacion: " + detalle[0].album.fechaPublicacion + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtSexodtCa">' + "Genero: " + detalle[0].album.genero + '</label>' + '<br>' + '<br>' +
                            '<label class="text-dark" for="txtNacdtCa">' + "Discografica: " + detalle[0].album.discografica + '</label>' +
                            '</div>' +
                            '<iframe width="460" height="315" src="' + detalle[0].link + '"' +
                            'frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; ' +
                            ' picture-in-picture" allowfullscreen>' + '</iframe>'
                            ;
                    $("#divdetalleCantante").html(str);




                    var contenido = "";
                    contenido = '<p>' + detalle[0].letra + '</p>';

                    $("#divletra").html(contenido);
                }
            });

}

function todoscantantes() {
    $.ajax(
            {
                type: "GET",
                url: "rest/cantante/getAll",
                async: true
            }).done(
            function (data) {
                cantantes = data;
                var str = '';
                var contenido = '<div class="row border border-dark mt-3 mx-2">' +
                        '<h3 id="idtitulo" class="py-2 bg-success border-bottom border-dark text-center text-light w-100">Todos los cantantes</h3>' +
                        '<div id="divcantantes" class="row py-2 mx-2">' +
                        '</div>' +
                        '</div>';

                $("#divContenido").html("");

                $("#divContenido").html(contenido);


                if (data.length < 1)
                {
                    //Para no dejar vacía la tabla se comoca una imagen y un mensaje de que o hay información 
                    str += "<tr>";
                    str += "<td colspan='10' class='text-center'>";
                    str += "<span class='text-danger font-weight-bold'>Por el momento, no tenemos un cantante con ese nombre.<span>";
                    str += "</td>";
                    str += "<tr>";
                    $("#divcantantes").html(str);
                    return;
                } else if (data.length == 1) {
                    cantantes = data;
                    for (var i = 0; i < cantantes.length; i++) {
                        str +=
                                '<div  class="card col-sm-3" id="Cantante' + i + '">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                ' <h5 id="nomproductoMas" class="text-center" >' + cantantes[i].nombre + '</h5>' +
                                '</div>' +
                                '<div id="dtsmas" class="card-body text-center">' +
                                "<img id='imgFoto' width='180' height='200' alt='' " +
                                "src='data:image/png;base64," + cantantes[i].foto + "' />" +
                                ' <div class="modal-footer"> ' +
                                '<span id="contDinamicomasc">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleCantante(' + cantantes[i].idCantante + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                    }

                } else if (data.length > 1) {
                    cantantes = data;
                    for (var i = 0; i < cantantes.length; i++) {
                        str +=
                                '<div  class="card col-3 mt-1" id="Cantante' + i + '">' +
                                '<div class="card border-dark">' +
                                '<div class="card-header text-white" style="background-color:#0c231e"> ' +
                                ' <h5 id="nomproductoCan" class="text-center" >' + cantantes[i].nombre + '</h5>' +
                                '</div>' +
                                '<div id="dtsCan" class="card-body text-center">' +
                                "<img id='imgFoto' width='180' height='200' alt='' " +
                                "src='data:image/png;base64," + cantantes[i].foto + "' />" +
                                ' <div class="modal-footer"> ' +
                                '<span id="contDinamicoCan">' + '</span>' +
                                '</div>' +
                                '<button class="btn btn-sm btn-primary" onclick="detalleCantante(' + cantantes[i].idCantante + ');" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-shopping-basket"></i> ver informacion</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '<br>';
                    }

                }
                $("#divcantantes").html(str);
            });
}



