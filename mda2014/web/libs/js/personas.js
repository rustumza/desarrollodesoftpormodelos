$(function() {
    var personsApp = {};
    (function(app) {

        app.ViewModel = {
            alertMessaje: ko.observable(),
            alertTitle: ko.observable(),
            isAlertVisible: ko.observable(false),
            modalTitle: ko.observable(),
            personsGrid: ko.observableArray([]),
            selectedPerson: ko.observable(),
        };

        app.Init = function() {
            app.Bindings();
            app.FindPersons();
        };


        app.Bindings = function() {
            ko.applyBindings(this);
        };

        app.FindPersons = function() {
            var serviceUrl = "http://localhost:8084/mda2014/servicios/servicioPersona.jsp";
            $.ajax({
                    type: "GET",
                    url: serviceUrl,
                    dataType: "json",
                }).done(function(persona) {
                    var mappedPersons =
                        $.map(persona, function(persona) {
                            return {
                                id: ko.observable(persona.id),
                                nombre: ko.observable(persona.nombre),
                                apellido: ko.observable(persona.apellido),
                                documento: ko.observable(persona.documento),
                                domicilio: {
                                    calle: ko.observable(persona.domicilio.calle),
                                    nro: ko.observable(persona.domicilio.nro)
                                }
                            };
                        });
                    app.ViewModel.personsGrid(mappedPersons);
                })
                .fail(function() {
                    app.ShowAlert(app.AlertTypes.danger, 'No se han encontrado datos.', "Error");
                });
        };

        app.NewPerson = function() {

            app.ViewModel.modalTitle('Nueva Persona');
            app.ViewModel.selectedPerson({
                id: ko.observable(null),
                nombre: ko.observable(""),
                apellido: ko.observable(""),
                documento: ko.observable(""),
                domicilio: {
                    calle: ko.observable(""),
                    nro: ko.observable(""),
                }
            });

            $('#personModal').modal('show');
        };

        app.EditPerson = function(persona) {

            app.ViewModel.modalTitle('Editar Persona');
            app.ViewModel.selectedPerson(persona);

            $('#personModal').modal('show');
        };

        app.ShowDeletePersonMessage = function(persona) {
            app.ViewModel.selectedPerson(persona);
            $('#DeletPersonModal').modal('show');
        };

        app.DeletePerson = function() {
            //Falta implementar el llamado al servicio para eliminar
            
            app.ViewModel.personsGrid.remove(app.ViewModel.selectedPerson());
             $('#DeletPersonModal').modal('hide');

        };

        app.SavePerson = function(persona) {
            var serviceUrl = "http://localhost:8084/mda2014/servicios/servicioGuardarPersona.jsp";

            var personJson = ko.toJS(persona);

            $.ajax({
                    type: "Post",
                    url: serviceUrl,
                    dataType: "json",
                }).done(function(id) {
                    //person.id(id); //El servicio podría retornar el id de la nueva persona
                    if (!persona.id()) {
                        persona.id(id);
                        app.ViewModel.personsGrid.push(persona);
                    }
                    $('#personModal').modal('hide');
                    app.ShowAlert(app.AlertTypes.success, 'Datos Guardados Correctamente.');
                })
                .fail(function() {
                    app.ShowAlert(app.AlertTypes.danger, 'Los datos ingresados no han sido guardados.', "Error");
                });
        };

        app.AlertTypes = {
            warning: 'alert-warning',
            success: 'alert-success',
            danger: 'alert-danger',
            info: 'alert-info'
        }

        app.ShowAlert = function(alertType, message, title) {
            $('#alert').removeClass();
            $('#alert').addClass('alert ' + alertType);
            app.ViewModel.alertMessaje(message);
            app.ViewModel.alertTitle(title);
            app.ViewModel.isAlertVisible(true);
        }

        app.CloseAlert = function() {
            app.ViewModel.isAlertVisible(false);
        }

        app.Init();

    })(personsApp);
});