/**
 * 
 */app.factory('BlogpostService',function($http){
	var blogpostService={};
	
	blogpostService.addBlogPost=function(blogPost){
		return $http.post("http://localhost:8082/CollaborationBackend/saveblogpost",blogpost)
	}
	
	blogpostService.getBlogPostsWaitingForApproval=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/getblogposts/"+0)
	}
	
	blogpostService.getBlogPostsApproved=function(){
		return $http.get("http://localhost:8082/CollaborationBackend/getblogposts/"+1)
	}
	
	blogpostService.getBlogPostById=function(id){
		return $http.get("http://localhost:8082/CollaborationBackend/getblogpostbyid/"+id)
	}
	
	blogpostService.updateBlogPost=function(blogpost){
		return $http.put("http://localhost:8082/CollaborationBackend/updateblogpost",blogpost)
	}
	blogpostService.addComment=function(blogcomment){
		return $http.post("http://localhost:8082/CollaborationBackend/addblogcomment",blogcomment)
	}
	blogpostService.getBlogComments=function(blogPostId){
		return $http.get("http://localhost:8082/CollaborationBackend/getblogcomments/"+blogPostId)
	}
	return blogpostService;
})

