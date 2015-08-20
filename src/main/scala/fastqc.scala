package ohnosequencesbundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File


abstract class Fastqc(val version: String) extends Bundle() {

  val instructions: AnyInstructions = {
    val fastqc = "FastQC/fastqc"
    val fastqcZip = s"fastqc_v${version}.zip"

    cmd("wget")(
      s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/fastqc/${version}/${fastqcZip}",
      "-O", fastqcZip
    ) -&-
    cmd("unzip")(fastqcZip) -&-
    cmd("chmod")("+x", fastqc) -&-
    cmd("ln")("-s", fastqc, "/usr/bin/fastqc") -&-
    say(s"${bundleName} is installed")
  }

  def fastqc(args: String*): CmdInstructions = cmd("fastqc")(args: _*)

}

case object testFastqc extends Fastqc(version = "")

case object fastqcCompat extends Compatible(
  amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
  testFastqc,
  generated.metadata.Bundles
)
