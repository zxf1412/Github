(function(){
	window.doImgCircle={};
	doImgCircle={
		width:{},
		height:{},
		imgArr:{},
		autoCircle:{},
		initIndex:0,
		init:function(param){
			this.width = param.width;
			this.height = param.height;
			this.imgArr = param.imgArr;

			if(param.autoCircle == undefined){
				this.autoCircle = true;
			}else{
				this.autoCircle = param.autoCircle;
			}

			var wrap = $("div[data-type='imgCircle']");
			wrap.css({
				width: this.width,
				height: this.height
			});
			var imgContainer = $(".imgContainer");
			var imgControl = $(".imgControl");
			imgContainer.empty();
			for (var i = 0; i < this.imgArr.length; i++) {

				//生成图片ul列表
				var newLi = $("<li>");
				newLi.attr("imageIndex",i);
				var newImg = $("<img src='"+this.imgArr[i]+"'>");
				newLi.append(newImg);
				imgContainer.append(newLi);

				//生成控制图片圆点的ul
				var newConLi = $("<li>");
				newConLi.attr("imageIndex",i);
				newConLi.append(i+1);
				imgControl.append(newConLi);

				if(i>0){
					newLi.hide();
					newConLi.addClass('imgControlNormal');
				}else{
					newConLi.addClass('imgControlSelect');
				}
			};
		},
		start:function(){
			
			var imgIndex = this.initIndex;
			var timerInterVal = null;
			if(this.autoCircle){
				var imgs = this.imgArr;
				timerInterVal = setInterval(this.doAuto, 3000);
				imgIndex++;
			}

			var conLis = $(".imgControl li");

			var clickIndex = 0;
			conLis.click(function(){
				clearInterval(timerInterVal);
				clickIndex = $(this).attr("imageindex");
				var clickImg = $(".imgContainer li[imageindex='"+(clickIndex)+"']");
				var curImg = $(".imgContainer li");

				curImg.fadeOut();
				clickImg.fadeIn(1500);

				var curCon = $(".imgControl li");
				var clickCon = $(".imgControl li[imageindex='"+(clickIndex)+"']");
				curCon.attr("class","imgControlNormal");
				clickCon.attr("class","imgControlSelect");
				setInterval(timerInterVal);
			});
		},
		doAuto:function(){
			
			if(this.initIndex < this.imgArr.length - 1){
				var curImg = $(".imgContainer li");
				var curCon = $(".imgControl li[imageindex='"+(this.initIndex)+"']");
				curCon.attr("class","imgControlNormal");
				
				(this.initIndex)++;
				var nextImg = $(".imgContainer li[imageindex='"+(this.initIndex)+"']");
				var nextCon = $(".imgControl li[imageindex='"+(this.initIndex)+"']");
				nextCon.attr("class","imgControlSelect");
				
				curImg.fadeOut();
				nextImg.fadeIn(1500);
			}else{
				this.initIndex = 0;
				var lastCon = $(".imgControl li[imageindex='"+(imgs.length - 1)+"']");
				var firstCon = $(".imgControl li[imageindex='0']");
				firstCon.attr("class","imgControlSelect");
				lastCon.attr("class","imgControlNormal");

				var lastImg = $(".imgContainer li[imageindex='"+(imgs.length - 1)+"']");
				lastImg.fadeOut();
				var firstImg = $(".imgContainer li[imageindex='0']");
				firstImg.fadeIn(2500);
			}
		}


	}

})();