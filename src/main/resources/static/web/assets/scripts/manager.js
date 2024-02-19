const { createApp } = Vue

createApp({
    data() {
        return {
            clientes: [],
            inputNombre: "",
            inputApellido: "",
            inputEmail: "",
            mensaje: "",
            name: "-",
            showAddClientss: true,
            showListClientss: false,

        };
    },

    created() {
        this.cliente1()
        this.cargarClientes()
    },
    methods: {
        cliente1() {
            axios
                .get("/api/clientes/current")
                .then((respuesta) => {
                    console.log(respuesta.data);
                    this.clienteCurrent = respuesta.data;
                    this.cuentasCurrent = this.clienteCurrent.cuentas;
                    this.name = this.clienteCurrent.nombre;
                    this.lastName = this.clienteCurrent.apellido;
                    this.email = this.clienteCurrent.email;
                    this.loanscurrent = this.clienteCurrent.loans
                    this.cards = this.clienteCurrent.cards
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },

        cargarClientes() {
            axios
                .get("/api/clientes")
                .then(respuesta => {
                    console.log(respuesta.data)
                    this.clientes = respuesta.data
                })
                .catch(error => {
                    console.error('Error al obtener clientes:', error);
                });
        },
        agregarCliente() {
            if (!this.inputNombre) {
                this.mensaje = "Complete the name field";
                return;
            }
            if (!this.inputApellido) {
                this.mensaje = "Complete the last name field";
                return;
            }
            if (!this.inputEmail) {
                this.mensaje = "Complete the email field";
                return;
            } else {
                let datoClientes = {
                    nombre: this.inputNombre,
                    apellido: this.inputApellido,
                    email: this.inputEmail,
                };
        
                axios
                    .post("/rest/clientes", datoClientes)
                    .then(respuesta => {
                        Swal.fire({
                            title: "Client Created!",
                            text: "The client has been created successfully.",
                            icon: "success",
                            background: "#7B97AC",
                            color: "#000",
                            position: "top", // Puedes ajustar la posición según tu preferencia
                            timer: 2000, // Tiempo en milisegundos (en este caso, 2 segundos)
                            showConfirmButton: false, // No mostrar botón de confirmación
                        }).then(() => {
                            location.reload();
                            this.cargarClientes();
                            this.borrarFormulario();
                        });
                    })
                    .catch(err => console.log(err));
            }
        },        
        
        

        borrarFormulario() {
            this.inputNombre = ""
            this.inputApellido = ""
            this.inputEmail = ""
            this.mensaje = ""
        },

        showListClients() {
            this.showListClientss = true;
            this.showAddClientss = false;
        },
        showAddClients() {
            this.showListClientss = false;
            this.showAddClientss = true;
        },

        capitalizeFirstLetter(str) {
            return str.charAt(0).toUpperCase() + str.slice(1);
        },

        logout() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });
        
            swalWithBootstrapButtons.fire({
                title: "Logout",
                text: "You are about to logout. Are you sure?",
                icon: "question",
                showCancelButton: true,
                confirmButtonText: "Yes",
                cancelButtonText: "No",
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .post("http://localhost:8080/api/logout")
                        .then(() => {
                            console.log("signed out!!!");
                            location.href = "http://localhost:8080/web/index.html";
                        })
                        .catch((error) => {
                            console.log(error);
                            swalWithBootstrapButtons.fire({
                                title: "Logout Failed",
                                text: "An error occurred while logging out.",
                                icon: "error"
                            });
                        });
                } else {
                    console.log("Logout canceled");
                }
            });
        }
    }

}).mount('#app');