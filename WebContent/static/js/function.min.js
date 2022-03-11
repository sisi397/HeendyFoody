/**
* --------------------------------
* Function JS
* --------------------------------
*/


var scrollt = 0,
	popDefaultZindex = 100,
	popLastZindex = 100,
    cTimeInterval = 0;	//인증번호 유효시간

var fn = (function() {
    "use strict";

    return {
		//공통
		common : function(){
            fn.scrollControl();

			//우클릭 드래그 선택 방지
			$(document).bind("contextmenu", function(){return false;});
			$(document).bind("dragstart", function(){return false;});
			$(document).bind("selectstart", function(){return false;});

			//AOS load
            var ua = navigator.userAgent.toLowerCase();
            //ie10 AOS not load
            if ( ua.indexOf("msie 10.0") <= -1 ){
                AOS.init({
                    disableMutationObserver: true,
                    offset: 100,
                });
            }

			//link #
			$('a[href="#"]').click(function(e){
				e.preventDefault();
			});

			//input delete button
			fn.inputDeleteInit();
			fn.inputDelete();

			//select
			fn.select(".select");
			fn.selectInit();

			//fileUpload
			fn.fileUpload();
			fn.fileUploadDocument();

			//inputFileUpload
			fn.inputFileUpload();

			//datePicker
			fn.datePicker();

			//grade-star
			fn.gradeStar();

			//탭메뉴
			$(document).on('click', '.tab-menu a', function(e){
				fn.tabMenu(e, this);
			});

			//토글영역
			fn.toggleInit();
			fn.toggleClick();

			//말줄임
			fn.ellipsis(".ellipsis");

			//reviewToggle
			fn.reviewToggle();

			//header init
			fn.headerInit();

			//매거진 템플릿 - mbannerboxswiper
			fn.mbannerboxswiper();

			//매거진 템플릿 - mbannerimgswiper
			fn.mbannerimgswiper();

			//매거진 템플릿 - mbannerprocessswiper
			fn.mbannerprocessswiper();

			//매거진 템플릿 - mtab
			fn.mtab();

            //footer familysite
            $(".familysite .btn-site").click(function(){
                $(this).toggleClass("active");
            })
		},

		//scrollControl
		scrollControl : function(){
			//FOOTER TOP 버튼
			var $btnTop = $("#footer .btn-top"),
				initBtnTopValue = $btnTop.css("bottom");
			$(window).on("load scroll", function(){
				var footerOffset = $(document).height() - $("#footer").offset().top,
					scrollOffset = $(document).height() - $(window).height() - $(window).scrollTop() + $btnTop.outerHeight()*2,
					scrollT = $(this).scrollTop();
				if (footerOffset >= scrollOffset) {
					$btnTop.css({"bottom": footerOffset - scrollOffset + $btnTop.outerHeight()});
				} else {
					$btnTop.css({"bottom": initBtnTopValue});
				}

				if (scrollT > 0) {
					$btnTop.addClass("scroll");
				} else {
					$btnTop.removeClass("scroll");
				}
			});
			$(window).resize(function(){
				var rValue = ($(window).width() - $("#footer .inner").width())/2 - 95;
				$btnTop.css("right", rValue +"px");
			}).trigger("resize");

			$btnTop.click(function(){
				$("html, body").animate({"scrollTop":"0"});
			});
        },

		//scroll 고정
		scroll : function(state, top){
			var scrolltNow = top;
			switch (state) {
				case "disabled" :
					$("body").css("top", -scrolltNow).addClass("fixed");
				break;
				case "enabled" :
					$("body").css("top", 0).removeClass("fixed");
                    scrollt = 0;
					$(window).scrollTop(scrolltNow);
				break;
			}
		},

		//팝업 열기
		popupOpen : function(obj){
			if(typeof obj === "object") {
				obj = obj.attributes.href.value;
			}
			var $obj = $(obj);

			$("body").addClass("pop-open");

			var isPopOpenCount = 0;
			//현재 열린 팝업 체크
			$(".popup").each(function(i){
				if ($(this).hasClass("open") && !$(this).hasClass("below")) {
					isPopOpenCount++;
					$(this).addClass("below");
					//$(this).animate({"backgroundColor":"rgba(0, 0, 0, 0)"}, 0);
				}
				if (i == ($(".popup").length - 1)){
					popLastZindex = popLastZindex + isPopOpenCount;
				}
			});

			$obj.css({"top":"0", "zIndex":popLastZindex}).animate({"opacity":"1"}, 200).addClass("open");
			if($(obj).find(".inner").height() > $(window).height()){//팝업 높이값이 화면보다 클 경우
				$("body").css("overflow-y", "hidden");
				$obj.css("overflow-y", "scroll");
				$obj.find(".inner").css({"top":"20px"});
			} else {
				$obj.find(".inner").css({"top":+(Math.floor(($(window).height()-$obj.find(".inner").outerHeight())/2))+"px"});
			}
            if ( $obj.hasClass("popcart") ){
                $obj.addClass("active");
                setTimeout(function(){
                    $obj.removeClass("active");
                    fn.popupCloseId($obj);
                }, 1000);
            }
            if ( $obj.hasClass("popshippingmulti") ){//여러배송지로 보내기 팝업 높이값 상관없이 팝업 내부 scroll 추가
				$("body").css("overflow-y", "hidden");
				$obj.css("overflow-y", "scroll");
            }

			if ( !$("body").hasClass("fixed") ){
				scrollt = $(window).scrollTop();
				if($("*[data-aos]").length == 0){//data-aos 사용하지 않은 경우
					fn.scroll("disabled", scrollt);
				}else {//data-aos 사용한 경우
					var scrollbar = $(window).outerWidth();
					$("body").css({"overflow-y":"hidden", "width":scrollbar});
				}
			}
			fn.popupScript(obj);
		},

		//팝업 닫기
		popupClose : function(){
			$(".popup").each(function(){
				var $obj = $(this);

				popLastZindex = popDefaultZindex;
				$obj.animate({"opacity":"0"}, 200, function(){
					$obj.css({"top":"100%", "zIndex":popLastZindex}).removeClass("open").removeClass("below");
					$("body").css("overflow-y", "scroll").removeClass("pop-open");
				});
			});
			if($("*[data-aos]").length == 0){//data-aos 사용하지 않은 경우
				fn.scroll("enabled", scrollt);
			} else {//data-aos 사용한 경우
				$("body").css({"overflow-y":"scroll", "width":"auto"});
			}
		},

		//팝업 닫기(Id지정)
		popupCloseId : function(obj){
			var $obj = $(obj),
				isPopOpenCount = 0;

			popLastZindex--;
			$(".popup").each(function(i){
				//닫힐 팝업 z-index 한단계 전 팝업 체크
				if ( $(this).css("z-index") == popLastZindex ){
					$(this).removeClass("below");
					//$(this).animate({"backgroundColor":"rgba(0, 0, 0, 0)"}, 0);
				}

				if ( $(this).hasClass("open") ){
					isPopOpenCount++;
				}

				if (i == ($(".popup").length - 1)){
					$obj.animate({"opacity":"0"}, 200, function(){
						$obj.css({"top":"100%"}).removeClass("open");
					});

					if (isPopOpenCount == 1){
						popLastZindex = popDefaultZindex;
						$("body").css("overflow-y", "scroll").removeClass("pop-open");

						if($("*[data-aos]").length == 0){//data-aos 사용하지 않은 경우
							fn.scroll("enabled", scrollt);
						} else {//data-aos 사용한 경우
							$("body").css({"overflow-y":"scroll", "width":"auto"});
						}
					}
				}
			});
		},

        //윈도우팝업창 열기
        popupWinOpen : function(theURL, popWinName, popWinWidth, popWinHeight){
            var popupX = (window.screen.width / 2) - (popWinWidth / 2);
            var popupY= (window.screen.height / 2) - (popWinHeight / 2);
            window.open(theURL, popWinName, "scrollbars=yes, width="+ popWinWidth + ", height="+ popWinHeight + ", left="+ popupX + ", top="+ popupY + ", screenX="+ popupX + ", screenY= "+ popupY);
		},

        //윈도우팝업창 scroll 비노출
        popupWinScroll : function(){
			if ($("#wrap").hasClass("winpopup")) {
				if( $(".popdeliverypic").length) {
					$("body").css("overflow-y","hidden");
				}
			}
		},

		//메인 팝업 열기
		popupOpenMain : function(obj){
			var $obj = $(obj);
			$obj.css({"position":"absolute","top":($("#header").outerHeight() + 10) +"px"}).animate({"opacity":"1"}, 200);
		},

		//메인 팝업 닫기
		popupClosenMain : function(obj){
			var $obj = $(obj);
			$obj.animate({"opacity":"0"}, 200, function(){
				$obj.css({"position":"fixed","top":$(document).width()+"px"});
			});
		},

		//popupScript
        popupScript : function(obj){
			var $obj = $(obj);
			//scrollbar check : 주문하기 쿠폰선택팝업, 주문하기 H.Bonus 팝업, 회원가입 이용약관 팝업, 약관 팝업
			if($obj.hasClass("popcouponchoice") || $obj.hasClass("pophbonusinfo") || $obj.hasClass("poppolicyjoin") || $obj.hasClass("poppolicy")){
				if (fn.hasScrollBar($obj.find(".scrollbox"))) {
					$obj.find(".scrollbox").removeClass("off").addClass("active");
				} else {
					$obj.find(".scrollbox").removeClass("active").addClass("off");
				}
			}

			//select
			fn.select($obj.find(".select"));
		},

		//탭메뉴
        tabMenu : function(e, obj){
            var $tabMenu = $(obj),
                objHref = obj.attributes.href.value;
            if(objHref.indexOf("#") != -1){
                e.preventDefault();

                var $tabContents = $(objHref)

                $tabMenu.siblings().removeClass("active");
    			$tabMenu.addClass("active");

    			$tabContents.siblings(".tab-contents").removeClass("active");
    			$tabContents.addClass("active");
            }
        },

		//input delete button Init
		inputDeleteInit : function(){
			$(".form-entry > input").each(function(){
				if ($(this).parent().hasClass("exist") && $(this).val()!="") {
					$(this).parent().find(".btn-del").show();
					$(this).parent().find(".txt-unit").show();
				}
				$(this).parent().find(".btn-del").attr("tabindex", -1);//Tab 클릭시 삭제 버튼 포커스 X
			});
		},

		//input delete button
		inputDelete : function(){
			var isDel = false;

			$(document).on("focusin", ".form-entry > input", function(){
				isDel = false;
				if ($(this).parent().hasClass("exist") && !$(this).parent().hasClass("search")) {
					$(this).parent().find(".txt-unit").show();
					$(this).parent().find(".btn-del").show();
				} else {
					if ($(this).val()!="") {
						$(this).parent().find(".btn-del").show();
					} else {
						$(this).parent().find(".btn-del").hide();
					}
				}
			});

			$(document).on("keyup", ".form-entry > input", function(){
				isDel = false;
				if ($(this).parent().hasClass("exist") && !$(this).parent().hasClass("search")) {
					$(this).parent().find(".txt-unit").show();
					$(this).parent().find(".btn-del").show();
				} else {
					if ($(this).val()!="") {
						$(this).parent().find(".btn-del").show();
					} else {
						$(this).parent().find(".btn-del").hide();
					}
				}
			});

			$(document).on("mousedown", ".form-entry > .btn-del", function(){
				$(this).parent().find("input").val("").focus();
				$(this).parent().removeClass("error");
				$(this).parent().removeClass("positive");
				$(this).parent().next(".inputmsg").remove();
				if ($(this).parent().hasClass("exist") && !$(this).parent().hasClass("search")) {
					$(this).parent().find(".txt-unit").show();
					$(this).show();
				} else {
					$(this).hide();
				}
				isDel = true;
			});

			$(document).on("focusout", ".form-entry > input", function(){
				var _this = $(this);
				if ($(this).parent().hasClass("exist")) {
					if ($(this).val()!="") {
						$(this).parent().find(".txt-unit").show();
						$(this).parent().find(".btn-del").show();
					} else {
						$(this).parent().find(".txt-unit").hide();
						$(this).parent().find(".btn-del").hide();
					}
				} else {
					$(this).parent().find(".btn-del").hide();
				}

				//.btn-del mousedown > input focusout > input focus 호출
				if (isDel) {
					var focusoutSet = setTimeout(function(){
						_this.focus();
						clearTimeout(focusoutSet);
					},30);//ie delay
				}
				isDel = false;
			});
		},

		//input delete button active
		inputDeleteActive : function(obj){
			var obj = $(obj);
			if(obj.val()!=""){
				obj.parent().find(".btn-del").show();
				obj.parent().find(".txt-unit").show();
			}
		},

		//select
		select : function(obj){
			var obj = $(obj);
			obj.each(function(i, select){
				if ( !$(this).find("div").hasClass("dropdown") ){
					if ( $(this).find("select").is(":disabled") ){
						$(this).addClass("disabled");
					}
					$(this).append('<div class="dropdown" tabindex="0"><span class="current"></span><div class="list"><ul></ul></div></div>');
					var dropdown = $(this).find(".dropdown");
					var options = $(this).find("select").find("option");
					var selected = $(this).find("select").find("option:selected");
					//var disabled = $(this).find("select").find("option:disabled");
					dropdown.find(".current").html(selected.text());
					options.each(function(j, o){
						var btn = $(o).data("btn") || "";//재입고 알림버튼
						var stock = $(o).data("stock") || "";//남은 수량
						var price = $(o).data("price") || "";//가격

						if ( $(o).attr("class") == "select-ti" ){
							dropdown.find("ul").append('<li class="option select-ti" data-value="' + $(o).val() + '"><span>' + $(o).text() + '</span></li>');
						} else if ( btn ){
							dropdown.find("ul").append('<li class="option soldout' + ($(o).is(':selected') ? ' selected' :'')  + ($(o).is(':disabled') ? ' disabled' :'') + '" data-value="' + $(o).val() + '"><span>' + $(o).text() + '</span>' + btn + '</li>');
						} else {
							if ( stock && price ){
								dropdown.find("ul").append('<li class="option pro' + ($(o).is(':selected') ? ' selected' :'')  + ($(o).is(':disabled') ? ' disabled' :'') + '" data-value="' + $(o).val() + '"><span>' + $(o).text() + '</span>' + price + '' + stock + '</li>');
							} else if ( price ){
								dropdown.find("ul").append('<li class="option pro' + ($(o).is(':selected') ? ' selected' :'')  + ($(o).is(':disabled') ? ' disabled' :'') + '" data-value="' + $(o).val() + '"><span>' + $(o).text() + '</span>' + price +'</li>');
							} else {
								dropdown.find("ul").append('<li class="option' + ($(o).is(':selected') ? ' selected' :'')  + ($(o).is(':disabled') ? ' disabled' :'') + '" data-value="' + $(o).val() + '"><span>' + $(o).text() + '</span></li>');
							}
						}
					});
				}
			});
		},

		//select init
		selectInit : function(){
			$("select").change(function(){
				$(this).addClass("active");
			});

			//입점신청 판매자 가입하기 : 이메일
			$("select").each(function(){
				if ($(this).parent().parent().hasClass("select-domain")) {
					if ( $(this).find("option:selected").text() == "직접입력" ){
						$(this).parent().parent().addClass("write");
					}

					$(this).change(function(){
						if ( $(this).find("option:selected").text() == "직접입력" ){
							$(this).parent().parent().addClass("write");
						} else {
							$(this).parent().parent().find(".inp-domain").val($(this).find("option:selected").text().replace(/\s/g, ""));
							$(this).parent().parent().removeClass("write");
						}
					});
				}
			});

			//Open/close
			$(document).on("click", ".dropdown", function(event){
				$(".dropdown").not($(this)).removeClass("open");
				$(this).toggleClass("open");
				if ($(this).hasClass("open")){
					$(this).find(".option").attr("tabindex", 0);
					$(this).find(".selected").focus();
				}else{
					$(this).find(".option").removeAttr("tabindex");
					$(this).focus();
				}
			});
			//Close when clicking outside
			$(document).on("click", function(event){
				if ($(event.target).closest(".dropdown").length === 0){
					$(".dropdown").removeClass("open");
					$(".dropdown .option").removeAttr("tabindex");
				}
				event.stopPropagation();
			});
			//Option click
			$(document).on("click", ".dropdown .option", function(event){
				if ( !$(this).hasClass("disabled") ){
					$(this).closest(".list").find(".selected").removeClass("selected");
					$(this).addClass("selected");
					var text = $(this).data("display-text") || $(this).html();
					if ( text == "<span>선택해주세요.</span>" ){
						$(this).closest(".dropdown").removeClass("active");
					}else{
						$(this).closest(".dropdown").addClass("active");
					}
					$(this).closest(".dropdown").find(".current").html(text);
					$(this).closest(".dropdown").prev("select").val($(this).data("value")).trigger("change");

					//입점신청 판매자 가입하기 : 이메일
					var $this = $(this),
						txt = $this.text();
					if ($this.closest(".select-domain").length) {
						if ( txt == "직접입력" ){
							setTimeout(function(){
								$this.closest(".select-domain").find(".inp-domain").val("").focus();
							},400);
						}
					}
				}
			});
			//Keyboard events
			$(document).on("keydown", ".dropdown", function(event){
				var focused_option = $($(this).find(".list .option:focus")[0] || $(this).find(".list .option.selected")[0]);
				//Space or Enter
				if (event.keyCode == 32 || event.keyCode == 13){
					if ($(this).hasClass("open")){
						focused_option.trigger("click");
					}else{
						$(this).trigger("click");
					}
					return false;
				//Down
				}else if (event.keyCode == 40){
					if (!$(this).hasClass("open")){
						$(this).trigger("click");
					} else{
						focused_option.next().focus();
					}
					return false;
				//Up
				}else if (event.keyCode == 38){
					if (!$(this).hasClass("open")){
						$(this).trigger("click");
					}else{
						var focused_option = $($(this).find(".list .option:focus")[0] || $(this).find(".list .option.selected")[0]);
						focused_option.prev().focus();
					}
					return false;
				//Esc
				}else if (event.keyCode == 27){
					if ($(this).hasClass("open")){
						$(this).trigger("click");
					}
					return false;
				}
			});
		},

		//fileimgResize
		fileimgResize : function(){
			$(".upload-file img").each(function(){
				var parent = $(this).closest('.upload-file'),
					parentarea = $(this).closest('.form-file'),
					box_width = parent.width(),
					img_width = $(this).width(),
					img_height = $(this).height();
	
				if ( img_width > img_height ){
					parent.addClass("vertical");
	
					if(parentarea.hasClass("big")){
						if(box_width <= img_width){
							parent.addClass('vertical');
						} else {
							parent.removeClass('vertical');
						}
					}
				}
			});
		},

		//fileUpload
		fileUpload : function(){
			//file upload
			$(document).on('change', '.upload-file .upload-hidden', function(){
				var $this = $(this),
					parent = $(this).parent().parent();
				parent.children('.upload-display').remove();
				parent.removeClass("vertical");

				if (!$(this)[0].files[0].type.match(/image\//)) return;
				var reader = new FileReader();
				reader.onload = function(e){
					var src = e.target.result;
					parent.append('<div class="upload-display"><img src="'+src+'" class="upload-thumb"></div>');
					var im = new Image();
					im.src = src;
					im.onload = function(){
						if ( im.width > im.height ){
							parent.addClass("vertical");
						}
					}
				}
				reader.readAsDataURL($(this)[0].files[0]);
			});
			//file upload delete
			$(document).on('click', '.upload-file .upload-display', function(){
				$(this).parent().find('.upload-hidden').val("");
				$(this).remove();
			});
		},

		//fileUploadDocument
		fileUploadDocument : function(){
			//file upload document
			$(document).on('change', '.upload-doc .upload-hidden', function(){
				var $this = $(this),
					name = $this[0].files[0].name,
					extension = name.substring(name.lastIndexOf('.'), name.length).toLowerCase();

				$this.closest('.form-file').find('.upload-display').addClass('active');
				$this.closest('.form-file').find('.upload-display').html('<span class="name">'+name.slice(0, name.length - extension.length)+'</span><span class="extension">'+extension+'</span>');
			});
			//file upload delete document
			$(document).on('click', '.upload-doc .upload-display', function(){
				if ($(this).hasClass('active')){
					$(this).parent().find('.upload-hidden').val("");
					$(this).removeClass('active');
					$(this).find('span').remove();
					$(this).html('<span>파일 첨부해주세요.</span>');
				}
			});
		},

		//inputFileUpload
		inputFileUpload : function(){
			$(document).on('change', '.file-box .upload-hidden', function(){
				if(window.FileReader){
					var filename = $(this)[0].files[0].name;
				}else{
					var filename = $(this).val().split('/').pop().split('\\').pop();
				}
				$(this).closest('.btn').siblings('.upload-name').val(filename);
				$(this).closest('.btn').siblings(".btn-del").show();
			});
		},

		//addSelect
		addSelect : function(obj, data, type){
			$(obj).html(data);
			if ( type == "type2" ){
				$(obj).addClass("select type2");
				//fn.select(obj);?
			} else {
				$(obj).addClass("select");
				fn.select(obj);
			}
		},

		//removeSelect
		removeSelect : function(obj){
			$(obj).remove();
		},

		//changeSelect
		changeSelect : function(obj, idx){
			$(obj+" option:eq("+idx+")").attr("selected", "selected");
			$(obj+" .dropdown ul li").removeClass("selected");
			$(obj+" .dropdown ul li").eq(idx).addClass("selected");
			$(obj+" .dropdown .current").html($(obj+" .dropdown ul li").eq(idx).text());
        },

		//disabledSelect
		disabledSelect : function(obj, flag){
			if ( flag != 'off' ){
				$(obj).addClass("disabled");
				$(obj).find("select").attr("disabled", "true");
			}else{
				$(obj).removeClass("disabled");
				$(obj).find("select").removeAttr("disabled");
			}
		},

        //datepicker
        datePicker: function(){
			$('.datepicker input').datepicker({
				yearSuffix: ".",
				showMonthAfterYear: true,
				dateFormat: 'yy.mm.dd',
				dayNamesMin: [ '일', '월', '화', '수', '목', '금', '토'],
				dayNames: ['일','월','화','수','목','금','토'],
				monthNames : ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
				monthNamesShort : ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
				showOn: 'button',
				onSelect : function(selectDate, inst) {
					var toDate = $(this).attr("data-to_date");
					var fromDate = $(this).attr("data-from_date");

					if (toDate) {
						var value = $("#" + toDate).val();
						if (value != "") {
							if (selectDate > value) {
								$.message.alert('message_g290')
								$(this).val("");
								return false;
							}
						}
					}

					if (fromDate) {
						var value = $("#" + fromDate).val();
						if (value != "") {
							if (selectDate < value) {
								$.message.alert('message_g291');
								$(this).val("");
								return false;
							}
						}
					}
				}
			}).datepicker('setDate',new Date());
		},

        //TOP
        top: function(e){
            e.preventDefault();
            $("html, body").animate({"scrollTop":0});
        },

		//toggleClass
		toggleClass : function(obj){
            $(obj).toggleClass("active");
        },

        //addClass
		addClass : function(obj){
            $(obj).addClass("active");
        },

        //removeClass
		removeClass : function(obj){
            $(obj).removeClass("active");
        },

		//timeSaleTimer
		timeSaleTimer : function(obj, today, dday){
			var obj = $(obj);
			var today = new Date(today).getTime();
			var dday = new Date(dday).getTime();
			var distance;
			var cdtimer;
			var nt = 0;
			obj.append('<span class="time-h"></span>:<span class="time-m"></span>:<span class="time-s"></span>');
			function timer(){
				distance = dday - (today+nt*1000);
				var d = Math.floor(distance / (1000 * 60 * 60 * 24));
				var h = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
				var m = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
				var s = Math.floor((distance % (1000 * 60)) / 1000);
				if ( d >= 0 && h >= 0 && m >= 0 && s >= 0 ){
					h += d * 24;
					h = ""+(h<10  ? h = "0"+h : h)+"";
					m = ""+(m<10  ? m = "0"+m : m)+"";
					s = ""+(s<10  ? s = "0"+s : s)+"";
					obj.find(".time-h").html(h);
					obj.find(".time-m").html(m);
					obj.find(".time-s").html(s);
				}else{
					obj.parent().html('<span class="txt-end">타임세일이 종료되었습니다.</span>');
					clearInterval(cdtimer);
				}
				nt++;
			}
			cdtimer = setInterval(timer, 1000);
			setTimeout(function(){
				obj.parent("div").animate({"opacity":"1"}, 400);
			}, 1000);
		},

		//trafficTimer
		trafficTimer : function(obj, delayTm, redirectUrl){
			var obj = $(obj);
	        var cdtimer;
	        var curDelayTm = delayTm;
	        var m;
	        var s;
	        obj.append('<span class="time-m"></span>분 <span class="time-s"></span>초');
	        function timer(){
	            if (curDelayTm >= 0){
	            	m = Math.floor(curDelayTm/60);
	                s = curDelayTm%60;
	                            	
	            	obj.find(".time-m").html(m);
	                obj.find(".time-s").html(s);
	            }
	            else{
					clearInterval(cdtimer);
	            	if(redirectUrl != null){
		            	location.href = redirectUrl;
	            	}
	            }
	            
	            curDelayTm--;
	        }
	        cdtimer = setInterval(timer, 1000);
	        setTimeout(function(){
	            obj.parent("div").animate({"opacity":"1"}, 600);
	        }, 1000);
		},

		//swiperFractionNumber
		swiperFractionNumber : function(obj){
			var current = (Number($(obj).find(".swiper-pagination-current").text())<10) ? "0" + $(obj).find(".swiper-pagination-current").text() : Number($(obj).find(".swiper-pagination-current").text()) ;
			var total = (Number($(obj).find(".swiper-pagination-total").text())<10) ? "0" + $(obj).find(".swiper-pagination-total").text() : Number($(obj).find(".swiper-pagination-total").text()) ;
			$(obj).find(".swiper-pagination-current").text(current);
			$(obj).find(".swiper-pagination-total").text(total);
		},

		//input message
        inputMsg : function(obj, msg, msgtype){
            var $obj = $(obj);
			$obj.parent().after('<p class="inputmsg">'+msg+'</p>');
			if (msgtype) {
				$obj.parent().addClass("positive");
			} else {
				$obj.parent().addClass("error");
			}
        },

		//inputMsgClear
		inputMsgClear : function(obj){
			if (obj){
				var $obj = $(obj);
				$obj.parent().removeClass("error");
				$obj.parent().removeClass("positive");
				$obj.parent().next(".inputmsg").remove();
			} else {
				$("label").removeClass("error");
				$("label").removeClass("positive");
				$(".inputmsg").remove();
			}
		},

		//grade-star
		gradeStar : function(){
			$(".grade-star.active > span").click(function(){
				$(this).parent().find("span").removeClass("active");
				$(this).parent().find("input").val($(this).index()+1);
				for ( var i = 0 ; i <= $(this).index() ; i++ ){
					$(this).parent().find("span").eq(i).addClass("active");
				}
                if ( $(this).parent().find("div").hasClass("txt") ){
                    $(this).parent().find(".txt .tot").text($(this).index()+1);
                }
			});
		},

		//토글영역
		toggleInit : function(){
			$(".toggle").each(function(){
				if($(this).hasClass("active")){
					$(this).find('.cont').show(0);
				}
			});
		},

		//토글영역
		toggleClick : function(){
			$(document).on('click', '.toggle .toggle-ti', function(){
                if ( !$(this).hasClass("nonactive") ){
                    if($(this).parent().hasClass("active")) {
                        $(this).parent().removeClass("active");
                        $(this).parent().find(".cont").first().slideUp();
                    } else {
                        $(this).parent().addClass("active");
                        $(this).parent().find(".cont").first().slideDown();
                    }
                }
			});
		},

		//토글영역 Ajax
		toggleAjaxClick : function(obj){
			var $this = $(obj);
			if($this.parent().hasClass("active")) {
				$this.parent().removeClass("active");
				$this.parent().find(".cont").first().slideUp();
			} else {
				$this.parent().addClass("active");
				$this.parent().find(".cont").first().slideDown();
			}
		},

		//옵션 수량 올리기
        optionEaUp : function(obj){
            var $obj = $(obj),
                eaVal = parseInt($obj.siblings("input").val()),
				max = parseInt($obj.siblings("input").attr("max")) || 0;

			if ( max ){
				if ( eaVal < max ){
					eaVal++;
				}
			}else{
	            eaVal++;
			}
            $obj.siblings("input").val(eaVal);
        },

        //옵션 수량 내리기
        optionEaDown : function(obj){
            var $obj = $(obj),
                eaVal = parseInt($obj.siblings("input").val()),
				min = parseInt($obj.siblings("input").attr("min")) || 0;

			if ( $obj.siblings("input[min]").length ){
				if ( eaVal > min ){
					eaVal--;
				}
			}else{
				if ( eaVal > 1 ){
					eaVal--;
				}
			}
			$obj.siblings("input").val(eaVal);
		},

		//말줄임
		ellipsis: function(obj) {
			$(obj).each(function() {
				var obj = $(this),
					tempTxt = obj.text(),
					tempTxtArr = tempTxt.split(""),
					tempTxtLen = tempTxt.length,
					ellipsisTxt = "",
					ellipsisTxtLen;

				obj.empty();
				obj.append("<div></div>");

				for (var i = 0; i <= tempTxtLen - 1; i++) {
					if (obj.height() >= obj.children().height()) {
						ellipsisTxt += tempTxtArr[i];
						obj.children().text(ellipsisTxt);
					}
				}

				if (obj.height() < obj.children().height()) {
					ellipsisTxtLen = ellipsisTxt.length;
					ellipsisTxt = ellipsisTxt.substr(0, ellipsisTxtLen - 3);
					ellipsisTxt += "...";
				}

				obj.empty();
				obj.text(ellipsisTxt);
			});
		},

		//filterNav
		filterNav : function(){
			//filternav
			$(".filternav input").click(function(){
				var $this = $(this),
					$ti = $(this).attr("data-active-ti"),
					$inname = $(this).attr("name");
				if ( $ti != "alldiv" ){
					if ( $this.attr("type") == "number" ){
						filternavDel("price", "price");
						$(".filternav").find(".price").addClass("active");
					}else{
                        if ( $ti == "price" ){
                            var val = $this.attr("data-value");
                            if ( val ){
                                val = val.split(", ");
                                if ( val[0] != "null" ){
                                    $("input[name='pricemin']").val(val[0]);
                                }else{
                                    $("input[name='pricemin']").val('');
                                }
                                if ( val[1] != "null" ){
                                    $("input[name='pricemax']").val(val[1]);
                                }else{
                                    $("input[name='pricemax']").val('');
                                }
                            }
							filternavDel("pricearea", "price");
                        }
						filternavAdd($(this));
					}
				}
			});
			$(".filternav .form-price input[type='number']").on("blur", function(){
				var minval = $(this).parent().find("input[type='number']").eq(0).val(),
					maxval = $(this).parent().find("input[type='number']").eq(1).val();
				if ( minval && maxval ){
					if ( !$(".select-filter button").hasClass("pricearea") ){
						$(".select-filter .con").prepend('<button type="button" class="pricearea btn fill small lightgray" data-active-input="pricearea" data-active-ti="price">'+minval+'~'+maxval+'</button>');
						fn.addClass(".form-filter .btn-filter");
						$(".select-filter").addClass("active");
					}else{
						$(".select-filter").find(".pricearea").html(minval+'~'+maxval);
					}
				}else if ( minval && !maxval ){
					if ( !$(".select-filter button").hasClass("pricearea") ){
						$(".select-filter .con").prepend('<button type="button" class="pricearea btn fill small lightgray" data-active-input="pricearea" data-active-ti="price">'+minval+'~</button>');
						fn.addClass(".form-filter .btn-filter");
						$(".select-filter").addClass("active");
					}else{
						$(".select-filter").find(".pricearea").html(minval+'~');
					}
				}else if ( !minval && maxval ){
					if ( !$(".select-filter button").hasClass("pricearea") ){
						$(".select-filter .con").prepend('<button type="button" class="pricearea btn fill small lightgray" data-active-input="pricearea" data-active-ti="price">~'+maxval+'</button>');
						fn.addClass(".form-filter .btn-filter");
						$(".select-filter").addClass("active");
					}else{
						$(".select-filter").find(".pricearea").html('~'+maxval);
					}
				}else{
					$(".filternav").find(".price").removeClass("active");
				}
			});
			$('.select-filter').on('click', 'button', function(){
				if ( $(this).attr("data-active-input") == "pricearea" || $(this).attr("data-active-input") == "price" ){
					$(".filternav .form-price input[type='number']").val('');
				}
				filternavDel($(this).attr("data-active-input"), $(this).attr("data-active-ti"));
			});
			
            $(".filternav .con button").click(function(){
				var $this = $(this),
                    $parent = $this.parent("li"),
					$ti = $(this).attr("data-active-ti");

				if ( !$parent.hasClass("active") ){
					$(".filternav .con ul li").removeClass("active").css({"height":+($parent.find("button").outerHeight())+"px"})
					$parent.addClass("active");
                    $parent.css({"height":+($parent.find("button").outerHeight()+$parent.find(".con").outerHeight())+"px"})
				}
            });

			$(".select-filter .con").on("click", "button", function(){
				if ( $(this).attr("data-active-input") == "pricearea" ){
					$(".filternav .form-price input[type='number']").val("");
				}
				filternavDel($(this).attr("data-active-input"), $(this).attr("data-active-ti"));
			});

			function filternavAdd(obj){
				var $obj = $(obj),
					$id = $(obj).attr("name"),
					$val = $(obj).next("span").html(),
					$ti = $(obj).attr("data-active-ti");

				if ( !$(obj).is(":checked") ){
					filternavDel($id, $ti);
					return;
				}
				if ( !$(".select-filter button").hasClass($id) ){
					$(".select-filter .con").prepend('<button type="button" class="btn fill small lightgray '+$id+'" data-active-input="'+$id+'" data-active-ti="'+$ti+'">'+$val+'</button>');
				}else{
					$(".select-filter").find("."+$id).html($val);
				}
				$(".select-filter").addClass("active");
			}

			function filternavDel(obj, ti){
				var $id = obj,
					$ti = ti;
				$(".filternav input[name='"+$id+"']").attr("checked", false);
				$(".select-filter").find("."+$id).remove();
				if ($(".select-filter .con button").length == 0){
					$(".select-filter").removeClass("active");
				}
			}
		},

		//filterNavReset
		filterNavReset : function(){
			$(".filternav .con input").attr("checked", false);
			$(".filternav input[type='number']").val('');
			$(".select-filter .con").find("button").remove();
			$(".select-filter").removeClass("active");
		},

		//reviewInit
		reviewInit : function(){
			$(".review-list li").each(function(){
				var $this = $(this);
				fn.reviewPicInit($this);
			});
		},

		//reviewtoggle
		reviewToggle : function(){
			var reviewpicSwiper = new Swiper('.reviewpicswiper', {
				loop: true,
				observer: true,
				pagination: {
					el: '.swiper-pagination-reviewpic',
					type: 'fraction',
				},
                navigation: {
                    nextEl: '.swiper-button-next-reviewpic',
                    prevEl: '.swiper-button-prev-reviewpic',
                },
			});
			$(document).on('click', '.review-list .recont .pic button', function(e){
				var $menu = $(this).parent().attr("data-menu"),
					$star = $(this).parent().attr("data-star"),
					$option = $(this).parent().attr("data-option"),
					$picidx = $(this).attr("data-picidx"),
					$pic = $(this).parent().find("button");

				reviewpicSwiper.removeAllSlides();
				$(".reviewpicswiper .swiper-slide").remove();
				$(".reviewpicswiper").css({"opacity":"0"});
				for ( var i = 0 ; i < $pic.length ; i++ ){
					reviewpicSwiper.appendSlide('<div class="swiper-slide"><img src="'+$pic.eq(i).find("img").attr("src")+'" alt=""></div>');
				}
				reviewpicSwiper.update();
				reviewpicSwiper.pagination.update();
				reviewpicSwiper.slideTo($picidx);
				$(".popreviewpic header .grade-star > span > span").css({"width":+(Number($star) *20)+"%"}).html($star);
				$(".popreviewpic header strong").html($menu);
				$(".popreviewpic header .txt-option").html($option);
				for ( var i = 0 ; i < $(".reviewpicswiper .swiper-slide").length ; i++ ){
					if ( $(".reviewpicswiper .swiper-slide").eq(i).find("img").height() > ($(".popup.popreviewpic .contents").outerHeight()-$(".popup.popreviewpic .contents .bottom").outerHeight()) ){
						$(".reviewpicswiper .swiper-slide").eq(i).find("img").css({"width":"auto", "height":+(($(".popup.popreviewpic .contents").outerHeight()-$(".popup.popreviewpic .contents .bottom").outerHeight()))+"px"});
					}
				}

				fn.popupOpen('#p_popReviewPic');
				setTimeout(function(){
					$(".reviewpicswiper").animate({"opacity":"1"});
				}, 200);
			});
		},

		//reviewPicInit
		reviewPicInit : function(obj){
			var $obj = $(obj);
			if ( $obj.find("div").hasClass("pic") ){
				$obj.find(".pic img").each(function(){
					var picSize = $(this).closest("button").height(),
						picWid = $(this).width(),
						picHei = $(this).height();
					if ( picWid > picHei ){
						$(this).css({"width":"auto", "height":picSize+"px"});
                    }
				});
			}
		},

		//prdpicSwiperInit
		prdpicSwiperInit : function(picBtn){
			//사용자 사진첨부 상세 팝업
			var qnapicSwiper = new Swiper('.prdpicswiper', {
				loop: true,
				observer: true,
				navigation: {
					prevEl: '.swiper-button-prev-prdpic',
					nextEl: '.swiper-button-next-prdpic',
				},
				pagination: {
					el: '.swiper-pagination-prdpic',
					type: 'fraction',
				},
			});
			$(document).on('click', picBtn, function(e){
				var $picidx = $(this).attr("data-picidx"),
					$pic = $(this).parent().find("button");

				qnapicSwiper.removeAllSlides();
				$(".prdpicswiper .swiper-slide").remove();
				$(".prdpicswiper").css({"opacity":"0"});
				for ( var i = 0 ; i < $pic.length ; i++ ){
					qnapicSwiper.appendSlide('<div class="swiper-slide"><img src="'+$pic.eq(i).find("img").attr("src")+'" alt=""></div>');
				}
				qnapicSwiper.update();
				qnapicSwiper.pagination.update();
				qnapicSwiper.slideTo($picidx);

				fn.popupOpen('#p_popPrdPic');
				setTimeout(function(){
					$(".prdpicswiper").animate({"opacity":"1"});
				}, 200);
			});
		},

		//popupWeighting
		popupWeighting : function(){
			fn.popupOpen('#p_popWeighting');
		},

		//hasScrollBar
		hasScrollBar : function(obj){
			var $obj = $(obj);
			return ($obj.prop("scrollHeight") == 0 && $obj.prop("clientHeight") == 0) || ($obj.prop("scrollHeight") > $obj.prop("clientHeight"));
		},

		//header init
		headerInit : function(){
			//개인화 추천 팝업
			$(document).on("click", ".poppersonal .btn-close, #header .btn-personal", function(){
				fn.popupPersonal();
			});

			//검색 입력 input 값이 있을 경우
			if ($("#header .search input").val() != "") {
				$("#header .searcharea").addClass("active");
			}

			$(document).mouseup(function(e){
				if ($("#header .searcharea").hasClass("active")) {
					if (!$("#header .search input, #header .search button").is(e.target) && !$(".popsearch").find("*").is(e.target)){
						if ($("#header .search input").val() == "") {
							$("#header .searcharea").removeClass("active");
						}
						$('.popsearch .defaultsearch').fadeOut();
						$('.popsearch .autosearch').fadeOut();
					}
				}
			});

			$("#header .searcharea .search").click(function(){
				if ($("#header .btn-personal").hasClass("active")) {
					fn.popupPersonal();
				}
			});

			//카테고리 전체보기 버튼 hover
			$(document).on({
				mouseenter : function(){
					$(".popcategory").addClass("active");
				},
				mouseleave : function(){
					$(".popcategory").removeClass("active");
				},
			}, "#header .btn-category");

			//카테고리 팝업 hover
			$(document).on({
				mouseenter : function(){
					$(".popcategory").addClass("active");
				},
				mouseleave : function(){
					$(".popcategory").removeClass("active");
				},
			}, ".popcategory");
			
			//브랜드직송, 특화브랜드관(큐레이션 H) hover
			$('.depth1.brand-wrap .depth2 button, .depth3').hover(function(){
		        $(this).parents('ul.lnb').addClass('on');
		    }, function() {
		        $(this).parents('ul.lnb').removeClass('on');
		    });

			$('.exhibition-wrap, .brand-ct').hover(function(){
		        $(this).parents('ul.lnb').addClass('on2');
		    }, function() {
		        $(this).parents('ul.lnb').removeClass('on2');
		    });

			//큐레이션 H 2개일 경우
			if ($("#header .etc .exhibition-list > li").length == 2){
				$("#header .etc .exhibition-list").addClass("col2");
			}
		},

		//인증번호 유효시간
		certificationTimer: function(time, obj){
			var minute = Number(time.split(':')[0]),
			second = Number(time.split(':')[1]),
			second = minute*60+(second-1);
			clearInterval(cTimeInterval);
			time = function(){
				if (second%60 < 10){
					if (parseInt(second/60) < 10){
						return ['0', parseInt(second/60), ':0', second%60].join('');
					}else{
						return [parseInt(second/60), ':0', second%60].join('');
					}
				}else{
					if (parseInt(second/60) < 10){
						return ['0', parseInt(second/60), ':', second%60].join('');
					}else{
						return [parseInt(second/60), ':', second%60].join('');
					}
				}
			}
			$(obj).text(time());
			cTimeInterval = setInterval(function(){
				if (second > 0) {
					second--;
					$(obj).text(time());
				} else {
					clearInterval(cTimeInterval);
				}
			}, 1000);
		},
        
        //loadding open
        loaddingOpen : function(obj){
            var $obj = $(obj);
            var loadingAnimation = function() {
				if ( loadingT > -(loadingImgH-loadingH) ){
					loadingT = loadingT-loadingH;
                    if ( loadingT == -(loadingImgH+loadingH)/2 ){
                        loadingTime = 50;
                    }
                    loadingTime -= loadingTime / 20;
                    $obj.find(".loadarea img").css({"top":loadingT});
				} else {
					loadingT = 0;
                    loadingTime = 50;
                    $obj.find(".loadarea img").css({"top":loadingT});
				}
                if ( $obj.hasClass("active") ){
                    setTimeout(function(){
                        loadingAnimation();
                    }, loadingTime);
                }
			}
            
            if ( !$obj.hasClass("active") ){
                $obj.fadeIn(100).addClass("active");
                var loadingH = 60,
                    loadingT = 0,
                    loadingImgH = 1860,
                    loadingTime = 50;
                setTimeout(function(){
                    loadingAnimation();
                }, loadingTime);
            }
        },
        
        //loadding close
        loaddingClose : function(obj){
            var $obj = $(obj);
            $obj.fadeOut(0).removeClass("active");
        },
        
		//개인화 상품 추천 팝업
		popupPersonal : function(){
			fn.toggleClass("#header .btn-personal");
			$("#p_popPersonal").slideToggle(650);

			if ($(".poppersonal").length > 0 && !$(".poppersonalswiper").hasClass("active")) {
				//개인화 상품 추천 팝업 poppersonalswiper init
				$(".poppersonalswiper").addClass("active");
				$(".poppersonalswiper").each(function(){
					var $this = $(this);
					if($this.find(".swiper-slide").length > 3) {
						var poppersonalswiper = new Swiper($this, {
							slidesPerView: 3,
							spaceBetween: 12,
							navigation: {
								nextEl: $this.parent().find('.swiper-button-next-poppersonal'),
								prevEl: $this.parent().find('.swiper-button-prev-poppersonal')
							},
							on: {
								init: function () {
									$this.parent().find('.swiper-button-next-poppersonal').addClass("active");
									$this.parent().find('.swiper-button-prev-poppersonal').addClass("active");
								}
							}
						});
					}
				});
			}
		},
		
		//매거진 템플릿 - mbannerboxswiper
		mbannerboxswiper : function(){
			var $bannerObj = $(".mbannerboxswiper");
			if ( $bannerObj.length > 0 ){
				for (var i=0,num=1; i<$(".mbanner.box").length; i++,num++){
					var $this = $(".mbanner.box").eq(i).find(".mbannerboxswiper");
					var $pagination = $(".mbanner.box").eq(i).find(".swiper-pagination-banner");

					eval("var mbannerboxSwiper"+num+"= new Swiper($this, {autoHeight: true, loop: true, pagination: {el: $pagination, clickable: true}})");	//mbannerboxSwiper1, mbannerboxSwiper2, mbannerboxSwiper3 ... 1부터 순차적으로 동적변수 생성됨
				}
			}
		},
		
		//매거진 템플릿 - mbannerimgswiper
		mbannerimgswiper : function(){
			var $bannerObj = $(".mbannerimgswiper");
			if ( $bannerObj.length > 0 ){
				for (var i=0,num=1; i<$(".mbanner.img").length; i++,num++){
					var $this = $(".mbanner.img").eq(i).find(".mbannerimgswiper");
					var $pagination = $(".mbanner.img").eq(i).find(".swiper-pagination-banner");

					eval("var mbannerimgSwiper"+num+"= new Swiper($this, {autoHeight: true, loop: true, pagination: {el: $pagination, clickable: true}})");	//mbannerimgSwiper1, mbannerimgSwiper2, mbannerimgSwiper3 ... 1부터 순차적으로 동적변수 생성됨
				}
			}
		},
		
		//매거진 템플릿 - mbannerprocessswiper
		mbannerprocessswiper : function(){
			$(".mbanner.process").each(function(){
				var $this = $(this),
					$mbannerprocessswiper = $this.find(".mbannerprocessswiper");
					
				var tot = $this.find(".swiper-slide").length < 10 ? "0"+$this.find(".swiper-slide").length : $this.find(".swiper-slide").length;
				var mbannerprocessswiper = new Swiper($mbannerprocessswiper, {
					autoHeight: true,
					watchSlidesVisibility: true,
					watchSlidesProgress: true,
					init: false
				});
				
				mbannerprocessswiper.on("init slideChange", function(){
					var current = mbannerprocessswiper.realIndex + 1;
					current = current < 10 ? "0"+current : current;
					$this.find(".swiper-pagination-tot strong").text(current);
					$this.find(".swiper-pagination-tot span").text(tot);
				});
				mbannerprocessswiper.init();
			});
		},
		
		//매거진 템플릿 - mtab
		//tab 아래 목록 링크 클릭 시에도 적용되어 ".box.tab a" -> ".box.tab .menu a" 수정(21.08.03 이윤석)
		mtab : function(){
			$(document).on("click", ".mbox.tab .menu a", function(){
				var $tabMenu = $(this).closest('li');
				var $tabContents = $('.mbox.tab .cont > li');
				var index = $(this).closest('li').index();
				$tabMenu.siblings().removeClass('active');
				$tabMenu.addClass('active');
				$tabContents.removeClass('active');
				$tabContents.eq(index).addClass('active');
			});
		},

		//비디오 재생, 일시정지 제어
		isVisibleVideo : function(){
			var fraction = 0.5;
			$(".video-js").each(function(index){
				var x = this.offsetLeft, y = $(this).offset().top, w = this.offsetWidth, h = this.offsetHeight, r = x + w, //right
					b = y + h, //bottom
					visibleX, visibleY, visible;

				visibleX = Math.max(0, Math.min(w, window.pageXOffset + window.innerWidth - x, r - window.pageXOffset));
				visibleY = Math.max(0, Math.min(h, window.pageYOffset + window.innerHeight - y, b - window.pageYOffset));

				visible = visibleX * visibleY / (w * h);
				if (visible > fraction) {
					videojs($(this).attr("id")).play();
				} else {
					videojs($(this).attr("id")).pause();
				}
			});
		},
	}
})();

//setCookie
function pubSetCookie (name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

//getCookie
function pubGetCookie (name) {
	var from_idx = document.cookie.indexOf(name+'=');
	if (from_idx != -1) {
		from_idx += name.length + 1
		to_idx = document.cookie.indexOf(';', from_idx)

		if (to_idx == -1) {
				to_idx = document.cookie.length
		}
		return unescape(document.cookie.substring(from_idx, to_idx))
	}
}

//setCookie popup
function pubCookiePopupCloseId (obj, cookieidx){
	pubSetCookie( cookieidx, "no" , 1);
	fn.popupClosenMain(obj);
}

$(window).on("load", function(){
	//init
	fn.common();
});

$(document).ready(function() {
	function talkChek() {
		var talk = $('.marketing-info label .talk')
		var checkLength = talk.length

		if (checkLength > 0) {
			$('.marketing-info .box').addClass('talkwrap');
			$('.marketing-info .col-input').addClass('talkwrap');
		} else {
			$('.marketing-info .box').removeClass('talkwrap');
			$('.marketing-info .col-input').removeClass('talkwrap');
		}
	}
	talkChek();

	$('.depth1.brand-wrap .depth2 button, .depth3').hover(function(){
        $(this).parents('ul.lnb').addClass('on');
    }, function() {
        $(this).parents('ul.lnb').removeClass('on');
    });

	$('.exhibition-wrap, .brand-ct').hover(function(){
        $(this).parents('ul.lnb').addClass('on2');
    }, function() {
        $(this).parents('ul.lnb').removeClass('on2');
    });

});