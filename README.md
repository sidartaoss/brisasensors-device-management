# BrisaSensors · Gestão de Dispositivos

Serviço de gestão de dispositivos do [BrisaSensors](https://github.com/sidartaoss/brisasensors), sistema fictício de monitoramento da qualidade do ar em ambientes internos. Mantém o inventário e o ciclo de vida dos dispositivos (comissionamento, configuração remota, calibração e descomissionamento) e publica os eventos do ciclo de vida (comissionado, realocado, descomissionado) que alimentam a cópia local do monitoramento do ar.

As fronteiras e decisões do serviço estão no [estudo de caso](https://github.com/sidartaoss/fronteiras-de-microsservicos#7-estudo-de-caso-brisasensors).

## Estado da implementação

- Cadastro de dispositivos com identificador TSID, consulta paginada, atualização, remoção, ativação e desativação, em Spring Boot 4 e Java 21, com H2 local. Porta 8080.
- Enquanto os eventos do ciclo de vida não são publicados, a ativação, a desativação e a remoção chamam o monitoramento do ar por HTTP, com prazos de conexão e de leitura e falhas traduzidas em 502 e 504.
- O cadastro ainda não reúne todos os dados de inventário do estudo de caso (número de série, fabricante, firmware, ambiente, situação e última calibração); eles chegam junto com os eventos do ciclo de vida.

As decisões de código estão no guia [Implementação de Microsserviços](https://github.com/sidartaoss/implementacao-de-microsservicos#7-implementação-de-referência-brisasensors).
