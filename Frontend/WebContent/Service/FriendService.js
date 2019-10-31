/**
 * 
 */app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.listOfSuggestedUsers=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/getsuggestedusers")
	}
	
	friendService.friendRequest=function(toId){
		return $http.post("http://localhost:8082/CollaborationBackend/friendrequest/"+toId)
	}
	
	friendService.listOfPendingRequests=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/getpendingrequests")
	}
	
	friendService.getUserDetails=function(fromId){
		return $http.get("http://localhost:8082/CollaborationBackend/getuserdetails/"+fromId)
	}
	friendService.updatePendingRequest=function(pendingRequest){
		return $http.put("http://localhost:8082/CollaborationBackend/updatependingrequest",pendingRequest)
	}
	friendService.listOfFriends=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/listoffriends")
	}
	return friendService;
})