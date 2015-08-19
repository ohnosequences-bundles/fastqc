package ohnosequencesbundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File


abstract class Fastqc(val version: String) extends Bundle() {

  val usrbin = "/usr/bin/"
  val fastqcDir = "FastQC"

  val instructions: AnyInstructions = {
    cmd("wget")(
      s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/fastqc/0${version}/fastqc_v${version}.zip",
      "-O", s"fastqc_v${version}.zip"
    ) -&-
    cmd("unzip")(s"fastqc_v${version}.zip") -&-
    cmd("chmod")(
      "+x", new File(s"${fastqcDir}/fastqc").getAbsolutePath
    ) -&-
    cmd("ln")(
      "-s", new File(s"${fastqcDir}/fastqc").getAbsolutePath, s"${usrbin}/fastqc"
    ) -&-
    say(s"${bundleName} is installed")
  }

}
