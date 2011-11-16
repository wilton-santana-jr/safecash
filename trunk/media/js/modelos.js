$(document).ready(function() {
    $("#lista").find("li").each(function(i) {
        $(this).click(function(e){
            var largura = $('body').outerWidth(true);
            var altura =  $('body').outerHeight(true);
            var alturaJanela = $(window).height();
            var modelo = $(this).text();
            var idModelo = $(this).attr('id');
            var path = $('#staticPath').val();

            $.post("get_more_info/", { id: idModelo }, function (data) {
                $("#descricaoModelo").text(data.desc);
                $('#down').attr('href', path + data.filename);
            }, 'json');

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

    $(".download").click(function(e) {
        $('#popUp').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    $(".remover").click(function(e) {
        var idModelo =  $('#valor').val();
        $.post("remove/", { id: idModelo});

        // trocar para remover o item dinamicamente
        document.location.reload(); 
    });
    
    $('.botao').click(function(e) {
          var largura = $('body').outerWidth(true);
          var altura =  $('body').outerHeight(true);
          var alturaJanela = $(window).height();
          
          $('#tela').css({width: largura, height: altura});
          $('#tela').fadeIn(200);
          $('#popUpInserir').css({
              top: alturaJanela/2 - $('#popUpInserir').height()/2,
              left: largura/2 - $('#popUpInserir').width()/2
          });
          
          $('#popUpInserir').fadeIn(200);
          $('.informativo').hide();
    });

    $(".armazenar").click(function(e) {
         var nomeModelo =  $("#popUpInserir input[type='text']").val();
         var enderecoModelo = $("#popUpInserir input[type='file']").val();
         var descricaoModelo = $("#popUpInserir textarea").val();
        
         if(nomeModelo && enderecoModelo && descricaoModelo){
            $('#form_modelo').submit();
            $('div.calendario').hide(200);
            $('#popUpInserir').fadeOut(200);
            $('#tela').fadeOut(200);
         } else {
            alert('Não foi possível inserir o modelo!');
         }
    });
});
