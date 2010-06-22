$(document).ready(
		function() {

			var idPedido;
			
			$('span.requisitante a').click(function(e) {

				var largura = $('body').outerWidth(true);
				var altura = $('body').outerHeight(true);
				var alturaJanela = $(window).height();

				// $('body').text(id);
					$('#tela').css( {
						width : largura,
						height : altura
					});
					
					
					$('#tela').fadeIn(200);
					idPedido = $(this).attr('id');
					var tipoRequisicao = $("input[name=" + idPedido + "]:hidden").val();
					
					var titulo = $(this).attr("title");
					
					if(tipoRequisicao == 'c')
					{
							$('#popUpContrato').css({top:alturaJanela/2 -$('#popUpContrato').height()/2,left:largura/2 - $('#popUpContrato').width()/2});
							$('#nomePopUpContrato').text(titulo);
							$('#informacoesContrato input:hidden').val(idPedido);
							
							var typeVar = 'list';
							var sourceVar = 'contrato';
							
							$.post("requisicao", {
								type : typeVar,
								source : sourceVar,
								chave : idPedido
							}, function(data) {
								$("#informacoesContrato ul").html(data);
							});
							
							$('#popUpContrato').fadeIn(200);
					}
					else if(tipoRequisicao == 'g')
					{
						$('#popUpGeral').css({top:alturaJanela/2 -$('#popUpGeral').height()/2,left:largura/2 - $('#popUpGeral').width()/2});							
						$('#nomePopUpGeral').text(titulo);
						$('#informacoesGeral input:hidden').val(idPedido);
						
						var typeVar = 'list';
						var sourceVar = 'geral';
						
						$.post("requisicao", {
							type : typeVar,
							source : sourceVar,
							chave : idPedido
						}, function(data) {
							$("#informacoesGeral ul").html(data);
						});
						
						$('#popUpGeral').fadeIn(200);
					}

					$('.informativo').hide();

				});

			$(".fecharGeral").click(function(e) {
				// $('div.calendario').hide(200);
					$('#popUpGeral').fadeOut(200);
					$('#tela').fadeOut(200);

				});
			$(".fecharContrato").click(function(e) {
				// $('div.calendario').hide(200);
					$('#popUpContrato').fadeOut(200);
					$('#tela').fadeOut(200);

				});

			$(".salvarGeral").click(function(e) {

				var typeVar = 'alter';
				var sourceVar = 'geral';
				var estadoRequisicao = $("#informacoesGeral select").val();
				
				$.post("requisicao", {
					type : typeVar,
					source : sourceVar,
					chave : idPedido,
					status : estadoRequisicao
				}, function(data) {
					$("#informacoes").html(data);
				});
				
				$('#popUpGeral').fadeOut(200);
				$('#tela').fadeOut(200);

			});
			
			$(".salvarContrato").click( function(e) {

				var typeVar = 'alter';
				var sourceVar = 'contrato';
				var estadoRequisicao = $("#informacoesContrato select").val();
				
				$.post("requisicao", {
					type : typeVar,
					source : sourceVar,
					chave : idPedido,
					status : estadoRequisicao
				}, function(data) {
					$("#informacoes").html(data);
				});
				
				$('#popUpContrato').fadeOut(200);
				$('#tela').fadeOut(200);
			});

			$(".responderGeral").click(function(e) {

				var idRequisicao = $('#informacoesGeral input:hidden').val();
				var estadoRequisicao = $("#informacoesGeral select").val();
				var pagina = $.post("nomedomapeamento", {
					id : idRequisicao,
					estado : estadoRequisicao
				});
				$('tudo').text(pagina);

				$('#popUpGeral').fadeOut(200);
				$('#tela').fadeOut(200);

			});
			$(".responderContrato").click(
					function(e) {

						var idRequisicao = $(
								'#informacoesContrato input:hidden').val();
						var estadoRequisicao = $("#informacoesGeral select")
								.val();
						var pagina = $.post("nomedomapeamento", {
							id : idRequisicao,
							estado : estadoRequisicao
						});
						$('tudo').text(pagina);
						$('#popUpContrato').fadeOut(200);
						$('#tela').fadeOut(200);

					});

			$(".baixarContrato").click(
					function(e) {

						var idRequisicao = $(
								'#informacoesContrato input:hidden').val();
						var estadoRequisicao = $("#informacoesGeral select")
								.val();
						var pagina = $.post("nomedomapeamento", {
							id : idRequisicao,
							estado : estadoRequisicao
						});

					});

		});

// JavaScript Document
