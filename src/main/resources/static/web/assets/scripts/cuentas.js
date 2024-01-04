const { createApp } = Vue;

createApp({
    data() {
        return {
            clienteCurrent: "",
            cuentasCurrent: "",
            visibleDatosPersonales: false,
            visibleCuentas: false,
            name: "",
            lastName: "",
            email: "",
            accounts: [],
            transactions: [],
            loans:[],
            visibleDetalleCuenta: false,
            visiblePrestamos:false,
            visibleCards: false,
            cards: [],
            cardType: "",
            cardColor: "",



        };
    },

    created() {
        this.cliente1()


    },

    methods: {
        createCard() {
            console.log(this.cardType)
            console.log(this.cardColor)
        axios
        .post("/api/clientes/current/cards", `type=${this.cardType}&color=${this.cardColor}`)
        .then((response) => {
            console.log("card created")
            location.reload()
        })
        .catch((error) => {
            console.error(error);
        });
        },
        
        createAccount() {
            axios
                .post("/api/clientes/current/accounts")
                .then((response) => {
                    location.reload()
                })
                .catch((error) => {
                    console.error(error);
                });
        },

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
            this.visibleCards = false;

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
                .get("/api/clientes/current")
                .then((respuesta) => {
                    console.log(respuesta.data);
                    this.clienteCurrent = respuesta.data;
                    this.cuentasCurrent = this.clienteCurrent.cuentas;
                    this.name = this.clienteCurrent.nombre;
                    this.lastName = this.clienteCurrent.apellido;
                    this.email = this.clienteCurrent.correo;

                    this.loans = this.clienteCurrent.loans
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },
        mostrarCards() {
            this.visibleCards = true;
            this.visibleDatosPersonales = false;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
        },

        mostrarDatosPersonales() {
            this.visibleDatosPersonales = true;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
            this.visibleCards = false;
        },
        mostrarCuentas() {
            this.visibleCuentas = true;
            this.visibleDatosPersonales = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
            this.visibleCards = false;
        },
        mostrarPrestamos() {
            this.visiblePrestamos = true
            this.visibleCuentas = false
            this.visibleDatosPersonales = false
            this.visibleDetalleCuenta = false
            this.visibleCards = false;
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
