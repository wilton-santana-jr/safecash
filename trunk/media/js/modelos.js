$(document).ready(function() {
    $("#lista").find("li").each(function(i) {
        $(this).click(function(e){
            var largura = $('body').outerWidth(true);
            var altura =  $('body').outerHeight(true);
            var alturaJanela = $(window).height();
            var modelo = $(this).text();
            var idModelo = $(this).attr('id');

            $.post("get_more_info/", { id: idModelo }, function (data) {
                $('#popUp p').text(data.desc);
            }, 'json');

            // $('body').text(id);
            $('#tela').css({width: largura, height: altura});
            $('#tela').fadeIn(200);
            $('#popUp').css({top: (alturaJanela/2) - (260/2), left: (largura/2) - (434/2)});
            $('#nomePopUp').text(modelo);
            $('#valor').val(idModelo);
            $('#popUp').fadeIn(200);
            $('.informativo').hide();
        });
    });

    $(".fechar").click(function(e) {
        $('#popUp').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    $(".fecharInserir").click(function(e) {
        $('#popUpInserir').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    $(".remover").click(function(e) {
        var idModelo =  $('#valor').val();
        $.post("remove/", { id: idModelo}, function (data) {
            if (data.code == 'error')
                alert('deu merda!');
        }, 'json');
        
        
        //$('#tudo').html(pagina);
        $('#popUp').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    $(".download").click(function(e) {
        var idModelo =  $('#valor').val();
        $.post("download/", { id: idModelo}, function (data) {
            window.open(data.url);
        }, 'json' );
        
        //$('#tudo').html(pagina);
        $('#popUp').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    $('.botao').click(function(e) {
          var largura = $('body').outerWidth(true);
          var altura =  $('body').outerHeight(true);
          var alturaJanela = $(window).height();
          // var modelo = $(this).text();
          //var idModelo = $(this).attr('id');
          // var descricao = $.post("nomedomapeamento", { id: idModelo} );
          // $('body').text(id);
          $('#tela').css({width:largura,height:altura});
          $('#tela').fadeIn(200);
          $('#popUpInserir').css({top:alturaJanela/2 -$('#popUpInserir').height()/2,left:largura/2 - $('#popUpInserir').width()/2});
          //$('#popUp').css({top:alturaJanela/2 -260/2,left:largura/2 - 434/2});
          //$('#nomePopUp').text(modelo);
          // $('#PopUp p').text(descricao);
          // $('#valor').val( idModelo);
          $('#popUpInserir').fadeIn(200);
          $('.informativo').hide();
    });

    $(".armazenar").click(function(e) {
         var nomeModelo =  $("#popUpInserir input[type='text']").val();
         var enderecoModelo = $("#popUpInserir input[type='file']").val();
        //console.log(enderecoModelo);
         var descricaoModelo = $("#popUpInserir textarea").val();
        // $.post("insert/", { nome: nomeModelo,endereco: enderecoModelo,descricao: descricaoModelo} );
        
         if(nomeModelo && enderecoModelo && descricaoModelo){
            $('#form_modelo').submit();
            $('div.calendario').hide(200);
            $('#popUpInserir').fadeOut(200);
            $('#tela').fadeOut(200);
         } else {
            alert('Não foi possível inserir o modelo!');
         }
         // $("#popUpInserir textarea").val(nomeModelo + enderecoModelo+descricaoModelo );
         
         //$('#tudo').html(pagina);
     });
     
});
