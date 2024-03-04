const { createApp } = Vue

createApp({
    data() {
        return {
            clientess: [],
            clientes: [],
            inputNombre: "",
            inputApellido: "",
            inputEmail: "",
            mensaje: "",
            name: "-",
            showAddClientss: true,
            showListClientss: false,
            showListCardss: false,
            cards: [],
            inputSearch: "",
            filteredCards: [],
            inputSearchClient: "",
            filteredClients: [],
            totalAccounts: "",
            showMoneyAccountss: false,
            totalLoans:"",
            totalBalance:"",


        };
    },

    created() {
        this.clientes_()
        this.cliente1()
        this.cargarClientes()
        this.allCards()
        this.filteredCards = this.cards
        this.filteredClients = this.clientess
        this.moneyAccounts()
        this.moneyLoans()
        this.totalBalance = parseInt(this.totalAccounts) - parseInt(this.totalLoans);


    },
    methods: {

        moneyAccounts() {
            axios
                .get("/api/cuentas")
                .then(response => {
                    // Obtén los balances de todas las cuentas
                    const balances = response.data.map(cuenta => cuenta.balance);

                    // Suma los balances utilizando reduce
                    this.totalAccounts = balances.reduce((total, balance) => total + balance, 0);
                })
                .catch(error => {
                    console.error("Error fetching account balances:", error);
                });
        },
        moneyLoans(){
            axios
            .get("/api/clientloans")
            .then(response => {
                const balanceLoans = response.data.map(clientloan => clientloan.amount);

                this.totalLoans = balanceLoans.reduce((total, balance) => total + balance, 0);
            })
        },

        clientes_() {
            axios
                .get("/api/clientes")
                .then((respuesta) => {
                    this.clientess = respuesta.data
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },
        cliente1() {
            axios
                .get("/api/clientes/current")
                .then((respuesta) => {
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
                            position: "top",
                            timer: 2000,
                            showConfirmButton: false,
                        }).then(() => {
                            location.reload();
                            this.cargarClientes();
                            this.borrarFormulario();
                        });
                    })
                    .catch(err => console.log(err));
            }
        },

        searchClients() {
            this.filteredClients = this.clientess.filter(cliente => cliente.nombre.toLowerCase().includes(this.inputSearchClient.toLowerCase()) || cliente.apellido.toLowerCase().includes(this.inputSearchClient.toLowerCase()) || cliente.cuentas.some(cuenta => cuenta.numero.toLowerCase().includes(this.inputSearchClient.toLowerCase())) || cliente.cards.some(card => card.number.includes(this.inputSearchClient)));
        },



        borrarFormulario() {
            this.inputNombre = ""
            this.inputApellido = ""
            this.inputEmail = ""
            this.mensaje = ""
        },

        showMoneyAccounts(){
            this.showMoneyAccountss = true;
            this.showListClientss = false;
            this.showAddClientss = false;
            this.showListCardss = false;
        },

        showListClients() {
            this.showListClientss = true;
            this.showAddClientss = false;
            this.showListCardss = false;
            this.showMoneyAccountss = false
        },
        showAddClients() {
            this.showListClientss = false;
            this.showAddClientss = true;
            this.showListCardss = false;
            this.showMoneyAccountss = false

        },

        showListCards() {
            this.showListCardss = true;
            this.showAddClientss = false;
            this.showListClientss = false;
            this.showMoneyAccountss = false

        },

        // Cards

        allCards() {
            axios
                .get("/api/cards")
                .then(response => {
                    this.cards = response.data
                    console.log(this.cards)

                })

        },

        capitalizeFirstLetter(str) {
            return str.charAt(0).toUpperCase() + str.slice(1);
        },

        searchCards() {
            this.filteredCards = this.cards.filter(card => card.cardholder.toLowerCase().includes(this.inputSearch.toLowerCase()) || card.number.toLowerCase().includes(this.inputSearch.toLowerCase()));
            console.log(this.filteredCards)
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
                        .post("/api/logout")
                        .then(() => {
                            console.log("signed out!!!");
                            location.href = "/web/index.html";
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