INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Letícia Maria Leite de Carvalho', '856.095.680-80',  'lele_maria@gmail.com', '2009-11-30', '(79)98888-8888',  'Lelê');
INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Luiza Maria Leite de Carvalho',  '167.832.820-07', 'lulu_maria@gmail.com', '2016-03-30', '(79)99999-9999', 'Lulu'  );
INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Carla Perez',  '025.928.540-45', 'carlinha@gmail.com', '1991-06-10', '(79)92345-9999', 'carlinha'  );
INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Frida Kahalo',  '418.208.180-39', 'kahalomail@gmail.com', '1986-06-09', '(79)99999-2222', 'frizz'  );
INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Jacaré do Tchan',  '229.903.960-04', 'segureotchan@gmail.com', '1975-12-30', '(79)99349-4233', 'jacare'  );
INSERT INTO ALUNO  ( nome, cpf, email, data_nascimento, celular, apelido)  VALUES( 'Chapolim Colorado',  '531.674.360-18', 'euchapolim@gmail.com', '1987-08-15', '(79)98899-2111', 'polim'  );


INSERT INTO PROFESSOR ( matricula, nome,  cpf, email, data_nascimento, celular) VALUES( 202420001,'Glauco Luiz Rezende de Carvalho', '009.717.570-62', 'glauco.carvalho@academico.ifs.edu.br', '1979-10-09', '(79)99999-9999');
INSERT INTO PROFESSOR ( matricula, nome,  cpf, email, data_nascimento, celular) VALUES( 202420002,'Renata Vasconcelos', '559.042.360-00', 'renata@academico.ifs.edu.br', '1980-11-29', '(79)99231-9999');
INSERT INTO PROFESSOR ( matricula, nome,  cpf, email, data_nascimento, celular) VALUES( 202420003,'Alex Paulo', '938.209.410-53', 'alexpaulo@academico.ifs.edu.br', '1981-04-05', '(79)99922-9249');


INSERT INTO DISCIPLINA ( disciplina, nome, numero_creditos, id_professor ) VALUES ( 10001, 'TCC-II', 5, 2);
INSERT INTO DISCIPLINA ( disciplina, nome, numero_creditos, id_professor ) VALUES ( 10002, 'Interface Gráfica', 3, 2);
INSERT INTO DISCIPLINA ( disciplina, nome, numero_creditos, id_professor ) VALUES ( 10003, 'TE-II', 5, 1);
INSERT INTO DISCIPLINA ( disciplina, nome, numero_creditos, id_professor ) VALUES ( 10004, 'TE-I', 4, 3);


INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 1, 20242938, true );
INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 5, 20242928, true );
INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 2, 20242438, false );
INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 6, 20242838, true );
INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 3, 20242778, true );
INSERT INTO MATRICULA ( id_aluno, matricula, matricula_ativa) VALUES ( 4, 20242008, true );


INSERT INTO TURMA ( turma, data_inicio, data_fim, id_disciplina ) VALUES ( 20001, '2024-08-01', '2024-12-31', 1);
INSERT INTO TURMA ( turma, data_inicio, data_fim, id_disciplina ) VALUES ( 20002, '2025-03-01', '2025-06-30', 3);


INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (1, 1),(1, 2),(1, 3), (1, 4),  (1, 5), (1, 6);
INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (2, 3),(2, 2),(2, 5), (2, 6);
