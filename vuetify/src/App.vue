<template>
  <v-app>
    <v-app-bar
      app
      color="secondary"
      dark
    >

    
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

     <h2>Biblioteca</h2>
      </div>

      <v-spacer></v-spacer>

    </v-app-bar>

    <v-content>
        <biblioteca-form></biblioteca-form>
        <biblioteca-table></biblioteca-table>
    </v-content>
  </v-app>
</template>

<script>

import BibliotecaForm from './components/BibliotecaForm.vue'
import BibliotecaTable from './components/BibliotecaTable.vue'

export default {

  name: 'App',

  components: {
    BibliotecaForm,
    BibliotecaTable
  },

  data: () => ({
//
  }),
};
</script>
