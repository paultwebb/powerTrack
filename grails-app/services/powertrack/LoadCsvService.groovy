package powertrack

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.math.BigDecimal;
import org.grails.plugins.csv.CSVMapReader
import org.apache.commons.lang.RandomStringUtils
class LoadCsvService {
	
	def DataSource
	String filePath = "data"
	String fileName
	Integer deletes
	Integer loads
	Integer errors

    def loadUsages() {
		
		//2014-03-10-00.00.00
		SimpleDateFormat df	= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		//DateFormat df	= new DateFormat("yyyy-MM-dd HH.mm.ss");
		
				//DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
		//String saCode
		ServiceAgreement curServiceAgreement
		loads=0;errors=0
		deletes=PowerUsage.executeUpdate('delete PowerUsage')
		//deletes=ServiceAgreement.executeUpdate('delete ServiceAgreement')
		
		fileName = "UsageData.csv"
		println "Loading ${filePath}/${fileName}"
		PowerUsage curUsage
		def reader = new File("${filePath}/${fileName}").toCsvMapReader()
		def results = [:]
		reader.each { map ->
		//	println "$map"
			curServiceAgreement = ServiceAgreement?.findByAgreementString(map.'Service Agreement')
		//	println "SA is: $curServiceAgreement"
		//	println map.'Service Agreement'
			if (!curServiceAgreement) {
			  println "Adding Service Agreement ${map.'Service Agreement'}"
			  curServiceAgreement = new ServiceAgreement()
			  curServiceAgreement.agreementString = map.'Service Agreement'
			  // Generate a random string for the Code, to be update later
			  String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
			  curServiceAgreement.agreementCode = RandomStringUtils.random(5, charset.toCharArray())
			  curServiceAgreement.description = "Added " + new Date()
			  if (!curServiceAgreement.save()) {
				  println "Error ${map}"
				  println curServiceAgreement.errors
			  } else {
			   //  println "Using SA $curServiceAgreement.agreementString"
			  }
		    }
			curUsage = new PowerUsage()
			//println "Adding Usage"
			
			curUsage.serviceAgreement = curServiceAgreement
			curUsage.startTime = df.parse(map.'Interval Start Time').toTimestamp()
			curUsage.endTime = df.parse(map.'Interval End Time').toTimestamp()
			curUsage.quantity = map.'Quantity'.toBigDecimal()
			curUsage.uom = map.'Unit of Measure'
			if (!curUsage.save()) {
				println "Error ${map}"
				println curUsage.errors
				errors++
			} else {
			    loads ++
			}
	
		}
		println "   ${deletes} deleted. ${loads} loaded. ${errors} errors."
		return ['table':'UsageData','deletes':deletes,'loads':loads,'errors':errors]
		
    }
}
