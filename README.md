# Minim 1 [QT2526 - 07/11/2025]

## `Part 1`
Funcionalitars requerides:
| Estat | Apartat | Descripció |
|:-----:|:---:|:-----------|
| ✅ |1|  Identificar les entitats d'informació i les seves propietats. Podeu fer una proposta bàsica de classes i d'atributs.|
| ✅ |2| Especificació del component que implementarà les operacions descrites anteriorment: interfície Java.|
| ✅ |3| Implementació d'una Façana (patró de disseny) que implementi la interfície definida prèviament. |
| ✅ |4| Implementació d'una prova (JUNIT) sobre el component desenvolupat. |

Veure la resolució dels tests efectuats al directori "CAPTURAS DE PANTALLA" amb el nom "JUNITtest".

### Proposta bàsica de classes i atributs:
#### Clases

| Clase   | Atributo          | Tipo     | Notas / Errores posibles |
|---------|-----------------|---------|--------------------------|
| Llibre  | id              | string  |                          |
|         | isbn            | string  |                          |
|         | titol           | string  |                          |
|         | editorial       | string  |                          |
|         | any             | string  |                          |
|         | num_edicio      | int     |                          |
|         | autor           | string  |                          |
|         | temática        | string  |                          |
|         | exemplars       | int     |                          |
| Lector  | id              | string  | ERROR: No hi ha cap llibre pendent de catalogar. |
|         | nom             | string  |                          |
|         | cognom          | string  |                          |
|         | dni             | string  |                          |
|         | birthdate       | string  |                          |
|         | direccio        | string  |                          |
| Prestac | id              | string  | ERROR: No hi han exemplars |
|         | id_lector       | string  | ERROR: No existeix el llibre/lector    |
|         | id_llibre       | string  |                          |
|         | data_prestec    | string  |                          |
|         | data_devolucio  | string  |                          |
|         | estat           | string  | #En tramit; tornat       |

tornat<br>ERROR: No hi han exemplars<br>ERROR: No existeix el llibre/lector

#### Implementació

| Atributo      | Tipo                             | Notas |
|---------------|---------------------------------|-------|
| munt          | Stack<Llibre>                   |       |
| biblio        | List<Stack<Llibre>>             |       |
| lectors       | List<Lector>                     |       |
| catalog       | List<Llibre>                     |       |
| registre      | List<Prestac>                    |       |
---
## `Part 2`
- Implementados algunos metodos GET, POST, PUT en clase Service

---
##### `Llegenda`
| Simbol | Descripció |
|:------:|:------------|
| ✅ | Correctament implementat y funcionant de forma intencional. |
| ⚠️ | Parcialment ben implementat pero amb errors detectats. |
| ❌ | No implementat o amb errors catastròfics. |
