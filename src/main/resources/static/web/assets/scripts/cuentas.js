const { createApp } = Vue;

createApp({
    data() {
        return {
            clienteMelba: "",
            cuentasMelba: "",
            visibleDatosPersonales: false,
            visibleCuentas: false,
            name: "",
            lastName: "",
            email: "",
            acounts: [],
            transactions: [],
            loans:[],
            visibleDetalleCuenta: false,
            visiblePrestamos:false,


        };
    },

    created() {
        this.cliente1()


    },

    methods: {
        formatTransactionDate(date) {
            const formattedDate = new Date(date).toLocaleDateString('en-US', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
            });
            return formattedDate;
        },

        accountId(id) {
            if (!id) {
                // Manejar el caso en el que no hay un parámetro 'id' en la URL
                console.error("ID not found in the URL");
                return;
            }

            axios
                .get(`/api/cuentas/${id}`)
                .then((response) => {
                    this.accounts = response.data;
                    this.transactions = response.data.transactions;
                    this.transactions.sort((a, b) => b.id - a.id);

                })
                .catch((error) => {
                    console.error(error);
                });

            this.visibleDetalleCuenta = true;
            this.visibleCuentas = false;
            this.visibleDatosPersonales = false;
            this.visiblePrestamos = false

        },


        clientes() {
            axios
                .get("/api/clientes")
                .then((respuesta) => {
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },

        cliente1() {
            axios
                .get("/api/clientes/1")
                .then((respuesta) => {
                    this.clienteMelba = respuesta.data;
                    this.cuentasMelba = this.clienteMelba.cuentas;
                    this.name = this.clienteMelba.nombre;
                    this.lastName = this.clienteMelba.apellido;
                    this.email = this.clienteMelba.correo;

                    this.loans = this.clienteMelba.loans
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },

        mostrarDatosPersonales() {
            this.visibleDatosPersonales = true;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
        },
        mostrarCuentas() {
            this.visibleCuentas = true;
            this.visibleDatosPersonales = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
        },
        mostrarPrestamos() {
            this.visiblePrestamos = true
            this.visibleCuentas = false
            this.visibleDatosPersonales = false
            this.visibleDetalleCuenta = false
        },
        logout() {
            axios
                .post("http://localhost:8080/api/logout")
                .then((response) => {
                    console.log("signed out!!!");
                    location.href = "http://localhost:8080/web/index.html";
                })
                .catch((error) => console.log(error));
        },
    },
}).mount('#app');
