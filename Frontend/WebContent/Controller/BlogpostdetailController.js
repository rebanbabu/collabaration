/**
 * 
 */app.controller('BlogPostDetailController',function($scope,$location,$routeParams,BlogPostService){
	var id=$routeParams.id
	$scope.showcomments=false
	/*
	 * select * from blogpost where id=44 [BlogPost object]
	 */
	$scope.blogPost=BlogPostService.getBlogPostById(id).then(function(response){
		$scope.blogPost=response.data;
		console.log("response added");
		console.log(response.data)
	},function(response){
		console.log(response.status)
		if(response.status==401)
			$location.path('/login')
	})
	function getBlogComments(){
		BlogPostService.getBlogComments(id).then(function(response){
			$scope.blogComments=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
	$scope.getComments=function(){
		$scope.showcomments=true;
	}
	
	getBlogComments() 
	
	$scope.updateBlogPost=function(){
		console.log($scope.blogPost)
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response){
			console.log(response.status)
			$location.path('/getallblogs')
		},function(response){
			if(response.status==401)
				$location.path('login')
		
		})
	}
	
	$scope.addComment=function(){
		$scope.blogComment.blogpost=$scope.blogPost
		/*
		 * blogComment : {blogPost:{id:'',blogTitle:'',postedBy:'',postedOn:''},commentText:'Good'}
		 */
		console.log($scope.blogComment)
		BlogPostService.addComment($scope.blogComment).then(function(response){
			$scope.blogComment.commentText=''
				getBlogComments()
			console.log(response.data)
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			$location.path('/getblogpostbyid/'+id)
		})
	}
})
	