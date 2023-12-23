const { createApp } = Vue

createApp({
    data() {
        return {
            clientes: [],
            json: "",
            inputNombre: "",
            inputApellido: "",
            inputEmail: "",
            mensaje: "",
        };
    },

    created() {

        this.cargarClientes()


    },
    methods: {

        cargarClientes() {
            axios
                .get("/clientes")
                .then(respuesta => {
                    this.json = respuesta.data._embedded
                    this.clientes = respuesta.data._embedded.clientes
                })
                .catch(error => {
                    console.error('Error al obtener clientes:', error);
                });
        },

        agregarCliente() {

            if (!this.inputNombre) {
                this.mensaje = "Complete el campo nombre"
                return
            }
            if (!this.inputApellido) {
                this.mensaje = "Complete el campo apellido"
                return
            }
            if (!this.inputEmail) {
                this.mensaje = "Complete el campo email"
                return
            }

             else{

            let datoClientes = {
                nombre: this.inputNombre,
                apellido: this.inputApellido,
                email: this.inputEmail,
                contrasena: this.inputEmail,

            };

            axios
                .post("/clientes", datoClientes)
                .then(respuesta => {
                    this.cargarClientes()
                    this.borrarFormulario()

                })
                .catch(err => console.log(err))
        }
    },

    borrarFormulario() {
        this.inputNombre = ""
        this.inputApellido = ""
        this.inputEmail = ""
        this.mensaje = ""

    },

    logout() {
        axios
            .post("http://localhost:8080/api/logout")
            .then((response) => {
                console.log("signed out!!!");
                location.href = "http://localhost:8080/web/index.html";
            })
            .catch((error) => console.log(error));
    }

}

}).mount('#app');