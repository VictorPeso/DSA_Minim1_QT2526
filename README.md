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
| Llibre  | id              | `string`  |                          |
|         | isbn            | `string`  |                          |
|         | titol           | `string`  |                          |
|         | editorial       | `string`  |                          |
|         | any             | `string`  |                          |
|         | num_edicio      | `string`  |                          |
|         | autor           | `string`  |                          |
|         | temática        | `string`  |                          |
|         | exemplars       | `int`     |                          |
| Lector  | id              | `string`  | ERROR: No hi ha cap llibre pendent de catalogar. |
|         | nom             | `string`  |                          |
|         | cognom          | `string`  |                          |
|         | dni             | `string`  |                          |
|         | birthdate       | `string`  |                          |
|         | direccio        | `string`  |                          |
| Prestac | id              | `string`  | ERROR: No hi han exemplars |
|         | id_lector       | `string`  | ERROR: No existeix el llibre/lector    |
|         | id_llibre       | `string`  |                          |
|         | data_prestec    | `string`  |                          |
|         | data_devolucio  | `string`  |                          |
|         | estat           | `string`  | `En tramit` `Tornat`       |

#### Implementació

| Atributo      | Tipo                     | Notas |
|---------------|-------------------------|-------|
| munt          | `Stack<Llibre>`         |       |
| biblio        | `List<Stack<Llibre>>`   |       |
| lectors       | `List<Lector>`          |       |
| catalog       | `List<Llibre>`          |       |
| registre      | `List<Prestac>`         |       |
---
## `Part 2`
| Estat | Apartat | Descripció |
|:-----:|:---:|:-----------|
| ❌ |1| Definir servei, operacions, rutes, mètodes HTTP, peticions, respostes, codis de resposta |
| ❌ |2| Implementar un servei REST que permeti realitzar les operacions especificades a la primera part de l'exercici. |

---
##### `Fitxers adjuunts`
Totes les captures es troben dintre del directori "CAPTURAS DE PANTALLA"
| Estat | Nom del fitxer | Descripció |
|:------:|:-----:|:-----------|
| ✅ | JUNITtest | Captura de pantalla amb l’execució del test JUNIT de la PART 1. |
| ❌ | - | Captura de pantalla amb la consola de l’execució del servei REST. |
| ❌ | - | Captura de pantalla amb l’execució de dues operaciones sobre swagger. |

---
##### `Llegenda`
| Simbol | Descripció |
|:------:|:-----------|
| ✅ | Correctament implementat y funcionant de forma intencional. |
| ⚠️ | Parcialment ben implementat pero amb errors detectats. |
| ❌ | No implementat o amb errors catastròfics. |
