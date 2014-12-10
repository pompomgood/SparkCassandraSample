 
### Preparing example Cassandra schema
Create a simple keyspace and table in Cassandra. Run the following statements in `cqlsh`:

```sql
CREATE KEYSPACE spark_cassandra_test WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
CREATE TABLE spark_cassandra_test.kv(key text PRIMARY KEY, value int);
```
      
Then insert some example data:

```sql
INSERT INTO spark_cassandra_test.kv(key, value) VALUES ('key1', 1);
INSERT INTO spark_cassandra_test.kv(key, value) VALUES ('key2', 2);
```

### Build

```
clean compile assembly:single
```

### Run

```
$SPARK_HOME/bin/spark-submit --verbose --class com.fuhu.server.test.spark.spark.cassandra.App /where/your/jar/spark-cassandra-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```