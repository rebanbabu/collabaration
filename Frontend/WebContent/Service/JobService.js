/**
 * 
 */ app.factory('JobService',function($http){
	var jobService={}
	
	 jobService.saveJob=function(job){
		return $http.post("http://localhost:8082/CollaborationBackend/savejob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/getallJobs")
	}
	
	jobService.getJobDetails=function(job){
		return $http.get("http://localhost:8082/CollaborationBackend/getjobbyid/"+job)
	}
	
	
	return jobService;
})