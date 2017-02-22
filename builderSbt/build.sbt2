//项目名称
name := "sbt.prjoect.name"

//组织名称
organization := "org.kevin"

//版本号
version := "1.0.0"

//使用的Scala版本号
scalaVersion := "2.10.6"

//添加依赖 groupID % artifactID % revision
libraryDependencies ++= Seq(
                            "org.apache.spark" % "spark-core_2.10" % "1.6.2",
                            "org.codehaus.jettison" % "jettison" % "1.3.8"
							)
                           
//添加测试代码编译或者运行期间使用的依赖
//libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "1.8" % "test")

resolvers ++= Seq(
	"nexus repository" at "http://120.24.183.163:8081/nexus/content/repositories/central/",
	"central maven" at "http://central.maven.org/maven2/",
	"jboss repository" at "http://repository.jboss.org/nexus/content"
)
