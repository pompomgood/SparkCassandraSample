 
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