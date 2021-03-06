# Simulação de mapeamento de um Drone

## Descrição
<p>
Coleta os dados de temperatura e umidade que são capturadas por drones atráves de uma fila Kafka e alerta, por email, caso essas condições sejam irregulares.
</p>

## Iniciando
<p>Existe a necessidade de se ter configurado docker, java e uma IDE com suporte java</p>

- [java](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
- [Intellij](https://www.jetbrains.com/pt-br/idea/)
- [docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/install/)


<p>1º passo - Faça um clone do projeto</p>
<p>2º passo - Na pasta do projeto, rode o comando descrito abaixo para criação dos containers do Kafka e Zookeeper</p>

```bash
~ docker-compose up -d
```
<p>Rode o comando <i>docker ps</i> ou <i>docker container ls</i> para ver se os containers estão ativos</p>

```bash
~ docker container ls
CONTAINER ID   IMAGE                              COMMAND                  CREATED       STATUS       PORTS                          NAMES
ce33427b578f   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   4 hours ago   Up 4 hours   0.0.0.0:9092->9092/tcp         kafka-producer-front-system_kafka_1
75b82db8bb3f   confluentinc/cp-zookeeper:latest   "/etc/confluent/dock…"   4 hours ago   Up 4 hours   2181/tcp, 2888/tcp, 3888/tcp   kafka-producer-front-system_zookeeper_1
```
<p>3º passo - Rode o comando abaixo para criar o tópico do Kafka para qual as mensagens serão enviadas</p>

```bash
~ docker-compose exec kafka kafka-topics --create --topic mapeamento-drone --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181 
```

<p>4º passo - Realize o install do maven</p>

```bash
~ mvn install
```

<p>5º passo - Execute a classe Application</p>  