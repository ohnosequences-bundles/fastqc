package ohnosequencesbundles.statika

import ohnosequences.statika._, bundles._, instructions._


case object fastqc extends Bundle() {

  def install: Results = {
    // do someting here
    success(s"${bundleName} is installed")
  }

}
