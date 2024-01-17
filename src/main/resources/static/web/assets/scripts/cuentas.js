const { createApp } = Vue;

createApp({
    data() {
        return {
            clienteCurrent: "",
            cuentasCurrent: "",
            visibleDatosPersonales: false,
            visibleCuentas: true,
            name: "",
            lastName: "",
            email: "",
            accounts: [],
            transactions: [],
            loanscurrent: [],
            visibleDetalleCuenta: false,
            visiblePrestamos: false,
            visibleCards: false,
            cards: [],
            cardType: "",
            cardColor: "",
            visibleGetCards: false,
            amount: "",
            description: "",
            originNumber: "",
            destinationNumber: "",
            loans: [],
            loanId: 0,
            amountLoan: 0,
            payments: 0,
            toAccount: "",
        };
    },

    created() {
        this.cliente1()
        this.getLoans()
    },

    methods: {

        getLoans() {
            axios
                .get("/api/loans")
                .then((response) => {
                    this.loans = response.data;
                    console.log(this.loans);
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        createLoan() {
            axios
                .post("/api/clientloans", `loanId=${this.loanId}&amount=${this.amountLoan}&payments=${this.payments}&destinationAccount=${this.toAccount}`)
                .then((result) => {
                    location.reload()
                })
                .catch((error) => {
                    console.log(error);
                });
        },

        createCard() {
            axios
                .post("/api/clientes/current/cards", `type=${this.cardType}&color=${this.cardColor}`)
                .then((response) => {
                    location.reload()
                })
                .catch((error) => {
                    console.error(error);
                });
            this.visibleCards = true;
            this.visibleCuentas = false;
            this.visibleDatosPersonales = false;
            this.visibleDetalleCuenta = false;
            this.visiblePrestamos = false;
            this.visibleGetCards = false;
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
        createTransaction() {

            axios
                .post("/api/transactions",
            `amount=${this.amount}&description=${this.description}&originNumber=${this.originNumber}&destinationNumber=${this.destinationNumber}`)
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
                    console.log(response.data);
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
                    this.email = this.clienteCurrent.email;
                    this.loanscurrent = this.clienteCurrent.loans
                    this.cards = this.clienteCurrent.cards
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },
    
        mostrarGetCards() {
            this.visibleGetCards = true;
            this.visibleCards = false;
            this.visibleDatosPersonales = false;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
        },
        mostrarCards() {
            this.visibleCards = true;
            this.visibleDatosPersonales = false;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
            this.visibleGetCards = false;

        },

        mostrarDatosPersonales() {
            this.visibleDatosPersonales = true;
            this.visibleCuentas = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
            this.visibleCards = false;
            this.visibleGetCards = false;
        },
        mostrarCuentas() {
            this.visibleCuentas = true;
            this.visibleDatosPersonales = false;
            this.visibleDetalleCuenta = false
            this.visiblePrestamos = false
            this.visibleCards = false;
            this.visibleGetCards = false;
        },
        mostrarPrestamos() {
            this.visiblePrestamos = true
            this.visibleCuentas = false
            this.visibleDatosPersonales = false
            this.visibleDetalleCuenta = false
            this.visibleCards = false;
            this.visibleGetCards = false;
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
