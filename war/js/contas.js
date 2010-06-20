$(document).ready(function() {

	$('#botao').click(function(e) {

		var largura = $('body').outerWidth(true);
		var altura = $('body').outerHeight(true);
		var alturaJanela = $(window).height();
		// var modelo = $(this).text();
			// var idModelo = $(this).attr('id');
			// var descricao = $.post("nomedomapeamento", { id: idModelo} );

			// $('body').text(id);
			$('#tela').css( {
				width : largura,
				height : altura
			});
			$('#tela').fadeIn(200);
			// $('div.calendario').css({top:alturaJanela/2
			// -260/2+115,left:largura/2 - 434/2+225});
			// $('div.calendario').fadeIn(200);
			$('#popUp').css( {
				top : alturaJanela / 2 - $('#popUp').height() / 2,
				left : largura / 2 - $('#popUp').width() / 2
			});
			// $('#nomePopUp').text(modelo);
			// $('#PopUp p').text(descricao);
			// $('#valor').val( idModelo);
			$('#popUp').fadeIn(200);

	});

	$(".fechar").click(function(e) {
		$('div.calendario').hide(200);
		$('#popUp').fadeOut(200);
		$('#tela').fadeOut(200);

	});

	$(".adicionar").click(function(e) {

		var origemTransacao = $("#origemTransacao").val();
		var descriacoTransacao = $("#descricaoTransacao").val();
		var tipoTransacao = $("#tipoTransacao").val();
		var valorTransacao = $("#valorTransacao").val();
		var dataTransacao = $("#dataTransacao").val();
		var actionVar = 'trans';
		
		$.post("contas", {
			origem : origemTransacao,
			descricao : descriacoTransacao,
			tipo : tipoTransacao,
			valor : valorTransacao,
			data : dataTransacao,
			action : actionVar
		}, function(data) {
			$("#corpoLista").html(data);
		});

	});
	
	$(".inserirConta").click(function(e) {
		var dataConta = $("#dataConta").val();
		var valorConta = $("#valorConta").val();
		var estadoConta = $("#estadoConta").val();
		var tipoConta = $("input[name='tipoConta']:checked").val();
		var descricaoConta = $('#descricaoConta').val();

		var actionVar = 'conta';
		
		$.post("contas", {
			descricao : descricaoConta,
			tipo : tipoConta,
			valor : valorConta,
			data : dataConta,
			estado : estadoConta,
			action : actionVar
		}, function(data) {
			$("#informacoes").html(data);
		});

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
			}
		});
		
	});

});
