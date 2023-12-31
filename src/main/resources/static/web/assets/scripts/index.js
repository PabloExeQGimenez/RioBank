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
          console.log("logueado")
          location.pathname = "/web/assets/pages/cuentas.html"
        })
        .catch(err => console.error('Error en el inicio de sesión:', err));
    },

    logout() {
      axios.post('/api/logout').then(response => {
        location.pathname = "/web/index.html"
      })
    },

  }

}).mount('#app');