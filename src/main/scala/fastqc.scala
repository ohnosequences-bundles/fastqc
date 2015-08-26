package ohnosequencesbundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File


abstract class Fastqc(val version: String) extends Bundle() {

  def instructions: AnyInstructions = {
    val fastqc = "FastQC/fastqc"
    val fastqcZip = s"fastqc_v${version}.zip"

    cmd("wget")(
      s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/fastqc/${version}/${fastqcZip}",
      "-O", fastqcZip
    ) -&-
    cmd("unzip")(fastqcZip) -&-
    cmd("chmod")("+x", fastqc) -&-
    cmd("ln")("-s", new File(fastqc).getCanonicalPath, "/usr/bin/fastqc") -&-
    say(s"${bundleName} is installed")
  }

  def fastqc(args: String*): CmdInstructions = cmd("fastqc")(args: _*)

}
