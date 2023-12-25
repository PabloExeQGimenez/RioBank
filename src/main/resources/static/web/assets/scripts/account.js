const { createApp } = Vue;

createApp({
    data() {
        return {
            acounts: [],
            transactions: [],


        };
    },

    created() {

        const parameters = new URLSearchParams(location.search);
        const id = parameters.get('id');

        axios
            .get("/api/cuentas/1")
            .then((response) => {
                this.accounts = response.data;
                console.log(response)
                this.transactions = response.data.transactions;
                this.transactions.sort((a, b) => b.id - a.id);

            })
            .catch((error) => {
                console.error(error);
            });

    },

    methods: {

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
