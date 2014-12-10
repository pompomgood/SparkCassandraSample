package com.fuhu.server.test.spark.spark.cassandra;

import java.io.PrintStream;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraJavaRDD;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

public class App {
	public static void main(String[] args) {
		PrintStream sout = System.out;
		String cassandraServerIP = "192.168.1.83";
		String sparkServer = "spark://192.168.1.83:7077";
		SparkConf conf = new SparkConf();
		conf.setAppName("Spark Cassandra demo");
		conf.set("spark.cassandra.connection.host", cassandraServerIP);
		JavaSparkContext sc = new JavaSparkContext(sparkServer,
				"test", conf);
		Tuple2<String, String>[] confs = conf.getAll();
		for (Tuple2<String, String> t2 : confs) {
			sout.println(t2._1 + ": " + t2._2);
		}
		
		CassandraJavaRDD<CassandraRow> cassandraRdd = javaFunctions(sc).cassandraTable("spark_cassandra_test","kv");
		sout.println("Count: "+ cassandraRdd.count());		
		filterTest(cassandraRdd);
		sc.stop();
		sout.println("Done!");
	}
	
	public static void filterTest(CassandraJavaRDD<CassandraRow> rdd){
		JavaRDD<CassandraRow> javaRdd = rdd.filter(new Function<CassandraRow,Boolean>(){

			public Boolean call(CassandraRow row) throws Exception {
				Integer devType = row.getInt("value");
				return devType.equals(1);
			}
			
		});
		
		System.out.println("Filter count: "+ javaRdd.count());
		
	}
}
