
```scala
package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File


abstract class FastQC(val version: String) extends Bundle() { fastqc =>

  lazy val fastqcZip = s"fastqc_v${version}.zip"
  lazy val folder = "FastQC"
  lazy val name = "fastqc"
  lazy val binary = new File(fastqc.folder, fastqc.name)

  lazy val downloadZip = cmd("wget")(
    s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/fastqc/${version}/${fastqcZip}"
  )

  lazy val unzip = cmd("unzip")(fastqcZip)

  lazy val makeExecutable = cmd("chmod")("+x", fastqc.binary.getCanonicalPath)

  lazy val linkBinary = cmd("ln")("-s", fastqc.binary.getCanonicalPath, s"/usr/bin/${fastqc.name}")

  def instructions: AnyInstructions = downloadZip -&- unzip -&- makeExecutable -&- linkBinary

  def runFastQC(args: String*): CmdInstructions = cmd("fastqc")(args: _*)

}

```




[main/scala/fastqc.scala]: fastqc.scala.md