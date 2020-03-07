<template>
  <v-container>
        <v-alert dismissible
             v-for="mensagem in mensagens"
             :key="mensagem.texto"
             :type="mensagem.tipo">{{ mensagem.texto}}</v-alert>
    <div>
      <v-card outlined class="mx-auto" max-width="1200">
        <v-simple-table>
          <template>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Páginas</th>
                <th>Editora</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="livro in livros" :key="livro.id">
                <td>{{livro.id}}</td>
                <td>{{livro.nome}}</td>
                <td>{{livro.pages}}</td>
                <td>{{livro.editora.nome}}</td>

                <div class="my-2">
                  <v-btn color="primary" small dark @click="editar(livro)">
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                </div>

                <div class="my-2">
                  <v-btn color="error" small dark @click.prevent="excluir(livro.id)">
                    <v-icon>mdi-trash-can</v-icon>
                  </v-btn>
                </div>
              </tr>
              <v-row justify="center">
                <v-dialog max-width="600px" persistent v-model="dialog">
                  <v-card>
                    <v-card-text>
                      <v-col cols="12">
                        <v-text-field label="Nome" v-model="livro.nome" solo required></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field label="Páginas" v-model="livro.pages" solo required></v-text-field>
                      </v-col>

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
                          v-on:change="editoraValue"
                          solo
                        ></v-select>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn color="primary" text @click="salvar()">Salvar</v-btn>
                      <v-btn color="primary" text @click="dialog=false">Fechar</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </tbody>
          </template>
        </v-simple-table>
      </v-card>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      livro: {
        id: "",
        nome: "",
        pages: "",
        editora: ""
      },

      editora: {
        id: "",
        nome: ""
      },

      mensagens: [],
      
      livros: [],

      editoras: [],

      dialog: false
    };
  },

  mounted() {
    this.obterLivros();
  },

  methods: {
    obterLivros() {
      this.$http("/book").then(response => {
        this.livros = response.data;
      });
    },

    editar(livro) {
      this.dialog = true;
      this.livro = livro;
    },

    salvar() {
      this.$http.put(`/book/update/${this.livro.id}`, this.livro).then(() => {
        this.limpar();
        this.dialog = false;
        this.obterLivros();
      });
    },
    limpar() {
      this.livro.nome = "";
      this.livro.pages = "";
      this.editora = null;
    },
    obterEditoras() {
      this.$http("/editora").then(response => {
        this.editoras = response.data;
      });
    },
    editoraValue() {
      this.livro.editora = this.editora;
    },
    excluir(id) {
        this.livro.id = id
        this.$http.delete(`/book/${id}`).then(() => {
          this.mensagens.push({
            texto: 'Operação realizada com sucesso!',
            tipo: 'success'

          });
          this.obterLivros()
        })
    }

  }
};
</script>