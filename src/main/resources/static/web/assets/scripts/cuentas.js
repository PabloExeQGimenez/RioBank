const { createApp } = Vue

createApp({
    data() {
        return {
            clienteMelba: "",
            cuentasMelba:"",
            visibleDatosPersonales: false,
            visibleCuentas: false,
            name:"",
            lastName:"",
            email:"",
        };
    },

    created() {

        this.cliente1()


    },
    methods: {
        clientes() {
            axios
                .get("/api/clientes")
                .then(respuesta => {
                    this.clienteMelba = respuesta.data
                    console.log(this.clienteMelba)
                    this.cuentasMelba = this.clienteMelba.cuentas
                    console.log(this.cuentasMelba)
                })
                .catch(error => {
                    console.error('Error al obtener clientes:', error);
                });

        cliente1() {
            axios
                .get("/api/clientes/1")
                .then(respuesta => {
                    this.clienteMelba = respuesta.data
                    console.log(this.clienteMelba)
                    this.cuentasMelba = this.clienteMelba.cuentas
                    console.log(this.cuentasMelba)

                    this.name = this.clienteMelba.nombre
                    this.lastName = this.clienteMelba.apellido
                    this.email = this.clienteMelba.correo
                })
                .catch(error => {
                    console.error('Error al obtener clientes:', error);
                });
        },

        mostrarDatosPersonales(){
            this.visibleDatosPersonales = true
            this.visibleCuentas = false
        },
        mostrarCuentas(){
            this.visibleCuentas= true
            this.visibleDatosPersonales = false

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