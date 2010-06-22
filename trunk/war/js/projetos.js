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

	$('#botao').click(function(e) {

		var alturaJanela = $(window).height($('#popUp').height());
		var largura = $('body').outerWidth(true);

		$('#tela').css( {
			width : largura,
			height : $('#popUp').height()
		});
		
		$('#tela').fadeIn(200);

		$('#popUp').css( {
			top : 0,
			left : largura / 2 - $('#popUp').width() / 2
		});

		$('#popUp').fadeIn(200);

	});

	$(".fechar").click(function(e) {
		$('div.calendario').hide(200);
		$('#popUp').fadeOut(200);
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

				var nomeProjeto = $('#nomeProjeto').val();
				var valorProjeto = $('#valorProjeto ').val();
				var dataIniProjeto = $('#dataIniProjeto').val();
				var dataTerProjeto = $('# dataTerProjeto').val();
				var responsavelProjeto = $('#responsavelProjeto').val();

				var dataEntrada = $('#dataConta').val();

				var valorEntrada = $('#valorConta').val();

				var estadoEntrada = $('#estadoConta').val();

				var tipoEntrada = $('input[name=tipoConta]:checked').val();

				var descricaoEntrada = $('#descricaoConta').val();

				var dataSaida = $('#dataContaSaida').val();

				var valorSaida = $('#valorContaSaida').val();

				var estadoSaida = $('#estadoContaSaida').val();

				var tipoSaida = $('input[name=tipoContaSaida]:checked').val();

				var descricaoSaida = $('#descricaoContaSaida').val();

				$.post("CaminhoPara_A_adicionar_Transacao", {
					origem : origemTransacao,
					descricao : descriacoTransacao,
					estado : estadoTransacao,
					valor : valorTransacao,
					data : dataTransacao
				}, function(data) {
					//
				});
			});

});// JavaScript Document
