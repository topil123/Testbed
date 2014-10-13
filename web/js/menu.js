(function ($) {
    $(document).ready(function () {
        $('#menu').accordion({
            active: false,
            collapsible: true
        });

    });

    $('#menu a').on('click', function (e) {
        var page = $(this).attr('href');
        $('#main').load(page);
        
        return false;
    });




})(jQuery);
