
  	function showtoptips(msg,msgtype){
         if(typeof(msg)!="undefined" && msg != "")
         { 
           document.getElementById("toptips").innerHTML=msg;
         }
         if(msgtype == 'ok'){
        	 $("#toptips").css("background-color","#62d347");
         }else if(msgtype == 'error'){
             $("#toptips").css("background-color","#ef5a5a");
         }else if(msgtype == "warning"){
             $("#toptips").css("background-color","#F7F188");
         }else{
             $("#toptips").css("background-color","#59a3ec");
         }
         var toptips = $("#toptips");
	     var toptipswidth = toptips.width();
	     toptips.css("left", (window.screen.availWidth - toptipswidth) / 2 + "px");
	     toptips.css("top",  get_scrollTop_of_body() + "px");
	     toptips.show();
	     setTimeout(function(){
	     toptips.slideUp(1000);
	     }, 3000);
    }
    
    
    function get_scrollTop_of_body(){ 
        var scrollTop; 
        if(typeof window.pageYOffset != 'undefined'){
            scrollTop = window.pageYOffset; 
        }else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat')        { 
            scrollTop = document.documentElement.scrollTop; 
        }else if(typeof document.body != 'undefined'){ 
            scrollTop = document.body.scrollTop; 
        } 
        return scrollTop; 

    }
    
    (function (){
    	$(document).ready(function(){
    		//如果页面中没有toptips,则添加 
    		if(!document.getElementById("toptips")){
    		var toptipsdiv = "<div style='position: absolute;' id='toptips'></div>";
    		$(document.body).append(toptipsdiv); 
    	}
    });
    $(window).scroll(function(){
    	
       var toptips = $("#toptips");    	
    	toptips.css("top",  get_scrollTop_of_body() + "px");
       });
     
    })();