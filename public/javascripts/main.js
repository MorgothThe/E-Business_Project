$('document').ready(function(){
    $('.message a').click(function () {
        console.log($(this));
        $('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
        $('h2').animate({opacity: "toggle"});
    });
});

