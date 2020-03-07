<template>
<div class="container">
  <v-card 
    outlined
    class="mx-auto"
    max-width="1200"
    >
    <v-alert dismissible
             v-for="mensagem in mensagens"
             :key="mensagem.texto"
             :type="mensagem.tipo">{{ mensagem.texto}}</v-alert>
    <v-form
      ref="form"
      v-model="valid"
      lazy-validation
      justify-center>
      <br />
      
      <v-col cols="12"
        justify-center>
        <h4>Formulário</h4>
        <br/>
        <v-text-field 
          v-model="livro.nome" 
          title="Nome" 
          solo 
          placeholder="Digite seu Nome"
          :rules="nomeRules" 
          required></v-text-field>
        <v-text-field 
          label="Quantidade de Páginas" 
          v-model="livro.pages"
          :rules="pagesRules" 
          required 
          solo></v-text-field>

        <v-container @click="obterEditoras">
          <v-select
            label="Editora"
            :items="editoras"
            v-model="editora"
            single-line
            item-value="id"
            item-text="nome"
            required
            return-object
            :rules="[v => !!v || 'Editora é Obrigatório!']"
            v-on:change="editoraValue"
            solo
          ></v-select>
        </v-container>

        <v-btn 
          @click="salvarLivro" 
          large 
          color="success"
          :disabled="!valid"
          class="mr-4"
          >Salvar</v-btn>
      </v-col>
    </v-form>
  </v-card>
  </div>
</template>

<script>

export default {
  data() {
    return {
      livro: {
        id: '',
        nome: '',
        pages: '',
        editora: null
      },

      editora: {
        id: '',
        nome: ''
      },
      valid: true,

      editoras: [],

      mensagens: [],

      nomeRules: [
        v => !!v || 'Nome é obrigatório!',
        v => (v && v.length >= 2 || 'O nome deve ter pelo menos 2 caracteres.')
      ],

      pagesRules: [
        v => !!v || 'O número de páginas é obrigatório!'
      ]
    };
  },

  methods: {
    obterEditoras() {
      this.$http("/editora").then(response => {
        this.editoras = response.data;
      });
    },

    salvarLivro() {
      if (this.$refs.form.validate()) {
        this.$http.post("/book/new", this.livro).then(() => {
          this.limpar()
          this.mensagens.push({
            texto: 'Operação realizada com sucesso!',
            tipo: 'success'

          }),
          this.reloadPage()
        });
      }
      
    },

    editoraValue() {
      this.livro.editora = this.editora;
    },

    limpar() {
      this.$refs.form.resetValidation(),
      this.livro.nome = "";
      this.livro.pages = "";
      this.editora = null;
    },

    reloadPage() {
      window.location.reload()
    }

  }
};
</script>