# the-unicorn-cake-api
Api de Java

## Documentação da API

#### **USUARIO**

#### **GET:** Retorna Usuario por CPF

```http
  http://localhost:8080/usuarios/${cpf}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/usuarios/111.111.111-11
```

#### **POST:** Cadastra um Usuario

```http
  http://localhost:8080/usuarios/
```

testar no PostMan com esse json:

```json
{"nome":"x","email":"xxx","cpf":"111.111.111-11", "tpSangue" : "O+", "idade"
 : 18, "genero" : "f", "peso" : 60, "altura" : 1.70, "senha" : "x"}
```

#### **PUT:** Atualiza o Usuario

```http
  http://localhost:8080/usuarios/${idUsuario}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/usuarios/1
```

testar no PostMan com esse json:

```json
{"nome":"x","email":"xxx","cpf":"111.111.111-11", "tpSangue" : "O+", "idade"
 : 18, "genero" : "f", "peso" : 60, "altura" : 1.70, "senha" : "x"}
```

#### **POST:** Loga o Usuario

```http
  http://localhost:8080/usuarios/logar
```

testar no PostMan com esse json:

```json
{"cpf":"111.111.111-11", "senha" : "x"}
```

#### **CONSULTA**

#### **POST:** Cadastra uma Consulta

```http
  http://localhost:8080/consultas/
```

testar no PostMan com esse json:

```json
{"cpf" : "111.111.111-11", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
```

#### **GET:** Retorna Consulta por ID

```http
  http://localhost:8080/consultas/id/${idConsulta}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/consultas/id/1
```

#### **DELETE:** Deleta Consulta por ID

```http
  http://localhost:8080/consultas/${idConsulta}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/consultas/id/1
```

#### **GET:** Retorna Consultas por CPF

```http
  http://localhost:8080/consultas/${cpf}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/consultas/111.111.111-11
```

#### **PUT:** Atualiza a Consulta

```http
  http://localhost:8080/consultas/${idConsulta}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/consultas/1
```

testar no PostMan com esse json:

```json
{"cpf" : "111.111.111-11", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
```

#### **EXAME**

#### **POST:** Cadastra um exame

```http
  http://localhost:8080/exames/
```

testar no PostMan com esse json:

```json
{"cpf" : "111.111.111-11", "nome": "x", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
```

#### **GET:** Retorna Exame por ID

```http
  http://localhost:8080/exames/id/${idExame}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/exames/id/1
```

#### **DELETE:** Deleta Exame por ID

```http
  http://localhost:8080/exames/${idExame}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/exames/id/1
```

#### **GET:** Retorna Exames por CPF

```http
  http://localhost:8080/exames/${cpf}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/exames/111.111.111-11
```

#### **PUT:** Atualiza o Exame

```http
  http://localhost:8080/exames/${idExame}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/exames/1
```

testar no PostMan com esse json:

```json
{"cpf" : "111.111.111-11", "nome": "x", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
```

#### **ALERGIA**

#### **POST:** Cadastra uma Alergia

```http
  http://localhost:8080/alergias/
```

testar no PostMan com esse json:

```json
{"cpf":"111.111.111-11", "tipo":"x", "descricaoGrau":"x"}
```

#### **GET:** Retorna Alergia por ID

```http
  http://localhost:8080/alergias/id/${idAlergia}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/alergias/id/1
```

#### **DELETE:** Deleta Alergia por ID

```http
  http://localhost:8080/alergias/${idAlergia}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/alergias/id/1
```

#### **GET:** Retorna Alergia por CPF

```http
  http://localhost:8080/alergias/${cpf}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/alergias/111.111.111-11
```

#### **PUT:** Atualiza a Alergia

```http
  http://localhost:8080/alergias/${idAlergia}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/alergias/1
```

testar no PostMan com esse json:

```json
{"cpf":"111.111.111-11", "tipo":"x", "descricaoGrau":"x"}
```

#### **MEDICAMENTO**

#### **POST:** Cadastra um Medicamento

```http
  http://localhost:8080/medicamentos/
```

testar no PostMan com esse json:

```json
{"cpf":"111.111.111-11", "nome":"x", "dosagem":"x", "viaDeAdmin" : "oral", "dataHoraInicio" : "2023-11-21T14:30:45", "quantidadeDias" : 3}
```

#### **GET:** Retorna Medicamento por ID

```http
  http://localhost:8080/medicamentos/id/${idMedicamento}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/medicamentos/id/1
```

#### **DELETE:** Deleta Medicamento por ID

```http
  http://localhost:8080/medicamentos/${idMedicamento}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/medicamentos/id/1
```

#### **GET:** Retorna Medicamento por CPF

```http
  http://localhost:8080/medicamentos/${cpf}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/medicamentos/111.111.111-11
```

#### **PUT:** Atualiza a Medicamento

```http
  http://localhost:8080/medicamentos/${idMedicamento}
```

no PostMan testar com essa url:

```http
  http://localhost:8080/medicamentos/1
```

testar no PostMan com esse json:

```json
{"cpf":"111.111.111-11", "nome":"x", "dosagem":"x", "viaDeAdmin" : "oral",
 "dataHoraInicio" : "2023-11-21T14:30:45", "quantidadeDias" : 3}
```
