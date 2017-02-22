package org.kevin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class BuilderSbt {
	private static Resource resource = new Resource();

	public static void main(String[] args) {
		try {
			if (args.length <= 0) {
				System.out.println("使用例子: java -jar builderSbt.jar windows true");
				System.out.println("第一个参数系统类型,进支持windows和linux");
				System.out.println("第二个参数是否创建java目录");
				System.exit(0);
			}
			if (args[0] != null) { 
				if (!"windows".equals(args[0]) && !"linux".equals(args[0])) {
					System.out.println("输入错误仅支持:window或linux");
					System.exit(0);
				}
			}
			Runtime runtime = Runtime.getRuntime();
			switch (args[0]) {
			case "windows":
				StringBuilder builder = new StringBuilder();
				builder.append("cmd /k md src\\main\\resources;")
						.append("src\\main\\scala;")
						.append("src\\test\\resources;")
						.append("project;");
				if (args.length > 1 && args[1] != null && "true".equals(args[1])) {
					builder.append("src\\main\\java;");
					builder.append("src\\test\\scala;");
				}
				runtime.exec(builder.toString());
				runtime.exec("cmd /k sbt eclipse");
				break;
			case "linux":
				String mkdir = "mkdir -p src/main/scala";
				runtime.exec(mkdir);
				mkdir = "mkdir -p src/main/resources";
				runtime.exec(mkdir);
				mkdir = "mkdir -p src/test/scala";
				runtime.exec(mkdir);
				mkdir = "mkdir -p src/test/resources";
				runtime.exec(mkdir);
				mkdir = "mkdir project";
				runtime.exec(mkdir);
				if (args.length > 1 && args[1] != null && "true".equals(args[1])) {
					mkdir = "mkdir -p src/test/java";
					runtime.exec(mkdir);
					mkdir = "mkdir -p src/main/java";
					runtime.exec(mkdir);
				}
				runtime.exec("sbt eclipse");
				break;
			}
			writerFile("build.sbt", "");
			writerFile("build.properties", "project/");
			writerFile("plugin.sbt", "project/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writerFile(String fileName, String outPath) throws FileNotFoundException, IOException {
		InputStreamReader reader = new InputStreamReader(resource.getResourceByName(fileName), "utf-8");
		BufferedReader bufferReader = new BufferedReader(reader);
		OutputStream out = new FileOutputStream(outPath + fileName);
		OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = bufferReader.readLine()) != null) {
			buffer.append(line);
			buffer.append("\n");
		}
		writer.write(buffer.toString());
		writer.flush(); 
		reader.close();
		bufferReader.close();
		out.close();
		writer.close();
	}
}
