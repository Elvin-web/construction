AOS.init();
// FilterClick


$(document).ready(()=>{
    $("#to_top").click(()=>{
        $("html,body").animate({
            scrollTop: 0
        },1500)
    });

    $(window).scroll(()=>{
        if($(this).scrollTop() > 100) {
            $('#to_top').css("opacity",'1')
        }
        else{
            $('#to_top').css("opacity",'0')
        }
    })
});
