const { createApp } = Vue

createApp({
  data() {
    return {
      inputEmail: "",
      inputPassword: "",
      nameRegister: "",
      lastNameRegister: "",
      emailRegister: "",
      passwordRegister: "",
    };
  },

  created() {


  },
  methods: {
    register() {
      axios.post("/api/clientes", `nombre=${this.nameRegister}&apellido=${this.lastNameRegister}&email=${this.emailRegister}&password=${this.passwordRegister}`)
        .then(response => {
          return axios.post("/api/login", `email=${this.emailRegister}&password=${this.passwordRegister}`)
        })
        .then(response => {
          console.log("logueado")
          location.pathname = "/web/assets/pages/cuentas.html"
        })
        .catch(err => console.error('Error en el registro:', err));


    },

    login() {
      axios.post("/api/login", `email=${this.inputEmail}&password=${this.inputPassword}`)
        .then(response => {
          Swal.fire({
            title: "Welcome!",
            text: "will be redirected to your personal account",
            icon: "success",
            timer: 2000,
            timerProgressBar: true,
            showConfirmButton: false,
            didClose: () => {
              location.pathname = "/web/assets/pages/cuentas.html";
            }
          });
        })
        .catch(err => {
          console.error('Error en el inicio de sesión:', err);
          Swal.fire({
            title: "Error!",
            text: "Invalid email or password, please try again.",
            icon: "error",
            button: "Ok",
          });
        });
    },
    

    logout() {
      axios.post('/api/logout').then(response => {
        location.pathname = "/web/index.html"
      })
    },

  }

}).mount('#app');