package powertrack

class LoadCsvController {

	def loadCsvService

	def beforeInterceptor = {
		//log.trace("Executing action $actionName with params $params from $request.locale.country")
	}
	def afterInterceptor = { model ->
		//log.trace("Executed action $actionName which resulted in model: $model")
	}
		
	def index() {
		//redirect(action:'loadCsv')
		//loadCsvService.loadDistricts()
		log.trace("in index")
		redirect(action:'loadCsv')	
			}
	
	def loadUsage() {
		def results = loadCsvService.loadUsages()
		redirect(action:'loadCsv', params:results)
	}
	
	def loadCsv() {
		//log.info('loadCsv action.0')
		render(view:'loadCsv', model:params)
		//log.info('loadCsv action.1')
	}
}
