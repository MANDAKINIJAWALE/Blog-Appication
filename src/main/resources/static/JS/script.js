
 

  
    $(document).ready(function()
  		  {
  	         $("#searchForm").on("submit",function(e)
  	        		 {
  	        	       e.preventDefault();  /* important: prevent form from submitting normally */
  	        	        $.ajax({
  	        	        	type:"GET",
  	        	        	url:"/ajaxSearch",
  	        	        	data:
  	        	        		{
  	        	        		   search:$("#searchInput").val().trim()
  	        	              
  	        	        		},
  	        	        	success:function(result)
  	        	        	{
  	        	        		$("#allSearch").hide();
  	        	        		$("#searchContent").html(result);
  	        	        	}
  	        	        	
  	        	        	
  	        	        });
  	        	        
  	        		 });
  	  
  		  });
