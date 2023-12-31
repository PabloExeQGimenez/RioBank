const { createApp } = Vue

createApp({
  data() {
    return {
      inputEmail: "",
      inputPassword: "",

    };
  },

  created() {


  },
  methods: {
   
    login() {
      axios.post("/api/login",`email=${this.inputEmail}&password=${this.inputPassword}`)
           .then(response => {
            console.log("logueado")
               location.pathname = "/web/assets/pages/cuentas.html"
           })
           .catch(err => console.error('Error en el inicio de sesión:', err));
       },
      
       logout() {
       axios.post('/api/logout').then(response => {
         location.pathname = "/web/index.html"})
       },

  }

}).mount('#app');