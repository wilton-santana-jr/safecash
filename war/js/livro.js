$(document).ready(function() {

	$('#calendario').calendario( {
		target : '#calendario',
		closeClick : false,
		referencePosition : '#popUp'
	});

	$('div.calendario').hide();

	$('div.calendario ul li a').click(function() {
		var html = $(this).html();
		$(this).remove();

		$('div.calendario ul li.default').removeClass('default');
		$('div.calendario ul li:empty').addClass('default');
		$('div.calendario ul li:empty').html('<a href="#">' + html + '</a>');
	});

	$('#botao').click(function(e) {

		var largura = $('body').outerWidth(true);
		var altura = $('body').outerHeight(true);
		var alturaJanela = $(window).height();

		$('#tela').css( {
			width : largura,
			height : altura
		});

		$('#tela').fadeIn(200);

		$('div.calendario').css( {
			top : alturaJanela / 2 - 260 / 2 + 115,
			left : largura / 2 - 434 / 2 + 225
		});

		$('div.calendario').fadeIn(200);

		$('#popUp').css( {
			top : alturaJanela / 2 - 260 / 2,
			left : largura / 2 - 434 / 2
		});

		$('#popUp').fadeIn(200);
		$('.informativo').hide();

	});

	$(".fechar").click(function(e) {
		$('div.calendario').hide(200);
		$('#popUp').fadeOut(200);
		$('#tela').fadeOut(200);

	});

	$(".inserir").click(function(e) {
		var origemConta = $("input[name='origem']:checked").val();
		var descricaoConta = $("input[name='descricao']").val();
		var tipoConta = $("input[name='tipo']:checked").val();
		var valorConta = $("#valor").val();
		var dataConta = $('#calendario').val();
		var pagina = $.post("livro", {
			descricao : descricaoConta,
			origem : origemConta,
			tipo : tipoConta,
			data : dataConta,
			valor : valorConta
		}, function(data) {

			$('div.calendario').hide(200);
			$('#popUp').fadeOut(200);
			$('#tela').fadeOut(200);

			$('#informacoes').html(data);

			$('#popUp').find(':input').each(function() {
				switch (this.type) {
				case 'password':
				case 'select-multiple':
				case 'select-one':
				case 'text':
				case 'textarea':
					$(this).val('');
					break;
				case 'checkbox':
				case 'radio':
					this.checked = false;
				}
				
				$('#conta').httr({checked:"checked"});
				$('#entrada').httr({checked:"checked"});
				
			});
		});
	});
	
	$('input:text').setMask();
	
});