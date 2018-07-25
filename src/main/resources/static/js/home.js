$(document).ready(function () {
    $('#btnConvert').click(function () {

        var query = $('#esQuery').val();
        var ip = $('#esIp').val();
        var filename = $('#filename').val();

        var params = [];
        $('.params').each(function() {
            params.push($( this ).text());
        });

        // for params array serialize
        jQuery.ajaxSettings.traditional = true;

        $.get("/convert", {query: query, ip: ip, param: params , filename: filename})
            .done(function (data) {
                $('#script').val(data.after);
            }).fail(function(jqXHR) {
            alert(jqXHR.responseJSON.description);
        });
    });

    $('#btnParam').click(
        function(){
            var toAdd = $('input[name=ListItem]').val();
            $('ol').append('<li class=params>' + toAdd + '</li><button class=btnParam>remove</button>');
            $('input[name=ListItem]').val('');
        });

    $("input[name=ListItem]").keyup(function(event){
        if(event.keyCode == 13){
            $("#button").click();
        }
    });

    $(document).on('click','.btnParam', function(){
        $(this).prev().remove();
        $(this).remove();

    });

    $('input').focus(function() {
        $(this).val('');
    });

    $('#btnClear').click(
        function(){
            $('#esQuery').val('');
            $('#script').val('');
            $('ol[name=param]').empty();
        });
});