const { createApp } = Vue;

createApp({
    data() {
        return {
            clienteCurrent: "",
            cuentasCurrent: [],
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
            cardId: 0,
            cuentaTipo: "SAVINGS",
        };
    },
    created() {
        this.cliente1()
        this.getLoans()
        this.allCards()
        this.cuentasCurrentt()
    },
    methods: {

        //Clients

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
                    this.name = this.clienteCurrent.nombre;
                    this.lastName = this.clienteCurrent.apellido;
                    this.email = this.clienteCurrent.email;
                    this.loanscurrent = this.clienteCurrent.loans
                })
                .catch((error) => {
                    console.error('Error al obtener clientes:', error);
                });
        },


        //Accounts
        createAccount() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });
            swalWithBootstrapButtons.fire({
                title: "Confirm Account Creation",
                icon: "question",
                showCancelButton: true,
                confirmButtonText: "Yes, create account!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                    
                        .post("/api/clientes/current/accounts", `tipo=${this.cuentaTipo}`)
                        .then(() => {
                            swalWithBootstrapButtons.fire({
                                title: "Account Created!",
                                text: "Your account has been created successfully.",
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                location.reload();
                            });
                        })
                        .catch(err => {
                            console.error('Error en la creación de la cuenta:', err);
                            swalWithBootstrapButtons.fire({
                                title: "Error",
                                text: "An error occurred while creating your account.",
                                icon: "error"
                            });
                        });
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    swalWithBootstrapButtons.fire({
                        title: "Cancelled",
                        text: "Account creation has been cancelled.",
                        icon: "error"
                    });
                }
            });
        },

        cuentasCurrentt() {
            axios.get("/api/clientes/current/accounts")
                .then((response) => {
                    this.cuentasCurrent = response.data;
                    console.log(this.cuentasCurrent);
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        deleteAccount(accountId) {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });

            swalWithBootstrapButtons.fire({
                title: "Confirm Deletion",
                text: "Are you sure you want to delete this account?",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .post(`/api/cuentas/${accountId}`)
                        .then(() => {
                            swalWithBootstrapButtons.fire({
                                title: "Account Deleted!",
                                text: `Account has been deleted successfully.`,
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                console.log(`Account deleted successfully`);
                                location.reload();
                            });

                        })
                        .catch((error) => {
                            console.error(`Error deleting account:`, error);
                            swalWithBootstrapButtons.fire({
                                title: "Error",
                                text: `"You can't delete an account with a balance greater than 0"`,
                                icon: "error"
                            });
                        });
                }
            });
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


        //Cards

        createCard() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });
            const cardConfirmationMessage = `
                <strong>Card Type:</strong> ${this.cardType}<br>
                <strong>Card Color:</strong> ${this.cardColor}
            `;
            swalWithBootstrapButtons.fire({
                title: "Confirm Card Creation",
                html: cardConfirmationMessage,
                icon: "question",
                showCancelButton: true,
                confirmButtonText: "Yes, create card!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .post("/api/clientes/current/cards", `type=${this.cardType}&color=${this.cardColor}`)
                        .then(() => {
                            swalWithBootstrapButtons.fire({
                                title: "Card Created!",
                                text: "To see your new card go to: Menu > Cards",
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                location.reload();
                            });
                        })
                        .catch((error) => {
                            console.error(error);
                            swalWithBootstrapButtons.fire({
                                title: "Error",
                                text: "An error occurred while creating your card.",
                                icon: "error",
                                background: "#7B97AC",
                                color: "#000"
                            });
                        });
                    this.visibleCards = true;
                    this.visibleCuentas = false;
                    this.visibleDatosPersonales = false;
                    this.visibleDetalleCuenta = false;
                    this.visiblePrestamos = false;
                    this.visibleGetCards = false;
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    swalWithBootstrapButtons.fire({
                        title: "Cancelled",
                        text: "Card creation has been cancelled.",
                        icon: "error",
                        background: "#7B97AC",
                        color: "#000"
                    });
                }
            });
        },

        allCards() {
            axios.get("/api/clientes/current/cards")
                .then((response) => {
                    this.cards = response.data;
                    console.log(this.cards);
                })
                .catch((error) => {
                    console.error(error);
                });

        },

        isCardExpired(thruDate) {
            const currentDate = new Date();
            const cardThruDate = new Date(thruDate);
            return currentDate < cardThruDate;
        },

        deleteCard(cardId) {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });

            swalWithBootstrapButtons.fire({
                title: "Confirm Deletion",
                text: "Are you sure you want to delete this card?",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .patch(`/api/clientes/current/cards?id=${cardId}`)
                        .then(() => {
                            swalWithBootstrapButtons.fire({
                                title: "Card Deleted!",
                                text: `Card has been deleted successfully.`,
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                console.log(`Card ${cardId} deleted successfully`);
                                this.cards = this.cards.filter((card) => card.id !== cardId && card.active);
                            });
                        })
                        .catch((error) => {
                            console.error(`Error deleting card ${cardId}:`, error);
                            swalWithBootstrapButtons.fire({
                                title: "Error",
                                text: `An error occurred while deleting card`,
                                icon: "error"
                            });
                        });
                }
            });
        },


        //Loans

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
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });
            const loanConfirmationMessage = `
            <strong>Amount: U$D</strong> ${this.amountLoan}<br>
            <strong>Payments:</strong> ${this.payments}<br>
            <strong>Destination Account:</strong> ${this.toAccount}
            `;
            swalWithBootstrapButtons.fire({
                title: "Confirm Loan Request",
                html: loanConfirmationMessage,
                icon: "question",
                showCancelButton: true,
                confirmButtonText: "Yes, submit request!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .post("/api/clientloans", `loanId=${this.loanId}&amount=${this.amountLoan}&payments=${this.payments}&destinationAccount=${this.toAccount}`)
                        .then(() => {
                            swalWithBootstrapButtons.fire({
                                title: "Loan Request Submitted!",
                                text: "To view details of your loan, go to: Menu > Loans",
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                location.reload();
                            });
                        })
                        .catch(err => {
                            console.error('Error en el registro o inicio de sesión:', err);
                            swalWithBootstrapButtons.fire({
                                title: "Error",
                                text: "An error occurred while processing your loan request.",
                                icon: "error",
                                background: "#7B97AC",
                                color: "#000"
                            });
                        });
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    swalWithBootstrapButtons.fire({
                        title: "Cancelled",
                        text: "Your loan request has been cancelled.",
                        icon: "error",
                        background: "#7B97AC",
                        color: "#000"
                    });
                }
            });
        },

        //Transactions
        
        createTransaction() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: "boton",
                    cancelButton: "boton",
                },
                buttonsStyling: false
            });

            const transactionConfirmationMessage = `
                <strong>Amount:</strong> ${this.amount}<br>
                <strong>Description:</strong> ${this.description}<br>
                <strong>Origin Number:</strong> ${this.originNumber}<br>
                <strong>Destination Number:</strong> ${this.destinationNumber}
            `;

            swalWithBootstrapButtons.fire({
                title: "Confirm Transaction",
                html: transactionConfirmationMessage,
                icon: "question",
                showCancelButton: true,
                confirmButtonText: "Yes, proceed!",
                cancelButtonText: "No, cancel",
                reverseButtons: true,
                background: "#7B97AC",
                color: "#000"
            }).then((result) => {
                if (result.isConfirmed) {
                    axios
                        .post("/api/transactions",
                            `amount=${this.amount}&description=${this.description}&originNumber=${this.originNumber}&destinationNumber=${this.destinationNumber}`)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: "Transaction Successful!",
                                text: "Your transaction has been completed successfully.",
                                icon: "success",
                                background: "#7B97AC",
                                color: "#000"
                            }).then(() => {
                                location.reload();
                            });
                        })
                        .catch((error) => {
                            console.error(error);
                            swalWithBootstrapButtons.fire({
                                title: "Transaction Failed",
                                text: "An error occurred while processing your transaction.",
                                icon: "error"
                            });
                        });
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    swalWithBootstrapButtons.fire({
                        title: "Transaction Cancelled",
                        text: "Your transaction has been cancelled.",
                        icon: "error"
                    });
                }
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

        
        //others

        capitalizeFirstLetter(str) {
            if (str) {
                return str.charAt(0).toUpperCase() + str.slice(1);
            }
            // Manejar el caso en que la cadena es undefined
            return "";
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
        },
        formatDate(date) {
            const formattedDate = new Date(date).toLocaleDateString('en-US', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',

            });
            return formattedDate;
        },


        //Views

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
    },

}).mount('#app');
