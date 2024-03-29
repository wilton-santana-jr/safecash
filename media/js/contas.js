$(document).ready(function() {
    $('#botao').click(function(e) {
        e.preventDefault();
        mostrarPopup("#popUp");
    });

    $(".fechar").click(function(e) {
        e.preventDefault();
        $('#popUp').fadeOut(200);
        $('#popUpEditar').fadeOut(200);
        $('#tela').fadeOut(200);
    });

     $("#tela").click(function(e) {
        e.preventDefault();
        $('#popUp').fadeOut(200);
        $('#popUpEditar').fadeOut(200);
        $('#tela').fadeOut(200);
    });
    
    atribuirAcaoLista();
    function atribuirAcaoLista()
    { 
        $('a.edicao').click(function(e){
            e.preventDefault();
          
            var id = $(this).attr('rel');        
            
            $.post("editarConta/", {
                id : id

            }, function(data) {

                $("#popUpCorpo").html(data);
                
                mostrarPopup("#popUpEditar");
                acoesPopUp()
            });

        });
    }
    function acoesPopUp()
    {
         $('a.inserirContaEd').click(function(){ 
                    
        var nomeConta = $("#nomeContaEd").val();
        var dataConta = $("#dataContaEd").val();
        var valorConta = $("#valorContaEd").val();
        var parcelasConta = $("#parcelasContaEd").val();
        var tipoConta = $("input[name='tipoContaEd']:checked").val();
        var livro = $("input[name='livroEd']:checked").val();
        var descricaoConta = $('#descricaoContaEd').val();
        
        var idConta = $('#id_conta').val();
        

        var index = 0;
        var transacoesPagas = new Array();
        $('input[name=transacaoPaga]').each(function(){
            transacoesPagas[index] = $(this).val();
            index = index + 1;
        });

        var transacoesApagadas = new Array();
        $('input[name=transacaoApagada]').each(function(){
            transacoesApagadas[index] = $(this).val();
            index = index + 1;
        });    
        $('input[name=transacaoPaga]').remove();
        $('input[name=transacaoApagada]').remove();

        $.post("alterarConta/", {
                nome: nomeConta,
                data: dataConta,
                valor: valorConta,
                parcelas: parcelasConta,
                tipo: tipoConta,
                livro: livro,
                descricao: descricaoConta,
                id: idConta,
                transacoesPagas : transacoesPagas,
                transacoesApagadas : transacoesApagadas
            }, function(data) {
                atualizarLista(false);
                $('#popUpEditar').fadeOut(200);
                $('#tela').fadeOut(200);


            }, 'json');
        });
        $('.tipo a.acaoPagar').click(function(e){
            e.preventDefault();
            var link = $(this)
            var id = $(this).attr('href')    
            jConfirm('Essa transação realmente foi paga?', 'Confirmação de Pagamento', function(r) {
                if( r)
                {
                     link.remove();
                    var span = "#" + id;
                     $(span).html("OK");
                     $(span).removeClass('pendente');   
                     $(span).addClass('ok');
                      $('body').append('<input name="transacaoPaga" value="'+id+'"/>');
                     
                }
            });
        });

        $('.tipo a.acaoApagar').click(function(e){
            e.preventDefault();
           
            var link = $(this)
            var id = $(this).attr('href')    
            jConfirm('Quer mesmo apagar essa transação?', 'Confirmação de Exclusão', function(r) {
                if( r)
                {
                     link.parent().parent().remove();
                    var span = "#" + id;
                     $('body').append('<input name="transacaoApagada" value="'+id+'"/>');
                     
                }
            });
        });
    }
    
    function atualizarLista(filtrar)
    {
        

         $.post("lista_dinamica/", {
                filtro : filtrar,
                mes: $('#mes').val(), 
                ano:$('#ano').val()
            }, function(data) {
                console.log(data)
                $('#informacoes').html(data);
                atribuirAcaoLista();
            });
        

        
    }
    function mostrarPopup(popup)
    {
        
        var largura = $('body').outerWidth(true);
            var altura = $('body').outerHeight(true);
            var alturaJanela = $(window).height();
            
            $('#tela').css({
                width: largura,
                height: altura
            });
            $('#tela').fadeIn(200);
            $(popup).css({
                top: alturaJanela / 2 - $(popup).height() / 2,
                left: largura / 2 - $(popup).width() / 2
            });
            $(popup).fadeIn(200);
        
    }
   
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
            atualizarLista(false);
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
    
    /*Filtro da lista*/
    $('#mes').change(function(){
        
        if($(this).val() != '' && $('#ano').val() != '')
            atualizarLista(true);
    });

    $('#ano').change(function(){
        if($(this).val() != '' && $('#mes').val() != '')
            atualizarLista(true);
    });
    
    $('#dataConta').focus(function(){
        $(this).calendario({
            target:'#dataConta'
        });
        //$(this).attr('disabled', 'disabled');
        //removeAttr('disabled');
    });
    $.mask.addPlaceholder("~","[+-]");
    
    $('#dataConta').mask("99/99/9999");
    $('#valorConta').maskMoney({symbol:'R$ ', showSymbol:true, thousands:'', decimal:','});

});
