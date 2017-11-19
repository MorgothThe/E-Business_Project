$('.message a').click(function () {
    $('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
    $('h2').animate({opacity: "toggle"});
})