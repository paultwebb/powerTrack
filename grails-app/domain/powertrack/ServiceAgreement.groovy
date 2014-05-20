package powertrack

class ServiceAgreement {

	//PROPERTIES
	String agreementCode
	String agreementString
	String description
	
	// RELATIONSHIP PROPERTIES
	static hasMany = [usages:PowerUsage]
	
    static constraints = {
		agreementCode (blank:false, unique:true, maxSize:5)
		description(blank:false, maxSize:50 )
		agreementString (blank:false, unique:true, maxSize:200)
    }

    // MAPPING
	static mapping = {
	}
	
	// METHODS
	String toString() {
		//return "County: code=${code}, county=${county}, countySeat=${countySeat}"
		return agreementCode
	}
}
