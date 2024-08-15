#Configuração para requisições http serem executadas com https

`server.port=8443
server.ssl.key-store=H:/TE-II/Certificados/keystore.p12
server.ssl.key-store-password=J@va2024
server.ssl.keyStoreType=JKS
server.ssl.keyAlias=tomcat`


###Aluno Controller
<p>No AlunoController.java</p>
<p>São definidos diversos métodos, sendo os principais para o CRUD:</p>
- ObterAlunosPorMatricula:
Obtém um aluno pelo número de matrícula.
@param matricula O número de matrícula do aluno a ser obtido.
@return Um objeto AlunoDto correspondente ao aluno encontrado.
@throws ObjectNotFoundException Caso o aluno não seja encontrado no banco de dados.

- CadastrarNovoAluno:
Salva um novo aluno no banco de dados.
@param alunoModel passado no body. O objeto AlunoModel a ser salvo.
@return Um objeto AlunoDto correspondente ao aluno salvo.
@throws DataIntegrityException Caso o aluno já esteja cadastrado no banco de dados.

- AtualizarAluno:
Atualiza os dados de um aluno existente no banco de dados.
@param alunoModel O objeto AlunoModel com os dados atualizados.
@param matricula  O número de matrícula do aluno a ser atualizado.
@return Um objeto AlunoDto correspondente ao aluno atualizado.
@throws ObjectNotFoundException Caso o aluno não seja encontrado no banco de dados.
     
- DeletarPorMatricula
Deleta um aluno do banco de dados pelo número de matrícula.
@param matricula O número de matrícula do aluno a ser deletado.
@throws DataIntegrityException Caso a matrícula do aluno não seja encontrada no banco de dados.


###Disciplina Controller
<p>No DisciplinaController.java</p>
<p>São definidos diversos métodos, sendo os principais para o CRUD:</p>

    /**
     * Obtém uma disciplina pelo número do id.
     *
     * @param id O número da Disciplina a ser obtido.
     * @throws ObjectNotFoundException Caso a disciplina não seja encontrado no banco de dados.
     */

    /**
     * Salva uma nova disciplina no banco de dados.
     *
     * @param disciplinaModel O objeto DisciplinaModel a ser salvo.
     * @return Um objeto DisciplinaDto correspondente a nova disciplina de aluno salva.
     * @throws DataIntegrityException Caso a disciplina já esteja cadastrada no banco de dados.
     */


    /**
     * Deleta uma Disciplina do banco de dados pelo número do id.
     *
     * @param id O id de Disciplina a ser deletado.
     * @throws DataIntegrityException Caso a Disciplina não seja encontrada no banco de dados.
     */




MATRICULA

    /**
     * Obtém um aluno pelo número de matrícula.
     *
     * @param matricula O número de matrícula do aluno a ser obtido.
     * @throws ObjectNotFoundException Caso a matricula não seja encontrado no banco de dados.
     */



    /**
     * Salva uma nova matricula de aluno no banco de dados.
     *
     * @param matriculaModel O objeto MatriculaModel a ser salvo.
     * @return Um objeto MatriculaDto correspondente a nova matricula de aluno salva.
     * @throws DataIntegrityException Caso o aluno já esteja cadastrado no banco de dados.
     */

    /**
     * Deleta uma matricula de aluno do banco de dados pelo número de matrícula.
     *
     * @param matricula O id de matrícula do aluno a ser deletado.
     * @throws DataIntegrityException Caso a matrícula do aluno não seja encontrada no banco de dados.
     */

Gerador de CPF válido: https://www.4devs.com.br/gerador_de_cpf

<---Implementações que podem ocorrer futuramente--->

    void deleteByEmail(String email);
    void deleteByCelular(String celular);
    Optional<ProfessorModel> findByCelular(String celular);



