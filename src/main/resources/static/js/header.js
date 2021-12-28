// Header Scroll
$(document).ready(() => {
    $(window).scroll(() => {
        if ($(this).scrollTop() > 70) {
            $('#Header').css("background-color", '#0a192c')
        } else {
            $('#Header').css("background-color", 'transparent')
        }
    })
});
