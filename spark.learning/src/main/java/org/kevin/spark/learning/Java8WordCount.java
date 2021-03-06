/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kevin.spark.learning;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public final class Java8WordCount {
	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			System.err.println("Usage: Java8WordCount <file>");
			System.exit(1);
		}
		SparkConf sparkConf = new SparkConf().setAppName("Java8WordCount");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		JavaRDD<String> lines = ctx.textFile(args[0], 1);

		JavaRDD<String> words = lines.flatMap((FlatMapFunction<String, String>) s -> {
			return Arrays.asList(s.split(" "));
		});

		JavaPairRDD<String, Integer> ones = words.mapToPair((PairFunction<String, String, Integer>) s -> {
			return new Tuple2<String, Integer>(s, 1);
		});

		JavaPairRDD<String, Integer> counts = ones.reduceByKey((Function2<Integer, Integer, Integer>) (x, y) -> {
				return x + y;
		});
		
//		List<Tuple2<String, Integer>> output = counts.collect();
//		for (Tuple2<?, ?> tuple : output) {
//			System.out.println(tuple._1() + ": " + tuple._2());
//		}
		counts.saveAsTextFile(args[1]);
		ctx.stop();
	}
}
