// wait until document is fully scriptable
$(function() {

	// select #flowplanes and make it scrollable. use circular and navigator
	// plugins
	$("#flowpanes").scrollable( {
		circular : true,
		mousewheel : true
	}).navigator( {
		// select #flowtabs to be used as navigator
		navi : "#flowtabs",

		// select A tags inside the navigator to work as items (not direct
		// children)
		naviItem : 'a',

		// assign "current" class name for the active A tag inside navigator
		activeClass : 'current',

		// make browser's back button work
		history : true
	});
   
    function addCalendarios() {
        $('#dataIniProjeto').focus(function(){
            $(this).calendario({ target: '#dataIniProjeto' });
        });
        
        $('#dataTerProjeto').focus(function(){
            $(this).calendario({ target: '#dataTerProjeto' });
        });
        

        $('#dataConta').focus(function(){
            $(this).calendario({ target: '#dataConta' });
        });
        
        $('#dataContaSaida').focus(function(){
            $(this).calendario({ target: '#dataContaSaida' });
        });
    }

    function addCalendariosPopupEditar() {
        $('#dataTerProjetoEd').focus(function(){
            $(this).calendario({ target: '#dataTerProjetoEd' });
        });

        $('#dataIniProjetoEd').focus(function(){
            $(this).calendario({ target: '#dataIniProjetoEd' });
        });
    }
    
    function mostrarPopup(popup) {
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

    addCalendarios();

	$('#botao').click(function(e) {
        e.preventDefault();
        mostrarPopup('#popUp');
	});

	$(".fechar").click(function(e) {
        e.preventDefault();
		$('div.calendario').hide(200);
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
	
	$("#corpoTransacao .adicionar").click(function(e) { // Conta de entrada
		var origemTransacao = $("#origemTransacao").val();
		var descriacoTransacao = $("#descricaoTransacao").val();
		var tipoTransacao = $("#tipoTransacao").val();
		var valorTransacao = $("#valorTransacao").val();
		var dataTransacao = $("#dataTransacao").val();
		var actionVar = 'trans';
		var destinoVar = 'entrada';
		
		$.post("projeto", {
			origem : origemTransacao,
			descricao : descriacoTransacao,
			tipo : tipoTransacao,
			valor : valorTransacao,
			data : dataTransacao,
			action : actionVar,
			destino : destinoVar
		}, function(data) {
			$("#corpoLista").html(data);
		});
	});

	$("#corpoTransacaoSaida .adicionar").click(function(e) { // Conta de saida
		var origemTransacao = $("#origemTransacaoSaida").val();
		var descriacoTransacao = $("#descricaoTransacaoSaida").val();
		var tipoTransacao = $("#tipoTransacaoSaida").val();
		var valorTransacao = $("#valorTransacaoSaida").val();
		var dataTransacao = $("#dataTransacaoSaida").val();
		var actionVar = 'trans';
		var destinoVar = 'saida';
		
		$.post("projeto", {
			origem : origemTransacao,
			descricao : descriacoTransacao,
			tipo : tipoTransacao,
			valor : valorTransacao,
			data : dataTransacao,
			action : actionVar,
			destino : destinoVar
		}, function(data) {
			$("#corpoListaSaida").html(data);
		});
	});

	$(".inserirProjeto").click(function(e) { // Inserir Projeto
        e.preventDefault();
        var nomeProjeto = $('#nomeProjeto').val();
        var valorProjeto = $('#valorProjeto ').val();
        var dataIniProjeto = $('#dataIniProjeto').val();
        var dataTerProjeto = $('#dataTerProjeto').val();
        var responsavelProjeto = $('#responsavelProjeto').val();
        var descricaoProjeto = $('#descricaoProjeto').val();


        var dataEntrada = $('#dataConta').val();
        var valorEntrada = $('#valorConta').val();
        var estadoEntrada = $('#estadoConta').val();
        var tipoEntrada = $('select[name=tipoContaEntrada]').val();
        var parcelasContaEntrada = $('#parcelasContaEntrada').val()
        var descricaoEntrada = $('#descricaoConta').val();

        var dataSaida = $('#dataContaSaida').val();
        var valorSaida = $('#valorContaSaida').val();
        var estadoSaida = $('#estadoContaSaida').val();
        var tipoSaida = $('select[name=tipoContaSaida]').val();
        var parcelasContaSaida = $('#parcelasContaSaida').val();
        var descricaoSaida = $('#descricaoContaSaida').val();

        $.post("cadatro_projeto_ajax", {
            nome_projeto: nomeProjeto,
            valor_projeto: valorProjeto,
            data_inicio_projeto: dataIniProjeto,
            data_termino_projeto: dataTerProjeto,
            responsavel_projeto: responsavelProjeto,
            descricao_projeto: descricaoProjeto,
            
            data_entrada:  dataEntrada,
            valor_entrada: valorEntrada,
            estado_entrada: estadoEntrada,
            parcelas_conta_entrada: parcelasContaEntrada,
            descricao_entrada: descricaoEntrada,
            tipo_entrada: tipoEntrada,

            data_saida: dataSaida,
            valor_saida: valorSaida,
            estado_saida: estadoSaida,
            parcelas_conta_saida: parcelasContaSaida,
            descricao_saida: descricaoSaida,
            tipo_saida: tipoSaida   
        }, function(data) {
            document.location.reload(); // trocar pela substituição dinâmica
        });
    });

    $('a.editarProjeto').click(function(e){
        e.preventDefault();
      
        var id = $(this).attr('rel');        
        
        $.post('detalhes/', { id: id }, function(data) {
            $('#conteudoPopup').html(data);
            
            mostrarPopup('#popUpEditar');
            addCalendariosPopupEditar();
        });
    });

    $('a.alterarProjeto').click(function(e){
        e.preventDefault();
        var id = $('#id_projeto').val();
        var nome = $('#nomeProjetoEd').val();
        var valor = $('#valorProjetoEd').val();
        var data_inicio = $('#dataIniProjetoEd').val();
        var data_fim = $('#dataTerProjetoEd').val();
        var responsavel = $('#responsavelProjetoEd').val();
        var descricao = $('#descricaoProjetoEd').val();
        
        $.post('editar/', {
            id: id,
            nome: nome,
            valor: valor,
            data_inicio: data_inicio,
            data_termino: data_fim,
            responsavel: responsavel,
            descricao: descricao
        }, function(data) {
            document.location.reload(); // trocar pela substituição dinâmica
        });
    });

    // trocar para loading dinamico
    $('#ano_combo').change(function(){
        var ano = $(this).val();
        
        if(ano != '') {
            var url = document.location.protocol + '//'
            url += document.location.host;

            if (ano == 'default') 
                document.location.assign(url + '/projetos');
            else
                document.location.assign( url + '/projetos/' + ano);
        }
    });

});
