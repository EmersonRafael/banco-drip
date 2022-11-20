# banco-drip

1 - Execultar o docker-compose.yml para subir o postgres,
    versionamento das tabelas estão com flyway

2 - Como Testar
http://localhost:8080/swagger-ui/index.html#

/transferencia/origem/{idContaOrigem}/destino/{idContaDestino}
```sh
curl -X 'POST' \
'http://localhost:8080/transferencia/origem/1/destino/2' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"valor": 1000,
"tipoTransferencia": "Externo"
}'
```

/transferencia/origem/{idContaOrigem}
```sh
curl -X 'GET' \
  'http://localhost:8080/transferencia/origem/1' \
  -H 'accept: */*'
```
