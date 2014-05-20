package powertrack

class PowerUsage {

	//PROPERTIES
	java.sql.Timestamp startTime
	java.sql.Timestamp endTime
	BigDecimal quantity
	String uom
	
	// RELATIONSHIP PROPERTIES
	static belongsTo = [serviceAgreement:ServiceAgreement]
	
    static constraints = {
		serviceAgreement (blank:false)
		startTime (blank:false)
		endTime (blank:false)
		quantity (blank:false)
		uom (blank:false)
    }

    // MAPPING
	static mapping = {
	}
	
	// METHODS
	String toString() {
		//return "County: code=${code}, county=${county}, countySeat=${countySeat}"
		return quantity
	}
}
