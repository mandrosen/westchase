
		function findEmbeddedImages(var elementId) {
			var emailTxt = document.getElementById(elementId);
			if (emailTxt) {
				emailTxt = emailTxt.value;
				if (emailTxt != null && emailTxt != "") {


					var regimg = /<img[^>]*>/i;
					var srcImg1 = /src="[^"]*"/i;
					var srcImg2 = /src='[^']*'/i;
					var match = emailTxt.match(regimg);


					if (match != null && match.length > 0 ){
						for (var m = 0; m < match.length; m++) {
							imgText = match[m]; 
							if (imgText != null && imgText.length > 0) { 
								var src1 = imgText.match(srcImg1);
								var src2 = imgText.match(srcImg2);

								if (src1 != null){
									var src = src1[0];
								} else if (src2 != null) {
									var src = src2[0];
									src = src.substring(5, src.length - 1);
								}
								
							}
						}
					}	 


					
				}
			}
			return true;
		}