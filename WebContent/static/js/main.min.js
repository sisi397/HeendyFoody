/**
* --------------------------------
* MAIN JS
* --------------------------------
*/

$(window).on('load', function(){
    var path = $("#wrap").attr('class').split(" ") || "";

    //메인팝업 swiper
    var $noticemainswiper = $(".popnoticemain .noticemainswiper");
    if ( $noticemainswiper.find(".swiper-slide").length > 1 ){
        $noticemainswiper.addClass("active");
        var popmainSwiper = new Swiper($noticemainswiper, {
            slidesPerView: 1,
            loop: true,
            autoHeight: true,
            navigation: {
                nextEl: ".swiper-button-next-noticemain",
                prevEl: ".swiper-button-prev-noticemain",
            },
            pagination: {
                el: ".swiper-pagination-noticemain",
                type: "fraction",
            }
        });
    }

    //bannerswiper
    var $mainbannerObj = $(".mainbannerswiper");
    if ( $mainbannerObj.length > 0 ){
        if ( $mainbannerObj.find(".swiper-slide").length > 1 ){
            $(".mainbanner").addClass("active");
            var tot = $mainbannerObj.find(".swiper-slide").length < 10 ? "0"+$mainbannerObj.find(".swiper-slide").length : $mainbannerObj.find(".swiper-slide").length;
            var mainbannerswiper = new Swiper('.mainbannerswiper', {
                loop: true,
                centeredSlides: true,
                slidesPerView: "auto",
                spaceBetween: 10,
                autoplay: {
                    disableOnInteraction: false,
                },
                init: false,
            });
            mainbannerswiper.on("init slideChange", function(){
                var current = mainbannerswiper.realIndex + 1;
                current = current < 10 ? "0"+current : current;
                $(".mainbanner .swiper-pagination-tot strong").text(current);
                $(".mainbanner .swiper-pagination-tot span").text(tot);
            });
            mainbannerswiper.init();

            $(".mainbanner .btn-play").click(function(){
                $(this).toggleClass("active");
                if ( $(this).hasClass("active") ){
                    mainbannerswiper.autoplay.start();
                }else{
                    mainbannerswiper.autoplay.stop();
                }
            });
        }else{
            $(".mainbanner").animate({"opacity": "1"}, 0);
        }
    }

    //todaybannerswiper
    var $todayswiper = $('.todaybannerwrap');
    if ( $todayswiper.length > 0 ){
        if ( $todayswiper.find(".swiper-slide").length > 1 ){
            var tot_today = $todayswiper.find(".swiper-slide").length < 10 ? "0"+$todayswiper.find(".swiper-slide").length : $todayswiper.find(".swiper-slide").length;

            var todaybannerswiper = new Swiper(".todaybanner .todaybannerswiper", {
                slidesPerView: "1",
                navigation: {
                    nextEl: ".swiper-button-next-today",
                    prevEl: ".swiper-button-prev-today",
                },
                on: {
                    init: function () {
                        $(".todaybanner .today-no .no").html(this.activeIndex+1).attr("class", "no n"+Number(this.activeIndex+1));
                    },
                    slideChangeTransitionEnd: function () {
                        $(".todaybanner .today-no .no").html(this.activeIndex+1).attr("class", "no n"+Number(this.activeIndex+1));
                    }
                },
                pagination: {
                    el: $todayswiper.find(".swiper-pagination"),
                    type: "fraction"
                },
                init: false
            });
            todaybannerswiper.on("init slideChange", function(){
                var current = todaybannerswiper.realIndex + 1;
                current = current < 10 ? "0"+current : current;
                $(".todaybannerwrap .swiper-pagination-tot strong").text(current);
                $(".todaybannerwrap .swiper-pagination-tot span").text(tot_today);
            });
            todaybannerswiper.init();
        }
    }


    //exhibitionswiper banpro
    var exhibitionSwiper = new Swiper(".exhibition-banpro .exhibitionswiper", {
        slidesPerView: 'auto',
        slidesOffsetBefore: 0,
        slidesOffsetAfter: 30,
        spaceBetween: 10,
        navigation: {
            nextEl: ".swiper-button-next-exhibition",
            prevEl: ".swiper-button-prev-exhibition",
        },
    });

    //exhibitionbigswiper
    var exhibitionSwiper = new Swiper(".exhibition-banprobig .exhibitionbigswiper", {
        direction: 'vertical',
        slidesPerView: 'auto',
        navigation: {
            nextEl: ".swiper-button-next-exhibitionbig",
            prevEl: ".swiper-button-prev-exhibitionbig",
        },
        scrollbar: {
            el: '.swiper-srollbar-exhibitionbig',
        },
        mousewheel: true,
    });

    //exhibitionswiper banproloop
    var exhibitionloopSwiper = new Swiper(".exhibition-banproloop .exhibitionswiper", {
        slidesPerView: 'auto',
        navigation: {
            nextEl: ".swiper-button-next-exhibitionloop",
            prevEl: ".swiper-button-prev-exhibitionloop",
        },
    });

    //timesaleswiper
    for ( var i = 0 ; i < $(".timesaleswiper .swiper-slide").length ; i++ ){
        var timestart = $(".timesaleswiper .swiper-slide").eq(i).attr("data-time-start"),
            timeend = $(".timesaleswiper .swiper-slide").eq(i).attr("data-time-end");
        $(".time-area").append('<div><div class="time">남은시간 <span class="timesalecounter" id="timesalecounter'+i+'"></span></div></div>');
        fn.timeSaleTimer('#timesalecounter'+i, timestart, timeend);
    }
    $(".time-area > div").eq(0).addClass("active");
    var timesaleswiper = new Swiper(".timesaleswiper", {
        navigation: {
            nextEl: ".swiper-button-next-timesale",
            prevEl: ".swiper-button-prev-timesale",
        },
        loop: 'true'
    });
    $(".time-area > div").eq(0).addClass("active");
    timesaleswiper.on("slideChange", function(){
        var current = timesaleswiper.realIndex;
        $(".time-area > div").removeClass("active");
        $(".time-area > div").eq(current).addClass("active");
    });

    //category
    $(".innercon.category").each(function(){
        var $obj = $(this),
            $categorytitleswiper = $obj.find(".categorytitleswiper"),
            $categoryswiper = $obj.find(".categoryswiper");

        var categorytitleswiper = new Swiper($categorytitleswiper, {
            spaceBetween: 36,
            slidesPerView: "auto",
            pagination: {
                el: $categorytitleswiper.find(".swiper-pagination-categorytitle"),
                clickable: true,
            },
            watchSlidesVisibility: true,
            watchSlidesProgress: true,
        });

        var categoryswiper = new Swiper($categoryswiper, {
            spaceBetween: 10,
            autoHeight: true,
            pagination: {
                el: $categoryswiper.find(".swiper-pagination-category"),
                clickable: true,
            },
        });
        var categoryswiperIdx = 0;
        $categorytitleswiper.find(".swiper-slide").eq(categoryswiperIdx).addClass("active");
        categoryswiper.on("slideChange", function(){
            categoryswiperIdx = categoryswiper.realIndex;
            $categorytitleswiper.find(".swiper-slide").removeClass("active");
            $categorytitleswiper.find(".swiper-slide").eq(categoryswiperIdx).addClass("active");
            $categorytitleswiper.find(".swiper-pagination-categorytitle .swiper-pagination-bullet").eq(categoryswiperIdx).click();
        });
        $categorytitleswiper.find(".swiper-slide").click(function(){
            $categorytitleswiper.find(".swiper-pagination-categorytitle .swiper-pagination-bullet").eq(1).click();
            $categoryswiper.find(".swiper-pagination-category .swiper-pagination-bullet").eq($(this).index()).click();
        });
        categoryswiper.on("transitionEnd", function(){
             AOS.refresh();
        });
        AOS.refresh();
    });

    //sale
	//filterNav
	fn.filterNav();

    //event
	var $eventbannerObj = $(".eventbannerswiper");
    if ( $eventbannerObj.length > 0 ){
        if ( $eventbannerObj.find(".swiper-slide").length > 1 ){
            $eventbannerObj.closest(".eventbanner").addClass("active");
            var tot = $eventbannerObj.find(".swiper-slide").length < 10 ? "0"+$eventbannerObj.find(".swiper-slide").length : $eventbannerObj.find(".swiper-slide").length;
            var eventbannerswiper = new Swiper(".eventbannerswiper", {
                loop: true,
                centeredSlides: true,
                slidesPerView: "auto",
                spaceBetween: 20,
                navigation: {
                    nextEl: ".swiper-button-next-eventbanner",
                    prevEl: ".swiper-button-prev-eventbanner",
                },
                init: false
            });
            eventbannerswiper.on("init slideChange", function(){
                var current = eventbannerswiper.realIndex + 1;
                current = current < 10 ? "0"+current : current;
                $eventbannerObj.closest(".eventbanner").find(".swiper-pagination-tot strong").text(current);
                $eventbannerObj.closest(".eventbanner").find(".swiper-pagination-tot span").text(tot);
            });
            eventbannerswiper.init();
        }
    }
    
    //bestswiper
    $('.bestswiper').each(function(){
        var $this = $(this);
        var bestswiper = new Swiper($this, {
            slidesPerView: 3,
            spaceBetween: 20,
            navigation: {
                nextEl: $this.parent().find('.swiper-button-next-best'),
                prevEl: $this.parent().find('.swiper-button-prev-best')
            }
        });
    });
    
	//onlyh
    $(".onlyh-list ul").each(function(){
        $(this).addClass("tot"+$(this).find("li").length);
    });

    //video
    fn.isVisibleVideo();
    $(window).scroll(function(){
        fn.isVisibleVideo();
    });
    
    $(document).ready(function() {
        //buyer more view
       function buyerMore() {
           var moreBtn = $('.buyer-more');
           var target = $('.today-buyerlist ul');

           moreBtn.on('click', function() {
               target.addClass('active');
               $(this).hide();
           });
       }
       buyerMore();
   });
});