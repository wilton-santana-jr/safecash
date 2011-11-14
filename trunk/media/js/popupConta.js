$(document).ready(function() {
    alert('carregou')
    $('a.inserirContaEd').click(function( 
                    
        var nomeConta = $("#nomeContaEd").val();
        var dataConta = $("#dataContaEd").val();
        var valorConta = $("#valorContaEd").val();
        var parcelasConta = $("#parcelasContaEd").val();
        var tipoConta = $("input[name='tipoContaEd']:checked").val();
        var livro = $("input[name='livroEd']:checked").val();
        var descricaoConta = $('#descricaoContaEd').val();
        
        var idConta = $('#id_conta').val();
        

     $.post("alterarConta/", {
            nome: nomeConta,
            data: dataConta,
            valor: valorConta,
            parcelas: parcelasConta,
            tipo: tipoConta,
            livro: livro,
            descricao: descricaoConta,
            id: idConta
        }, function(data) {
            $('#popUp').fadeOut(200);
            $('#tela').fadeOut(200);


        }, 'json');
    ));
});

