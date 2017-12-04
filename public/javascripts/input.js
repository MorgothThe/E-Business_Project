$('document').ready(function () {
    $('.edit-btn').click(function () {
        $('.profile-input').animate({ opacity: "toggle" }, "slow");
        $('.letters p').animate({ opacity: "toggle" },500);
    });
});