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
      clienteCurrent: "",
      name: "-",
      lastName: "",
    };
  },

  created() {
    this.cliente1()
  },
  methods: {
    cliente1() {
      axios
        .get("/api/clientes/current")
        .then((respuesta) => {
          console.log(respuesta.data);
          this.clienteCurrent = respuesta.data;
          this.name = this.clienteCurrent.nombre;
          this.lastName = this.clienteCurrent.apellido;
        })
        .catch((error) => {
          console.error('Error al obtener clientes:', error);
        });
    },
    register() {
      axios.post("/api/clientes", `nombre=${this.nameRegister}&apellido=${this.lastNameRegister}&email=${this.emailRegister}&password=${this.passwordRegister}`)
        .then(response => {
          return axios.post("/api/login", `email=${this.emailRegister}&password=${this.passwordRegister}`);
        })
        .then(response => {
          Swal.fire({
            title: "Your registration was successful!",
            text: "We will redirect you to your personal account",
            icon: "success",
            timer: 2000,
            timerProgressBar: true,
            showConfirmButton: false,
            background: '#7B97AC',
            color: '#fff',
            didClose: () => {
              location.pathname = "/web/assets/pages/cuentas.html";
            }
          });
        })
        .catch(err => {
          console.error('Error en el registro o inicio de sesión:', err);
          Swal.fire({
            title: "Error",
            text: "Hubo un problema en el registro o inicio de sesión",
            icon: "error",
            button: "Aceptar",
          });
        });
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
            background: '#7B97AC',
            color: 'white',
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