$(document).ready(function() {
    $('#botao').click(function(e) {
        var largura = $('body').outerWidth(true);
        var altura = $('body').outerHeight(true);
        var alturaJanela = $(window).height();
        // var modelo = $(this).text();
        // var idModelo = $(this).attr('id');
        // var descricao = $.post("nomedomapeamento", { id: idModelo} );

        // $('body').text(id);
        $('#tela').css({
            width: largura,
            height: altura
        });
        $('#tela').fadeIn(200);
        // $('div.calendario').css({top:alturaJanela/2
        // -260/2+115,left:largura/2 - 434/2+225});
        // $('div.calendario').fadeIn(200);
        $('#popUp').css({
            top: alturaJanela / 2 - $('#popUp').height() / 2,
            left: largura / 2 - $('#popUp').width() / 2
        });
        // $('#nomePopUp').text(modelo);
        // $('#PopUp p').text(descricao);
        // $('#valor').val( idModelo);
        $('#popUp').fadeIn(200);
    });

    $(".fechar").click(function(e) {
        $('#popUp').fadeOut(200);
        $('#tela').fadeOut(200);
    });

    /*$(".adicionar").click(function(e) {
        var origemTransacao = $("#origemTransacao").val();
        var descriacoTransacao = $("#descricaoTransacao").val();
        var tipoTransacao = $("#tipoTransacao").val();
        var valorTransacao = $("#valorTransacao").val();
        var dataTransacao = $("#dataTransacao").val();
        var actionVar = 'trans';

        $.post("contas", {
            origem: origemTransacao,
            descricao: descriacoTransacao,
            tipo: tipoTransacao,
            valor: valorTransacao,
            data: dataTransacao,
            action: actionVar
        }, function(data) {
            $("#corpoLista").html(data);
        });
    });*/

    $(".inserirConta").click(function(e) {
        var nomeConta = $("#nomeConta").val();
        var dataConta = $("#dataConta").val();
        var valorConta = $("#valorConta").val();
        var parcelasConta = $("#parcelasConta").val();
        var tipoConta = $("input[name='tipoConta']:checked").val();
        var livro = $("input[name='livro']:checked").val();
        var descricaoConta = $('#descricaoConta').val();

        $.post("insert/", {
            nome: nomeConta,
            data: dataConta,
            valor: valorConta,
            parcelas: parcelasConta,
            tipo: tipoConta,
            livro: livro,
            descricao: descricaoConta,
        }, function(data) {
            $('#popUp').fadeOut(200);
            $('#tela').fadeOut(200);

            $('#popUp').find(':input').each(function() {
                switch (this.type) {
                    case 'password':
                    case 'text':
                    case 'textarea':
                        $(this).val('');
                        break;
                    case 'checkbox':
                    case 'radio':
                        this.checked = false;
                        break;
                }
            });
        }, 'json');
    });

    /*$('#dataConta').focus(function(){
        $(this).calendario({
            target:'#dataConta'
        });
        //$(this).attr('disabled', 'disabled');
        //removeAttr('disabled');
    });*/

    //$('#dataConta').mask("99/99/9999");
    //$('#valorConta').mask("999999999.99");

    $('input:text').setMask();
});
